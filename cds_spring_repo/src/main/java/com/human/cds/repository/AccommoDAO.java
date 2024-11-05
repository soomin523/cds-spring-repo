package com.human.cds.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.AccommodationRoomVO;
import com.human.cds.vo.AccommodationVO;
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
				// contenttypeid가 32이고, first_image가 null이 아닌 경우만 삽입
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


	public AccommodationVO getAccommodationByContentId(String contentId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(MAPPER + ".getAccommodationByContentId", contentId);
	}

	public List<AccommodationRoomVO> getRoomsByContentId(String contentId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(MAPPER + ".getRoomsByContentId", contentId);
	}

	public List<AcommoImgVO> getAccommodationsByRegion(int areacode, int pageSize, int offset,String cat3) {
		Map<String, Object> params = new HashMap<>();
		params.put("areaCode", areacode);
		params.put("limit", pageSize);
		params.put("offset", offset);
		params.put("cat3", cat3);
		return sqlSession.selectList(MAPPER + ".getAccommodationsByRegion", params);
	}
	
	
	public List<AcommoImgVO> getFilteredAccommodationsByRegion(int areacode, int offset, int pageSize, String cat3,
			String search) {
		Map<String, Object> params = new HashMap<>();
	    params.put("areaCode", areacode);
	    params.put("limit", pageSize);
	    params.put("offset", offset);
	    params.put("cat3", cat3);
	    params.put("search",search);
		// TODO Auto-generated method stub
		return sqlSession.selectList(MAPPER+ ".getFilteredAccommodationsByRegion",params);
	}

	public void incrementcnt(String contentId) {
		sqlSession.update(MAPPER+".incrementcnt",contentId);
		
	}

}
