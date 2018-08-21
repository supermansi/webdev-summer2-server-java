package com.example.webdevsummer2serverjava.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer2serverjava.models.Exam;
import com.example.webdevsummer2serverjava.models.FillInTheBlanksExamQuestion;
import com.example.webdevsummer2serverjava.repositories.ExamRepository;
import com.example.webdevsummer2serverjava.repositories.FillInTheBlanksRepository;

@RestController
@CrossOrigin(origins="*")
public class FillInTheBlanksService {
	
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	FillInTheBlanksRepository blanksRepository;
	
	@PostMapping("/api/exam/{examId}/blanks")
	public void saveAssignment(@PathVariable("examId") int examId,
								@RequestBody FillInTheBlanksExamQuestion blanks) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam= data.get();
			blanks.setExam(exam);
			blanksRepository.save(blanks);
		}
	}

}
