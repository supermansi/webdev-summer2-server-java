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

import com.example.webdevsummer2serverjava.models.Course;
import com.example.webdevsummer2serverjava.models.Module;
import com.example.webdevsummer2serverjava.repositories.CourseRepository;
import com.example.webdevsummer2serverjava.repositories.LessonRepository;
import com.example.webdevsummer2serverjava.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*")
public class ModuleService {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/module")
	public List<Module> findAllModules() {
		return (List<Module>) moduleRepository.findAll();
	}
	
	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(@PathVariable("courseId") int id,
			@RequestBody Module newModule) {
		Optional<Course> data = courseRepository.findById(id);
		if(data.isPresent()) {
			Course course = data.get();
			newModule.setCourse(course);
			return moduleRepository.save(newModule);
		}
		return null;
	}
	
	@GetMapping("/api/course/{courseId}/module")
	public List<Module> findAllModulesForCourse(@PathVariable("courseId") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			return course.getModules();
		}
		return null;
	}
	
	@DeleteMapping("/api/module/{mId}")
	public void deleteModule(@PathVariable("mId") int moduleId) {
		moduleRepository.deleteById(moduleId);
	}
}
