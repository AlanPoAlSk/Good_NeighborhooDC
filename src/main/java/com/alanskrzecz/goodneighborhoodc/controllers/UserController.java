package com.alanskrzecz.goodneighborhoodc.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alanskrzecz.goodneighborhoodc.models.Neighborhood;
import com.alanskrzecz.goodneighborhoodc.models.User;
import com.alanskrzecz.goodneighborhoodc.services.NeighborhoodService;
import com.alanskrzecz.goodneighborhoodc.services.TaskService;
import com.alanskrzecz.goodneighborhoodc.services.UserService;
import com.alanskrzecz.goodneighborhoodc.validator.UserValidator;

import jakarta.validation.Valid;

@Controller
public class UserController {
	private UserService userService;
    
	private UserValidator userValidator;
	
	 private final NeighborhoodService neighborhoodService;
	 private final TaskService taskService;
	
	public UserController(UserService userService, UserValidator userValidator, NeighborhoodService neighborhoodService, TaskService taskService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.neighborhoodService = neighborhoodService;
        this.taskService = taskService;
    }
    
    
    @RequestMapping(value = {"/", "/dashboard"})
    public String dashboard(Principal principal, Model model) {
        // 1
        String username = principal.getName();
        User currentUser = userService.findByUsername(username);
        Neighborhood neighborhood = currentUser.getNeighborhood();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("tasks", taskService.allTasks(neighborhood));
        return "dashboard";
    }
    
    @RequestMapping("/register")
    public String registerForm(@ModelAttribute("user") User user, Model model) {
    	List<Neighborhood> neighborhoods = neighborhoodService.getAllNeighborhoods();
    	Collections.sort(neighborhoods, Comparator.comparing(Neighborhood::getName));

        model.addAttribute("neighborhoods", neighborhoods);
        return "registrationPage";
    }
    
    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
    	userValidator.validate(user, result);
        if (result.hasErrors()) {
        	List<Neighborhood> neighborhoods = neighborhoodService.getAllNeighborhoods();
        	Collections.sort(neighborhoods, Comparator.comparing(Neighborhood::getName));

            model.addAttribute("neighborhoods", neighborhoods);
            return "registrationPage";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }
    
    
 // 1
    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }
        return "loginPage";
    }
    

}
