package com.human.cds.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.FestivalDBVO;
import com.human.cds.vo.FestivalModalVO;
import com.human.cds.vo.FestivalVO;
import com.human.cds.vo.FestivalVO.Festival;

@Repository
public class FestivalDAO {
	
	private final SqlSession sqlSession;
	@Autowired
	public FestivalDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public static final String MAPPER = "com.human.cds.mapper.FestivalMapper";

	public int insertFestival(FestivalVO data) {
		int result = 0;
		
		try {
			
			List<Festival> items = data.getResponse().getBody().getItems().getItem();
			
			for(Festival festival : items) {
				result += sqlSession.insert(MAPPER+".insertFestival", festival);
			}
			
		} catch (Exception e) {
			System.out.println("insertFastival DAO 동작 중 오류 발생");
			e.printStackTrace();
		}
		
		return result;
	}

	public List<FestivalDBVO> getFestivalList() {
		
		List<FestivalDBVO> festivalList = null;
		
		try {
			festivalList = sqlSession.selectList(MAPPER+".getFestivalList");
			
		} catch (Exception e) {
			System.out.println("getFestivalList DAO 동작 중 오류 발생");
			e.printStackTrace();
		}
		
		return festivalList;
	}

	public List<FestivalDBVO> getFestivalAreaSelectList(String areaCode) {
		
		List<FestivalDBVO> festivalList = null;
		
		try {
			festivalList = sqlSession.selectList(MAPPER+".getFestivalAreaSelectList", areaCode);
			
		} catch (Exception e) {
			System.out.println("getFestivalAreaSelectList DAO 동작 중 오류 발생");
			e.printStackTrace();
		}
		
		return festivalList;
	}

	public int updateAreaName(Map<String, String> map) {
		int result = 0;
		
		try {
			result = sqlSession.update(MAPPER+".updateAreaName", map);
			
		} catch (Exception e) {
			System.out.println("updateAreaName DAO 동작 중 오류 발생");
			e.printStackTrace();
		}
		
		return result;
	}

	public List<FestivalDBVO> getFestivalDateSelectList(String selectDate) {
		
		List<FestivalDBVO> festivalList = null;
		
		try {
			festivalList = sqlSession.selectList(MAPPER+".getFestivalDateSelectList", selectDate);
			
		} catch (Exception e) {
			System.out.println("getFestivalDateSelectList DAO 동작 중 오류 발생");
			e.printStackTrace();
		}
		
		return festivalList;
	}

	public List<FestivalDBVO> getFestivalAreaDateSelectList(Map<String, String> map) {
		
		List<FestivalDBVO> festivalList = null;
		
		try {
			festivalList = sqlSession.selectList(MAPPER+".getFestivalAreaDateSelectList", map);
			
		} catch (Exception e) {
			System.out.println("getFestivalAreaDateSelectList DAO 동작 중 오류 발생");
			e.printStackTrace();
		}
		
		return festivalList;
	}

	public FestivalModalVO getFestival(String contentid) {
		
		FestivalModalVO vo = null;
		
		try {
			vo = sqlSession.selectOne(MAPPER+".getFestival", contentid);
			
		} catch (Exception e) {
			System.out.println("getFestival DAO 동작 중 오류 발생");
		}
		
		return vo;
	}

}
