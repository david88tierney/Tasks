package com.codingdojo.exam.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;



@Entity
@Table(name="tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1, max=255, message="A Task is Required")
	private String name;
	
	
	@Valid
	private String priority;
	


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="assignee_id")
	private User assignee;
	
	
	public Task(){
		
	}
	
	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public String getPriority() {return priority;}

	public void setPriority(String priority) {this.priority = priority;}
	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public String getName() {return name;}

	public void setName(String name) {this.name = name;}

	public User getUser() {return user;}

	public void setUser(User user) {this.user = user;}


}
