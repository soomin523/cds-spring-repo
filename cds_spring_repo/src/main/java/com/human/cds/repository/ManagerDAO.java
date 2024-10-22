package com.human.cds.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.MemberVO;

@Repository
public class ManagerDAO {

	// MemberMapper.xml파일을 구분하기 위한 네임스페이스
	private static final String MAPPER = "com.human.cds.mapper.ManagerMapper";

	// MyBatis를 이용해서 DB작업을 하는데 핵심적인 역할을 하는 객체: SqlSession
	private SqlSession sqlSession;

	@Autowired
	public ManagerDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<MemberVO> getMemberList() {
		return sqlSession.selectList(MAPPER+".getMemberList");
	}

	public int deleteMember(String m_id) {
		return sqlSession.delete(MAPPER+".deleteMember", m_id);
	}
}