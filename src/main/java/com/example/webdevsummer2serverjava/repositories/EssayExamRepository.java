package com.example.webdevsummer2serverjava.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer2serverjava.models.EssayExamQuestion;

public interface EssayExamRepository extends CrudRepository<EssayExamQuestion, Integer> {

}
