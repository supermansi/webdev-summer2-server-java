package com.example.webdevsummer2serverjava.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer2serverjava.models.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
}