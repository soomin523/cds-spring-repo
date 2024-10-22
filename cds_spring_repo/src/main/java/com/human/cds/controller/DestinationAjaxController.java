package com.human.cds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.human.cds.service.DestinationService;
import com.human.cds.vo.DestinationDBVO;
import com.human.cds.vo.DestinationModalVO;
import com.human.cds.vo.FestivalModalVO;

@RestController
@RequestMapping("/destination")
public class DestinationAjaxController {

	@Autowired //의존자동주입
	private DestinationService DestinationServiceImpl;

	//시군구 이름 받기
	@GetMapping("/getSigungunName.do")
	public List<DestinationDBVO> getSigunguname(@RequestParam String areacode) {
	
		
		List<DestinationDBVO> sigunguList = DestinationServiceImpl.getSigunguName(areacode);
		return sigunguList;
	}
	
	@GetMapping("/getDesList.do")
	public List<DestinationDBVO> getDesList(String areacode, String sigungucode){
		
		System.out.println("areacode:"+areacode+", sigungucode:"+sigungucode);
		
		List<DestinationDBVO> desList = DestinationServiceImpl.getDesList(areacode, sigungucode);
		
		return desList;
	}
	
	@GetMapping("/getDesInfo.do")
	public DestinationModalVO getDestinationInfoList(@RequestParam String contentid) {
		
		DestinationModalVO vo = null;
		
		return null; 
	}
}
