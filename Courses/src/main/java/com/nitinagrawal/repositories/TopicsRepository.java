package com.nitinagrawal.repositories;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nitinagrawal.entities.Topic;

@Repository
public class TopicsRepository {
	
	private static int counter = 1;
	private Set<Topic> topics;
	@Autowired
	private TopicCourseMapper topicCourseMapper;
	
	public TopicsRepository() {
		topics = Database.getTopics();
	}

	public Set<Topic> getAllTopics() {
		return Collections.unmodifiableSet(topics);
	}
	
	public Topic getTopic(String id) {
		Topic topic = 
				topics.stream()
				      .filter(a -> a.getId().equalsIgnoreCase(id))
				      .findFirst()
					  .orElseGet(TopicsRepository::getEmptyTopic);
		return topic;
	}
	
	public Topic addTopic(Topic topic) {
		if(topic == null)
			return getEmptyTopic();
		String id = topic.getName()+counter;
		counter++;
		topic = new Topic(id, topic.getName(), topic.getDescription());
		topics.add(topic);
		topicCourseMapper.addTopicToMap(topic);
		return topic;
	}
	
	public boolean deleteTopic(String topicId) {
		Topic delTopic = topics.stream()
							   .filter(topic -> topic.getId().equalsIgnoreCase(topicId))
							   .findFirst()
							   .orElse(getEmptyTopic());
		if(delTopic == null || delTopic.getId() == null)
			return false;
		topics.remove(delTopic);
		return topicCourseMapper.delTopicFromMap(delTopic);
	}
	
	public boolean updateTopic(Topic updatedTopic) {
		if(updatedTopic == null)
			return false;
		Topic existingTopic = topics.stream()
									.filter(topic -> topic.getId().equalsIgnoreCase(updatedTopic.getId()))
									.findFirst()
									.orElse(getEmptyTopic());
		if(existingTopic == null || existingTopic.getId() == null)
			return false;
		topics.remove(existingTopic);
		Topic newTopic = addTopic(updatedTopic);
		return topicCourseMapper.replaceTopic(existingTopic.getId(), newTopic.getId());
	}
	
	public static Topic getEmptyTopic() {
		return new Topic();
	}
}
