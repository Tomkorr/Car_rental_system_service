package com.carRental.controller;

import com.carRental.form.RentalOrdersCreateForm;
import com.carRental.model.Employees;
import com.carRental.model.RentalOrderDetails;
import com.carRental.model.RentalOrders;
import com.carRental.repository.*;
import com.carRental.service.CarsService;
import com.carRental.service.RentalOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.inject.Inject;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.List;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.round;

/**
 * Created by Tomek on 11.12.2016.
 */
@Controller
public class RentalOrdersController {

    private RentalOrdersService rentalOrderSrv;
    private CarsService carSrv;

    @Inject
    public RentalOrdersController(RentalOrdersService rentalOrderSrv, CarsService carSrv) {
	this.rentalOrderSrv = rentalOrderSrv;
	this.carSrv = carSrv;
    }

    @Autowired
    private CustomersRepository customerRepo;

    @Autowired
    private CarsRepository carsRepo;

    @Autowired
    private EmployeesRepository employeeRepo;

    @Autowired
    private RentalOrderDetailsRepository orderDetailsRepo;

    @Autowired
    private RentalOrdersRepository ordersRepo;

    @RequestMapping(value = "/rental_orders_list", method = RequestMethod.GET)
    public ModelAndView getRentalOrdersListView() {
	ModelAndView model = new ModelAndView("rental_orders_list");
	model.addObject("activeOrders", orderDetailsRepo.findActiveOrders());
	model.addObject("inactiveOrders", orderDetailsRepo.findInactiveOrders());
	model.addObject("toConfirmOrders", orderDetailsRepo.findToConfirmOrders());
	model.addObject("reservedOrders", orderDetailsRepo.findReservedOrders());
	model.addObject("cancelledOrders", orderDetailsRepo.findCancelledOrders());
	model.addObject("countPassengerCars", carsRepo.countPassengerCars());
	model.addObject("countDeliveryCars", carsRepo.countDeliveryCars());
	model.addObject("countCustomers", customerRepo.findAll().size());
	model.addObject("countCars", carsRepo.countPassengerCars() + carsRepo.countDeliveryCars());
	model.addObject("countEmployees", employeeRepo.countEmployess());
	model.addObject("countOrders", orderDetailsRepo.countActiveOrders()
		+ orderDetailsRepo.countInactiveOrders() + orderDetailsRepo.countReservedOrders());
	model.addObject("countCustomers", customerRepo.findAll().size());
	model.addObject("countToConfirm", orderDetailsRepo.countToConfirmOrders());
	model.addObject("countActive", orderDetailsRepo.countActiveOrders());
	model.addObject("countInactive", orderDetailsRepo.countInactiveOrders());
	model.addObject("countReserved", orderDetailsRepo.countReservedOrders());
	model.addObject("countCancelled", orderDetailsRepo.countCancelledOrders());

	List<RentalOrderDetails> ordersToConfirm = orderDetailsRepo.findToConfirmOrders();
	List<Integer> ordersToConfirmId = new ArrayList<>();
	for (int i = 0; i < ordersToConfirm.size(); i++) {
	    ordersToConfirmId.add(ordersToConfirm.get(i).getOrderId());
	}
	model.addObject("ordersToConfirmId", ordersToConfirmId);

	List<RentalOrderDetails> ordersReserved = orderDetailsRepo.findReservedOrders();
	List<Integer> reservedOrdersId = new ArrayList<>();
	for (int i = 0; i < ordersReserved.size(); i++) {
	    reservedOrdersId.add(ordersReserved.get(i).getOrderId());
	}
	model.addObject("ordersReservedId", reservedOrdersId);

	List<RentalOrderDetails> ordersActive = orderDetailsRepo.findActiveOrders();
	List<Integer> activeOrdersId = new ArrayList<>();
	for (int i = 0; i < ordersActive.size(); i++) {
	    activeOrdersId.add(ordersActive.get(i).getOrderId());
	}
	model.addObject("ordersActiveId", activeOrdersId);

	Integer employeeLoggedId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	model.addObject("employee", employeeRepo.findOne(employeeLoggedId));

	return model;
    }

