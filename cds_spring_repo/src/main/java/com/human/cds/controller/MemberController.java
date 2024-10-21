package com.human.cds.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.human.cds.service.MemberService;
import com.human.cds.vo.MemberVO;

import java.util.Properties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Controller
@RequestMapping("/member")
public class MemberController {
    
    private MemberService memberServiceImpl;
    private JavaMailSenderImpl mailSender;
    
    @Value("${172267091290-704rp9g9evbu8na2co56nmop1i13d1ul.apps.googleusercontent.com}")
    private String googleClientId;
    @Value("${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}")
    private String googleClientPw;
    
    @Autowired//생성자를 이용해서 필드에 의존 자동 주입함
    public MemberController(MemberService memberServiceImpl, JavaMailSenderImpl mailSender) {
        this.memberServiceImpl = memberServiceImpl;
        this.mailSender = mailSender;
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
        String viewName = "member/login";

        // 전화번호 중복 확인
        if (memberServiceImpl.isPhoneDuplicate(vo.getPhone())) {
            model.addAttribute("message", "이미 사용 중인 전화번호입니다.");
            return "member/signup"; // 중복 시 회원가입 페이지로 되돌아감
        }

        // 이메일 중복 확인
        if (memberServiceImpl.isEmailDuplicate(vo.getEmail())) {
            model.addAttribute("message", "이미 사용 중인 이메일입니다.");
            return "member/checkEmail.do"; // 중복 시 회원가입 페이지로 되돌아감
        }

        // 회원가입 처리
        boolean result = memberServiceImpl.registerMember(vo);

        if (result) {
        	model.addAttribute("joinMember", vo);
            viewName = "member/signupEnd"; // 성공 시 회원가입 완료 페이지로 이동
        } else {
            model.addAttribute("message", "회원가입에 실패했습니다.");
            viewName = "member/signup"; // 실패 시 다시 회원가입 페이지로 이동
        }

        return viewName;
    }

    
	/*
	 * // 로그아웃 처리
	 * 
	 * @GetMapping("/logout.do") public String logout(HttpServletRequest request) {
	 * HttpSession session = request.getSession(); if (session != null) {
	 * session.invalidate(); // 세션 무효화 } return "redirect:/login.do"; // 로그아웃 후 로그인
	 * 페이지로 리다이렉트 }
	 */
    

    
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
    @PostMapping("/verifyEmailCode")
    @ResponseBody
    public boolean verifyEmailCode(@RequestParam("email") String email, @RequestParam("code") String code) {
        return memberServiceImpl.verifyCode(email, code);  // 인증번호 확인
    }

    // 이메일 사용 가능 여부 체크
    @PostMapping("/checkEmailAvailability")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> checkEmailAvailability(@RequestParam String email) {
        Map<String, Boolean> response = new HashMap<>();
        boolean available = memberServiceImpl.isEmailAvailable(email); // 이메일 사용 가능 여부 확인
        response.put("available", available);
        return ResponseEntity.ok(response);
    }

    
    
    //회원가입 기능
    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute MemberVO member, Model model) {
        try {
        	memberServiceImpl.registerMember(member);  // 회원가입 처리
            return "redirect:/member/login";  // 성공 시 로그인 페이지로 리다이렉트
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/signup";  // 실패 시 회원가입 페이지로 돌아감
        }
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

    
    
    /*
    // 비밀번호 찾기 처리
    @PostMapping("/passwordFind")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> findPassword(
        @RequestParam("member_id") String memberId,
        @RequestParam("name") String name,
        @RequestParam("email") String email) {
        
        Map<String, Object> response = new HashMap<>();
        MemberVO member = memberService.getMemberById1(Integer.parseInt(memberId));
        
        if (member != null && member.getName().equals(name) && member.getEmail().equals(email)) {

        	response.put("success", true);
            response.put("message", "Password reset link has been sent to your email.");
        } else {
            response.put("success", false);
            response.put("message", "Member information is incorrect.");
        }
        
        return ResponseEntity.ok(response);
    }
*/

    
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
    /*
    //인증 코드 전송 로직
    @PostMapping("/checkEmail")
    @ResponseBody
    public String sendVerificationCode(@RequestParam String email, Model model) {
        try {
            // 메일 인증 코드 생성 및 전송
            String authCode = authEmail(email); // 메일 전송 메소드 호출
            // 코드 저장 로직 추가 (예: Redis, 데이터베이스 등)
            
            return authCode; // 성공 시 응답
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR"; // 오류 발생 시
        }
    }

    
    private String authEmail(String email) {
        // 6자리 랜덤 인증 코드 생성
        int authNumber = (int)(Math.random() * 888889) + 111111; // 111111 <= r < 1000000

        // 이메일 제목 및 내용 설정
        String setFrom = "your_email@example.com"; // 송신자 이메일
        String title = "회원가입 인증 이메일입니다"; // 이메일 제목
        String content = "홈페이지를 방문해 주셔서 감사합니다.<br><br>"
                       + "인증번호: " + authNumber + "<br>"
                       + "해당 인증번호를 인증번호 확인란에 입력해 주세요."; // 이메일 내용

        // 메일 전송
        mailSend(setFrom, email, title, content);

        // 생성된 인증 코드를 반환
        return Integer.toString(authNumber);
    }


    private void mailSend(String setFrom, String email, String title, String content) {
        try {
            // MimeMessage 객체 생성
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // 이메일 설정
            helper.setFrom(setFrom); // 송신자 이메일
            helper.setTo(email); // 수신자 이메일
            helper.setSubject(title); // 이메일 제목
            helper.setText(content, true); // 이메일 내용 (HTML 형식)

            // 이메일 전송
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
        }
    }
    


 // 인증 코드 확인 로직
    @PostMapping("/verifyCode")
    @ResponseBody
    public String verifyCode(@RequestParam String enteredCode, @RequestParam String authcode) {

        // 입력된 코드와 비교
        if (enteredCode != null && enteredCode.equals(authcode)) {
            // 인증 성공
            return "ok";
        } else {
            // 인증 실패
            return "fail";
        }
    }



    /*
    // 아이디 존재 여부 확인 처리
    @PostMapping("/exsitsId")
    @ResponseBody
    public String exsitsId (@RequestParam("id") String memberId) {
        return memberService.getExistsId(memberId);
    }

    */
    }
