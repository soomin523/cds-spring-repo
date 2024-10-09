package com.human.cds.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.CourseInfoDTO.Item;

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
            List<Item> items = data.getResponse().getBody().getItems().getItem();
            for (Item courseInfo : items) {
                if ("25".equals(courseInfo.getContenttypeid())) {  // contenttypeid가 25인 데이터만 삽입
                    result += sqlSession.insert(MAPPER + ".insertCourseInfo", courseInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

	public List<Map<String, Object>> getTitleAndContentId() {
		 return sqlSession.selectList(MAPPER + ".selectTitleAndContentId");
	}

	public void updateOverview(String contentId, String overview) {
		Map<String, Object> params = new HashMap<>();
        params.put("contentId", contentId);
        params.put("overview", overview);

        sqlSession.update(MAPPER + ".updateOverview", params);
		
	}

	public List<Item> getCoursesByRegion(String areaCode) {
	    // 쿼리 실행 전 로그 출력 (디버깅용)
	    System.out.println("쿼리 실행 중, 지역 코드: " + areaCode);
	    
	    // SQL 쿼리 실행
	    List<Item> items = sqlSession.selectList(MAPPER + ".getCoursesByRegion", areaCode);
	    
	    // 쿼리 결과 로그 출력 (디버깅용)
	    System.out.println("쿼리 결과: " + items.size() + "개");
	    
	    return items;  // 쿼리 결과 반환
	}

}
