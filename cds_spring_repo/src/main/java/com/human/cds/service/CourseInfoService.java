package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.CourseInfoDTO.Item;
import com.human.cds.vo.CourseInfoVO;

public interface CourseInfoService {

    // 데이터를 삽입하는 메서드
    int insertCourseInfo(CourseInfoDTO data);
    
    void updateOverview(String contentId, String overview);

	List<CourseInfoVO> getCoursesByRegion(String areaCode);

	CourseInfoVO getCourseByContentId(String contentid);

	void updateCourseDetails(String contentId, String contentTypeId, String distance, String taketime);

    
    	
}
