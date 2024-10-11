package com.human.cds.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.human.cds.service.SupportService;

@Controller
@RequestMapping("/support") //공통으로 적용되는 URL 정의
public class SupportController {
	
//	@Autowired
//	private  SupportService supportServiceImpl;
	
	//redirect:/index.do
	
	@GetMapping("/support.do")
	public String support() {
		return "/support/support";
	}
	
}