    @RequestMapping(value = "/create_rental_order/{carId}", method = RequestMethod.GET)
    public ModelAndView getCreateRentalOrderView(@PathVariable("carId") Integer carId) {
	ModelAndView model = new ModelAndView("create_rental_order");
	model.addObject("RentalOrdersCreateForm", new RentalOrdersCreateForm());
	model.addObject("Customers", customerRepo.findAll());
	model.addObject("countPassengerCars", carsRepo.countPassengerCars());
	model.addObject("countDeliveryCars", carsRepo.countDeliveryCars());
	model.addObject("countCustomers", customerRepo.findAll().size());
	model.addObject("countCars", carsRepo.countPassengerCars() + carsRepo.countDeliveryCars());
	model.addObject("countEmployees", employeeRepo.countEmployess());
	model.addObject("countOrders", orderDetailsRepo.countActiveOrders()
		+ orderDetailsRepo.countInactiveOrders() + orderDetailsRepo.countReservedOrders());
	model.addObject("carId", carId);
	model.addObject("car", carsRepo.findOne(carId));
	model.addObject("countCustomers", customerRepo.findAll().size());
	model.addObject("countToConfirm", orderDetailsRepo.countToConfirmOrders());
	model.addObject("countActive", orderDetailsRepo.countActiveOrders());
	model.addObject("countInactive", orderDetailsRepo.countInactiveOrders());
	model.addObject("countReserved", orderDetailsRepo.countReservedOrders());
	model.addObject("countCancelled", orderDetailsRepo.countCancelledOrders());
	Integer employeeLoggedId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	model.addObject("employee", employeeRepo.findOne(employeeLoggedId));
	return model;
    }

    @RequestMapping(value = "/create_rentall_order/{carId}", method = RequestMethod.POST)
    public String CreateRentalOrder(@PathVariable("carId") Integer carId,
	    @ModelAttribute("RentalOrdersCreateForm") @Valid RentalOrdersCreateForm form,
	    @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
	LocalDate currentDate = LocalDate.now();
	Integer employeeId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();

	System.out.println(currentDate + "  " + LocalDate.parse(startDate));
	try {
	    LocalDate start = LocalDate.parse(startDate);
	    LocalDate end = LocalDate.parse(endDate);

	    if (end.isBefore(start)) {
		return "redirect:/create_rental_order/" + carId + "?dateError";
	    }

	    List<RentalOrderDetails> reserved = orderDetailsRepo.findByCarIdAndZarezerwowaneDoPotwierdznia(carId);
	    carsRepo.findOne(carId).setReserved(true);

	    for (int i = 0; i < reserved.size(); i++) {
		if (between(start, reserved.get(i).getRentStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), reserved.get(i).getRentEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
			|| between(end, reserved.get(i).getRentStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), reserved.get(i).getRentEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
		    return "redirect:/create_rental_order/" + carId + "?dateExist";
		}
	    }

	    if (start.equals(currentDate)) {
		//carSrv.setCarAvailableFalse(carId);
		double rentCost = round(rentalOrderSrv.getRentPrice(Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant()), carsRepo.findOne(carId).getPricePerDay()), 0);
		rentalOrderSrv.saveRentalOrder(new RentalOrders(null, carId, form.getCustomerId(), employeeId, null, null, null, null,
			new Date(), new SimpleDateFormat("yyyy-MM-dd").parse(start.toString()), new SimpleDateFormat("yyyy-MM-dd").parse(end.toString()), "Do potwierdzenia", rentCost, true));
	    }
	    if (start.isAfter(currentDate)) {
		double rentCost = round(rentalOrderSrv.getRentPrice(Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant()), carsRepo.findOne(carId).getPricePerDay()), 0);
		rentalOrderSrv.saveRentalOrder(new RentalOrders(null, carId, form.getCustomerId(), employeeId, null, null, null, null,
			new Date(), new SimpleDateFormat("yyyy-MM-dd").parse(start.toString()), new SimpleDateFormat("yyyy-MM-dd").parse(end.toString()), "Zarezerwowane", rentCost, true));
	    }

