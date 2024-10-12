package com.human.cds.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.SupportVO;

@Repository
public class SupportDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public static final String MAPPER = "com.human.cds.mapper.SupportMapper";

	public List<SupportVO> getNoticeList() {
		return sqlSession.selectList(MAPPER+".getNoticeList");
	}

	public List<SupportVO> getGuideList() {
		return sqlSession.selectList(MAPPER+".getGuideList");
	}

	public List<SupportVO> getQuestionList() {
		return sqlSession.selectList(MAPPER+".getQuestionList");
	}

	public int insertSupport(SupportVO vo) {
		return sqlSession.insert(MAPPER+".insertSupport", vo);
	}

	public SupportVO getsupport(String s_idx) {
		return sqlSession.selectOne(MAPPER+".getsupport", s_idx);
	}
	
	public int updateSupport(SupportVO vo) {
		return sqlSession.update(MAPPER+".updateSupport", vo);
	}

	public void deleteSupport(String s_idx) {
		sqlSession.update(MAPPER+".deleteSupport", s_idx);
	}


}
