package com.human.cds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.human.cds.api.DestinationDetailCommonApiExplorer;
import com.human.cds.api.DestinationIntroApiExplorer;
import com.human.cds.service.DestinationService;
import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.DestinationDBVO;
import com.human.cds.vo.DestinationIntroVO;
import com.human.cds.vo.DestinationIntroVO.Item;
import com.human.cds.vo.DestinationModalVO;
import com.human.cds.vo.DetailCommonVO;

@RestController
@RequestMapping("/destination")
public class DestinationAjaxController {

	private String serviceKey = "cHMBzY2Ljo7k%2Fuc9cuO7pzwJoPCyA3zZM5rAV0c6bXxkV6dB66ov2nfRGgk%2F9P%2FA55kmN25hvQEB5rK116XY5w%3D%3D";
	private String srcUrlDesIntro = "https://apis.data.go.kr/B551011/KorService1/detailIntro1";
	private String srcUrlDesCommon = "https://apis.data.go.kr/B551011/KorService1/detailCommon1";
	private String numOfRows = "30";
	
	
	
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
		
		try {
			Class<DestinationIntroVO> introVo = DestinationIntroVO.class;
			Class<DetailCommonVO> overviewVo = DetailCommonVO.class;
		
		vo = DestinationServiceImpl.getDestinationInfoList(contentid);
		DestinationIntroVO introdata = (DestinationIntroVO)DestinationIntroApiExplorer.getApiJsonData(serviceKey, 
										srcUrlDesIntro, contentid, introVo);
		
		List<Item> introList = introdata.getResponse().getBody().getItems().getItem();
		//vo에 관광지 상세정보 저장
		Item introItem = introList.get(0);
		vo.setChkbabycarriage(introItem.getChkbabycarriage());
		vo.setChkpet(introItem.getChkpet());
		vo.setInfocenter(introItem.getInfocenter());
		vo.setRestdate(introItem.getRestdate());
		vo.setUsetime(introItem.getUsetime());
		vo.setParking(introItem.getParking());
		
		//common api불러오기
		DetailCommonVO comdata = (DetailCommonVO)DestinationDetailCommonApiExplorer.getApiJsonData(serviceKey, srcUrlDesCommon,
										contentid ,overviewVo);
		List<DetailCommonVO.Item> comList = comdata.getResponse().getBody().getItems().getItem();
		//vo에 콘텐츠 개요 저장하기
		DetailCommonVO.Item item = comList.get(0);
		vo.setOverview(item.getOverview());
		
		} catch (Exception e) { 
			System.out.println("getDestinationInfoList 컨트롤러 동작 중 오류 발생");
			e.printStackTrace();
		}
		 
		return vo; 
	}
}
