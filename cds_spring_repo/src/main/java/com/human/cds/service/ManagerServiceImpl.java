package com.human.cds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.ManagerDAO;
import com.human.cds.vo.MemberVO;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	private ManagerDAO dao;

	@Autowired
	public ManagerServiceImpl(ManagerDAO dao) {
		this.dao = dao;
	}
	
	//관리자 회원관리페이지 회원 조회
	@Override
	public List<MemberVO> getMemberList() {
		return dao.getMemberList();
	}

	@Override
	public int deleteMember(String m_id) {
		return dao.deleteMember(m_id);
	}
		
}
