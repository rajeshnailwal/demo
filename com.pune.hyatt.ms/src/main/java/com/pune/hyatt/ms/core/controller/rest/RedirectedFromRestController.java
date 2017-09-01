package com.pune.hyatt.ms.core.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectedFromRestController {
	
	@RequestMapping("/redirectedto")
	public String getMeSomething(){
		return "Nothing";
	}
}
