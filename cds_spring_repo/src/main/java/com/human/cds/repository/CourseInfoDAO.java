package com.human.cds.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.CourseInfoDTO.Item;
import com.human.cds.vo.CourseInfoVO;
import com.human.cds.vo.FestivalDBVO;

@Repository
public class CourseInfoDAO {

	private final SqlSession sqlSession;

	@Autowired
	public CourseInfoDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public static final String MAPPER = "com.human.cds.mapper.CourseInfoMapper";

	// contenttypeid가 25인 값들만 삽입
	public int insertCoursesWithType25(CourseInfoDTO data) {
	    int result = 0;
	    try {
	        // API 응답에서 아이템 리스트 가져오기
	        List<Item> items = data.getResponse().getBody().getItems().getItem();
	        
	        // 유효한 area_code 값 목록 정의
	        List<String> validAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "39");

	        for (Item courseInfo : items) {
	            // contenttypeid가 25이고, first_image가 존재하며, area_code가 유효한 값일 경우만 삽입
	            if ("25".equals(courseInfo.getContenttypeid()) 
	                    && courseInfo.getFirstimage() != null 
	                    && !courseInfo.getFirstimage().isEmpty()
	                    && validAreaCodes.contains(courseInfo.getAreacode())) {
	                result += sqlSession.insert(MAPPER + ".insertCourseInfo", courseInfo);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}


	public List<Map<String, Object>> getTitleAndContentId() {
		return sqlSession.selectList(MAPPER + ".getTitleAndContentId");
	}


	public List<CourseInfoVO> getCoursesByRegion(String areaCode) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(MAPPER + ".getCoursesByRegion", areaCode);
	}
	
	public List<CourseInfoVO> getAllCourses() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(MAPPER + ".getAllCourses");
	}
	
	public List<CourseInfoVO> getCoursesByTheme(String cat2) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(MAPPER + ".getCoursesByTheme", cat2);
	}
	
	public List<CourseInfoVO> getCoursesByRegionAndTheme(String areaCode, String cat2) {
        Map<String, String> params = new HashMap<>();
        params.put("areaCode", areaCode);
        params.put("cat2", cat2);
        return sqlSession.selectList(MAPPER + ".getCoursesByRegionAndTheme", params);
    }
	

	public CourseInfoVO getCourseByContentId(String contentid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(MAPPER + ".getCourseByContentId", contentid);
	}
	
	public void updateOverview(String contentId, String overview) {
		Map<String, Object> params = new HashMap<>();
		params.put("contentId", contentId);
		params.put("overview", overview);

		sqlSession.update(MAPPER + ".updateOverview", params);

	}
	
	public void updateCourseDetails(String contentid,String contenttypeid, String distance, String taketime) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("contentId", contentid);
	    params.put("contentTypeId", contenttypeid);
	    params.put("distance", distance);
	    params.put("taketime", taketime);

	    int result = sqlSession.update(MAPPER + ".updateCourseDetails", params);

	    // 업데이트가 성공했는지 로그로 확인
	    if(result > 0) {
	        System.out.println("업데이트 성공: " + contentid);
	    } else {
	        System.out.println("업데이트 실패 또는 해당 contentId가 없습니다: " + contentid);
	    }
	}


	public FestivalDBVO getRandomFestival() {
        return sqlSession.selectOne(MAPPER + ".getRandomFestival");
    }

	public List<CourseInfoVO> getCoursesRandomList() {
		return sqlSession.selectList(MAPPER+".getRandomCourses");
	}



}
