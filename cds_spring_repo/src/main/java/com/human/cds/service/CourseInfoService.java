package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.CourseInfoVO;
import com.human.cds.vo.FestivalDBVO;

public interface CourseInfoService {

    // 데이터를 삽입하는 메서드
    int insertCourseInfo(CourseInfoDTO data);
    
    void updateOverview(String contentId, String overview);

	List<CourseInfoVO> getCoursesByRegion(String areaCode,String cat2);

	CourseInfoVO getCourseByContentId(String contentid);

	void updateCourseDetails(String contentId, String contentTypeId, String distance, String taketime);

	FestivalDBVO getRandomFestival();

	List<CourseInfoVO> getCoursesRandomList();

    
    	
}
