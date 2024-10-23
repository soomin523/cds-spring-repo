package com.human.cds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.CourseInfoDAO;
import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.CourseInfoVO;
import com.human.cds.vo.FestivalDBVO;

@Service
public class CourseInfoServiceImpl implements CourseInfoService {

	@Autowired
	private CourseInfoDAO courseInfoDAO;

	@Override
	public int insertCourseInfo(CourseInfoDTO data) {
		// DAO를 호출해 contenttypeid가 25인 값만 데이터베이스에 삽입
		return courseInfoDAO.insertCoursesWithType25(data);
	}

	@Override
	public void updateOverview(String contentId, String overview) {
		courseInfoDAO.updateOverview(contentId, overview);

	}

	@Override
	public List<CourseInfoVO> getCoursesByRegion(String areaCode, String cat2) {
	    if ((areaCode == null || areaCode.equalsIgnoreCase("all") || areaCode.isEmpty()) && (cat2 == null || cat2.isEmpty())) {
	        // 지역과 테마가 모두 null 또는 전체일 경우 전체 코스 반환
	        return courseInfoDAO.getAllCourses();
	    } else if ((areaCode == null || areaCode.equalsIgnoreCase("all") || areaCode.isEmpty()) && cat2 != null) {
	        // 전체 지역에 대해 특정 테마만 선택한 경우 해당 테마의 코스 반환
	        return courseInfoDAO.getCoursesByTheme(cat2);
	    } else if (areaCode != null && !areaCode.equalsIgnoreCase("all") && (cat2 == null || cat2.isEmpty())) {
	        // 특정 지역만 선택한 경우 해당 지역의 코스 반환
	        return courseInfoDAO.getCoursesByRegion(areaCode);
	    } else {
	        // 특정 지역과 특정 테마를 모두 선택한 경우 해당 지역과 테마의 코스 반환
	        return courseInfoDAO.getCoursesByRegionAndTheme(areaCode, cat2);
	    }
	}



	@Override
	public CourseInfoVO getCourseByContentId(String contentid) {
		// TODO Auto-generated method stub
		return courseInfoDAO.getCourseByContentId(contentid);
	}

	@Override
	public void updateCourseDetails(String contentId, String contentTypeId, String distance, String taketime) {
		courseInfoDAO.updateCourseDetails(contentId, contentTypeId, distance, taketime);

	}

	@Override
	public FestivalDBVO getRandomFestival() {
		// TODO Auto-generated method stub
		return courseInfoDAO.getRandomFestival();
	}

	@Override
	public List<CourseInfoVO> getCoursesRandomList() {
		// TODO Auto-generated method stub
		return courseInfoDAO.getCoursesRandomList();
	}


}
