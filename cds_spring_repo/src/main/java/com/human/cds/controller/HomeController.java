package com.human.cds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.human.cds.service.*;
import com.human.cds.vo.*;

@Controller
public class HomeController {
	
	private FestivalService festivalServiceImpl;
	private CourseInfoService courseInfoServiceImpl;
	private SupportService supportServiceImpl;
	
	@Autowired
	public HomeController(FestivalService festivalServiceImpl, CourseInfoService courseInfoServiceImpl,
			SupportService supportServiceImpl) {
		this.festivalServiceImpl = festivalServiceImpl;
		this.courseInfoServiceImpl = courseInfoServiceImpl;
		this.supportServiceImpl = supportServiceImpl;
	}
	
	//기타 요청 URL에 대한 처리 메소드
	@GetMapping("/")
	public String home(Model model) {
		
		//관광지 리스트
		
		//축제 리스트
		List<FestivalDBVO> festivalList = festivalServiceImpl.getFestivalList();
		model.addAttribute("festivalList", festivalList);
		
		//공연전시 리스트
		
		//커뮤니티 리스트
		
		//코스 리스트
		List<CourseInfoVO> courseList = courseInfoServiceImpl.getCoursesByRegion(null, null);
		model.addAttribute("courseList", courseList);
		
		//고객센터 리스트
		List<SupportVO> supportList = supportServiceImpl.getSuppportList();
		model.addAttribute("supportList", supportList);
		
		
		return "home";
	}
	
	@GetMapping("/index.do")
	public String index(Model model) {
		
		//관광지 리스트
		
		//축제 리스트
		List<FestivalDBVO> festivalList = festivalServiceImpl.getFestivalList();
		model.addAttribute("festivalList", festivalList);
		
		//공연전시 리스트
		
		//커뮤니티 리스트
		
		//코스 리스트
		List<CourseInfoVO> courseList = courseInfoServiceImpl.getCoursesByRegion(null, null);
		model.addAttribute("courseList", courseList);
		
		//고객센터 리스트
		List<SupportVO> supportList = supportServiceImpl.getSuppportList();
		model.addAttribute("supportList", supportList);
		
		
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
