package com.human.cds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.human.cds.repository.DestinationDAO;
import com.human.cds.service.DestinationService;

@Controller
public class DestinationAjaxController {

	@Autowired //의존자동주입
	private DestinationService DestinationServiceImpl;
	private DestinationDAO DestinationDAO;
    
    @GetMapping("/Course.do")
    public String Course(Model model) {
        
        return "tourCourse/Course";
    }
}
