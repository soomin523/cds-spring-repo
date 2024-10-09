package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.CourseInfoDTO.Item;

public interface CourseInfoService {

    // 데이터를 삽입하는 메서드
    int insertCourseInfo(CourseInfoDTO data);
    
    void updateOverview(String contentId, String overview);

    List<Item> getCoursesByRegion(String areaCode);
    
    	
}
