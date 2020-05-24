package com.nitinagrawal.repositories;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nitinagrawal.entities.Course;

@Repository
public class CoursesRepository {
	
	private static int counter = 1;
	private Set<Course> courses;
	@Autowired
	private TopicCourseMapper topicCourseMapper;
	
	public CoursesRepository() {
		courses = Database.getCourses();
		}

	public Set<Course> getAllCourses() {
		return Collections.unmodifiableSet(courses);
	}
	
	public Course getCourse(String id) {
		Course course = 
				courses.stream()
				       .filter(a -> a.getId().equalsIgnoreCase(id))
				       .findFirst()
				       .orElseGet(CoursesRepository::getEmptyCourse);
		return course;
	}
	
	public Course addCourse(Course course) {
		if(course == null)
			return getEmptyCourse();
		String id = course.getName()+counter;
		counter++;
		course = new Course(id, course.getName(), course.getDescription(), course.getPrice());
		courses.add(course);
		return course;
	}
	
	public boolean deleteCourse(String courseId) {
		Course delCourse = courses.stream()
								  .filter(course -> course.getId().equalsIgnoreCase(courseId))
								  .findFirst()
								  .orElse(getEmptyCourse());
		if(delCourse == null || delCourse.getId() == null)
			return false;
		courses.remove(delCourse);
		topicCourseMapper.delCourseFromMap(delCourse);
		return true;
	}
	
	public boolean updateCourse(Course updatedCourse) {
		if(updatedCourse == null)
			return false;
		Course existingCourse = courses.stream()
								       .filter(course -> course.getId().equalsIgnoreCase(updatedCourse.getId()))
								       .findFirst()
								       .orElse(getEmptyCourse());
		if(existingCourse == null || existingCourse.getId() == null)
			return false;
		courses.remove(existingCourse);
		Course newCourse = addCourse(updatedCourse);
		return topicCourseMapper.replaceCourse(existingCourse.getId(), newCourse.getId());		
	}
	
	public static Course getEmptyCourse() {
		return new Course();
	}
}
