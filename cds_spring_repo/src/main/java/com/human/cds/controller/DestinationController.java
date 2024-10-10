package com.human.cds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.human.cds.api.DestinationAreaBasedApiExplorer;
import com.human.cds.service.DestinationService;
import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.FestivalVO;

@Controller
@RequestMapping("destination")//공통으로 정의되는 url = 폴더명
public class DestinationController {
	private String serviceKey = "cHMBzY2Ljo7k%2Fuc9cuO7pzwJoPCyA3zZM5rAV0c6bXxkV6dB66ov2nfRGgk%2F9P%2FA55kmN25hvQEB5rK116XY5w%3D%3D";
	private String srcUrlFestival = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
	private String srcUrlAreaCode = "https://apis.data.go.kr/B551011/KorService1/areaCode1";
	private String numOfRows = "30";
	
	@Autowired
	private DestinationService DestinationServiceImpl;
	
	
	
	//festival 리스트 DB에 저장하기
	@GetMapping("/DestinationList.do")
	public String insertDestination(Model model) {
		
		try {
			
			int pageNo = 1;
			boolean morePages = true;
			int result = 0;
			
			Class<CourseInfoDTO> vo = CourseInfoDTO.class;
			
			while(true) {
				CourseInfoDTO data = (CourseInfoDTO)DestinationAreaBasedApiExplorer.getApiJsonData(serviceKey, srcUrlFestival, 
						String.valueOf(pageNo), numOfRows, vo);
				if(data.getResponse().getBody().getItems().equals("")) break;
				morePages = data.getResponse().getBody().getItems().getItem().size() > 0;
				if(!morePages) break;
				
				result += DestinationServiceImpl.insertDestination(data);
				
				pageNo++;
				
			}
			
			if(result >= 1) {
				model.addAttribute("msg", "입력 성공");
			}else {
				model.addAttribute("msg", "관광지 목록을 저장하던 중 오류가 발생했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("insertDestination 컨트롤러 동작 중 오류 발생");
			e.printStackTrace();
		}
		
		return "home";
	}
}
