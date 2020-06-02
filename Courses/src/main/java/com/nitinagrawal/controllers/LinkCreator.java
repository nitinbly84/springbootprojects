package com.nitinagrawal.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.Link;

@SuppressWarnings("rawtypes") 
public class LinkCreator {
	
	private static String mailURI = "sendemail?to=<Comma(,) separated list of email ids>";
	
	public static Link getSelfLink(Class className, String identifier) {
		Link selfLink = linkTo(className).slash(identifier).withSelfRel();
		return selfLink;
	}
	
	public static Link getSelfLink(Class className, String core, String identifier) {
		Link selfLink = linkTo(className).slash(core).slash(identifier).withSelfRel();
		return selfLink;
	}
	
	public static Link getSelfMailLink(Class className, String core, String identifier) {
		Link selfLink = linkTo(className).slash(core).slash(identifier).slash(mailURI).withRel("e-mail");
		return selfLink;
	}
	
	public static Link getSelfMailLink(Class className, String core) {
		Link selfLink = linkTo(className).slash(core).slash(mailURI).withRel("e-mail");
		return selfLink;
	}
	
	public static Link getNextLink(Class className, String core, String identifier, String resource) {
		Link selfLink = linkTo(className).slash(core).slash(identifier).slash(resource).withRel(resource);
		return selfLink;
	}
	
}
