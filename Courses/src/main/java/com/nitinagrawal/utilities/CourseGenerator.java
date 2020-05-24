package com.nitinagrawal.utilities;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitinagrawal.entities.Course;
import com.nitinagrawal.repositories.CoursesRepository;

@Service
public class CourseGenerator {

	private String[] data = new String[3];
	private int i = 0;

	@Autowired
	private CoursesRepository coursesRepository;
	
	public void generate() {
		readCourseData();
	}
	
	private void readCourseData() {
		String topicFile = "/resources/courses";
		try (Stream<String> stream = Files.lines(Paths.get(CourseGenerator.class.getResource(topicFile).toURI()))) {
			stream.forEach(row -> loadCourse(row));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadCourse(String row) {
		if(row.contains("-----------")) {
			coursesRepository.addCourse(new Course("1", data[0], data[1], Float.parseFloat(data[2])));
			data = new String[3];
			i = 0;
			return;
		}
		data[i] = row;
		i++;
	}
}
