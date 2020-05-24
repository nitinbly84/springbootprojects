package com.nitinagrawal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitinagrawal.entities.Course;
import com.nitinagrawal.entities.Lesson;
import com.nitinagrawal.entities.Topic;
import com.nitinagrawal.repositories.CourseLessonsMapper;
import com.nitinagrawal.repositories.CoursesRepository;
import com.nitinagrawal.repositories.TopicCourseMapper;

@Service
public class CourseService {
	
	@Autowired
	private TopicCourseMapper topicCourseMapper;
	@Autowired
	private CoursesRepository coursesRepository;
	@Autowired
	private CourseLessonsMapper courseLessonsMapper;
	
	public Set<Course> getAllCourses() {
		return coursesRepository.getAllCourses();
	}

	public Course getCourse(String id) {
		return coursesRepository.getCourse(id);
	}
	
	public List<Lesson> getLessonsForCourse(String id) {
		return new ArrayList<>();
	}
	
	public Course addCourse(Course Course) {
		return coursesRepository.addCourse(Course);
	}
	
	public boolean updateCourse(Course Course) {
		return coursesRepository.updateCourse(Course);
	}
	
	public boolean clearCourse(String courseId) {
		return topicCourseMapper.clearCourse(courseId);
	}
	
	public Set<Topic> getTopicsForCourse(String courseId) {
		return topicCourseMapper.getTopicsForCourse(courseId);
	}
	
	public boolean deleteCourse(String id) {
		return coursesRepository.deleteCourse(id);
	}
	
	public boolean addLessonToCourse(String courseId, String lessonId) {
		return true;
//		return courseLessonsMapper.addLessonToCourse(courseId, topicId);
	}
	
	public boolean deleteLessonFromCourse(String courseId, String lessonId) {
//		return courseLessonsMapper.deleteLessonFromCourse(courseId, topicId);
		return true;
	}
}
