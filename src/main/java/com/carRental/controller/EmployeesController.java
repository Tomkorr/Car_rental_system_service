package com.carRental.controller;

import com.carRental.form.EmployeeCreateForm;
import com.carRental.model.Addresses;
import com.carRental.model.Employees;
import com.carRental.repository.AddressesRepository;
import com.carRental.repository.CarsRepository;
import com.carRental.repository.CustomersRepository;
import com.carRental.repository.EmployeesRepository;
import com.carRental.repository.RentalOrderDetailsRepository;
import com.carRental.service.AddressesService;
import com.carRental.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Dell on 20.03.2017.
 */
@Controller
public class EmployeesController {

    private final EmployeesService employeesSrv;
    private final AddressesService addressSrv;

    @Inject
    public EmployeesController(EmployeesService employeesSrv, AddressesService addressSrv) {
        this.employeesSrv = employeesSrv;
        this.addressSrv = addressSrv;
    }

    @Autowired
    private CustomersRepository customerRepo;

    @Autowired
    private AddressesRepository addressRepo;

    @Autowired
    private EmployeesRepository employeesRepo;

    @Autowired
    private CarsRepository carsRepo;

    @Autowired
    private EmployeesRepository employeeRepo;

    @Autowired
    private RentalOrderDetailsRepository ordersRepo;

    public String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        return passwordEncoder.encode(password);
    }

    @RequestMapping(value = "/employees_list", method = RequestMethod.GET)
    public ModelAndView getEmployeesListView() {
        ModelAndView model = new ModelAndView("employees_list");
        model.addObject("employeesList", employeesRepo.findAll());
        model.addObject("countPassengerCars", carsRepo.countPassengerCars());
        model.addObject("countDeliveryCars", carsRepo.countDeliveryCars());
        model.addObject("countCustomers", customerRepo.findAll().size());
        model.addObject("countCars", carsRepo.countPassengerCars() + carsRepo.countDeliveryCars());
        model.addObject("countEmployees", employeeRepo.countEmployess());
        model.addObject("countOrders", ordersRepo.countActiveOrders()
                + ordersRepo.countInactiveOrders() + ordersRepo.countReservedOrders());
        Integer employeeId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
        model.addObject("employee", employeeRepo.findOne(employeeId));

        return model;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/employee_create", method = RequestMethod.GET)
    public ModelAndView getCreateEmployeeView() {
        ModelAndView model = new ModelAndView("employee_create");
        model.addObject("EmployeeCreateForm", new EmployeeCreateForm());
        model.addObject("countPassengerCars", carsRepo.countPassengerCars());
        model.addObject("countDeliveryCars", carsRepo.countDeliveryCars());
        model.addObject("countCustomers", customerRepo.findAll().size());
        model.addObject("countCars", carsRepo.countPassengerCars() + carsRepo.countDeliveryCars());
        model.addObject("countEmployees", employeeRepo.countEmployess());
        model.addObject("countOrders", ordersRepo.countActiveOrders()
                + ordersRepo.countInactiveOrders() + ordersRepo.countReservedOrders());
        Integer employeeId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
        model.addObject("employee", employeeRepo.findOne(employeeId));
        return model;
    }

    @RequestMapping(value = "/employee_create", method = RequestMethod.POST)
    public String createEmployee(@ModelAttribute("EmployeeCreateForm") @Valid EmployeeCreateForm form) {

        Integer addressId = addressRepo.saveAddress(form.getCityForm(), form.getStreetForm(), form.getZipCodeForm(),
                form.getStreetNumberForm());

        employeesSrv.saveEmployee(new Employees(null, addressId, form.getFirstNameForm(), form.getLastNameForm(),
                form.getAgeForm(), form.getGenderForm(), form.getPhoneForm(), form.getEmailForm(), form.getRoleNameForm(),
                form.getLogin(), hashPassword(form.getPassword()), true));

        return "redirect:/employees_list";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/employee_edit/{employeeId}", method = RequestMethod.GET)
    public ModelAndView getEditEmployeeView(@PathVariable("employeeId") Integer employeeId) {
        ModelAndView model = new ModelAndView("employee_edit");
        model.addObject("employee", employeesRepo.findOne(employeeId));
        model.addObject("address", addressRepo.findOne(employeesRepo.findOne(employeeId).getAddressId()));
        model.addObject("EmployeeCreateForm", new EmployeeCreateForm());
        model.addObject("countPassengerCars", carsRepo.countPassengerCars());
        model.addObject("countDeliveryCars", carsRepo.countDeliveryCars());
        model.addObject("countCustomers", customerRepo.findAll().size());
        model.addObject("countCars", carsRepo.countPassengerCars() + carsRepo.countDeliveryCars());
        model.addObject("countEmployees", employeeRepo.countEmployess());
        model.addObject("countOrders", ordersRepo.countActiveOrders()
                + ordersRepo.countInactiveOrders() + ordersRepo.countReservedOrders());

        return model;
    }

    @RequestMapping(value = "/employee_edit", method = RequestMethod.POST)
    public String editEmployee(@ModelAttribute("EmployeeCreateForm") EmployeeCreateForm form, @ModelAttribute Employees employee,
                               @ModelAttribute Addresses address) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        try {
            address.setAddressId(form.getAddressId());
            address.setCity(form.getCityForm());
            address.setStreet(form.getStreetForm());
            address.setStreetNumber(form.getStreetNumberForm());
            address.setZipCode(form.getZipCodeForm());

            addressSrv.updateAddress(address);

            employee.setEmployeeId(form.getEmployeeId());
            employee.setAddressId(form.getAddressId());
            employee.setFirstName(form.getFirstNameForm());
            employee.setLastName(form.getLastNameForm());
            employee.setEmail(form.getEmailForm());
            employee.setAge(form.getAgeForm());
            employee.setGender(form.getGenderForm());
            employee.setPhone(form.getPhoneForm());
            employee.setRoleName(form.getRoleNameForm());
            employee.setLogin(form.getLogin());
            employee.setPassword(passwordEncoder.encode(form.getPassword()));

            employeesSrv.updateEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/employees_list?editSuccess";
    }

    @RequestMapping(value = "/employee_details/{employeeId}", method = RequestMethod.GET)
    public ModelAndView getEmployeeDetailsView(@PathVariable("employeeId") Integer employeeId) {
        ModelAndView model = new ModelAndView("employee_details");
        model.addObject("employeeDetails", employeesRepo.findOne(employeeId));
        model.addObject("employeeAddressDetails", addressRepo.findOne(employeesRepo.findOne(employeeId).getAddressId()));
        model.addObject("countPassengerCars", carsRepo.countPassengerCars());
        model.addObject("countDeliveryCars", carsRepo.countDeliveryCars());
        model.addObject("countCustomers", customerRepo.findAll().size());
        model.addObject("countCars", carsRepo.countPassengerCars() + carsRepo.countDeliveryCars());
        model.addObject("countEmployees", employeeRepo.countEmployess());
        model.addObject("countOrders", ordersRepo.countActiveOrders()
                + ordersRepo.countInactiveOrders() + ordersRepo.countReservedOrders());
        Integer employeeLoggedId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
        model.addObject("employee", employeeRepo.findOne(employeeLoggedId));
        //model.addObject("countOrders", orderDetailsRepo.countOrdersByEmployeeId(employeeId));
        return model;
    }

    @RequestMapping(value = "/delete_employee/{employeeId}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable("employeeId") Integer employeeId) {

        employeesSrv.deleteEmployee(employeeId);

        return "redirect:/employees_list?delete";
    }
}
