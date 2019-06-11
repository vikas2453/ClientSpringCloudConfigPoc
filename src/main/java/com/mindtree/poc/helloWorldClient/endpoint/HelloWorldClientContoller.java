package com.mindtree.poc.helloWorldClient.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import lombok.Data;

@RestController
@Data
public class HelloWorldClientContoller {
	
	private static String helloWorldBaseURL; 
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@RequestMapping("/")
	public String getHomeInfoFromHelloWolrd() {
		ResponseEntity<String> response = restTemplate.exchange(getBaseURL()+"/home", HttpMethod.GET, null, String.class);
		return response.getBody();
	}
	
	@RequestMapping("/greet")
	public String getGreetInfoFromHelloWolrd() {
		ResponseEntity<String> response = restTemplate.exchange(getBaseURL()+"/greet", HttpMethod.GET, null, String.class);
		return response.getBody();
	}
	
	public  String getBaseURL() {
		//here we pass name of the application
		// second argument is if server are secure
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("helloWorld", false);
		helloWorldBaseURL = instanceInfo.getHomePageUrl();
		return helloWorldBaseURL;
		
	}

}
