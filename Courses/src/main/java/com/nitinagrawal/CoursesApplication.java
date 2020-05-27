package com.nitinagrawal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.SpringVersion;

import com.nitinagrawal.utilities.CourseGenerator;
import com.nitinagrawal.utilities.PopulateTopicCourseMappings;
import com.nitinagrawal.utilities.TopicsGenerator;

// localhost:8080/swagger-ui.html
@ComponentScan({"com.nitinagrawal.repositories", "com.nitinagrawal.utilities", "com.nitinagrawal.services",
	            "com.nitinagrawal.controllers", "com.nitinagrawal.swagger", "com.nitinagrawal.security",
	            "com.nitinagrawal.healthEndPoints"})
@EntityScan("com.nitinagrawal.entities")
@SpringBootApplication
public class CoursesApplication {

	@Autowired
	private TopicsGenerator topicsGenerator;
	@Autowired
	private CourseGenerator courseGenerator;
	@Autowired
	private PopulateTopicCourseMappings populateTopicCourseMappings;
	
	public static void main(String[] args) {
		System.out.println("Spring version being used in Application......."+SpringVersion.getVersion());
		SpringApplication.run(CoursesApplication.class, args);
	}
	
	@PostConstruct
	private void initailizer() {
		topicsGenerator.generate();
		courseGenerator.generate();
		populateTopicCourseMappings.setTopicCourseMappings();
	}
}
