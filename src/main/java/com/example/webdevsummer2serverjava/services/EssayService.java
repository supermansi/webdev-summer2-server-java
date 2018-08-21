package com.example.webdevsummer2serverjava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer2serverjava.models.EssayExamQuestion;
import com.example.webdevsummer2serverjava.models.Exam;
import com.example.webdevsummer2serverjava.repositories.EssayExamRepository;
import com.example.webdevsummer2serverjava.repositories.ExamRepository;


@RestController
@CrossOrigin(origins="*")
public class EssayService {
	
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	EssayExamRepository essayRepository;
	
	@PostMapping("/api/exam/{examId}/essay")
	public void saveAssignment(@PathVariable("examId") int examId,
								@RequestBody EssayExamQuestion essay) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam= data.get();
			essay.setExam(exam);
			essayRepository.save(essay);
		}
	}
}
