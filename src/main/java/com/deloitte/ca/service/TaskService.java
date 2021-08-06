package com.deloitte.ca.service;

import java.util.List;
import com.deloitte.ca.entity.Task;

public interface TaskService {
	
	Task saveTask(Task Task);
	
	Task getTaskById(Long id);
	
	Task updateTask(Task Task);
	
	void deleteTaskById(Long id);
	
	List<Task> getAllTasksForUser(Long userid);
}
