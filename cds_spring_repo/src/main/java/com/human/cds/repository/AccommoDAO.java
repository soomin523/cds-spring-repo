package com.human.cds.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.CourseInfoDTO.Item;

@Repository
public class AccommoDAO {
	
	private final SqlSession sqlSession;

	@Autowired
	public AccommoDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public static final String MAPPER = "com.human.cds.mapper.AccomoMapper";


	public int insertAccommo(CourseInfoDTO data) {
		int result = 0;
		try {
			List<Item> items = data.getResponse().getBody().getItems().getItem();
			for (Item accommoInfo : items) {
				// contenttypeid가 25이고, first_image가 null이 아닌 경우만 삽입
				if ("32".equals(accommoInfo.getContenttypeid()) && accommoInfo.getFirstimage() != null
						&& !accommoInfo.getFirstimage().isEmpty()) {
					result += sqlSession.insert(MAPPER + ".insertaccommoInfo", accommoInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
}
