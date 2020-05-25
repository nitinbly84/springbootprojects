package com.nitinagrawal;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.nitinagrawal.utilities.CourseGenerator;
import com.nitinagrawal.utilities.PopulateTopicCourseMappings;
import com.nitinagrawal.utilities.TopicsGenerator;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

// localhost:8080/swagger-ui.html
@ComponentScan({"com.nitinagrawal.repositories", "com.nitinagrawal.utilities", "com.nitinagrawal.services", "com.nitinagrawal.controllers", "com.nitinagrawal.swagger"})
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
		SpringApplication.run(CoursesApplication.class, args);
	}
	
	 @Bean
	   public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2).select()
	    		  										//.paths(PathSelectors.ant("/topics")) //One can use this way also to document
	    		  																			   //only specific APIs based on the URL
	    		  										// This way it will document every API under this base package. Choose either
	    		  										// above method or below method or both to let Swagger document about your APIs
	    		  										// only, else it will document Spring specific APIs also & that will make your
	    		  										// document a messy one. Its a builder pattern.
	    		  										.apis(RequestHandlerSelectors.basePackage("com.nitinagrawal"))
	    		  										.build()
	    		  										.apiInfo(appDetails());
	   }
	 
	 private static ApiInfo appDetails() {
		 String title = "A Sample Courses Project API";
		 String description = "Its a Rest API based project created using SpringBoot, so that one can quickly start exploring SpringBoot features"
		 		            + "\n without worrying about creating a sample project to learn & practise on SpringBoot."
		 		            + "\n One can extend this project to explore other areas & post it back, so that others also can learn.";
		 String version = "1.0";
		 String termsOfServiceUrl = "This project has been created only for learning purposes don't use in your production work.";
		 springfox.documentation.service.Contact contact = new springfox.documentation.service.Contact("Nitin Agrawal", "https://www.nitinagrawal.com", "nitin.bly@gmail.com");
		 String license = "Licensed for Educational purposes only";
		 String licenseUrl = "https://www.nitinagrawal.com";
		 
		return new ApiInfo(title, description, version, termsOfServiceUrl, contact, license, licenseUrl, getVendorExtension());
	 }
	 
	@SuppressWarnings("rawtypes")
	private static Collection<VendorExtension> getVendorExtension() {
		 VendorExtension<String> extensions = 
				 new VendorExtension<String>() {
			
													@Override
													public String getName() {
														return "Nitin Agrawal";
													}
									
													@Override
													public String getValue() {
														return "A Sample Courses Project API";
													}
												};
			 Collection<VendorExtension> vendorExtensions = new ArrayList<>();
			 vendorExtensions.add(extensions);
			 return vendorExtensions;
	 }
	
	@PostConstruct
	private void initailizer() {
		topicsGenerator.generate();
		courseGenerator.generate();
		populateTopicCourseMappings.setTopicCourseMappings();
	}
}
