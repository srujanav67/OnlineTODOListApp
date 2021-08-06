package com.deloitte.ca.web;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.deloitte.ca.entity.Task;
import com.deloitte.ca.entity.User;
import com.deloitte.ca.service.TaskService;
import com.deloitte.ca.service.UserService;

@Controller
public class TaskController {
	
	private TaskService taskService;
	
	@Autowired
	private UserService userService;

	public TaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}
	
	// handler method to handle list Tasks and return model and view
	@GetMapping("/tasks")
	public String listTasks(Model model) {
		
		User user = userService.findByUsername(findLoggedInUsername());
		model.addAttribute("tasks", taskService.getAllTasksForUser(user.getId()));
		return "tasks";
	}
	
	public String findLoggedInUsername() {  	
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username != null) {
            return username;
        }
        return null;
    }
	
	@GetMapping("/tasks/new")
	public String createTaskForm(Model model) {
		
		// create Task object to hold Task form data
		Task task = new Task();
		model.addAttribute("task", task);
		return "createtask";
		
	}
	
	@PostMapping("/tasks")
	public String saveTask(@ModelAttribute("task") Task task) {
		User user = userService.findByUsername(findLoggedInUsername());
		task.setUser(user);
		taskService.saveTask(task);
		return "redirect:/tasks";
	}
	
	@GetMapping("/tasks/edit/{id}")
	public String editTaskForm(@PathVariable Long id, Model model) {
		model.addAttribute("task", taskService.getTaskById(id));
		return "edittask";
	}

	@PostMapping("/tasks/{id}")
	public String updateTask(@PathVariable Long id,
			@ModelAttribute("task") Task Task,
			Model model) {
		
		// get Task from database by id
		Task existingTask = taskService.getTaskById(id);
		existingTask.setId(id);
		existingTask.setTaskname(Task.getTaskname());
		//existingTask.setUpdated(Task.getUpdated());
		
		// save updated Task object
		taskService.updateTask(existingTask);
		return "redirect:/tasks";		
	}
	
	@PostMapping("/tasks/changeStatus")
	public String updateTaskStatus(@RequestBody Map<String, Object> UpdStats) {
		
		Long id = Long.parseLong(UpdStats.get("id").toString()) ;
		boolean status = Boolean.parseBoolean(UpdStats.get("isTaskCompleted").toString());
		
		// get Task from database by id
		Task existingTask = taskService.getTaskById(id);
		existingTask.setId(id);
		existingTask.setIsTaskCompleted(status);
		existingTask.setUpdated(new Date());
		
		// save updated Task object
		taskService.updateTask(existingTask);
	
		return "redirect:/tasks";		
	}
	
	// handler method to handle delete Task request
	@GetMapping("/tasks/{id}")
	public String deleteTask(@PathVariable Long id) {
		taskService.deleteTaskById(id);
		return "redirect:/tasks";
	}
	
}

