package com.human.cds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.CommunityDAO;
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
}
