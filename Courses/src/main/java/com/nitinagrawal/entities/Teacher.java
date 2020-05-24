package com.nitinagrawal.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Teacher {
	
	@Id
	private String id;
	private String name;
	
	public Teacher() {
	}
	
	public Teacher(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Teacher(Teacher teacher) {
		if(teacher == null)
			return;
		this.id = teacher.id;
		this.name = teacher.name;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equalsIgnoreCase(((Teacher)obj).id);
	}
	
	@Override
	public String toString() {
		return id + " : " + name;
	}
}
