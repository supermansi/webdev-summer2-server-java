package com.example.webdevsummer2serverjava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer2serverjava.models.Lesson;
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

	@PostMapping("/api/topid/{topicId}/widget")
	public List<Widget> saveWidgets(@PathVariable("topicId") int topicId, 
									@RequestBody List<Widget> widgets) {
		List<Widget> savedWidgets = new ArrayList<Widget>();
		Optional<Topic> data = topicRepository.findById(topicId);
		System.out.println(widgets);
		widgetRepository.deleteAll();
		if(data.isPresent()) {
			Topic topic = data.get();
			for(Widget widget: widgets) {
				widget.setTopic(topic);
				savedWidgets.add(widgetRepository.save(widget));
			}
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
	
	@PutMapping("/api/widget/{widgetId}")
	public Widget updateWidget(@PathVariable("widgetId") int widgetId,@RequestBody Widget newWidget) {
		Optional<Widget> data= widgetRepository.findById(widgetId);
		if(data.isPresent()) {
			Widget widget = data.get();
			widget.setUrl(newWidget.getUrl());
			widget.setText(newWidget.getText());
			widget.setOrdered(newWidget.getOrdered());
			widget.setSize(newWidget.getSize());
			widget.setListItems(newWidget.getListItems());
			return widgetRepository.save(widget);
		}
		return null;
	}
	
	@PostMapping("api/topic/{topicId}/widget")
	public void saveAllWidgets(@PathVariable("topicId") int topicId,@RequestBody List<Widget> widgets) {
		Optional<Topic> data=topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic =data.get();
			List<Widget> widgetlist=topic.getWidgets();
			for(Widget widget:widgetlist) {
				widgetRepository.deleteById(widget.getId());
			}
		}
		for(Widget widget:widgets) {
			Optional<Topic> datatopic=topicRepository.findById(topicId);
			if(data.isPresent()) {
				Topic topic =datatopic.get();
				widget.setTopic(topic);
				widgetRepository.save(widget);
			}
		}
	}
	
	
}
