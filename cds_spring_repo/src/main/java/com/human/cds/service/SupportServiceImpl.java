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

}
