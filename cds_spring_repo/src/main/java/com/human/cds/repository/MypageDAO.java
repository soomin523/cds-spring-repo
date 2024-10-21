package com.human.cds.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.CommentVO;
import com.human.cds.vo.CourseInfoVO;
import com.human.cds.vo.DestinationDBVO;
import com.human.cds.vo.MemberVO;

@Repository
public class MypageDAO {

	private static final String MAPPER = "com.human.cds.mapper.MypageMapper"; 
	private SqlSession sqlSession;
	
	@Autowired
	public MypageDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<DestinationDBVO> getDestinationList() {
	
		return sqlSession.selectList(MAPPER+".getDestinationList");
	}



	public int updateMyInfo(MemberVO vo) {
		int result= 0;
		result = sqlSession.update(MAPPER+".updateMyInfo",vo);
		
		
		return result;
	}

	public int cancel(int m_id) {
		int result = 0;
		result = sqlSession.update(MAPPER+".cancel",m_id);
		return result;
	}

	public List<CommentVO> getCommentsByMemberId(String memberId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(MAPPER+".getCommentsByMemberId", memberId);
	}

	public CourseInfoVO getCourseInfoByContentId(String contentId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(MAPPER+".getCourseInfoByContentId",contentId);
	}
	
	
}
 