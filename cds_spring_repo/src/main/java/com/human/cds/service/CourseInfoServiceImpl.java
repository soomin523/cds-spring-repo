package com.human.cds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.CourseInfoDAO;
import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.CourseInfoDTO.Item;
import com.human.cds.vo.CourseInfoVO;

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
	public List<CourseInfoVO> getCoursesByRegion(String areaCode) {
		return courseInfoDAO.getCoursesByRegion(areaCode);
	}

	@Override
	public CourseInfoVO getCourseByContentId(String contentid) {
		// TODO Auto-generated method stub
		return courseInfoDAO.getCourseByContentId(contentid);
	}

	@Override
	public void updateCourseDetails(String contentId, String contentTypeId, String distance, String taketime) {
		courseInfoDAO.updateCourseDetails(contentId,contentTypeId,distance,taketime);
		
	}
	
	
	
}
