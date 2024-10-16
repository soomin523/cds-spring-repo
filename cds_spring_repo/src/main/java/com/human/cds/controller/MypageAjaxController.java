package com.human.cds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.human.cds.service.MypageLikeService;
import com.human.cds.vo.MypageLikeVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MypageAjaxController {

	@GetMapping("/mypage/mypagelike_course.do")
	public String redirectToPage() {
	    return "/mypage/mypagelike_course"; // 클라이언트에서 사용할 URL
	}
}
