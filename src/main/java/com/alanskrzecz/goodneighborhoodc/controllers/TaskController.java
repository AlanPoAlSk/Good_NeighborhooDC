package com.alanskrzecz.goodneighborhoodc.controllers;

import java.security.Principal;
import java.util.Base64;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alanskrzecz.goodneighborhoodc.models.Task;
import com.alanskrzecz.goodneighborhoodc.models.Task.TaskStatus;
import com.alanskrzecz.goodneighborhoodc.models.User;
import com.alanskrzecz.goodneighborhoodc.services.TaskService;
import com.alanskrzecz.goodneighborhoodc.services.UserService;

import jakarta.validation.Valid;

@Controller
public class TaskController {
	private TaskService taskService;
	private UserService userService;

	public TaskController(TaskService taskService, UserService userService) {
		this.taskService = taskService;
		this.userService = userService;
	}
	
	private boolean isAuthorizedUser(Task task, Principal principal) {
	    return task != null && principal != null &&
	           task.getUser() != null && task.getUser().getUsername().equals(principal.getName());
	}
	
	@GetMapping("/users/tasks/new")
	public String newTaskForm(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Get the user ID from the database using the username (assuming you have a findByUsername method)
        User user = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("user_id", user);
		model.addAttribute("newTask", new Task());
		return "newTask";
	}
	
	@PostMapping("/users/tasks/new")
	public String processNewTask(
			@Valid @ModelAttribute("newTask") Task newTask,BindingResult result,@RequestParam("imageFile") MultipartFile imageFile, Model model
			) {
		if (result.hasErrors()) {
			model.addAttribute("newTask", new Task());
			System.out.println("Validation errors: " + result.getAllErrors());
	        // Handle validation errors (e.g., return to the form with error messages)
	        return "newTask";
		}
		if (!imageFile.isEmpty()) {
	        newTask.setImageFile(imageFile);
	    }
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Get the user ID from the database using the username (assuming you have a findByUsername method)
        User user = userService.findByUsername(userDetails.getUsername());
		newTask.setUser(user);
		taskService.addTask(newTask);
		return "redirect:/dashboard";
		
	}
	
	@GetMapping("/users/tasks/{id}")
	public String taskDetails(@PathVariable("id")Long id, Model model, Principal principal) {
		 Task task = taskService.oneTask(id);
//		 System.out.println("Principal Object: " + principal);
//		 
//		 if (!isAuthorizedUser(task, principal)) {
//		        return "redirect:/dashboard";
//		    }
		 
		 String currentUsername = principal.getName();
		 Long currentUserId = userService.getUserIdByUsername(currentUsername);
//		 System.out.println("Task Accepted User ID: " + (task.getAcceptedUser() != null ? task.getAcceptedUser().getId() : "null"));
//		 System.out.println("Current User ID: " + currentUserId);
	
		if (task != null) {
			byte[] imageBytes = task.getImageBytes();

	        if (imageBytes != null) {
	            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	            model.addAttribute("base64Image", base64Image);
	        }

	        model.addAttribute("currentUserId", currentUserId);
	        model.addAttribute("task", task);
	    } else {
	        // Handle the case where the task is not found
	        // You may want to redirect to an error page or handle it appropriately
	    	return "redirect:/dashboard";
	    }
		return "taskDetails";
	}
	
	@GetMapping("/users/tasks/{id}/edit")
	public String editTask(@PathVariable("id")Long id, Model model, Principal principal) {
		Task task = taskService.oneTask(id);
		if (!isAuthorizedUser(task, principal)) {
	       
	        return "redirect:/dashboard";
	    }
		model.addAttribute("task", taskService.oneTask(id));
		return "editTask";
	}
	
	@PutMapping("/users/tasks/{id}/edit")
	public String processEditTask(@Valid @ModelAttribute("task")Task task, BindingResult result, Principal principal) {
		
		if (!isAuthorizedUser(task, principal)) {
	       
	        return "redirect:/dashboard";
	    }
		if(result.hasErrors()) {
			return "editTask";
		} else {
			taskService.updateTask(task);
			return "redirect:/dashboard";
		}
	}
	
	@PostMapping("/users/tasks/{id}/apply")
	public String applyToTask(@PathVariable("id") Long id, Principal principal) {
	    Task task = taskService.oneTask(id);
	    System.out.println("Task: " + task);
	    if (principal == null) {
	        // Handle unauthenticated user, redirect to login or show an error
	        return "redirect:/login";
	    }
	    System.out.println("ID: " + id);
	    System.out.println("Principal: " + principal);
	    System.out.println("Task Status: " + task.getStatus());
	    if (task != null && task.getStatus() == TaskStatus.OPEN && task.getAcceptedUser() == null) {
	        User user = userService.findByUsername(principal.getName());
	        task.setAcceptedUser(user);
	        task.setStatus(TaskStatus.ACCEPTED);
	        taskService.updateTask(task);
	    } else {
	    	System.out.println("Task not found or not open.");
	    }
	    return "redirect:/dashboard";
	}
	
	@PostMapping("/users/tasks/{id}/undo")
	public String undoApply(@PathVariable("id") Long id, Principal principal) {
		Task task = taskService.oneTask(id);
	    if (task != null && task.getStatus() == TaskStatus.ACCEPTED && task.getAcceptedUser() != null) {
	        String username = principal.getName();
	        if (task.getAcceptedUser().getUsername().equals(username)) { // Check if the current user is the one who accepted the task
	            task.setAcceptedUser(null);
	            task.setStatus(TaskStatus.OPEN);
	            taskService.updateTask(task);
	            System.out.println("successMessage ,Apply status undone successfully.");
	        } else {
	        	System.out.println("errorMessage ,You are not authorized to undo the apply status for this task.");
	        }
	    } else {
	    	System.out.println("errorMessage, Task not found or not in the ACCEPTED status.");
	    }
	    return "redirect:/dashboard";
	}
	

	@GetMapping("/users/tasks/personal/{id}")
	public String acceptedTasks(@PathVariable("id") Long userId,Model model, Principal principal) {
		if (principal == null || !userId.equals(userService.findByUsername(principal.getName()).getId())) {
	        return "redirect:/dashboard";
	    }

	    User user = userService.oneUser(userId);
	    if (user == null) {
	        return "redirect:/dashboard";
	    }
	    List<Task> acceptedTasks = taskService.findTasksByUserOrAcceptedUser(user);
	    
	    model.addAttribute("user", user);
	    model.addAttribute("acceptedTasks", acceptedTasks);
	    return "personalPage";
	}
	
	@DeleteMapping("users/tasks/{id}")
	public String processDeleteTask(@PathVariable("id")Long id, Principal principal) {
		Task task = taskService.oneTask(id);
		if (!isAuthorizedUser(task, principal)) {
	       
	        return "redirect:/dashboard";
	    }
		taskService.deleteTask(id);
		return "redirect:/dashboard";
	}

}
