package com.nitinagrawal.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nitinagrawal.entities.Mail;
import com.nitinagrawal.services.EmailService;
import com.nitinagrawal.utilities.EmailGenerator;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;
	private File file;
	//@Autowired
	//EmailGenerator emailGenerator;
	// Can also use @RequestParam(name="to") or @RequestParam(value="to")
	// Can make @RequestParam(required=false) to make the query parameter optional
	// but in that case we will be getting value null if not given & we need to handle
	// that situation.
	@PostMapping(value = "/sendemail")
	public ResponseEntity<String> sendEmail(@RequestParam("to") Optional<List<String>> emailTo, @RequestBody(required=false) String message, @RequestParam("attach") Optional<String> filePath) {
		List<String> mailTo = emailTo.orElse(new ArrayList<>());
		List<String> failed = new ArrayList<>();
		if(mailTo.isEmpty()) {
			return new ResponseEntity<>("To address is not given to send the email to. Please give this query parameter like - /sendemail?to=email.com or give comma(,) separated list of email ids."
					, HttpStatus.BAD_REQUEST);
		}

		if(filePath.isPresent()) {
			file = new File(filePath.get());
			if(!file.exists())
				file = null;
		}
		Mail mail = EmailGenerator.generateEmail(mailTo, message, file);
		try {
			failed = mailTo.stream()
					.filter(mailAddress -> !emailService.sendEmail(mailAddress, mail))
					.collect(toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(failed.isEmpty())
			return new ResponseEntity<>("Email sent successfully.", HttpStatus.OK);
		if(failed.size() < mailTo.size()) {
			String list = failed.toString().substring(1, failed.toString().length()-1);
			return new ResponseEntity<>("Email sent failed for " + list + " Check the email addresses & Try again!", HttpStatus.PARTIAL_CONTENT);
		}
		if(file != null)
			file.delete();
		return new ResponseEntity<>("Email send failed. Try again", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
	}
}
