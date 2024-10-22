package com.human.cds.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.human.cds.repository.CommunityDAO;  
import com.human.cds.vo.CommunityVO;  

@Service
public class CommunityServiceImpl implements CommunityService { // abstract 제거
    
    @Autowired
    private CommunityDAO communityDAO;  

    @Override
    public boolean addComment(int postId, String username, String comment) {
        CommunityVO communityVO = new CommunityVO();
        communityVO.setC_idx(postId);  
        communityVO.setMemberId(username); 
        communityVO.setComment(comment); 
        return communityDAO.insertComment(communityVO) > 0; 
    }

    @Override
    public boolean deleteComment(String commentId) {
        return communityDAO.deleteComment(commentId) > 0; 
    }

    @Override
    public List<CommunityVO> getCommentsByContentId(String contentId, int offset, int pageSize) {
        return communityDAO.getCommentsByContentId(contentId, offset, pageSize); 
    }

    @Override
    public int getCommentCount(String contentId) {
        return communityDAO.countCommentsByContentId(contentId); 
    }

    @Override
    public boolean addLike(int postId, String username) {
        return communityDAO.insertLike(postId, username) > 0; 
    }

    @Override
    public boolean deleteComment(CommunityVO commentVO) {
        // 댓글 삭제 구현 (추가 필요)
        return false; // 구현이 필요합니다.
    }

    @Override
    public void savePost(String title, String content, String region, MultipartFile[] images) {
        // CommunityVO 객체 생성 및 값 설정
        CommunityVO communityVO = new CommunityVO();
        communityVO.setTitle(title);
        communityVO.setContent(content);
        communityVO.setLocation(region);

        // 게시물 저장
        communityDAO.insertPost(communityVO);
        
        // 게시물 ID 가져오기
        int postId = communityVO.getC_idx(); // 게시물 ID를 DAO 메서드에 설정된 대로 가져옴

        // 이미지 저장 로직
        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                // 파일 이름 생성 (현재 시간 + 원본 파일명)
                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                // 파일을 저장할 경로 설정 (예: "/path/to/upload/directory/" + fileName)
                Path path = Paths.get("/path/to/upload/directory/" + fileName);
                try {
                    // 이미지 파일을 서버에 저장
                    Files.write(path, image.getBytes());
                    // 이미지 경로를 데이터베이스에 저장
                    communityDAO.insertImagePath(postId, fileName); // 게시물 ID와 이미지 경로를 DB에 저장
                } catch (IOException e) {
                    e.printStackTrace(); // 예외 처리
                }
            }
        }
    }

	@Override
	public List<CommunityVO> getCommunityList() {
		return communityDAO.getCommunityList();
	}
}
