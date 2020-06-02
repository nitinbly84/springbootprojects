package com.nitinagrawal.swagger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerEnabler implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry
				.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");

		registry
				.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	// Added to make Swagger work with Hateoas library
	@Primary
    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)//.protocols(Collections.singleton("https"))
													  //.host("/locahost:8080")
													  .select()
													  //.paths(PathSelectors.ant("/topics")) //One can use this way also to document
													  //only specific APIs based on the URL
													  // This way it will document every API under this base package. Choose either
													  // above method or below method or both to let Swagger document about your APIs
													  // only, else it will document Spring specific APIs also & that will make your
													  // document a messy one. Its a builder pattern.
													  .apis(RequestHandlerSelectors.basePackage("com.nitinagrawal"))
													  .paths(PathSelectors.any())
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
}
