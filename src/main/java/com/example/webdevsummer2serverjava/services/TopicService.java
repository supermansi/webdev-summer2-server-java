package com.example.webdevsummer2serverjava.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer2serverjava.models.Lesson;
import com.example.webdevsummer2serverjava.models.Topic;
import com.example.webdevsummer2serverjava.repositories.CourseRepository;
import com.example.webdevsummer2serverjava.repositories.LessonRepository;
import com.example.webdevsummer2serverjava.repositories.ModuleRepository;
import com.example.webdevsummer2serverjava.repositories.TopicRepository;

@RestController
@CrossOrigin(origins="*")
public class TopicService {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@GetMapping("/api/topic")
	public Iterable<Topic> findAllTopics() {
		return (Iterable<Topic>) topicRepository.findAll();
	}
	
	@DeleteMapping("/api/topic/{tid}")
	public void deleteTopic(@PathVariable("tid") int topicId) {
		topicRepository.deleteById(topicId);
	}
	
	@PostMapping("/api/course/{cid}/module/{mid}/lesson/{lid}/topic")
	public Topic createTopic(@PathVariable("cid") int courseId,
			@PathVariable("mid") int moduleId,
			@PathVariable("lid") int lessonId,
			@RequestBody Topic topic) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			topic.setLesson(lesson);
			return topicRepository.save(topic);
			
		}
		return null;
	}
	
	@GetMapping("/api/course/{cid}/module/{mid}/lesson/{lid}")
	public Iterable<Topic> findAllTopicsForLesson(@PathVariable("lid") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			return lesson.getTopics();
		}
		return null;
	}
	
}
