package com.example.webdevsummer2serverjava.services;

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

import com.example.webdevsummer2serverjava.models.Exam;
import com.example.webdevsummer2serverjava.models.Topic;
import com.example.webdevsummer2serverjava.repositories.ExamRepository;
import com.example.webdevsummer2serverjava.repositories.TopicRepository;

@RestController
@CrossOrigin(origins="*")
public class ExamService {
	
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@GetMapping("/api/exam")
	public List<Exam> findAllExams() {
		return (List<Exam>) examRepository.findAll();
	}
	
	@GetMapping("/api/exam/{examId}")
	public Exam findExamById(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam= data.get();
			return exam;
		}
		return null;
	}
	
	@GetMapping("/api/topic/{topicId}/exam")
	public List<Exam> findAllExamsForTopic(@PathVariable("topicId") int topicId){ 
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			List<Exam> exam= topic.getExams();
			return exam;
		}
		return null;
	}
	
	@PostMapping("/api/topic/{topicId}/exam")
	public void saveExam(@PathVariable("topicId") int topicId,
								@RequestBody Exam exam) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			List<Exam> examList = topic.getExams();
			for(Exam e: examList) {
				examRepository.deleteById(e.getId());
			}
			exam.setTopic(topic);
			examRepository.save(exam);
		}
	}
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteWidget(@PathVariable("examId") int examId) { 
		examRepository.deleteById(examId);
	}

}
