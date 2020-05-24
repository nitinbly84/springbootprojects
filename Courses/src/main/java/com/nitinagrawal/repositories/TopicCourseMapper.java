package com.nitinagrawal.repositories;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.nitinagrawal.entities.Course;
import com.nitinagrawal.entities.Topic;

@Repository
public class TopicCourseMapper {
	
	private Set<Topic> topics;
	private Set<Course> courses;
	private Map<Topic, Set<Course>> topicCourses;
	private Map<Course, Set<Topic>> courseTopics;
	private TopicsRepository topicsRepository;
	private CoursesRepository coursesRepository;
	
	public TopicCourseMapper() {
		courses = new CoursesRepository().getAllCourses();
		topics = new TopicsRepository().getAllTopics();
		topicCourses = Database.getTopicsCourse();
		courseTopics = Database.getCourseTopics();
	}
	
	public List<Course> getCoursesForTopic(String id) {
		if(id == null || id.isEmpty())
			return new ArrayList<>();
		List<Course> courses = topicCourses.keySet()
							               .stream()
							               .filter(a -> id.equalsIgnoreCase(a.getId()))
							               .flatMap(topic -> topicCourses.get(topic).stream())
							               .collect(toList());
		return Collections.unmodifiableList(courses);
	}
	
	// NullPointer needs to be tested
	public boolean addCourseToTopic(String courseId, String topicId) {
		if((courseId == null || courseId.isEmpty()) || (topicId == null || topicId.isEmpty()))
			return false;
		final Topic topic = topics.stream()
							.filter(t -> topicId.equalsIgnoreCase(t.getId()))
							.findFirst()
							.orElse(TopicsRepository.getEmptyTopic());
		Set<Course> oldCourses = topicCourses.get(topic);
		courses.stream()
		       .filter(course -> courseId.equalsIgnoreCase(course.getId()))
		       .findFirst()
		       .ifPresent(course -> {
							    	   Set<Topic> topics;
							    	   if((topics = courseTopics.get(course)) == null) {
							    		   topics = new HashSet<>();
							    		   courseTopics.put(course, topics);
							    	   }
							    	   topics.add(topic);
							    	   oldCourses.add(course);
		       			});
		return true;
	}
	
	public boolean deleteCourseFromTopic(String courseId, String topicId) {
		if(courseId == null || courseId.isEmpty() || topicId == null || topicId.isEmpty())
			return false;
		Course delCourse = courses.stream()
								  .filter(course -> courseId.equalsIgnoreCase(course.getId()))
								  .findFirst()
								  .orElseGet(CoursesRepository::getEmptyCourse);
		if(delCourse.getId() == null)
			return false;
		courseTopics.get(delCourse)
					.stream()
					.flatMap(id -> topics.stream().filter(topic -> topic.getId().equalsIgnoreCase(topicId)))
					.map(topic -> topicCourses.get(topic))
					.forEach(list -> list.remove(delCourse));
		return true;
	}
	
	public boolean clearTopic(String topicId) {
		if(topicId == null || topicId.isEmpty())
			return false;
		Topic delTopic = topics.stream()
							   .filter(topic -> topicId.equalsIgnoreCase(topic.getId()))
							   .findFirst()
							   .orElseGet(TopicsRepository::getEmptyTopic);
		topicCourses.remove(delTopic)
			        .stream()
			        .map(course -> courseTopics.get(course))
			        .forEach(topics -> topics.remove(topicId));
		topicCourses.put(delTopic, new HashSet<>());
		return true;
	}
	
	public boolean clearCourse(String courseId) {
		if(courseId == null || courseId.isEmpty())
			return false;
		Course delCourse = courses.stream()
							      .filter(course -> courseId.equalsIgnoreCase(course.getId()))
							      .findFirst()
							      .orElse(CoursesRepository.getEmptyCourse());
		courseTopics.remove(delCourse)
					.stream()
					.map(topic -> topicCourses.get(topic))
					.forEach(courses -> courses.remove(delCourse.getId()));
		courseTopics.put(delCourse, new HashSet<>());		
		return true;
	}
	
	public boolean addTopicToMap(Topic topic) {
		topicCourses.put(topic, new HashSet<>());
		return true;
	}
	
	public boolean delTopicFromMap(Topic topic) {
		if(topic == null)
			return false;
		topicCourses.remove(topic)
			        .stream()
			        .map(course -> courseTopics.get(course))
			        .forEach(topics -> topics.remove(topic));
		return true;
	}
	
	public boolean delCourseFromMap(Course course) {
		courseTopics.remove(course)
			        .stream()
			        .map(topic -> topicCourses.get(topic))
			        .forEach(topics -> topics.remove(course));
		return true;
	}
	
	public boolean replaceTopic(String oldTopicId, String newTopicId) {
		Topic oldTopic = topicsRepository.getTopic(oldTopicId);
		Topic newTopic = topicsRepository.getTopic(newTopicId);
		if(oldTopic.getId() == null || newTopic.getId() == null)
			return false;
		Set<Course> courses = topicCourses.remove(oldTopic);
		topicCourses.put(newTopic, courses);
		courses.stream()
		       .map(course -> courseTopics.get(course))
		       .forEach(set -> {set.remove(oldTopic); set.add(newTopic);});
		return true;
	}
	
	public boolean replaceCourse(String oldCourseId, String newCourseId) {
		Course oldCourse = coursesRepository.getCourse(oldCourseId);
		Course newCourse = coursesRepository.getCourse(newCourseId);
		if(oldCourse.getId() == null || newCourse.getId() == null)
			return false;
		Set<Topic> topics = courseTopics.remove(oldCourse);
		courseTopics.put(newCourse, topics);
		topics.stream()
		      .map(topic -> topicCourses.get(topic))
		      .forEach(set -> {set.remove(oldCourse); set.add(newCourse);});		
		return true;
	}
	
	public Set<Topic> getTopicsForCourse(String courseId) {
		if(courseId == null || courseId.isEmpty())
			return new HashSet<>();
		Set<Topic> topics = courses.stream()
							       .filter(course -> course.getId().equalsIgnoreCase(courseId))
							       .findFirst()
							       .map(course -> courseTopics.get(course))
							       .orElse(new HashSet<>());
		return Collections.unmodifiableSet(topics);
	}
}
