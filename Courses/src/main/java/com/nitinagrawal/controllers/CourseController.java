package com.nitinagrawal.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nitinagrawal.entities.Course;
import com.nitinagrawal.entities.Lesson;
import com.nitinagrawal.entities.Topic;
import com.nitinagrawal.services.CourseService;

@RestController
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping("/courses")
	public Set<Course> getAllCourses() {
		return courseService.getAllCourses();
	}

	@RequestMapping("/courses/{id}")
	public Course getTopic(@PathVariable String id) {
		return courseService.getCourse(id);
	}
	
	@RequestMapping("/courses/{courseId}/lessons")
	public List<Lesson> getLessonsForCourse(@PathVariable("courseId") String id) {
		return courseService.getLessonsForCourse(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/courses")
	public Course addCourse(@RequestBody Course Course) {
		return courseService.addCourse(Course);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/courses/{courseId}")
	public String updateCourse(@PathVariable String courseId, @RequestBody Course Course) {
		if(Course.getId().equalsIgnoreCase(courseId) && courseService.updateCourse(Course))
			return "Success";
		return "Failed!!! Check the data again";
	}
	
	@RequestMapping("/courses/{courseId}/topics")
	public Set<Topic> getTopicsForCourse(@PathVariable String courseId) {
		return courseService.getTopicsForCourse(courseId);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/courses/{courseId}/clear")
	public boolean clearCourse(@PathVariable String courseId) {
		return courseService.clearCourse(courseId);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/courses/{courseId}")
	public boolean deleteCourse(@PathVariable("courseId") String id) {
		return courseService.deleteCourse(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/courses/{courseId}/lessons/{lessonId}")
	public boolean addLessonToCourse(@PathVariable String courseId, @PathVariable String lessonId) {
		return courseService.addLessonToCourse(courseId, lessonId);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/courses/{courseId}/lessons/{lessonId}")
	public boolean deleteLessonFromCourse(@PathVariable String courseId, @PathVariable String lessonId) {
		return courseService.deleteLessonFromCourse(courseId, lessonId);
	}
}
