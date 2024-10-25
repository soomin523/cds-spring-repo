package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.FestivalDBVO;
import com.human.cds.vo.FestivalModalVO;
import com.human.cds.vo.FestivalVO;

public interface FestivalService {

	int insertFestival(FestivalVO data);
	
	List<FestivalDBVO> getAreaList(String areaCode);

	List<FestivalDBVO> getFestivalList();

	List<FestivalDBVO> getFestivalAreaSelectList(String areaCode);

	int updateAreaName(String areaName, String sigunguName, String contentid);

	List<FestivalDBVO> getFestivalDateSelectList(String selectDate);

	List<FestivalDBVO> getFestivalAreaDateSelectList(String selectDate, String areaCode);

	FestivalModalVO getFestival(String contentid);

	List<FestivalDBVO> getFestivalSoonList(String areaCode, String selectDate);

	List<FestivalDBVO> getFestivalSearchTitle(String searchText);

	List<FestivalDBVO> getFestivalRandomList(List<String> contentid);

	List<FestivalDBVO> getMoreFestivalData(int page);

	List<FestivalDBVO> getFestivalAllRandomList();

	List<FestivalDBVO> getFestivaldetailSelectList(String selectarea, String selectsigungu);

	List<FestivalDBVO> getFestivalNoAreaSoonList(String selectDate);

	List<FestivalDBVO> getFestivalAllList();

}
