package com.human.cds.controller;

import org.springframework.web.bind.annotation.*;

import com.human.cds.service.MemberService;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/ajax")
public class MemberAjaxController {

    @Autowired
    private MemberService memberService;

    // 이메일 중복 여부를 비동기적으로 확인하는 메소드
    @PostMapping("/validateEmail")
    @ResponseBody
    public ResponseEntity<String> validateEmail(@RequestParam("email") String email) {
        boolean isAvailable = memberService.isEmailAvailable(email);
        if (isAvailable) {
            return ResponseEntity.ok("이메일을 사용할 수 있습니다.");
        } else {
            return ResponseEntity.status(400).body("이 이메일은 이미 사용 중입니다.");
        }
    }
}
