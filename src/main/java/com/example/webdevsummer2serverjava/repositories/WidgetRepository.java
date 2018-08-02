package com.example.webdevsummer2serverjava.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer2serverjava.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {

}
