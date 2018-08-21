package com.example.webdevsummer2serverjava.models;

import javax.persistence.Entity;

@Entity
public class FillInTheBlanksExamQuestion extends ExamQuestion{
	
	private String variable;
	private String value;
	
	public String getVariable() {
		return variable;
	}
	public void setVariable(String variable) {
		this.variable = variable;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
