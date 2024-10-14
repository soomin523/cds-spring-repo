package com.human.cds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	@GetMapping("/manager.do")
	public String manager(String select, Model model) {
		model.addAttribute("select", select);
		
		return "/manager/manager";
	}

}
