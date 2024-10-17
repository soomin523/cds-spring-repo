package com.human.cds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")//공통 url(폴더명)
public class MypageController {

	// /mypage/mypage.jsp 요청을 처리하는 메소드
	@GetMapping("/mypagemain.do")
	public String showMypage(Model model) {
		  // 필요한 데이터를 모델에 추가할 수 있음 (예: 사용자 정보)
        // model.addAttribute("attributeName", attributeValue);
		
		//mypage.jsp 를 반환
		return "mypage/mypagemain"; //view이름으로 jsp파일 경로 반환
	}
	
	// /mypage/mypage.jsp 요청을 처리하는 메소드
		@GetMapping("/mypageamend.do")
		public String showMypageAmend(Model model) {
			  // 필요한 데이터를 모델에 추가할 수 있음 (예: 사용자 정보)
	        // model.addAttribute("attributeName", attributeValue);
			
			//mypage.jsp 를 반환
			return "mypage/mypageamend"; //view이름으로 jsp파일 경로 반환
		}
	
		@GetMapping("/mypagequery.do")
		public String showMypagequery(Model model) {
			  // 필요한 데이터를 모델에 추가할 수 있음 (예: 사용자 정보)
	        // model.addAttribute("attributeName", attributeValue);
			
			//mypage.jsp 를 반환
			return "mypage/mypagequery"; //view이름으로 jsp파일 경로 반환
		
		}
		
		@GetMapping("/mypagestatus.do")
		public String showMypagestatys(Model model) {
			  // 필요한 데이터를 모델에 추가할 수 있음 (예: 사용자 정보)
	        // model.addAttribute("attributeName", attributeValue);
			
			//mypage.jsp 를 반환
			return "mypage/mypagestatus"; //view이름으로 jsp파일 경로 반환
		
		}
		
		
		@GetMapping("/mypagewrite.do")
		public String showMypagewrite(Model model) {
			  // 필요한 데이터를 모델에 추가할 수 있음 (예: 사용자 정보)
	        // model.addAttribute("attributeName", attributeValue);
			
			//mypage.jsp 를 반환
			return "mypage/mypagewrite"; //view이름으로 jsp파일 경로 반환
		
		}
		
		@GetMapping("/mypagelike.do")
		public String showMypagelike(Model model) {
			  // 필요한 데이터를 모델에 추가할 수 있음 (예: 사용자 정보)
	        // model.addAttribute("attributeName", attributeValue);
			
			//mypage.jsp 를 반환
			return "mypage/mypagelike"; //view이름으로 jsp파일 경로 반환
		
		}
}
