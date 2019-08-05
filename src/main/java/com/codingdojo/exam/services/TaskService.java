package com.codingdojo.exam.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.codingdojo.exam.models.Task;
import com.codingdojo.exam.repositories.TaskRepository;

@Service
public class TaskService {
	
	private TaskRepository tR;
	
	public TaskService(TaskRepository tR) {
		this.tR = tR;
	}
	
	public Task create(Task task) {return tR.save(task);}
	
	public ArrayList<Task> findAll() {return (ArrayList<Task>) tR.findAll();}
	
	public Task findById(Long id) {return tR.findById(id).get();}
	
	public Task update(Task task) {return tR.save(task);}
	
	public void destroy(Long id) {tR.deleteById(id);}
	
	public Task findName(String name) {
		return tR.findByName(name);
	}
	
	
	

}
