package com.carRental.controller;

import com.carRental.form.CarCreateForm;
import com.carRental.model.Cars;
import com.carRental.model.Customers;
import com.carRental.model.RentalOrders;
import com.carRental.repository.CarsRepository;
import com.carRental.repository.CustomersRepository;
import com.carRental.repository.EmployeesRepository;
import com.carRental.repository.RentalOrderDetailsRepository;
import com.carRental.repository.RentalOrdersRepository;
import com.carRental.service.CarsService;
import com.carRental.service.RentalOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import org.joda.time.LocalDate;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Tomek on 11.12.2016.
 */
@Controller
public class CarsController {

    private final CarsService carSrv;
    private final RentalOrdersService rentalOrderSrv;

    @Inject
    public CarsController(CarsService carSrv, RentalOrdersService rentalOrderSrv) {
	this.rentalOrderSrv = rentalOrderSrv;
	this.carSrv = carSrv;
    }

    @Autowired
    private CarsRepository carsRepo;

    @Autowired
    private RentalOrdersRepository orderRepo;

    @Autowired
    private RentalOrderDetailsRepository orderDetailsRepo;

    @Autowired
    private EmployeesRepository employeeRepo;

    @Autowired
    private CustomersRepository customerRepo;
    
    @Autowired
    private RentalOrderDetailsRepository ordersRepo;

    @RequestMapping(value = "/passenger_list", method = RequestMethod.GET)
    public ModelAndView getAvailablePassengerCarsListView() {
	ModelAndView model = new ModelAndView("passenger_list");
	model.addObject("countPassengerCars", carsRepo.countPassengerCars());
	model.addObject("countDeliveryCars", carsRepo.countDeliveryCars());
	model.addObject("countCustomers", customerRepo.findAll().size());
	model.addObject("countCars", carsRepo.countPassengerCars() + carsRepo.countDeliveryCars());
	model.addObject("countEmployees", employeeRepo.countEmployess());
	model.addObject("countOrders", ordersRepo.countActiveOrders()
		+ ordersRepo.countInactiveOrders() + ordersRepo.countReservedOrders());
	Integer employeeId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	model.addObject("availableCars", carsRepo.findAvailablePassengerCars());
	model.addObject("noAvailableCars", carsRepo.findNoAvailablePassengerCars());
	model.addObject("onReviewCars", carsRepo.findOnReviewPassengerCars());
	model.addObject("inRepairCars", carsRepo.findInRepairPassengerCars());
	model.addObject("countOnReviewCars", carsRepo.countOnReviewPassengerCars());
	model.addObject("countInRepairCars", carsRepo.countInRepairPassengerCars());
	model.addObject("countCustomers", customerRepo.findAll().size());
	model.addObject("countActive", carsRepo.countActivePassengerCars());
	model.addObject("countInactive", carsRepo.countInactivePassengerCars());
	Integer employeeLoggedId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	model.addObject("employee", employeeRepo.findOne(employeeLoggedId));

	return model;
    }

    @RequestMapping(value = "/delivery_list", method = RequestMethod.GET)
    public ModelAndView getAvailableDeliveryCarsListView() {
	ModelAndView model = new ModelAndView("passenger_list");
	model.addObject("availableCars", carsRepo.findAvailableDeliveryCars());
	model.addObject("noAvailableCars", carsRepo.findNoAvailableDeliveryCars());
	model.addObject("countPassengerCars", carsRepo.countPassengerCars());
	model.addObject("countDeliveryCars", carsRepo.countDeliveryCars());
	model.addObject("countCustomers", customerRepo.findAll().size());
	model.addObject("countCars", carsRepo.countPassengerCars() + carsRepo.countDeliveryCars());
	model.addObject("countEmployees", employeeRepo.countEmployess());
	model.addObject("countOrders", ordersRepo.countActiveOrders()
		+ ordersRepo.countInactiveOrders() + ordersRepo.countReservedOrders());
	Integer employeeId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	model.addObject("countCustomers", customerRepo.findAll().size());
	model.addObject("countActive", carsRepo.countActiveDeliveryCars());
	model.addObject("countInactive", carsRepo.countInactiveDeliveryCars());
	Integer employeeLoggedId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	model.addObject("employee", employeeRepo.findOne(employeeLoggedId));

	return model;
    }

    @RequestMapping(value = "/car_create", method = RequestMethod.GET)
    public ModelAndView getCreateCarView() {
	ModelAndView model = new ModelAndView("car_create");
	model.addObject("CarCreateForm", new CarCreateForm());
	model.addObject("countPassengerCars", carsRepo.countPassengerCars());
	model.addObject("countDeliveryCars", carsRepo.countDeliveryCars());
	model.addObject("countCustomers", customerRepo.findAll().size());
	model.addObject("countCars", carsRepo.countPassengerCars() + carsRepo.countDeliveryCars());
	model.addObject("countEmployees", employeeRepo.countEmployess());
	model.addObject("countOrders", ordersRepo.countActiveOrders()
		+ ordersRepo.countInactiveOrders() + ordersRepo.countReservedOrders());
	Integer employeeId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	model.addObject("countCustomers", customerRepo.findAll().size());
	model.addObject("countCars", carsRepo.countPassengerCars() + carsRepo.countDeliveryCars());
	model.addObject("countEmployees", employeeRepo.countEmployess());
	model.addObject("countOrders", ordersRepo.countActiveOrders()
		+ ordersRepo.countInactiveOrders() + ordersRepo.countReservedOrders());
	Integer employeeLoggedId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	model.addObject("employee", employeeRepo.findOne(employeeLoggedId));

	return model;
    }

