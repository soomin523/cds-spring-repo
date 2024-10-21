package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.CommentVO;
import com.human.cds.vo.CourseInfoVO;
import com.human.cds.vo.DestinationDBVO;
import com.human.cds.vo.MemberVO;

public interface MypageService {

	List<DestinationDBVO> getDestinationList();

	int updateMyInfo(MemberVO vo);

	int cancel(int m_id);

	List<CommentVO> getCommentsByMemberId(String memberId);

	CourseInfoVO getCourseInfoByContentId(String contentId);




	

}
