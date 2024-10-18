package com.human.cds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.MypageDAO;
import com.human.cds.vo.DestinationDBVO;
import com.human.cds.vo.MemberVO;

@Service
public class MypageServiceImpl implements MypageService {

	private MypageDAO dao;
	
	@Autowired
	public MypageServiceImpl(MypageDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public List<DestinationDBVO> getDestinationList() {
		
		return dao.getDestinationList();
	}

	@Override
	public int updateMyInfo(MemberVO vo) {
		return dao.updateMyInfo(vo);
	}

	@Override
	public int cancel(int m_id) {
		return dao.cancel(m_id);
	}

}
