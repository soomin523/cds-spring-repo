package com.human.cds.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.DestinationDAO;
import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.DestinationDBVO;
import com.human.cds.vo.DestinationModalVO;

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

	@Override
	public List<DestinationDBVO> getSigunguName(String areacode) {
		return dao.getSigunguName(areacode);
	}

	
	//지역코드와 시군구 코드에 맞게 리스트 뽑기
	@Override
	public List<DestinationDBVO> getDesList(String areacode, String sigungucode) {
		return dao.getDesList(areacode, sigungucode);
	}

	@Override
	public List<DestinationDBVO> getDesNoList() {
		return dao.getDesNoList();
	}

	@Override
	public DestinationModalVO getDestinationInfoList(String contentid) {
		return dao.getDestinationInfoList(contentid);
	}



}
