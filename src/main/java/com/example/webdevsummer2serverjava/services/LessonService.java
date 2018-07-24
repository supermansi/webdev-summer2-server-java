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

import com.example.webdevsummer2serverjava.models.Lesson;
import com.example.webdevsummer2serverjava.models.Module;
import com.example.webdevsummer2serverjava.repositories.CourseRepository;
import com.example.webdevsummer2serverjava.repositories.LessonRepository;
import com.example.webdevsummer2serverjava.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins="*")
public class LessonService {
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/lesson")
	public Iterable<Lesson> findAllLessons() {
		return (Iterable<Lesson>) lessonRepository.findAll();
	}
	
	@DeleteMapping("/api/lesson/{lId}")
	public void deleteLesson(@PathVariable("lId") int lessonId) {
		lessonRepository.deleteById(lessonId);
	}
	
	@PostMapping("/api/course/{cid}/module/{mid}/lesson")
	public Lesson createLesson(@PathVariable("cid") int courseId,
			@PathVariable("mid") int moduleId,
			@RequestBody Lesson lesson) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			lesson.setModule(module);
			return lessonRepository.save(lesson);
		}
		return null;
	}
	
	@GetMapping("/api/course/{cid}/module/{mid}/lesson")
	public Iterable<Lesson> findAllLessonsForModule(@PathVariable("cid") int courseId,
			@PathVariable("mid") int moduleId) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			return module.getLessons();
		}
		return null;
	}
	
}
