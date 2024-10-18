package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.DestinationDBVO;
import com.human.cds.vo.MemberVO;

public interface MypageService {

	List<DestinationDBVO> getDestinationList();

	int updateMyInfo(MemberVO vo);

	int cancel(int m_id);




	

}
