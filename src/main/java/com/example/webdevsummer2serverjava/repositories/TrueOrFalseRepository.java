package com.example.webdevsummer2serverjava.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer2serverjava.models.TrueOrFalseExamQuestion;

public interface TrueOrFalseRepository extends CrudRepository<TrueOrFalseExamQuestion,  Integer> {

}
