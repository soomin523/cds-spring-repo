package com.human.cds.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.AcommoImgVO;
import com.human.cds.vo.AcommointroVO;
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

	public List<Map<String, Object>> getTitleAndContentId() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(MAPPER + ".getTitleAndContentId");
	}

	public void accomoupdate(String contentId, String overview) {
		Map<String, Object> params = new HashMap<>();
		params.put("contentId", contentId);
		params.put("overview", overview);

		sqlSession.update(MAPPER + ".accomoupdate", params);

	}

	public void insertRoomInfo(AcommointroVO.Item item) {
	    sqlSession.insert(MAPPER + ".insertRoomInfo", item); // item의 모든 필드가 함께 삽입됨
	}

	public List<AcommoImgVO> accoImg() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(MAPPER+".accoImg");
	}

}
