package com.nitinagrawal.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nitinagrawal.entities.Course;
import com.nitinagrawal.entities.Topic;
import com.nitinagrawal.services.TopicService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	@RequestMapping(method=RequestMethod.GET, value="/topics", produces="application/json")
	// Check the options available in below annotation, to provide
	// the relevant information in the documentation.
	@ApiOperation(value="Get all topics.",
	              notes="Fetches all the topics present in the system.",
	              responseContainer="Set of Topic.class")//We can't use response here as it is a container & Swagger will lose the information about the type of elements in it.
	public Set<Topic> getAllTopics() {
		return topicService.getAllTopics();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/topics/{id}", produces="application/json")
	@ResponseBody
	@ApiOperation(value="Get the Topic details.",
			      notes="Get the details of the topic having given TopicID.",
			      response=Topic.class) 
	public Topic getTopic(@ApiParam(name="Topic ID", value="Topic ID of the topic for which you need details.") @PathVariable String id) {
		return topicService.getTopic(id);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/topics/{topicId}/courses")
	public List<Course> getCoursesForTopic(@PathVariable("topicId") String id) {
		return topicService.getCoursesForTopic(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics")
	public Topic addTopic(@RequestBody Topic topic) {
		return topicService.addTopic(topic);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{topicId}", consumes="application/json")
	public String updateTopic(@PathVariable String topicId, @RequestBody(required=true) Topic topic) {
		if(topic.getId().equalsIgnoreCase(topicId) && topicService.updateTopic(topic))
			return "Success";
		return "Failed!!! Check the data again";
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{topicId}/clear")
	public boolean clearTopic(@PathVariable String topicId) {
		return topicService.clearTopic(topicId);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{topicId}")
	public boolean deleteTopic(@PathVariable("topicId") String id) {
		return topicService.deleteTopic(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{topicId}/courses/{courseId}")
	public boolean addCourseToTopic(@PathVariable String topicId, @PathVariable String courseId) {
		return topicService.addCourseToTopic(topicId, courseId);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{topicId}/courses/{courseId}")
	public boolean deleteCourseFromTopic(@PathVariable String courseId, @PathVariable String topicId) {
		return topicService.deleteCourseFromTopic(courseId, topicId);
	}
}
