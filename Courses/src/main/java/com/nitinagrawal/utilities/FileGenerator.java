package com.nitinagrawal.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Update this class to generate the formatted file
 * & better generate file as PDF with protection of the data
 * so that no one can change the data once generated.
 */
@Component
public class FileGenerator {

	public String createFile(Object data) {
		ObjectMapper mapper = new ObjectMapper();
		String dir = "D:/CourseAppGeneratedFiles/";
		String time = java.time.LocalDateTime.now().toString()
												   .replace("-", "_")
												   .replace(":", "_")
												   .replace(".", "_");
		String fileName = "Result_"+ time + ".txt";
		System.out.println(dir+fileName);
		File file = new File(dir+fileName);
		if(file.exists())
			file.delete();
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try(FileWriter fw = new FileWriter(file)) {
			String jsonString = mapper.writeValueAsString(data);
			fw.write(jsonString);
			fw.flush();
			return dir+fileName;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
