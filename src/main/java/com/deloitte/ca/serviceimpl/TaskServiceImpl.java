package com.deloitte.ca.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deloitte.ca.entity.Task;
import com.deloitte.ca.repository.TaskRepository;
import com.deloitte.ca.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

	private TaskRepository taskRepository;
	
	public TaskServiceImpl(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}
	
	@Override
	public Task saveTask(Task Task) {
		return taskRepository.save(Task);
	}

	@Override
	public Task getTaskById(Long id) {
		return taskRepository.findById(id).get();
	}

	@Override
	public Task updateTask(Task Task) {
		return taskRepository.save(Task);
	}

	@Override
	public void deleteTaskById(Long id) {
		taskRepository.deleteById(id);	
	}

	@Override
	public List<Task> getAllTasksForUser(Long id) {
		
		List<Task> tasks = null;
		tasks = taskRepository.findAllByUserId(id);
		return tasks;
	}

}
