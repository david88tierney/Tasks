package com.codingdojo.exam.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.exam.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{
	Task findByName(String name);
}
