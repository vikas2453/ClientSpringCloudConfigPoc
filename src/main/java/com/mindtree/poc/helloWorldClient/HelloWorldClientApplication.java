package com.mindtree.poc.helloWorldClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
// @EnableDiscoveryClient is the annotation so that this application can communicate with Eureka discovery server
// Please note that this is a client to the service so this application doesn't need to register itself with discovery server
// however it will take information from discovery server about the service
@EnableDiscoveryClient
public class HelloWorldClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldClientApplication.class, args);
	}

}
