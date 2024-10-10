package com.human.cds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.DestinationDAO;
import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.FestivalVO;

@Service
public class DestinationServiceImpl implements DestinationService {

	@Autowired
	private DestinationDAO dao; 
	
	//축제 정보 DB에 저장
	@Override
	public int insertDestination(CourseInfoDTO data) {
		return dao.insertDestination(data);
	}

}
