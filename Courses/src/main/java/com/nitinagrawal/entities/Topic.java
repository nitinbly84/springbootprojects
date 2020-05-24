package com.nitinagrawal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Topic {
	
	@Id
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
