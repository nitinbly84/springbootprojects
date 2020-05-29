package com.nitinagrawal.utilities;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nitinagrawal.entities.Mail;

@Service
public class EmailGenerator {

	public static Mail generateEmail(List<String> mailTo, String message, File file) {

		Mail mail = new Mail();
		if(file != null)
			mail.setAttachment(file);
		if(message == null || message.isEmpty())
			message = "Data from CourseApp.";
		if(file != null)
			message+="\nRetreived data is in attached file";

		mail.setMailBody(message);
		mail.setMailSubject("Mail from CourseApp");
		mail.setTo(mailTo);
		return mail;
	}
}