    @RequestMapping(value = "/car_create", method = RequestMethod.POST)
    public String createCar(@ModelAttribute("CarCreateForm") CarCreateForm form) {
	try {
	    carSrv.saveCar(new Cars(null, form.getBrandForm(), form.getYearForm(), form.getColorForm(), form.getEngineForm(),
		    form.getTransmissionForm(), form.getFuelForm(), true, false, form.getPricePerDayForm(), form.getModelForm(), form.getTypeForm(), false, false));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "redirect:/rental_orders_list";
    }

    @RequestMapping(value = "/delete_car/{carId}", method = RequestMethod.GET)
    public String deleteCar(@PathVariable("carId") Integer carId) {

	//orderRepo.deleteOrdersByCarId(carId);
	carsRepo.carSetStatusDeletedTrue(carId);

	return "redirect:/rental_orders_list?delete";
    }

    @RequestMapping(value = "/set_not_available/{carId}", method = RequestMethod.GET)
    public String setCarNotAvailable(@PathVariable("carId") Integer carId) {

	carSrv.setCarAvailableFalse(carId);

	return "redirect:/passenger_list";
    }

    @RequestMapping(value = "/set_available/{carId}", method = RequestMethod.GET)
    public String setCarAvailable(@PathVariable("carId") Integer carId) {

	carSrv.setCarAvailableTrue(carId);

	return "redirect:/passenger_list";
    }

    @RequestMapping(value = "/set_car_in_repair_true/{carId}", method = RequestMethod.GET)
    public String setCarInRepairTrue(@PathVariable("carId") Integer carId) {

	carSrv.setCarInRepairTrue(carId);
	carSrv.setCarOnReviewFalse(carId);
	carSrv.setCarAvailableFalse(carId);

	return "redirect:/passenger_list";
    }

    @RequestMapping(value = "/set_car_on_review_false/{carId}", method = RequestMethod.GET)
    public String setCarOnReviewFalse(@PathVariable("carId") Integer carId) {

	carSrv.setCarInRepairFalse(carId);
	carSrv.setCarOnReviewFalse(carId);
	carSrv.setCarAvailableTrue(carId);

	return "redirect:/passenger_list";
    }

    @RequestMapping(value = "/set_car_in_repair_false/{carId}", method = RequestMethod.GET)
    public String setCarInRepairFalse(@PathVariable("carId") Integer carId) {

	carSrv.setCarInRepairFalse(carId);
	carSrv.setCarOnReviewTrue(carId);
	//carSrv.setCarAvailableFalse(carId);

	return "redirect:/passenger_list";
    }

    @RequestMapping(value = "/car_details/{carId}", method = RequestMethod.GET)
    public ModelAndView getCarDetailsView(@PathVariable("carId") Integer carId) {
	ModelAndView model = new ModelAndView("car_details");
	Cars car = carSrv.getCarById(carId);

	try {
	    if (!car.isAvailable()) {
		RentalOrders rentalOrder = orderRepo.findByCarIdAndActive(carId);
		Customers customer = customerRepo.findOne(rentalOrder.getCustomerId());
		Date rentStartDate = orderRepo.findByCarIdAndActive(carId).getRentStartDate();
		Date rentEndDate = orderRepo.findByCarIdAndActive(carId).getRentEndDate();
		Integer price = carSrv.getCarById(carId).getPricePerDay();
		model.addObject("orderPrice", rentalOrderSrv.getRentPrice(rentStartDate, rentEndDate, price));
		model.addObject("rentalOrderDetails", rentalOrder);
		model.addObject("customer", customer);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	model.addObject("CarDetails", car);
	model.addObject("countPassengerCars", carsRepo.countPassengerCars());
	model.addObject("countDeliveryCars", carsRepo.countDeliveryCars());
	model.addObject("countCustomers", customerRepo.findAll().size());
	model.addObject("countCars", carsRepo.countPassengerCars() + carsRepo.countDeliveryCars());
	model.addObject("countEmployees", employeeRepo.countEmployess());
	model.addObject("countOrders", ordersRepo.countActiveOrders()
		+ ordersRepo.countInactiveOrders() + ordersRepo.countReservedOrders());
	Integer employeeId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	model.addObject("reservedList", orderDetailsRepo.findByCarIdAndOrderStatus(carId, "Zarezerwowane"));
	model.addObject("countOrders", orderDetailsRepo.countOrdersByCarId(carId));
	Integer employeeLoggedId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	model.addObject("employee", employeeRepo.findOne(employeeLoggedId));

	return model;
    }
}
