package com.mindtree.poc.helloWorldClient.config;

import org.apache.http.entity.ContentType;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeignClientInterceptor {
	
	@Bean
	public RequestInterceptor requestInterceptor() {
		log.info("intercepting the request for a feign request");
	  return requestTemplate -> {
	      requestTemplate.header("user", "user");
	      requestTemplate.header("password", "password");
	      requestTemplate.header("Accept", ContentType.APPLICATION_JSON.getMimeType());
	  };
	}

}
