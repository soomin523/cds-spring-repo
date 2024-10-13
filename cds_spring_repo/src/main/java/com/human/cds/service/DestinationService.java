package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.DestinationDBVO;
import com.human.cds.vo.FestivalVO;

public interface DestinationService {

	int insertDestination(CourseInfoDTO data);

	List<DestinationDBVO> getDestinationNameList();

	int updateAreaName(String areaName, String sigunguName, String contentid);

}
