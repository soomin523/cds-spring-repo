package com.human.cds.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.DestinationDBVO;
import com.human.cds.vo.DestinationModalVO;


@Repository
public class DestinationDAO {

	@Autowired
	private  SqlSession sqlSession;
	public static final String MAPPER = "com.human.cds.mapper.DestinationMapper";

	
	//DB에 정보 집어넣기
	public int insertDestination(CourseInfoDTO data) {
		int result = 0;
		
		try {
			
			List<CourseInfoDTO.Item> items = data.getResponse().getBody().getItems().getItem();
			
			for(CourseInfoDTO.Item item : items) {
				if(item.getFirstimage() != null && item.getFirstimage() != "") {
					result += sqlSession.insert(MAPPER+".insertDestination", item);
				}

			}
			
		} catch (Exception e) {
			System.out.println("insertDestination DAO 동작 중 오류 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	//DB에 지역 코드 시군구 코드 조회
	public List<DestinationDBVO> getDestinationNameList() {
		
		//조회
		return sqlSession.selectList(MAPPER+".DestinationNameList");
	}


	public int updateAreaName(Map<String, String> map) {
		return sqlSession.update(MAPPER+".DestinationName",map);
	}


	public List<DestinationDBVO> getSigunguName(String areacode) {
		return sqlSession.selectList(MAPPER+".DestinationSigungu",areacode);
	}


	public List<DestinationDBVO> getDesList(String areacode , String sigungucode) {
		Map<String, String> map = new HashMap<>();
		map.put("areacode", areacode);
		map.put("sigungucode", sigungucode);
		System.out.println(map);
		return sqlSession.selectList(MAPPER+".DesList", map);
	}

	public List<DestinationDBVO> getDesNoList() {
		return sqlSession.selectList(MAPPER+".DesNoList");
	}

	public DestinationModalVO getDestinationInfoList(String contentid) {
		System.out.println("오버뷰 아이디가 안들어와");
		return sqlSession.selectOne(MAPPER+".DestinationInfoList", contentid);
	}
	
}
