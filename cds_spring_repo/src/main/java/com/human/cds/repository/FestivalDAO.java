package com.human.cds.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

	public List<Festival> getFestivalList() {
		
		List<Festival> festivalList = null;
		
		try {
			festivalList = sqlSession.selectList(MAPPER+".getFestivalList");
			
		} catch (Exception e) {
			System.out.println("getFestivalList DAO 동작 중 오류 발생");
			e.printStackTrace();
		}
		
		return festivalList;
	}

	public List<Festival> getFestivalAreaSelectList(String areaCode) {
		
		List<Festival> festivalList = null;
		
		try {
			festivalList = sqlSession.selectList(MAPPER+".getFestivalAreaSelectList", areaCode);
			
		} catch (Exception e) {
			System.out.println("getFestivalAreaSelectList DAO 동작 중 오류 발생");
			e.printStackTrace();
		}
		
		return festivalList;
	}

	public int updateAreaName(Map<String, String> map) {
		FestivalVO vo = null;
		
		try {
			
		} catch (Exception e) {
			System.out.println("updateAreaName DAO 동작 중 오류 발생");
			e.printStackTrace();
		}
		
		return 0;
	}

}
