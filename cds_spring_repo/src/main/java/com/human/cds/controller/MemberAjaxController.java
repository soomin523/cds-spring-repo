package com.human.cds.controller;

import com.human.cds.vo.CheckEmailDTO;
import com.human.cds.vo.CheckIdDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.human.cds.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController //@Controller + @ResponseBody
@RequiredArgsConstructor
public class MemberAjaxController {
	
	//필드 정의
	private final MemberService memberServiceImpl;
	
	//아이디 중복검사
	@PostMapping("/member/checkId.do")
	public String checkId(@RequestBody CheckIdDTO checkIdDTO) {
		String result = "PASS";//중복된 아이디가 없는 경우 결과값
		
		if (memberServiceImpl.checkId(checkIdDTO.getMember_id())) { // 중복된 아이디가 있는 경우
		    result = "FAIL";
		}

		return result;
	}


	
	//이메일 인증
		@PostMapping("/member/checkEmail.do")
		public String checkEmail(@RequestBody CheckEmailDTO emailDTO) {
			System.out.println("이메일 인증 이메일: "+ emailDTO.getEmail());
			return memberServiceImpl.authEmail(emailDTO.getEmail());
		}


	}



/*
 * package com.human.cds.controller;
 * 
 * import org.springframework.web.bind.annotation.*;
 * 
 * import com.human.cds.service.MemberService;
 * 
 * import org.springframework.http.ResponseEntity; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller;
 * 
 * @Controller
 * 
 * @RequestMapping("/ajax") public class MemberAjaxController {
 * 
 * @Autowired private MemberService memberService;
 * 
 * // 이메일 중복 여부를 비동기적으로 확인하는 메소드
 * 
 * @PostMapping("/member/checkEmail.do")
 * 
 * @ResponseBody public ResponseEntity<String>
 * validateEmail(@RequestParam("email") String email) { boolean isAvailable =
 * memberService.isEmailAvailable(email); if (isAvailable) { return
 * ResponseEntity.ok("이메일을 사용할 수 있습니다."); } else { return
 * ResponseEntity.status(400).body("이 이메일은 이미 사용 중입니다."); } } }
 */