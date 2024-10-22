package com.human.cds.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.cds.service.MemberService;
import com.human.cds.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
    
    private MemberService memberServiceImpl;
    
    @Autowired//생성자를 이용해서 필드에 의존 자동 주입함
    public MemberController(MemberService memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }

    // 로그인 페이지
    @GetMapping("/login.do")
    public String login() {
        return "member/login"; // login.jsp
    }

    // 회원가입 페이지
    @GetMapping("/signup.do")
    public String register() {
        return "member/signup"; // signup.jsp
    }

    // 아이디 찾기 페이지
    @GetMapping("/idFind.do")
    public String idFind() {
        return "member/idFind"; // idFind.jsp
    }

    // 비밀번호 찾기 페이지
    @GetMapping("/passwordFind.do")
    public String passwordFind() {
        return "member/passwordFind"; // passwordFind.jsp
    }

    // 비밀번호 변경
    @GetMapping("/passwordChange.do")
    public String passwordChange() {
        return "member/passwordChange"; // passwordChange
    }
    
    // 회원가입 처리
    @PostMapping("/signupProcess.do")
    public String registerMember(MemberVO vo, Model model) {
    	String viewName = "member/signup";

        // 전화번호 중복 확인
        if (memberServiceImpl.isPhoneDuplicate(vo.getPhone())) {
        	model.addAttribute("message", "이미 사용 중인 전화번호입니다.");
        	return "member/signup"; // 중복 시 회원가입 페이지로 되돌아감
        }

        // 회원가입 처리
        boolean result = memberServiceImpl.registerMember(vo);
        
        if (result) {
        	model.addAttribute("joinMember", vo);
        	viewName = "member/signupEnd";
        }

        return viewName;
    }
    
    // 이메일 인증
    @PostMapping("/checkEmail.do")
    @ResponseBody
    public String checkEmail(String email) {
        try {
            String code = memberServiceImpl.sendVerificationCode(email);  // 인증번호 전송
            return code;  // 인증번호 반환
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";  // 이메일 전송 실패 시 오류 반환
        }
    }

    // 이메일 인증번호 확인
    @PostMapping("/verifyEmailCode.do")
    @ResponseBody
    public String verifyEmailCode(@RequestParam String enteredCode, @RequestParam String authcode) {
    	String result = "f";
    	
    	if(enteredCode.equals(authcode)) {
    		result = "ok";
    	}
    	
        return result;  // 인증번호 확인
    }

    // 이메일 사용 가능 여부 체크
    @PostMapping("/checkEmailAvailability")
    @ResponseBody
    public String checkEmailAvailability(@RequestParam String email) {
        String result = "f";
        boolean available = memberServiceImpl.isEmailAvailable(email); // 이메일 사용 가능 여부 확인
        if(available) {
        	result = "ok";
        }
        return result;
    }
    

    // 로그인 처리
    @PostMapping("/loginPass.do")
    @ResponseBody
    public String loginMember(
        @RequestParam("member_id") String memberId,
        @RequestParam("password") String password,
        HttpServletRequest request) {
        
        String result = "f";
        MemberVO member = memberServiceImpl.login(memberId, password);
        
        if (member != null) {
        	HttpSession session = request.getSession();
        	session.setAttribute("member", member);
            result = "ok";
        }

        return result; 
    }

    
    // 아이디 찾기 처리
    @PostMapping("/idFind")
    @ResponseBody
    public String findId(
        @RequestParam("name") String name,
        @RequestParam("email") String email) {
        
        MemberVO member = null;
        String member_id = null;
        
        member = memberServiceImpl.findMemberId(name,email);
        
        if(member != null) {
        	member_id = member.getMember_id();
        }
        
        return member_id;
    }
    
    // 비밀번호 찾기 처리
    @PostMapping("/passwordFind")
    @ResponseBody
    public String findPassword(
        @RequestParam("member_id") String member_id,
        @RequestParam("name") String name,
        @RequestParam("email") String email) {
        
        MemberVO member = null;
        String passwordFind = null;
        
        member = memberServiceImpl.findMemberPassword(member_id,name,email);
        
        if(member != null) {
        	passwordFind = member.getPassword();
        }
        
        return passwordFind;
    }
    
    // 아이디 중복 확인 처리
    @PostMapping("/checkDuplicate")
    @ResponseBody
    //public ResponseEntity<Map<String, Boolean>> checkDuplicate(@RequestBody Map<String, String> request) {
    public String checkDuplicate(String member_id) {
    	String result = "f";
    	
        //String memberId = request.get("member_id"); // 클라이언트에서 "member_id" 키로 데이터 수신
        
        System.out.println("member_id: "+member_id);
        
        boolean isDuplicate = memberServiceImpl.checkId(member_id);  // 서비스에서 중복 확인 로직 수행
        if(!isDuplicate) {
        	result = "ok";
        }

        return result; // 중복 여부를 클라이언트로 반환
    }
    
}
