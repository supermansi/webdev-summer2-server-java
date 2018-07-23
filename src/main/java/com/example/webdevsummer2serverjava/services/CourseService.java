package com.example.webdevsummer2serverjava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer2serverjava.models.Course;
import com.example.webdevsummer2serverjava.repositories.CourseRepository;

@RestController
@CrossOrigin(origins="*")
public class CourseService {
	
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/course")
	public List<Course> findAllCourses() {
		return (List<Course>) courseRepository.findAll();
	}

	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course){
/*		if(course.getTitle().isEmpty()) {
			System.out.println(course.getTitle());
			course.setTitle("New Course");
		}*/
		return  courseRepository.save(course);
	}
	
	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") int id) {
		courseRepository.deleteById(id);
	}
	
	@GetMapping("/api/course/{id}")
	public Course findCourseById(@PathVariable("id") int id) {
		Optional<Course> data = courseRepository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@PutMapping("/api/course")
	public Course updateCourse(@RequestBody Course newcourse) {
		 Optional<Course> data = courseRepository.findById(newcourse.getId());
		 if(data.isPresent()) {
			 Course course = data.get();
			 course.setTitle(newcourse.getTitle());
			 course.setModified(newcourse.getModified());
			 courseRepository.save(course);
			 return newcourse;
		 }
		 return null;
	}
}
