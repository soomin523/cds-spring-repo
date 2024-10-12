package com.human.cds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.SupportDAO;
import com.human.cds.vo.SupportVO;

@Service
public class SupportServiceImpl implements SupportService {
	
	@Autowired
	private SupportDAO dao;

	@Override
	public List<SupportVO> getNoticeList() {
		return dao.getNoticeList();
	}

	@Override
	public List<SupportVO> getGuideList() {
		return dao.getGuideList();
	}

	@Override
	public List<SupportVO> getQuestionList() {
		return dao.getQuestionList();
	}

	@Override
	public int insertSupport(SupportVO vo) {
		return dao.insertSupport(vo);
	}

	@Override
	public SupportVO getsupport(String s_idx) {
		return dao.getsupport(s_idx);
	}
	
	@Override
	public int updateSupport(SupportVO vo) {
		return dao.updateSupport(vo);
	}

	@Override
	public void deleteSupport(String s_idx) {
		dao.deleteSupport(s_idx);
	}

}
