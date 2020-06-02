package com.nitinagrawal.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.nitinagrawal.entities.Course;
import com.nitinagrawal.entities.Lesson;
import com.nitinagrawal.entities.Topic;
import com.nitinagrawal.services.CourseService;

@Profile({"devCourse", "dev"})
@RestController
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping("/courses")
	public ResponseEntity<Set<Course>> getAllCourses(HttpServletRequest request) {
		String pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		HttpHeaders headers = new HttpHeaders();
		headers.add("email : ", LinkCreator.getSelfMailLink(this.getClass(), pattern).toString());
		ResponseEntity<Set<Course>> response;
		HttpStatus status = HttpStatus.OK;
		Set<Course> allCourses = courseService.getAllCourses();
		allCourses.forEach(course -> {
			if(!course.hasLink("self"))
				course.add(LinkCreator.getSelfLink(this.getClass(), pattern, course.getId()));
		});
		//		response = new ResponseEntity<Set<Topic>>(allTopics, status);
		response = ResponseEntity.status(status)
								 .headers(headers)
								 .body(allCourses);
				return response;
	}

	@RequestMapping("/courses/{id}")
	public Course getCourse(@PathVariable String id) {
		Course course = courseService.getCourse(id);
		if(!course.hasLink("e-mail"))
			course.add(LinkCreator.getSelfMailLink(this.getClass(), "courses", id));
		if(!course.hasLink("lessons"))
			course.add(LinkCreator.getNextLink(this.getClass(), "courses", id, "lessons"));
		return course;
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
