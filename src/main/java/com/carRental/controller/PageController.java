package com.carRental.controller;

import com.carRental.repository.CarsRepository;
import com.carRental.repository.CustomersRepository;
import com.carRental.repository.EmployeesRepository;
import com.carRental.repository.RentalOrderDetailsRepository;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Tomek on 10.12.2016.
 */
@Controller
public class PageController {

    @Autowired
    private CarsRepository carsRepo;

    @Autowired
    private CustomersRepository customerRepo;

    @Autowired
    private EmployeesRepository employeeRepo;

    @Autowired
    private RentalOrderDetailsRepository ordersRepo;

    public String hashPassword(String password) {
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	return passwordEncoder.encode(password);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndexView() {
	ModelAndView model = new ModelAndView("index");
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	model.addObject("user", employeeRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginView(Principal principal) {
	ModelAndView model = new ModelAndView("login");
	if (principal != null) {
	    System.out.println(principal.getName());
	    model.setViewName("redirect:/");
	    return model;
	}
	model.addObject("photo", "c215");
	return model;
    }

    @GetMapping("/403")
    public ModelAndView get404View() {
	ModelAndView model = new ModelAndView("403");
	return model;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String getLogoutView() {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (auth != null) {
	    SecurityContextHolder.getContext().setAuthentication(null);
	}

	return "redirect:/login?logout";
    }

}
