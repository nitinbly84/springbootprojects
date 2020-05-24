package com.nitinagrawal.utilities;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.nitinagrawal.entities.Topic;
import com.nitinagrawal.repositories.TopicsRepository;

@Service
@Configuration
public class TopicsGenerator {

	private String[] data = new String[2];
	private int i = 0;
	
	@Autowired
	private TopicsRepository topicsRepository;
	
	public void generate() {
		readTopicData();
	}
	
	private void readTopicData() {
		String topicFile = "/resources/topics";
		try (Stream<String> stream = Files.lines(Paths.get(TopicsGenerator.class.getResource(topicFile).toURI()))) {
			stream.forEach(row -> loadTopic(row));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadTopic(String row) {
		if(row.contains("-----------") && data[0] != null && data[1] != null) {
			topicsRepository.addTopic(new Topic("1", data[0], data[1]));
			data = new String[2];
			i = 0;
			return;
		}
		data[i] = row;
		i++;
	}
	
}
