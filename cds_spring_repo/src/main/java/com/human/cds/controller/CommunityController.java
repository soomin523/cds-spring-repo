package com.human.cds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/community")
public class CommunityController {

    // 커뮤니티 메인 페이지로 이동 (community.jsp)
    @GetMapping("/commu")
    public String communityMain() {
        return "community/community";  // community.jsp로 이동
    }

    // 게시물 업로드 페이지로 이동 (uploadPost.jsp)
    @GetMapping("/upload.do")
    public String communityUpload() {
        return "community/uploadPost";  // uploadPost.jsp로 이동
    }
    
    // 커뮤니티 보기 페이지로 이동 (community.jsp)
    @RequestMapping("/commu.do")
    public String viewCommunityPage() {
        return "community/community";  // community.jsp로 이동
    }

    // 게시물 업로드 처리
    @PostMapping("/uploadPost")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> uploadPost(
        @RequestParam("title") String title,
        @RequestParam("content") String content,
        @RequestParam("region") String region,
        @RequestParam("images") MultipartFile[] images
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 파일 및 게시물 데이터 처리 (예: 데이터베이스에 저장)
            for (MultipartFile file : images) {
                if (!file.isEmpty()) {
                    // 파일 저장 로직 추가 (예: 서버 디렉터리에 저장하거나 DB에 저장)
                }
            }
            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            return ResponseEntity.status(500).body(response);
        }
    }
}
