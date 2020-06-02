package com.nitinagrawal.entities;

import java.io.File;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

@Entity
public class Mail extends RepresentationModel<Mail> {

	@Column(nullable = false)
	@ElementCollection
	private List<String> to;
	@Id
	private String mailSubject;
	private String mailBody;
	private File attachment;

	public List<String> getTo() {
		return to;
	}
	public void setTo(List<String> to) {
		this.to = to;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public File getAttachment() {
		return attachment;
	}
	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

}
