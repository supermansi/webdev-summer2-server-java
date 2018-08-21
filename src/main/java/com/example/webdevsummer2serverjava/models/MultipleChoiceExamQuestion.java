package com.example.webdevsummer2serverjava.models;

import javax.persistence.Entity;

@Entity
public class MultipleChoiceExamQuestion extends ExamQuestion{

	private String options;
	private String answer;
	
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
