package com.human.cds.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
            viewName = "member/signupEnd"; // 성공 시 회원가입 완료 페이지로 이동
        } else {
            model.addAttribute("message", "회원가입에 실패했습니다.");
            viewName = "member/signup"; // 실패 시 다시 회원가입 페이지로 이동
        }

        return viewName;
    }

    
    // 로그아웃 처리
    @GetMapping("/logout.do")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return "redirect:/login.do"; // 로그아웃 후 로그인 페이지로 리다이렉트
    }

    
    @RestController
    @CrossOrigin("*")
    public class LoginController {
        @Value("${google.client.id}")
        private String googleClientId;
        @Value("${google.client.pw}")
        private String googleClientPw;

        @RequestMapping(value="/api/v1/oauth2/google", method = RequestMethod.POST)
        public String loginUrlGoogle(){
            String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                    + "&redirect_uri=http://localhost:8080/api/v1/oauth2/google&response_type=code&scope=email%20profile%20openid&access_type=offline";
            return reqUrl;
        }
    }
    
    
    //이메일 인증
    @PostMapping("/checkEmail.do")
    @ResponseBody
    public String checkEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        try {
            String code = memberServiceImpl.sendVerificationCode(email);  // 인증번호 전송
            return code;  // 인증번호 반환
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";  // 이메일 전송 실패 시 오류 반환
        }
    }

    //이메일 인증번호 확인
    @PostMapping("/verifyEmailCode")
    @ResponseBody
    public boolean verifyEmailCode(@RequestParam("email") String email, @RequestParam("code") String code) {
        return memberServiceImpl.verifyCode(email, code);  // 인증번호 확인
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

    // 아이디 찾기 처리
    @PostMapping("/idFind")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> findId(
        @RequestParam("name") String name,
        @RequestParam("email") String email) {
        
        Map<String, Object> response = new HashMap<>();
        MemberVO member = memberService.getMemberByEmail(email);
        
        if (member != null && member.getName().equals(name)) {
            response.put("success", true);
            response.put("id", member.getMember_id()); // Return found member ID
        } else {
            response.put("success", false);
            response.put("message", "No matching member found.");
        }
        
        return ResponseEntity.ok(response);
    }
    */
    // 아이디 중복 확인 처리
    @PostMapping("/checkDuplicate")
    @ResponseBody
    //public ResponseEntity<Map<String, Boolean>> checkDuplicate(@RequestBody Map<String, String> request) {
    public ResponseEntity<Map<String, Boolean>> checkDuplicate(String member_id) {
    	
    	
        //String memberId = request.get("member_id"); // 클라이언트에서 "member_id" 키로 데이터 수신
        
        System.out.println("member_id: "+member_id);
        
        boolean isDuplicate = memberServiceImpl.checkId(member_id);  // 서비스에서 중복 확인 로직 수행

        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);

        return ResponseEntity.ok(response); // 중복 여부를 클라이언트로 반환
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