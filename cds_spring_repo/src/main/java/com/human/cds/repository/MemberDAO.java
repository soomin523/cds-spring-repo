package com.human.cds.repository;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.cds.vo.MemberVO;

@Repository
public class MemberDAO {

	// MemberMapper.xml파일을 구분하기 위한 네임스페이스
	private static final String MAPPER = "com.human.cds.mapper.MemberMapper";

	// MyBatis를 이용해서 DB작업을 하는데 핵심적인 역할을 하는 객체: SqlSession
	private SqlSession sqlSession;

	@Autowired
	public MemberDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 회원 가입
	public boolean insertMember(MemberVO member) {
		System.out.println(member.getMember_id());
		
		member.setEmail(member.getEmail_prefix()+member.getEmail_domain());

		try {
			if (sqlSession.insert(MAPPER + ".insertMember", member) == 1)
				return true;
		} catch (Exception e) {
			System.out.println("회원가입 중 예외발생");
			e.printStackTrace();
		}

		return false;
	}

	// 아이디 중복검사: ajax통신
	@ResponseBody
	public boolean checkId(String member_id) {
		int result = 0;
		result = (Integer)sqlSession.selectOne(MAPPER + ".checkId", member_id);
		try {
			if (result == 1)
				return true;
		} catch (Exception e) {
			System.out.println("아이디 중복검사 중 예외 발생");
		}

		return false;
	}

	public MemberVO login(Map<String, String> map) {
		MemberVO vo = null;
		try {
			vo = sqlSession.selectOne(MAPPER+".login", map);
		} catch (Exception e) {
			System.out.println("로그인 중 예외 발생");
			e.printStackTrace();
		}

		return vo;
	}

	public MemberVO findMemberId(Map<String, String> map) {
		MemberVO vo = null;
		try {
			vo = sqlSession.selectOne(MAPPER+".idFind", map);
		} catch (Exception e) {
			System.out.println("아이디 찾기 중 예외 발생");
			e.printStackTrace();
		}

		return vo;
	}

	public MemberVO findMemberPassword(Map<String, String> map) {
		MemberVO vo = null;
		try {
			vo = sqlSession.selectOne(MAPPER+".passwordFind", map);
		} catch (Exception e) {
			System.out.println("비밀번호 찾기 중 예외 발생");
			e.printStackTrace();
		}

		return vo;
	}

	public int googleLogin(MemberVO member) {
		int number = sqlSession.selectOne(MAPPER+".memberCount");
		
		member.setMember_id("google"+number);
		number++;
		
		return sqlSession.insert(MAPPER+".googleLogin", member);
	}

	public int kakaoLogin(MemberVO vo) {
		int number = sqlSession.selectOne(MAPPER+".memberCount");
		
		vo.setMember_id("kakao"+number);
		number++;
		
		return sqlSession.insert(MAPPER+".kakaoLogin", vo);
	}

	public int naverLogin(MemberVO member) {
		int number = sqlSession.selectOne(MAPPER+".memberCount");
		
		member.setMember_id("naver"+number);
		number++;
		
		return sqlSession.insert(MAPPER+".naverLogin", member);
	}

	public boolean isEmailAvailable(String email) {
		
		boolean result = true;
		
		if((int)sqlSession.selectOne(MAPPER+".checkEmailDuplicate", email) == 1) {
			result = false;
		}
		
		return result;
	}

	public boolean isPhoneDuplicate(String phone) {
		
		boolean result = false;
		
		if((int)sqlSession.selectOne(MAPPER+".checkPhoneDuplicate", phone) == 1) {
			result = true;
		}
		
		return result;
	}
}