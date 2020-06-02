package com.nitinagrawal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description="Details about the individual Topic")
public class Topic extends RepresentationModel<Topic> {
	
	@Id
	@ApiModelProperty(value="Unique id of a topic", name="TopicID",
	notes="While submitting a Topic to the system for the addition, you can give any id. System will ignore that id while adding it to the system."
			+ "But give the correct id while searching for it in the system or using it for other purposes in the system.")
	private String id;
	@Column(nullable = false)
	private String name;
	private String description;
	
	public Topic() {
	}
	
	public Topic(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public Topic(Topic topic) {
		if(topic == null)
			return;
		this.id = topic.id;
		this.name = topic.name;
		this.description = topic.description;
	}

	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equalsIgnoreCase(((Topic)obj).id);
	}
	
	@Override
	public String toString() {
		return id + " : " + name + " : " + description;
	}
}
