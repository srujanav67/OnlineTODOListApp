package com.deloitte.ca.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.deloitte.ca.entity.User;
import com.deloitte.ca.service.TaskService;
import com.deloitte.ca.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		User user = userService.findByUsername(findLoggedInUsername());
		if(user != null)
		{
			model.addAttribute("tasks", taskService.getAllTasksForUser(user.getId()));
			return "tasks";
		}
		else
			return "login";
	}
	
	public String findLoggedInUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username != null) {
            return username;
        }
        return null;
    }
}
