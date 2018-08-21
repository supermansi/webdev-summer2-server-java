package com.example.webdevsummer2serverjava.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer2serverjava.models.Exam;
import com.example.webdevsummer2serverjava.models.TrueOrFalseExamQuestion;
import com.example.webdevsummer2serverjava.repositories.ExamRepository;
import com.example.webdevsummer2serverjava.repositories.TrueOrFalseRepository;

@RestController
@CrossOrigin(origins="*")
public class TrueFalseService {
	
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	TrueOrFalseRepository trueFalseRepository;
	
	@PostMapping("/api/exam/{examId}/truefalse")
	public void saveAssignment(@PathVariable("examId") int examId,
								@RequestBody TrueOrFalseExamQuestion truefalse) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam= data.get();
			truefalse.setExam(exam);
			trueFalseRepository.save(truefalse);
		}
	}

}
