package com.nitinagrawal.services;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitinagrawal.entities.Mail;
import com.nitinagrawal.mail.BuildMailProperties;

@Service
public class EmailService {

	@Autowired
	BuildMailProperties mailProperties;

	public boolean sendEmail(String to, Mail mail) {

		try {
			Properties props = mailProperties.getProperties();
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
				}
			});
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(props.getProperty("username"), false));

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.setSubject(mail.getMailSubject());
			msg.setContent(mail.getMailBody(), "text/html");
			msg.setSentDate(new Date());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(mail.getMailBody(), "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			if(mail.getAttachment() != null) {
				MimeBodyPart attachPart = new MimeBodyPart();
				attachPart.attachFile(mail.getAttachment());
				multipart.addBodyPart(attachPart);
			}
			msg.setContent(multipart);
			Transport.send(msg);
			return true;
		} catch(Exception e) {

		}
		return false;
	}
}
