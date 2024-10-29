package com.human.cds.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.human.cds.service.CommunityService;
import com.human.cds.vo.CommunityVO;

@Controller
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService; 

    //커뮤니티 페이지 이동
    @GetMapping("/commu")
    public String communityMain(Model model) {
    	
        List<CommunityVO> communityList = communityService.getCommunityList();
        model.addAttribute("communityList", communityList);
        
        return "community/community"; 
    }

    //게시글 작성 페이지 이동
    @GetMapping("/upload.do")
    public String communityUpload() {
        return "community/uploadPost";  
    }
    
    //게시글 수정 페이지 이동
    @GetMapping("updatePage.do")
    public String updatePage(String c_idx, Model model) {
    	int id = Integer.parseInt(c_idx);
    	
    	CommunityVO vo = communityService.getCommunity(id);
    	model.addAttribute("post", vo);
    	
    	return "community/updatePost";
    }

    //게시글 추가하기
//    @PostMapping("/uploadPost.do")
//    public String uploadPost(CommunityVO vo, Model model){
//        
//    	String viewName = "community/uploadPost";
//    	
//    	if(communityService.uploadPost(vo) == 1) {
//    		List<CommunityVO> communityList = null;
//    		communityList = communityService.getCommunityList();
//    		model.addAttribute("communityList", communityList);
//    		viewName = "community/community";
//    	}else {
//    		model.addAttribute("msg", "게시물 등록에 실패하였습니다.");
//    	}
//    	
//    	return viewName;
//    }
    
    //지역별 목록 조회하기
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
    
    //최신/평점순 + 지역별 목록 조회
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
    
    //검색어 목록 조회
    @GetMapping("/getSearchList.do")
    public String getSearchList(String search, Model model) {
    	List<CommunityVO> vo = null;
    	vo = communityService.getSearchList(search);
    	if(vo != null) {
    		model.addAttribute("communityList", vo);
    	}
    	
    	return "community/community";
    }
    
    @PostMapping("/updatePost.do")
    public String updatePost(CommunityVO vo, Model model) {
        String viewName = "community/updatePost";

        if (communityService.updatePost(vo) == 1) {
            List<CommunityVO> communityList = communityService.getCommunityList();
            model.addAttribute("communityList", communityList);
            viewName = "community/community";
        } else {
            model.addAttribute("msg", "게시물 수정에 실패하였습니다.");
        }

        return viewName;
    }

    // 커뮤니티 게시글 추가 메서드
    @PostMapping("/uploadPost.do")
    public String uploadPost(@RequestParam("imagenames") MultipartFile[] uploadFiles,
                              @ModelAttribute CommunityVO vo, HttpServletRequest request, Model model) {
    	
    	//게시글 추가 실패시 뷰이름
    	String viewName = "community/uploadPost";
    	
    	int result = communityService.uploadPost(uploadFiles, request, vo);
    	
    	if(result == 1) {//게시글 추가 성공
    		List<CommunityVO> communityList = communityService.getCommunityList();
            model.addAttribute("communityList", communityList);
            model.addAttribute("msg", "파일 업로드 성공");
            
    		viewName = "community/community";
    	}
    	
    	 return viewName; // 성공 페이지로 이동    
    }


}

