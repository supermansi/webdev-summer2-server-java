package com.example.webdevsummer2serverjava.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer2serverjava.models.Topic;
import com.example.webdevsummer2serverjava.models.Widget;
import com.example.webdevsummer2serverjava.repositories.TopicRepository;
import com.example.webdevsummer2serverjava.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class WidgetService {
	
	@Autowired
	WidgetRepository widgetRepository;
	
	@Autowired
	TopicRepository topicRepository;

	@PostMapping("/api/widget")
	public List<Widget> saveWidgets(@RequestBody List<Widget> widgets) {
		List<Widget> savedWidgets = new ArrayList<Widget>();
		System.out.println(widgets);
		widgetRepository.deleteAll();
		for(Widget widget: widgets) {
			savedWidgets.add(widgetRepository.save(widget));
		}
		return savedWidgets;
	}
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}
	
	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if(data.isPresent()) {
			Widget widget = data.get();
			return widget;
		}
		return null;
	}
	
	@GetMapping("/api/topic/{topidId}/widget")
	public List<Widget> findWidgetByTopicId(@PathVariable("topicId") int topicId){
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			List<Widget> widgets = topic.getWidgets();
			//Collections.sort(widgets);
			return widgets;
		}
		return null;
	}
		
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId) {
		widgetRepository.deleteById(widgetId);
	}	
}
