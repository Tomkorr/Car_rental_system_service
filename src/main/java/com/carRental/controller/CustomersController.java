package com.carRental.controller;

import com.carRental.form.CustomerCreateForm;
import com.carRental.model.Addresses;
import com.carRental.model.Customers;
import com.carRental.repository.AddressesRepository;
import com.carRental.repository.CarsRepository;
import com.carRental.repository.CustomersRepository;
import com.carRental.repository.EmployeesRepository;
import com.carRental.repository.RentalOrderDetailsRepository;
import com.carRental.repository.RentalOrdersRepository;
import com.carRental.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Tomek on 11.12.2016.
 */
@Controller
public class CustomersController {

    private final CustomersService customerSrv;

    @Inject
    public CustomersController(CustomersService customerSrv) {
        this.customerSrv = customerSrv;
    }

    @Autowired
    private CustomersRepository customerRepo;

    @Autowired
    private AddressesRepository addressRepo;
    
    @Autowired
    private EmployeesRepository employeeRepo;

    @Autowired
    private CarsRepository carsRepo;

    @Autowired
    private RentalOrdersRepository rentalOrderRepo;
    
    @Autowired
    private RentalOrderDetailsRepository ordersRepo;

    @RequestMapping(value = "/customers_list", method = RequestMethod.GET)
    public ModelAndView getCustomersListView() {
        ModelAndView model = new ModelAndView("customers_list");
        model.addObject("customersList", customerRepo.findAll());
        model.addObject("countPassengerCars", carsRepo.countPassengerCars());
	model.addObject("countDeliveryCars", carsRepo.countDeliveryCars());
	model.addObject("countCustomers", customerRepo.findAll().size());
	model.addObject("countCars", carsRepo.countPassengerCars() + carsRepo.countDeliveryCars());
	model.addObject("countEmployees", employeeRepo.countEmployess());
	model.addObject("countOrders", ordersRepo.countActiveOrders()
		+ ordersRepo.countInactiveOrders() + ordersRepo.countReservedOrders());
	Integer employeeLoggedId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	model.addObject("employee", employeeRepo.findOne(employeeLoggedId));

        return model;
    }

    @RequestMapping(value = "/customer_create", method = RequestMethod.GET)
    public ModelAndView getCreateCustomerView() {
        ModelAndView model = new ModelAndView("customer_create");
        model.addObject("CustomerCreateForm", new CustomerCreateForm());
        model.addObject("countPassengerCars", carsRepo.countPassengerCars());
	model.addObject("countDeliveryCars", carsRepo.countDeliveryCars());
	model.addObject("countCustomers", customerRepo.findAll().size());
	model.addObject("countCars", carsRepo.countPassengerCars() + carsRepo.countDeliveryCars());
	model.addObject("countEmployees", employeeRepo.countEmployess());
	model.addObject("countOrders", ordersRepo.countActiveOrders()
		+ ordersRepo.countInactiveOrders() + ordersRepo.countReservedOrders());
	Integer employeeLoggedId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	model.addObject("employee", employeeRepo.findOne(employeeLoggedId));
        return model;
    }

    @RequestMapping(value = "/customer_create", method = RequestMethod.POST)
    public String createCustomer(@ModelAttribute("CustomerCreateForm") @Valid CustomerCreateForm form) {

        Integer addressId = addressRepo.saveAddress(form.getCityForm(), form.getStreetForm(), form.getZipCodeForm(),
                form.getStreetNumberForm());

        customerSrv.saveCustomer(new Customers(null, addressId, form.getFirstNameForm(), form.getLastNameForm(),
                form.getAgeForm(), form.getGenderForm(), form.getPhoneForm(), form.getEmailForm()));

        return "redirect:/customers_list";
    }

    @RequestMapping(value = "/delete_customer/{customerId}", method = RequestMethod.GET)
    public String deleteCustomer(@PathVariable("customerId") Integer customerId) {

        for (int i = 0; i < rentalOrderRepo.findByCustomerId(customerId).size(); i++) {
            if (rentalOrderRepo.findByCustomerId(customerId).get(i).getOrderStatus().equals("Rozpoczęte")) {
                carsRepo.carSetAvailable(true, rentalOrderRepo.findByCustomerId(customerId).get(i).getCarId());
            }
        }
        rentalOrderRepo.deleteOrdersByCustomerId(customerId);
        customerRepo.delete(customerId);

        return "redirect:/customers_list?delete";
    }

    @RequestMapping(value = "/customer_details/{customerId}", method = RequestMethod.GET)
    public ModelAndView getCustomerDetailsView(@PathVariable("customerId") Integer customerId) {
        ModelAndView model = new ModelAndView("customer_details");
        Customers customer = customerSrv.getCustomerById(customerId);
        Integer addressId = customer.getAddressId();
        Addresses address = addressRepo.findOne(addressId);
        model.addObject("customerDetails", customer);
        model.addObject("customerAddressDetails", address);
        model.addObject("orderCars", carsRepo.findAllCustomerCars(customerId));
        model.addObject("countPassengerCars", carsRepo.countPassengerCars());
	model.addObject("countDeliveryCars", carsRepo.countDeliveryCars());
	model.addObject("countCustomers", customerRepo.findAll().size());
	model.addObject("countCars", carsRepo.countPassengerCars() + carsRepo.countDeliveryCars());
	model.addObject("countEmployees", employeeRepo.countEmployess());
	model.addObject("countOrders", ordersRepo.countActiveOrders()
		+ ordersRepo.countInactiveOrders() + ordersRepo.countReservedOrders());
	Integer employeeLoggedId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	model.addObject("employee", employeeRepo.findOne(employeeLoggedId));

        return model;
    }

}
