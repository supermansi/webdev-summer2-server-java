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

import com.example.webdevsummer2serverjava.models.Assignment;
import com.example.webdevsummer2serverjava.models.Topic;
import com.example.webdevsummer2serverjava.repositories.AssignmentRepository;
import com.example.webdevsummer2serverjava.repositories.TopicRepository;

@RestController
@CrossOrigin(origins="*")
public class AssignmentService {
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@GetMapping("/api/assignment")
	public List<Assignment> findAllAssignments() {
		return (List<Assignment>) assignmentRepository.findAll();
	}
	
	@GetMapping("/api/assignment/{aid}")
	public Assignment findAssignmentById(@PathVariable("aid") int assignmentId) {
		Optional<Assignment> data = assignmentRepository.findById(assignmentId);
		if(data.isPresent()) {
			Assignment assignment = data.get();
			return assignment;
		}
		return null;
	}
	
	@GetMapping("/api/topic/{topicId}/assignment")
	public List<Assignment> findAssignmentByTopicId(@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			List<Assignment> assignment = topic.getAssignments();
			return assignment;
		}
		return null;
	}
	
	@PostMapping("/api/topic/{topicId}/assignment")
	public void saveAssignment(@PathVariable("topicId") int topicId,
								@RequestBody Assignment assignment) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			List<Assignment> assignmentList = topic.getAssignments();
			for(Assignment a: assignmentList) {
				assignmentRepository.deleteById(a.getId());
			}
			assignment.setTopic(topic);
			assignmentRepository.save(assignment);
		}
	}
	
	@DeleteMapping("/api/assignment/{aid}")
	public void deleteWidget(@PathVariable("aid") int aid) { 
		assignmentRepository.deleteById(aid);
	}
}
