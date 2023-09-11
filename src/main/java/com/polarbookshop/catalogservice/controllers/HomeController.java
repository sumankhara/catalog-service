package com.polarbookshop.catalogservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polarbookshop.catalogservice.config.PolarProperties;

@RestController
public class HomeController {
	
	private final PolarProperties polarProperties;

	public HomeController(PolarProperties polarProperties) {
		super();
		this.polarProperties = polarProperties;
	}

	@GetMapping("/")
	public String getGreeting() {
		return polarProperties.getGreeting();
	}
}
