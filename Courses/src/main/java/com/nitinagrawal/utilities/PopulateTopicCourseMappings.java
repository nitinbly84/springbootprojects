package com.nitinagrawal.utilities;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitinagrawal.entities.Course;
import com.nitinagrawal.entities.Topic;
import com.nitinagrawal.repositories.CoursesRepository;
import com.nitinagrawal.repositories.TopicCourseMapper;
import com.nitinagrawal.repositories.TopicsRepository;

@Service
public class PopulateTopicCourseMappings {

	@Autowired
	private CoursesRepository coursesRepository;
	@Autowired
	private TopicsRepository topicsRepository;
	@Autowired
	private TopicCourseMapper topicCourseMapper;

	public void setTopicCourseMappings() {
		Set<Course> populateCourses = coursesRepository.getAllCourses();
		Set<Topic> populateTopics = topicsRepository.getAllTopics();
		for(Topic topic : populateTopics) {
			for(Course course : populateCourses) {
				if(course.getName().endsWith(topic.getName())) {
					topicCourseMapper.addCourseToTopic(course.getId(), topic.getId());
				}
			}
		}
	}
}
