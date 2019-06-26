package com.mindtree.poc.helloWorldClient.endpoint;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import feign.Feign;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@Data
@Slf4j
@Import(FeignClientsConfiguration.class)
public class HelloWorldClientContoller {

	private OpenFeignClient helloWorldOpenFeign;

	private static String helloWorldBaseURL;

	@Value("${helloWorldInstance}")
	private static String helloWorldInstance;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EurekaClient eurekaClient;

	public HelloWorldClientContoller() {

	}

	@Autowired
	public HelloWorldClientContoller(Decoder decoder, Encoder encoder) {
		helloWorldOpenFeign = Feign.builder().encoder(encoder).decoder(decoder)
				.target(Target.EmptyTarget.create(OpenFeignClient.class));
	}

	@RequestMapping("/")
	public String getHomeInfoFromHelloWolrd() {
		ResponseEntity<String> response = restTemplate.exchange(getBaseURL() + "/home", HttpMethod.GET, null,
				String.class);
		return response.getBody();
	}

	@RequestMapping("/greet")
	public String getGreetInfoFromHelloWolrd() {
		ResponseEntity<String> response = restTemplate.exchange(getBaseURL() + "/greet", HttpMethod.GET, null,
				String.class);
		return response.getBody();
	}

	@RequestMapping("/greetfromOpenFeign")
	public String getGreetInfoFromHelloWolrdwithOpenFeign() {
		String response =null;
		try {
			response = helloWorldOpenFeign.getGreeting(new URI(getBaseURL() + "/greet"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public String getBaseURL() {
		// here we pass name of the application
		// second argument is if server are secure
		log.info("helloWorldInstance is " + helloWorldInstance);
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("HelloWorld", false);
		helloWorldBaseURL = instanceInfo.getHomePageUrl();
		return helloWorldBaseURL;

	}

}
