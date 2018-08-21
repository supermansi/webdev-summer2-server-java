package com.example.webdevsummer2serverjava.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer2serverjava.models.EssayExamQuestion;
import com.example.webdevsummer2serverjava.models.Exam;
import com.example.webdevsummer2serverjava.models.MultipleChoiceExamQuestion;
import com.example.webdevsummer2serverjava.repositories.ExamRepository;
import com.example.webdevsummer2serverjava.repositories.MultipleChoiceRepository;

@RestController
@CrossOrigin(origins="*")
public class MultipleChoiceService {
	
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	MultipleChoiceRepository multipleChoiceRepository;
	
	@PostMapping("/api/exam/{examId}/choice")
	public void saveAssignment(@PathVariable("examId") int examId,
								@RequestBody MultipleChoiceExamQuestion choice) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam= data.get();
			choice.setExam(exam);
			multipleChoiceRepository.save(choice);
		}
	}

}
