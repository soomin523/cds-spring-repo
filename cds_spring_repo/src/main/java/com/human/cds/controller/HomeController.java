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
	private ProductsService productsServiceImpl;
	private DestinationService destinationServiceImpl;
	private CommunityService communityServiceImpl;
	
	@Autowired
	public HomeController(FestivalService festivalServiceImpl, CourseInfoService courseInfoServiceImpl,
			SupportService supportServiceImpl, ProductsService productsServiceImpl, 
			DestinationService destinationServiceImpl, CommunityService communityServiceImpl) {
		this.festivalServiceImpl = festivalServiceImpl;
		this.courseInfoServiceImpl = courseInfoServiceImpl;
		this.supportServiceImpl = supportServiceImpl;
		this.productsServiceImpl = productsServiceImpl;
		this.destinationServiceImpl = destinationServiceImpl;
		this.communityServiceImpl = communityServiceImpl;
	}
	
	//기타 요청 URL에 대한 처리 메소드
	@GetMapping("/")
	public String home(Model model) {
		
		//관광지 리스트
		List<DestinationDBVO> destinationList = destinationServiceImpl.getDesNoList();
		model.addAttribute("destinationList", destinationList);
		
		//축제 리스트
		List<FestivalDBVO> festivalList = festivalServiceImpl.getFestivalAllRandomList();
		model.addAttribute("festivalList", festivalList);
		
		//공연전시 리스트
		List<ProductsVO.Products> productList = productsServiceImpl.getEventProducts();
		model.addAttribute("productList", productList);
		
		//커뮤니티 리스트
		List<CommunityVO> communityList = communityServiceImpl.getCommunityList();
		model.addAttribute("communityList", communityList);
		
		//코스 리스트
		List<CourseInfoVO> courseList = courseInfoServiceImpl.getCoursesRandomList();
		model.addAttribute("courseList", courseList);
		
		//고객센터 리스트
		List<SupportVO> supportList = supportServiceImpl.getSuppportList();
		model.addAttribute("supportList", supportList);
		
		
		return "home";
	}
	
	@GetMapping("/index.do")
	public String index(Model model) {
		
		//관광지 리스트
		List<DestinationDBVO> destinationList = destinationServiceImpl.getDesNoList();
		model.addAttribute("destinationList", destinationList);
		
		//축제 리스트
		List<FestivalDBVO> festivalList = festivalServiceImpl.getFestivalAllRandomList();
		model.addAttribute("festivalList", festivalList);
		
		//공연전시 리스트
		List<ProductsVO.Products> productList = productsServiceImpl.getEventProducts();
		model.addAttribute("productList", productList);
		
		//커뮤니티 리스트
		List<CommunityVO> communityList = communityServiceImpl.getCommunityList();
		model.addAttribute("communityList", communityList);
		
		//코스 리스트
		List<CourseInfoVO> courseList = courseInfoServiceImpl.getCoursesRandomList();
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
