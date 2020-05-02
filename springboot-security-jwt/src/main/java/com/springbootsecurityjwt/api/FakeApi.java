package com.springbootsecurityjwt.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FakeApi {

	@GetMapping("")
	public Map<String, String> sayWelcome() {
		Map<String, String> element = new HashMap<String, String>();
		element.put("message", "Welcome");
		return element;
	}

	@GetMapping("/home")
	public Map<String, String> getElement() {
		Map<String, String> element = new HashMap<String, String>();
		element.put("message", "Home");
		return element;
	}

}
