package com.nitinagrawal.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitinagrawal.entities.Course;
import com.nitinagrawal.entities.Topic;
import com.nitinagrawal.repositories.TopicCourseMapper;
import com.nitinagrawal.repositories.TopicsRepository;

@Service
public class TopicService {
	
	@Autowired
	private TopicsRepository topicsRepository;
	@Autowired
	private TopicCourseMapper topicCourseMapper;
	
	public Set<Topic> getAllTopics() {
		return topicsRepository.getAllTopics();
	}
	
	public Topic getTopic(String id) {
		return topicsRepository.getTopic(id);
	}
	
	public List<Course> getCoursesForTopic(String id) {
		return topicCourseMapper.getCoursesForTopic(id);
	}
	
	public Topic addTopic(Topic topic) {
		return topicsRepository.addTopic(topic);
	}
	
	public boolean updateTopic(Topic topic) {
		return topicsRepository.updateTopic(topic);
	}
	
	public boolean clearTopic(String topicId) {
		return topicCourseMapper.clearTopic(topicId);
	}
	
	public boolean deleteTopic(String id) {
		return topicsRepository.deleteTopic(id);
	}
	
	public boolean addCourseToTopic(String topicId, String courseId) {
		return topicCourseMapper.addCourseToTopic(courseId, topicId);
	}
	
	public boolean deleteCourseFromTopic(String courseId, String topicId) {
		return topicCourseMapper.deleteCourseFromTopic(courseId, topicId);
	}

}
