package com.example.webdevsummer2serverjava.models;

import javax.persistence.Entity;

@Entity
public class TrueOrFalseExamQuestion extends ExamQuestion{
	
	private boolean isTrue;

	public boolean isTrue() {
		return isTrue;
	}

	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}

}
