package com.deloitte.ca.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "tasks")
public class Task {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "task_name", nullable = false)
	private String taskname;
	
	private Boolean isTaskCompleted;
	
	@CreationTimestamp
	@Column(name = "created_date")
	private Date created;
	
	@UpdateTimestamp
	@Column(name = "updated_date")
	private Date updated;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Task() {
		
	}

	public Task(String taskname, Boolean isTaskCompleted, Date created, Date updated, User user) {
		super();
		this.taskname = taskname;
		this.isTaskCompleted = isTaskCompleted;
		this.created = created;
		this.updated = updated;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	
	public Boolean getIsTaskCompleted() {
		return isTaskCompleted;
	}

	public void setIsTaskCompleted(Boolean isTaskCompleted) {
		this.isTaskCompleted = isTaskCompleted;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
