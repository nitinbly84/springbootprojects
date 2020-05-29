package com.nitinagrawal.mail;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.nitinagrawal.entities.Topic;
import com.nitinagrawal.utilities.FileGenerator;

public class SendMailLFilter implements Filter {

	private FileGenerator fileGenerator = new FileGenerator();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("########## Initiating CustomURLFilter filter ##########");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String requestURL = request.getRequestURI();
		if(requestURL.contains("sendemail") && !requestURL.equalsIgnoreCase("/sendemail")) {
			String[] parts = changeRequestUrl(requestURL);
			String protocol = request.getProtocol();
			String initialPart = protocol.substring(0, protocol.indexOf("/")).toLowerCase()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
			Set<Topic> result = processRequest(initialPart+parts[0], true);
			String fileName = fileGenerator.createFile(result);
			String newUrl = "/"+parts[1];
			if(fileName != null && !fileName.isEmpty())
				newUrl+="?attach="+fileName;
			if(Integer.toString(response.getStatus()).startsWith("2")) {
				System.out.println("Sending Mail.....");
				servletRequest.getRequestDispatcher(newUrl).forward(request, response);
			}
		}

		System.out.println("This filter is called for every request to application....");

		//call next filter in the filter chain
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("Destroy() called for CustomURLFilter....");
	}

	private String[] changeRequestUrl(String requestURL) {
		int index;
		String[] parts = new String[2];
		if((index=requestURL.indexOf("sendemail")) > 0) {
			parts[0] = requestURL.substring(0, index);
			parts[1] = requestURL.substring(index);
		}
		return parts;
	}

	/*
	 * Currently this method is handling a get request for all the topics.
	 * But can develop several methods for different kind of outputs from
	 * respective APIs.
	 */
	private Set<Topic> processRequest(String url, Boolean isAuhenticated)
	{
		final String uri = url;

		HttpHeaders headers;
		// Look for the way to move out the credentials out as these are present in properties
		// file & can be used from there directly.
		if(!isAuhenticated)
			headers = new HttpHeaders();
		else
			headers = createHeaders("admin", "password");
		headers.setContentType(MediaType.APPLICATION_JSON);
		Set<Topic> topics = new HashSet<>();
		HttpEntity<Set<Topic>> request = new HttpEntity<>(topics, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Set> result = restTemplate.exchange(uri, HttpMethod.GET, request, Set.class);

		return result.getBody();
	}

	private HttpHeaders createHeaders(String username, String password){
		return new HttpHeaders() {{
			String auth = username + ":" + password;
			byte[] encodedAuth = Base64.encodeBase64( 
					auth.getBytes(Charset.forName("US-ASCII")) );
			String authHeader = "Basic " + new String( encodedAuth );
			set( "Authorization", authHeader );
		}};
	}

}