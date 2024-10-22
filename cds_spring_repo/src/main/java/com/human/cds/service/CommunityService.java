package com.human.cds.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.human.cds.vo.CommunityVO;

public interface CommunityService {
    boolean addComment(int postId, String username, String comment); // 댓글 추가
    boolean addLike(int postId, String username); // 좋아요 추가
    boolean deleteComment(CommunityVO commentVO); // 댓글 삭제
    List<CommunityVO> getCommentsByContentId(String contentId, int offset, int pageSize); // 특정 게시물에 대한 댓글 목록 조회
    int getCommentCount(String contentId); // 댓글 수 조회
	boolean deleteComment(String commentId);
	void savePost(String title, String content, String region, MultipartFile[] images);
	List<CommunityVO> getCommunityList();
}
