package com.human.cds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.human.cds.service.CommunityService;
import com.human.cds.vo.CommunityVO;

@Controller
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService; 

    @GetMapping("/commu")
    public String communityMain(Model model) {
    	
        List<CommunityVO> communityList = communityService.getCommunityList();
        model.addAttribute("communityList", communityList);
        
        return "community/community"; 
    }

    @GetMapping("/upload.do")
    public String communityUpload() {
        return "community/uploadPost";  
    }

    @PostMapping("/uploadPost.do")
    public String uploadPost(CommunityVO vo, Model model){
        
    	String viewName = "community/uploadPost";
    	
    	if(communityService.uploadPost(vo) == 1) {
    		List<CommunityVO> communityList = null;
    		communityList = communityService.getCommunityList();
    		model.addAttribute("communityList", communityList);
    		viewName = "community/community";
    	}else {
    		model.addAttribute("msg", "게시물 등록에 실패하였습니다.");
    	}
    	
    	return viewName;
    }
    
    
    @GetMapping("/getLocationList.do")
    public String getLocationList(String location, Model model) {
    	
    	List<CommunityVO> communityList = null;
    	
    	if(location.equals("all")) {
    		communityList = communityService.getCommunityList();
    	}else {
    		communityList = communityService.getLocationList(location);    		
    	}
    	model.addAttribute("communityList", communityList);
		model.addAttribute("area", location); 
    	
    	return "community/community";
    }
    
    
    @GetMapping("/commupost.do")
    public String getCommupost(String select, String location, Model model) {
    	List<CommunityVO> vo = null;
    	vo = communityService.getCommupost(select, location);
    	
    	if(vo != null) {
    		model.addAttribute("communityList", vo);
    		model.addAttribute("area", location); 
    	}
    	
    	return "community/community";
    }
    
    @GetMapping("/getSearchList.do")
    public String getSearchList(String search, Model model) {
    	List<CommunityVO> vo = null;
    	vo = communityService.getSearchList(search);
    	if(vo != null) {
    		model.addAttribute("communityList", vo);
    	}
    	
    	return "community/community";
    }
    
}
