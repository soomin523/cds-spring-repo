package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.SupportVO;

public interface SupportService {

	List<SupportVO> getNoticeList();

	List<SupportVO> getGuideList();

	List<SupportVO> getQuestionList();


}
