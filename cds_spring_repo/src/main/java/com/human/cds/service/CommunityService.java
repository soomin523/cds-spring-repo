package com.human.cds.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.human.cds.vo.CommunityVO;

public interface CommunityService {
    // 댓글 추가
    boolean addComment(int postId, String username, String comment);
    
    // 좋아요 추가
    boolean addLike(int postId, String username);
    
    // 댓글 삭제
    boolean deleteComment(CommunityVO commentVO);
    
    // 특정 게시물에 대한 댓글 목록 조회
    List<CommunityVO> getCommentsByContentId(String contentId, int offset, int pageSize);
    
    // 댓글 수 조회
    int getCommentCount(String contentId);
    
    // 댓글 삭제 (댓글 ID로 삭제)
    boolean deleteComment(String commentId);
    
    // 게시물 저장 메서드 (제목, 내용, 지역, 위치, 평점, 이미지 포함)
    void savePost(String title, String content, String region, String location, int rating, MultipartFile[] images);
    
    // 게시물 목록 조회
    List<CommunityVO> getCommunityList();

    //이미지 첨부
	void savePost(String title, String content, String region, MultipartFile[] images);
}
