package com.nitinagrawal.healthEndPoints;

import javax.persistence.Entity;

//This entity is used to provide the details about the feature.
//Design your entity as per the information you want to give out.
@Entity
public class Feature {
    private String id;
    private String name;
    
	public Feature(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
