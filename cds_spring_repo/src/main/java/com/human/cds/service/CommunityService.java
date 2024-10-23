package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.CommunityVO;

public interface CommunityService {
   
    // 게시물 목록 조회
    List<CommunityVO> getCommunityList();

	CommunityVO getCommunity(int id);

	int uploadPost(CommunityVO vo);

	List<CommunityVO> getLocationList(String location);
}

