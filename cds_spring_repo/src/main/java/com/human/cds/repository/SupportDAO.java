package com.human.cds.repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.SupportVO;

@Repository
public class SupportDAO {

	private SqlSession sqlSession;

	@Autowired
	public SupportDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public static final String MAPPER = "com.human.cds.mapper.SupportMapper";

	public List<SupportVO> getNoticeList() {
		List<SupportVO> supportList = sqlSession.selectList(MAPPER + ".getNoticeList");
		for(SupportVO vo : supportList) {
			correctMySQLDate(vo);
		}
		return supportList;
		
	}

	public List<SupportVO> getGuideList() {
		List<SupportVO> supportList = sqlSession.selectList(MAPPER + ".getGuideList");
		for(SupportVO vo : supportList) {
			correctMySQLDate(vo);
		}
		return supportList;
	}

	public List<SupportVO> getQuestionList() {
		List<SupportVO> supportList = sqlSession.selectList(MAPPER + ".getQuestionList");
		for(SupportVO vo : supportList) {
			correctMySQLDate(vo);
		}
		return supportList;
	}

	public int insertSupport(SupportVO vo) {
		return sqlSession.insert(MAPPER + ".insertSupport", vo);
	}

	public SupportVO getsupport(String s_idx) {
		SupportVO supportone = sqlSession.selectOne(MAPPER + ".getsupport", s_idx);
		correctMySQLDate(supportone);
		
		return supportone;
	}

	public int updateSupport(SupportVO vo) {
		return sqlSession.update(MAPPER + ".updateSupport", vo);
	}

	public void deleteSupport(String s_idx) {
		sqlSession.update(MAPPER + ".deleteSupport", s_idx);
	}

	public List<SupportVO> getSupportList() {
		List<SupportVO> supportList = sqlSession.selectList(MAPPER + ".getSupportList");
		
		for(SupportVO vo : supportList) {
			correctMySQLDate(vo);
		}
		return supportList;
	}

	public void correctMySQLDate(SupportVO vo) { // 매개변수는 시간 필드를 가지는 VO객체

		// Date객체를 LocalDateTime객체로 변경하기

		// 1. 등록일: reg_date
		// System.out.println("등록일: "+vo.getReg_date());
		LocalDateTime localDateTime = vo.getPost_date().toInstant() // Date -> Instant
				.atZone(ZoneId.systemDefault()) // Instant -> ZonedDateTime
				.toLocalDateTime(); // ZonedDateTime -> LocalDateTime

		// MySQL에 저장된 날짜 그대로 사용하기 위해서 가져온 날짜값에서 9시간 빼기
		LocalDateTime updatedLocalDateTime = localDateTime.minusHours(9);

		// 변경된 LocalDateTime객체를 다시 Date객체로 변경해서 reg_date값을 다시 세팅해줌
		Instant instant = updatedLocalDateTime.atZone(ZoneId.systemDefault()).toInstant();
		Date correctReg_date = Date.from(instant);
		// Date correctReg_date = Date.from(instant); //java.util.Date클래스

		vo.setPost_date(correctReg_date);
		// System.out.println("정정된 등록일: "+vo.getReg_date());

		// 2. 수정일: update_date
		localDateTime = vo.getUpdate_date().toInstant() // Date -> Instant
				.atZone(ZoneId.systemDefault()) // Instant -> ZonedDateTime
				.toLocalDateTime(); // ZonedDateTime -> LocalDateTime

		// MySQL에 저장된 날짜 그대로 사용하기 위해서 가져온 날짜값에서 9시간 빼기
		updatedLocalDateTime = localDateTime.minusHours(9);

		// 변경된 LocalDateTime객체를 다시 Date객체로 변경해서 reg_date값을 다시 세팅해줌
		instant = updatedLocalDateTime.atZone(ZoneId.systemDefault()).toInstant();
		Date correctUpdate_date = Date.from(instant);
		// Date correctUpdate_date = Date.from(instant); //java.util.Date클래스

		vo.setUpdate_date(correctUpdate_date);
	}

}
