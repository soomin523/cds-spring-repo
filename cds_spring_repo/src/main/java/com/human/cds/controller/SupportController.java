package com.human.cds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.human.cds.service.SupportService;
import com.human.cds.vo.SupportVO;

@Controller
@RequestMapping("/support") //공통으로 적용되는 URL 정의
public class SupportController {
	
	@Autowired
	private  SupportService supportServiceImpl;
	
	//redirect:/index.do
	
	@GetMapping("/support.do")
	public String support(Model model) {
		String viewName = "home";
		
		List<SupportVO> notice = null;
		notice = supportServiceImpl.getNoticeList();
		List<SupportVO> guide = null;
		guide = supportServiceImpl.getGuideList();
		List<SupportVO> question = null;
		question = supportServiceImpl.getQuestionList();
		
		if(notice != null && guide != null && question != null) {
			model.addAttribute("notice", notice);
			model.addAttribute("guide", guide);
			model.addAttribute("question", question);
			viewName = "/support/support";
		}
		
		return viewName;
	}
	
	@GetMapping("supportDetail.do")
	public String supportDetail(String select, Model model) {
		
		List<SupportVO> vo = null;
		
		switch(select) {
			case "notice": vo = supportServiceImpl.getNoticeList(); break;
			case "guide": vo = supportServiceImpl.getGuideList(); break;
			case "question": vo = supportServiceImpl.getQuestionList(); break;
		}
		model.addAttribute("select", select);
		model.addAttribute("support", vo);
		return "/support/supportDetail";
	}
	
}
