package com.nitinagrawal.repositories;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.nitinagrawal.entities.Course;
import com.nitinagrawal.entities.Lesson;
import com.nitinagrawal.entities.Topic;

public class Database {
	private static Set<Course> courses;
	private static Set<Topic> topics;
	private static Map<Topic, Set<Course>> topicCourses;
	private static Map<Course, Set<Topic>> courseTopics;
	private static Map<Course, Set<Lesson>> courseLessons;
	private static Map<Lesson, Set<Course>> lessonCourses;
	
	static {
		courses = new HashSet<>();
		topics = new HashSet<>();
		topicCourses = new HashMap<>();
		courseTopics = new HashMap<>();
	}
	
	public static Set<Topic> getTopics() {
		return topics;
	}
	
	public static Set<Course> getCourses() {
		return courses;
	}
	
	public static Map<Topic, Set<Course>> getTopicsCourse() {
		return topicCourses;
	}
	
	public static Map<Course, Set<Topic>> getCourseTopics() {
		return courseTopics;
	}
}
