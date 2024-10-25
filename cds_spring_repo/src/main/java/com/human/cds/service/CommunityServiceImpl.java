package com.human.cds.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.CommunityDAO;
import com.human.cds.vo.CommunityContentVO;
import com.human.cds.vo.CommunityVO;

@Service
public class CommunityServiceImpl implements CommunityService {

    private CommunityDAO communityDAO;

    @Autowired
    public CommunityServiceImpl(CommunityDAO communityDAO) {
    	this.communityDAO = communityDAO;
    }

    @Override
    public List<CommunityVO> getCommunityList() {
        return communityDAO.getCommunityList();
    }

	@Override
	public CommunityVO getCommunity(int id) {
		return communityDAO.getCommunity(id);
	}

	@Override
	public int uploadPost(CommunityVO vo) {
		return communityDAO.uploadPost(vo);
	}

	@Override
	public List<CommunityVO> getLocationList(String location) {
		return communityDAO.getLocationList(location);
	}

	@Override
	public List<CommunityVO> getCommupost(String select, String area) {
		Map<String, String> map = new HashMap<>();
		map.put("select", select);
		map.put("area", area);
		
		return communityDAO.getCommupost(map);
	}

	@Override
	public List<CommunityVO> getSearchList(String search) {
		return communityDAO.getSearchList(search);
	}

	@Override
	public int insertComment(String memberId, String content, String c_idx) {
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("content", content);
		map.put("c_idx", c_idx);
		
		return communityDAO.insertComment(map);
	}

	@Override
	public CommunityContentVO getComment(int c_idx) {
		return communityDAO.getComment(c_idx);
	}

	@Override
	public int insertLike(String memberId, String c_idx) {
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("c_idx", c_idx);
		
		return communityDAO.insertLike(map);
	}

	@Override
	public int deletePost(int c_idx) {
		return communityDAO.deletePost(c_idx);
	}

	@Override
	public int deleteComment(int comment_id) {
		return communityDAO.deleteComment(comment_id);
	}

	@Override
	public int updatePost(CommunityVO vo) {
		return communityDAO.updatePost(vo);
	}
}

