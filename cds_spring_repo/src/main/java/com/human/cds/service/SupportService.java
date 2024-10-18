package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.SupportVO;

public interface SupportService {

	List<SupportVO> getNoticeList();
	
	List<SupportVO> getGuideList();
	
	List<SupportVO> getQuestionList();

	int insertSupport(SupportVO vo);

	SupportVO getsupport(String s_idx);
	
	int updateSupport(SupportVO vo);

	void deleteSupport(String s_idx);

	List<SupportVO> getSuppportList();


}
