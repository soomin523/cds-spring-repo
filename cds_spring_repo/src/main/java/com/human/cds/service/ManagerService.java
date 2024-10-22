package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.MemberVO;  // MemberVO가 정의된 패키지를 import

public interface ManagerService {

	List<MemberVO> getMemberList();

	int deleteMember(String m_id);

}