	    carsRepo.findOne(carId).setReserved(true);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return "redirect:/rental_orders_list";
    }

    @RequestMapping(value = "/cancel_order", method = RequestMethod.GET)
    public String DeleteRentalOrder(@RequestParam("rentalOrderId") Integer rentalOrderId, @RequestParam("carId") Integer carId,
	    @RequestParam("cancelReason") String cancelReason) {
	Integer employeeId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	ordersRepo.findOne(rentalOrderId).setEmployeeIdCancelled(employeeId);
	ordersRepo.findOne(rentalOrderId).setCancelReason(cancelReason);
	rentalOrderSrv.setOrderCancelledTrue(rentalOrderId);
	carsRepo.findOne(carId).setIsRented(false);
	carSrv.setCarOnReviewTrue(carId);
	//carSrv.setCarAvailableFalse(carId);

	if (carsRepo.findOne(carId).isAvailable() == false && carsRepo.findOne(carId).isIsRented() == false) {
	    return "redirect:/rental_orders_list";
	}

	return "redirect:/rental_orders_list";
    }

    @RequestMapping(value = "/order_details/{rentalOrderId}", method = RequestMethod.GET)
    public ModelAndView OrderDetails(@PathVariable("rentalOrderId") Integer rentalOrderId) {
	ModelAndView model = new ModelAndView("order_details");
	RentalOrderDetails order = orderDetailsRepo.findOne(rentalOrderId);
	model.addObject("countPassengerCars", carsRepo.countPassengerCars());
	model.addObject("countDeliveryCars", carsRepo.countDeliveryCars());
	model.addObject("countCustomers", customerRepo.findAll().size());
	model.addObject("countCars", carsRepo.countPassengerCars() + carsRepo.countDeliveryCars());
	model.addObject("countEmployees", employeeRepo.countEmployess());
	model.addObject("countOrders", orderDetailsRepo.countActiveOrders()
		+ orderDetailsRepo.countInactiveOrders() + orderDetailsRepo.countReservedOrders());
	Integer employeeId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	model.addObject("countToConfirm", orderDetailsRepo.countToConfirmOrders());
	model.addObject("countActive", orderDetailsRepo.countActiveOrders());
	model.addObject("countInactive", orderDetailsRepo.countInactiveOrders());
	model.addObject("countReserved", orderDetailsRepo.countReservedOrders());
	model.addObject("countCancelled", orderDetailsRepo.countCancelledOrders());
	model.addObject("orderDetails", order);
	model.addObject("employeeReserve", employeeRepo.findOne(order.getEmployeeIdReserve()));
	if (order.getEmployeeIdLetOut() != null) {
	    model.addObject("employeeLetOut", employeeRepo.findOne(order.getEmployeeIdLetOut()));
	}
	if (order.getEmployeeIdReceive() != null) {
	    model.addObject("employeeReceive", employeeRepo.findOne(order.getEmployeeIdReceive()));
	}
	if (order.getEmployeeIdCancelled() != null) {
	    model.addObject("employeeCancelled", employeeRepo.findOne(order.getEmployeeIdCancelled()));
	}
	Integer employeeLoggedId = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	model.addObject("employee", employeeRepo.findOne(employeeLoggedId));
	return model;
    }

    @RequestMapping(value = "/confirm_let_out_car/{rentalOrderId}/{carId}", method = RequestMethod.GET)
    public String ConfirmLetOutCar(@PathVariable("rentalOrderId") Integer rentalOrderId, @PathVariable("carId") Integer carId) {
	Integer employeeIdLetOut = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	ordersRepo.findOne(rentalOrderId).setEmployeeIdLetOut(employeeIdLetOut);
	carSrv.setCarIsRentedTrue(carId);
	ordersRepo.setStatus("Rozpoczęte", rentalOrderId);

	return "redirect:/rental_orders_list";
    }

    @RequestMapping(value = "/confirm_receive_car/{rentalOrderId}/{carId}", method = RequestMethod.GET)
    public String ConfirmReceiveCar(@PathVariable("rentalOrderId") Integer rentalOrderId, @PathVariable("carId") Integer carId) {
	Integer employeeIdReceive = employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeId();
	ordersRepo.findOne(rentalOrderId).setEmployeeIdReceive(employeeIdReceive);
	ordersRepo.findOne(rentalOrderId).setOrderStatus("Zakończone");
	carsRepo.findOne(carId).setIsRented(false);
	carSrv.setCarOnReviewTrue(carId);

	return "redirect:/rental_orders_list";
    }

    @RequestMapping(value = "/cancel_reservation/{rentalOrderId}", method = RequestMethod.POST)
    public String deleteReservedOrder(@PathVariable("rentalOrderId") Integer rentalOrderId, @RequestParam("carId") Integer carId) {
	rentalOrderSrv.setOrderCancelledTrue(rentalOrderId);
	carsRepo.findOne(carId).setIsRented(false);

	return "redirect:/rental_orders_list";
    }

    public boolean between(LocalDate date, LocalDate dateStart, LocalDate dateEnd) {
	if (date != null && dateStart != null && dateEnd != null) {
	    if (date.isAfter(dateStart.minusDays(1)) && date.isBefore(dateEnd.plusDays(1))) {
		return true;
	    } else {
		return false;
	    }
	}
	return false;
    }

    public static LocalDate fromDate(Date date) {
	Instant instant = Instant.ofEpochMilli(date.getTime());
	return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
		.toLocalDate();
    }

    public boolean checkCarIsReserved(Integer carId) {
	List<RentalOrders> carOrders = ordersRepo.findByCarId(carId);
	boolean isRented = false;

	for (RentalOrders order : carOrders) {
	    if (Arrays.asList("Do potwierdzenia", "Zarezerwowane", "Rozpoczęte").contains(order.getOrderStatus())) {
		isRented = true;
		break;
	    }
	}
	return isRented;
    }
}
