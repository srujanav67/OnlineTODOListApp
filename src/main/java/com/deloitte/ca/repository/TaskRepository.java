package com.deloitte.ca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deloitte.ca.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	List<Task> findAllByUserId(Long userId);
}
