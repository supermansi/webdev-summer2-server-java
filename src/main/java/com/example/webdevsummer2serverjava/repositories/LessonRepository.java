package com.example.webdevsummer2serverjava.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer2serverjava.models.Course;

public interface LessonRepository extends CrudRepository<Course, Integer>{

}
