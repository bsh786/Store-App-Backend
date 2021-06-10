package com.bashir.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

	@GetMapping(value="/status/check")
	public String checkStatus()
	{
		return "Working";
	}
	
}
