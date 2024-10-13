package com.human.cds.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.DestinationDAO;
import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.DestinationDBVO;

@Service
public class DestinationServiceImpl implements DestinationService {

	@Autowired
	private DestinationDAO dao; 
	
	//축제 정보 DB에 저장
	@Override
	public int insertDestination(CourseInfoDTO data) {
		return dao.insertDestination(data);
	}

	@Override
	public List<DestinationDBVO> getDestinationNameList() {
		return dao.getDestinationNameList();
	}

	@Override
	public int updateAreaName(String areaName, String sigunguName, String contentid) {
		Map<String, String> map = new HashMap<>();
		map.put("areaname",areaName);
		map.put("sigunguname", sigunguName);
		map.put("contentid",contentid);

		return dao.updateAreaName(map);
		
	}

}