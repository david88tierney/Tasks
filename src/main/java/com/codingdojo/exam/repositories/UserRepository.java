package com.codingdojo.exam.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.exam.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
	List<User> findAll();
	User findByEmailAndPassword(String email, String password);
	Optional<User> findById(Long id);
	User findByName(String name);
}