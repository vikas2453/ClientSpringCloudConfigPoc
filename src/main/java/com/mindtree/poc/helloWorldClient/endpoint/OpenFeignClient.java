package com.mindtree.poc.helloWorldClient.endpoint;

import java.net.URI;

import org.springframework.cloud.openfeign.FeignClient;

import feign.RequestLine;

@FeignClient(name="helloWorldOpenFeign")
public interface OpenFeignClient {

	 @RequestLine("GET")
	 public String getGreeting(URI baseUri); 
}
