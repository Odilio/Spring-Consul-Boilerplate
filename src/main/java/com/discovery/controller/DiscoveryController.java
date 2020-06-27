package com.discovery.controller;

import java.net.URI;
import java.util.Optional;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Controller
 *
 * @author Odilio Noronha Filho
 */
@RestController
class DiscoveryController{

	@Autowired
	private DiscoveryClient discoveryClient;

	private final RestTemplate restTemplate = new RestTemplate();
	 
	@GetMapping("/health-check")
	public ResponseEntity<String> customHealthCheck() {
	    String message = "Is Alive";
	    return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/services")
	public Optional<URI> serviceURL(){
		return discoveryClient.getInstances("consulApp")
				.stream()
				.map(instance -> instance.getUri())
				.findFirst();
	}
	
	@GetMapping("/discoveryClient")
	public String discoveryMethod() throws ServiceUnavailableException {
	    URI service = serviceURL()
	      .map(s -> s.resolve("/method"))
	      .orElseThrow(ServiceUnavailableException::new);
	    return restTemplate.getForEntity(service, String.class)
	      .getBody();
	}
	 
	@GetMapping("/method")
	public String method() {
	    return "Method Found for discovery";
	}

}