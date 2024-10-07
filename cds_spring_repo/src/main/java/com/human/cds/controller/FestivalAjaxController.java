package com.human.cds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.human.cds.service.FestivalService;
import com.human.cds.vo.FestivalVO.Festival;

@RestController
@RequestMapping("/festival") //공통으로 적용되는 URL 정의
public class FestivalAjaxController {
	
	private final FestivalService festivalServiceImpl;
	@Autowired
	public FestivalAjaxController(FestivalService festivalServiceImpl) {
		this.festivalServiceImpl = festivalServiceImpl;
	}
	
	//festival 지역 선택에 따른 리스트 불러오기
		@GetMapping("/getFestivalAreaSelectList.do")
		public List<Festival> getFestivalAreaSelectList(@RequestParam String areaCode) {
			
			List<Festival> festivalList = null;
			
			if(areaCode.equals("")) {
				festivalList = festivalServiceImpl.getFestivalList();
			}else {
				festivalList = festivalServiceImpl.getFestivalAreaSelectList(areaCode);
			}
			
			return festivalList;
		}
	
}
