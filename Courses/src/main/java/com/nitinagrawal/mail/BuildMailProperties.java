package com.nitinagrawal.mail;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BuildMailProperties {
	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private String port;
	@Value("${spring.mail.username}")
	private String username;
	@Value("${spring.mail.password}")
	private String password;
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String mailSmtpAuth;
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String mailSmtpStarttlsEnable;
	@Value("${spring.security.user.name}")
	private String adminName;
	@Value("${spring.security.user.password}")
	private String adminPassword;
	
	public Properties getProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", mailSmtpAuth);
		props.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("username", username);
		props.put("password", password);
		
		return props;
	}
	
	public String getAdminName() {
		return adminName;
	}
	
	public String getAdminPassword() {
		return adminPassword;
	}
}
