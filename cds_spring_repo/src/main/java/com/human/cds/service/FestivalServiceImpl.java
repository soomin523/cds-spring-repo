package com.human.cds.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.FestivalDAO;
import com.human.cds.vo.FestivalDBVO;
import com.human.cds.vo.FestivalModalVO;
import com.human.cds.vo.FestivalVO;

@Service
public class FestivalServiceImpl implements FestivalService {
	
	private FestivalDAO dao;

	@Autowired
	public FestivalServiceImpl(FestivalDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public int insertFestival(FestivalVO data) {
		return dao.insertFestival(data);
	}
	
	@Override
	public List<FestivalDBVO> getAreaList(String areaCode) {
		return dao.getAreaList(areaCode);
	}

	@Override
	public List<FestivalDBVO> getFestivalList() {
		return dao.getFestivalList();
	}

	@Override
	public List<FestivalDBVO> getFestivalAreaSelectList(String areaCode) {
		return dao.getFestivalAreaSelectList(areaCode);
	}

	@Override
	public int updateAreaName(String areaName, String sigunguName, String contentid) {
		Map<String, String> map = new HashMap<>();
		map.put("areaName", areaName);
		map.put("sigunguName", sigunguName);
		map.put("contentid", contentid);
		return dao.updateAreaName(map);
	}

	@Override
	public List<FestivalDBVO> getFestivalDateSelectList(String selectDate) {
		return dao.getFestivalDateSelectList(selectDate);
	}

	@Override
	public List<FestivalDBVO> getFestivalAreaDateSelectList(String selectDate, String areaCode) {
		Map<String, String> map = new HashMap<>();
		map.put("selectDate", selectDate);
		map.put("areaCode", areaCode);
		return dao.getFestivalAreaDateSelectList(map);
	}

	@Override
	public FestivalModalVO getFestival(String contentid) {
		return dao.getFestival(contentid);
	}

	@Override
	public List<FestivalDBVO> getFestivalSoonList(String areaCode, String selectDate) {
		Map<String, String> map = new HashMap<>();
		map.put("selectDate", selectDate);
		map.put("areaCode", areaCode);
		return dao.getFestivalSoonList(map);
	}

	@Override
	public List<FestivalDBVO> getFestivalSearchTitle(String searchText) {
		return dao.getFestivalSearchTitle(searchText);
	}

	@Override
	public List<FestivalDBVO> getFestivalRandomList(List<String> contentid) {
		return dao.getFestivalRandomList(contentid);
	}
	
	@Override
	public List<FestivalDBVO> getFestivalAllRandomList() {
		return dao.getFestivalAllRandomList();
	}

	@Override
	public List<FestivalDBVO> getMoreFestivalData(int page) {
		page = page * 20 + 1;
		
		return dao.getMoreFestivalData(page);
	}

	@Override
	public List<FestivalDBVO> getFestivaldetailSelectList(String selectarea, String selectsigungu) {
		
		Map<String, String> map = new HashMap<>();
		map.put("selectarea", selectarea);
		map.put("selectsigungu", selectsigungu);
		return dao.getFestivaldetailSelectList(map);
	}

	@Override
	public List<FestivalDBVO> getFestivalNoAreaSoonList(String selectDate) {
		return dao.getFestivalNoAreaSoonList(selectDate);
	}

	@Override
	public List<FestivalDBVO> getFestivalAllList() {
		return dao.getFestivalAllList();
	}

}
