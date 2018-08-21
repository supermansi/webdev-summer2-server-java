package com.example.webdevsummer2serverjava.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer2serverjava.models.MultipleChoiceExamQuestion;

public interface MultipleChoiceRepository extends CrudRepository<MultipleChoiceExamQuestion, Integer> {

}
