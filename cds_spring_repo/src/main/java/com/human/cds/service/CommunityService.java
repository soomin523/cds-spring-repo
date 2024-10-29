package com.human.cds.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.human.cds.vo.CommunityContentVO;
import com.human.cds.vo.CommunityImgVO;
import com.human.cds.vo.CommunityVO;

public interface CommunityService {
   
    // 게시물 목록 조회
    List<CommunityVO> getCommunityList();

	CommunityVO getCommunity(int id);

	int uploadPost(CommunityVO vo);

	List<CommunityVO> getLocationList(String location);

	List<CommunityVO> getCommupost(String select, String area);

	List<CommunityVO> getSearchList(String search);

	int insertComment(String memberId, String content, String c_idx);

	CommunityContentVO getComment(int c_idx);

	int insertLike(String memberId, String c_idx);

	int deletePost(int c_idx);

	int deleteComment(int comment_id);

	int updatePost(CommunityVO vo);

	void saveImage(CommunityImgVO imgVO);

	int uploadPost(MultipartFile[] uploadFiles, HttpServletRequest request, CommunityVO vo);

}

