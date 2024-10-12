package com.human.cds.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.cds.service.MemberService;
import com.human.cds.vo.MemberVO;

import lombok.AllArgsConstructor;

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
    public String registerMember(
        MemberVO vo) {
        String viewName = "member/login";
        
        boolean result = memberServiceImpl.registerMember(vo);
        
        if(result) {
        	viewName = "member/signupEnd";
        }
        
        return viewName;
    }
    
    /*

    // 로그인 처리
    @PostMapping("/login.do")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> loginMember(
        @RequestParam("member_id") String memberId,
        @RequestParam("password") String password) {
        
        Map<String, Object> response = new HashMap<>();
        MemberVO member = memberServiceImpl.getMemberById1(Integer.parseInt(memberId));  
        
        if (member != null && member.getPassword().equals(password)) {
            response.put("success", true);
        } else {
            response.put("success", false);
            response.put("message", "Invalid credentials");
        }

        return ResponseEntity.ok(response);
    }

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
    
 // 아이디 중복 확인 처리
    @PostMapping("/checkDuplicate")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> checkDuplicate(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        boolean isDuplicate = memberService.isUsernameTaken(username);  // 서비스에서 중복 확인 로직 수행

        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);

        return ResponseEntity.ok(response);
    }

    
    // 아이디 존재 여부 확인 처리
    @PostMapping("/exsitsId")
    @ResponseBody
    public String exsitsId (@RequestParam("id") String memberId) {
        return memberService.getExistsId(memberId);
    }
    
    */
}
