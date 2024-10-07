package com.human.cds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	//기타 요청 URL에 대한 처리 메소드
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/web")
	public String web() {
		return "home";
	}
	
	@GetMapping("/index.do")
	public String index() {
		return "home";
	}
	
	@GetMapping("/error/error403.do")
	public String error403() {
		return "error/error403";
	}
	
	@GetMapping("/error/error404.do")
	public String error404() {
		return "error/error404";
	}
	
	@GetMapping("/error/error405.do")
	public String error405() {
		return "error/error405";
	}
	
	@GetMapping("/error/error500.do")
	public String error500() {
		return "error/error500";
	}
	
}
