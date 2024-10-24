package com.human.cds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.human.cds.service.CommunityService;
import com.human.cds.vo.CommunityContentVO;
import com.human.cds.vo.CommunityVO;

@RestController
@RequestMapping("/community") // 공통으로 적용되는 URL 정의
public class CommunityAjaxController {
    
    @Autowired
    private CommunityService communityService; // 커뮤니티 서비스 주입
    
    @GetMapping("/getcommunity.do")
    public CommunityVO getCommunity(@RequestParam int id){
    	return communityService.getCommunity(id);
    }
    
    @GetMapping("insertComment.do")
    public CommunityContentVO insertComment(@RequestParam String memberId, @RequestParam String content,
    		@RequestParam String c_idx) {
    	CommunityContentVO vo = null;
    	if(communityService.insertComment(memberId, content, c_idx) == 1) {
    		vo = communityService.getComment(Integer.parseInt(c_idx));
    	}
    	
    	return vo;
    }

    @GetMapping("insertLike.do")
    public int insertLike(@RequestParam String memberId,@RequestParam String c_idx) {
    	return communityService.insertLike(memberId, c_idx);
    }

    
}
