package com.human.cds.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MypageAjaxController {

	//즐겨찾기 목록 ajax컨트롤러
	@GetMapping("/mypage/mypagelike_course.do")//js에서 입력받는 값(요청받은값)
	public String redirectToCoursePage() {
	    return "/cds/mypage/mypagelike.do"; // ajax 응답결과값
	}
	
	@GetMapping("/mypage/mypagelike_festival.do")
	public String redirectToFestivalPage() {
	    return "/cds/mypage/mypagelike.do"; // ajax 응답결과값
	}
	
	@GetMapping("/mypage/mypagelike_product.do")
	public String redirectToProductPage() {
	    return "/cds/mypage/mypagelike.do"; // ajax 응답결과값
	}
	
	//작성내역 목록 ajax 컨트롤러
	@GetMapping("/mypage/mypagewrite_post")
	public String redirectToPostPage() {
		return "/cds/mypage/mypagewrite.do";
	}
	
	@GetMapping("/mypage/mypagewrite_comment")
	public String redirectToCommentPage() {
		return "/cds/mypage/mypagewrite.do";
	}
}
