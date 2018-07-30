package com.example.webdevsummer2serverjava.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Widget {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private String widgetType;
	
	// List Widget
	private String listItems;
	private String ordered;
	
	// Heading Widget
	private int size;
	private String text; // Link, Paragraph Widget
	
	// Link, Image Widget
	private String url;
	
	@ManyToOne
	@JsonIgnore
	private Topic topic;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getListItems() {
		return listItems;
	}
	public void setListItems(String listItems) {
		this.listItems = listItems;
	}
	
	public String getOrdered() {
		return ordered;
	}
	public void setOrdered(String ordered) {
		this.ordered = ordered;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public String getWidgetType() {
		return widgetType;
	}
	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
