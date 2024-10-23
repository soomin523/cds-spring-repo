package com.human.cds.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.cds.service.MypageService;
import com.human.cds.vo.DestinationDBVO;
import com.human.cds.vo.MemberVO;

@Controller
@RequestMapping("/mypage")//공통 url(폴더명)
public class MypageController {

	private MypageService mypageServiceImpl;
	
	@Autowired
	public MypageController(MypageService mypageServiceImpl) {
		this.mypageServiceImpl = mypageServiceImpl;
	}
	
	
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
			//mypage.jsp 를 반환
			return "mypage/mypageamend"; //view이름으로 jsp파일 경로 반환
		}
	
		@GetMapping("/mypagequery.do")
		public String showMypagequery(Model model) {
			  // 필요한 데이터를 모델에 추가할 수 있음 (예: 사용자 정보)
	        // model.addAttribute("attributeName", attributeValue);
			
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
	        // model.addAttribute("attributeName(키값(el문에서 씀))", attributeValue);
			
			
			//관광지 정보들 조회
			List<DestinationDBVO> desVo = mypageServiceImpl.getDestinationList();
			model.addAttribute("desList", desVo);
			
			//mypage.jsp 를 반환
			return "mypage/mypagelike"; //view이름으로 jsp파일 경로 반환
		
		}
		
		//회원탈퇴 요청
		@GetMapping("/cancelProcess")
		public String cancelProcess(HttpServletRequest request, Model model) {
			HttpSession session = request.getSession();
			MemberVO vo = (MemberVO)session.getAttribute("member");
			int m_id = vo.getM_id();
			
			System.out.println("controller");
			
			int result = mypageServiceImpl.cancel(m_id);
			
			String viewName = "mypage/mypagestatus";//회원탈퇴 실패시 뷰이름
	
			if(result == 1) {//회원탈퇴 성공
				session.invalidate();//세션 초기화
				viewName = "redirect:/index.do";
				
			}else {//회원탈퇴 실패
				String msg = "시스템에 오류가 발생했습니다. 빠른 시일 내에 시스템을 정상화하도록 하겠습니다.";
				model.addAttribute("msg", msg);
			}
			
			return viewName;
		}
		
		@GetMapping("/logout.do")
		public String logout(HttpServletRequest request) {
			HttpSession session = request.getSession();
			session.invalidate();//세션 초기화
			
			
			return "redirect:/index.do";
		}
		
		
}
