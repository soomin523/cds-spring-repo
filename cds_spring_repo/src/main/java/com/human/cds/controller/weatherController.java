package com.human.cds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weather")
public class weatherController {

	@GetMapping("/weather.do")
	public String Weather() {
		
		return "weather/weather";
	}
}
