package com.human.cds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.cds.service.SupportService;
import com.human.cds.vo.SupportVO;

@Controller
@RequestMapping("/support") //공통으로 적용되는 URL 정의
public class SupportController {
	
	private  SupportService supportServiceImpl;
	
	@Autowired
	public SupportController(SupportService supportServiceImpl) {
		this.supportServiceImpl = supportServiceImpl;
	}
	
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
	
	@GetMapping("/supportDetail.do")
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
	
	@GetMapping("/newContent.do")
	public String newContent() {
		return "/support/supportDetail";
	}
	
	@PostMapping("/insertSupport.do")
	@ResponseBody
	public String insertSupport(SupportVO vo) {
		String result = "error";
		
		if(supportServiceImpl.insertSupport(vo) == 1) {
			result = "success";
		}
		
		return result;
	}
	
	@GetMapping("/getsupport.do")
	@ResponseBody
	public SupportVO getsupport(@RequestParam String s_idx) {
		SupportVO vo = null;
		
		vo = supportServiceImpl.getsupport(s_idx);
		
		return vo;
	}
	
	@PostMapping("/updateSupport.do")
	@ResponseBody
	public String updateSupport(SupportVO vo) {
		String result = "error";
		
		if(supportServiceImpl.updateSupport(vo) == 1) {
			result = "success";
		}
		
		return result;
	}
	
	@GetMapping("/deleteSupport.do")
	public String deleteSupport(String s_idx, String category) {		
		supportServiceImpl.deleteSupport(s_idx);
		
		return "redirect:supportDetail.do?select="+category;
	}
	
}
