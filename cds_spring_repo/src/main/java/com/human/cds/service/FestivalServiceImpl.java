package com.human.cds.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.FestivalDAO;
import com.human.cds.vo.FestivalVO;
import com.human.cds.vo.FestivalVO.Festival;

@Service
public class FestivalServiceImpl implements FestivalService {
	
	private final FestivalDAO dao;
	@Autowired
	public FestivalServiceImpl(FestivalDAO dao) {
		this.dao = dao;
	}

	@Override
	public int insertFestival(FestivalVO data) {
		return dao.insertFestival(data);
	}

	@Override
	public List<Festival> getFestivalList() {
		return dao.getFestivalList();
	}

	@Override
	public List<Festival> getFestivalAreaSelectList(String areaCode) {
		return dao.getFestivalAreaSelectList(areaCode);
	}

	@Override
	public int updateAreaName(String areaCode, String sigunguCode) {
		Map<String, String> map = new HashMap<>();
		map.put("areaCode", areaCode);
		map.put("sigunguCode", sigunguCode);
		return dao.updateAreaName(map);
	}

}
