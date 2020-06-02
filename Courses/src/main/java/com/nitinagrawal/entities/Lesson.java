package com.nitinagrawal.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

@Entity
public class Lesson extends RepresentationModel<Lesson> {
	
	@Id
	private String id;
	private String name;
	private String agenda;
	
	public Lesson() {
	}
	
	public Lesson(String id, String name, String agenda) {
		this.id = id;
		this.name = name;
		this.agenda = agenda;
	}
	
	public Lesson(Lesson lesson) {
		if(lesson == null)
			return;
		this.id = lesson.id;
		this.name = lesson.name;
		this.agenda = lesson.agenda;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getAgenda() {
		return agenda;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equalsIgnoreCase(((Lesson)obj).id);
	}
	
	@Override
	public String toString() {
		return id + " : " + name + " : " + agenda;
	}
}
