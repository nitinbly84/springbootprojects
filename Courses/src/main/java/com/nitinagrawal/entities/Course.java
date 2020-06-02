package com.nitinagrawal.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

@Entity
public class Course extends RepresentationModel<Course> {
	
	@Id
	private String id;
	private String name;
	private String description;
	private float price;
	
	public Course() {
	}
	
	public Course(String id, String name, String description, float price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public Course(Course course) {
		if(course == null)
			return;
		this.id = course.id;
		this.name = course.name;
		this.description = course.description;
		this.price = course.price;
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

	public float getPrice() {
		return price;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equalsIgnoreCase(((Course)obj).id);
	}
	
	@Override
	public String toString() {
		return id + " : " + name + " : " + description;
	}
}
