package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.FestivalVO;
import com.human.cds.vo.FestivalVO.Festival;

public interface FestivalService {

	int insertFestival(FestivalVO data);

	List<Festival> getFestivalList();

	List<Festival> getFestivalAreaSelectList(String areaCode);

	int updateAreaName(String areaCode, String sigunguCode);

}
