package com.human.cds.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.CourseInfoDTO;


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

}
