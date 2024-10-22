package com.human.cds.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.human.cds.service.CommunityService; // 커뮤니티 서비스 추가
import com.human.cds.vo.CommunityVO;
import com.human.cds.vo.MemberVO;

@Controller
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService; // 커뮤니티 서비스 주입

    // 커뮤니티 메인 페이지로 이동 (community.jsp)
    @GetMapping("/commu")
    public String communityMain(Model model) {
    	
    	//기존에 저장된 커뮤니티 테이블의 데이터 가져오기
    	List<CommunityVO> communityList = communityService.getCommunityList();
    	model.addAttribute("communityList", communityList);
    	
        return "community/community";  // community.jsp로 이동
    }

    // 게시물 업로드 페이지로 이동 (uploadPost.jsp)
    @GetMapping("/upload.do")
    public String communityUpload() {
        return "community/uploadPost";  // uploadPost.jsp로 이동
    }
    
    @PostMapping("/checkLoginStatus.do")
    @ResponseBody
    public String checkLoginStatus(HttpSession session) {
        MemberVO member = (MemberVO) session.getAttribute("member"); // 세션에서 member 객체 가져오기
        System.out.println(member);
        
        return (member != null) ? "loggin" : "belogin";
    }
    

    
    // 게시물 업로드 처리
    @PostMapping("/uploadPost")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> uploadPost(
        @RequestParam("title") String title,
        @RequestParam("content") String content,
        @RequestParam("region") String region,
        @RequestParam("images") MultipartFile[] images,
        HttpSession session // 세션 추가
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 로그인 여부 확인
            if (session.getAttribute("loginUser") == null) {
                response.put("success", false);
                response.put("message", "로그인 후 게시물을 업로드 해주세요.");
                return ResponseEntity.status(403).body(response);
            }

            // 유효성 검사
            if (title.isEmpty() || content.isEmpty() || region.isEmpty()) {
                response.put("success", false);
                response.put("message", "모든 필드를 입력해주세요.");
                return ResponseEntity.badRequest().body(response);
            }

            // 파일 및 게시물 데이터 처리 (예: 데이터베이스에 저장)
            for (MultipartFile file : images) {
                if (!file.isEmpty()) {
                    // 파일 저장 로직 추가 (예: 서버 디렉터리에 저장하거나 DB에 저장)
                    String filePath = "/path/to/upload/directory/" + file.getOriginalFilename();
                    file.transferTo(new File(filePath)); // 파일 저장
                }
            }

            // 게시물 데이터베이스 저장 (예: communityService 사용)
            communityService.savePost(title, content, region, images);

            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // 예외를 로그에 기록
            response.put("success", false);
            return ResponseEntity.status(500).body(response);
        }
    }
}
