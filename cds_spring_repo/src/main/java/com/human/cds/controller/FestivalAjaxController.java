package com.human.cds.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.human.cds.api.FDetailCommonApiExplorer;
import com.human.cds.api.FDetailIntroApiExplorer;
import com.human.cds.api.FestivalImgApiExplorer;
import com.human.cds.service.FestivalService;
import com.human.cds.vo.DetailCommonVO;
import com.human.cds.vo.FDetailIntroVO;
import com.human.cds.vo.FDetailIntroVO.DetailIntro;
import com.human.cds.vo.FestivalDBVO;
import com.human.cds.vo.FestivalImgVO;
import com.human.cds.vo.FestivalImgVO.FestivalImg;
import com.human.cds.vo.FestivalModalVO;

@RestController
@RequestMapping("/festival") //공통으로 적용되는 URL 정의
public class FestivalAjaxController {
	
	private String serviceKey = "YftsgUs9AS%2BDd6iT8qdcCgPUmuzwaPf4v1c0HASM7b62%2FwJt8UIlMdcD6VlzGrBOq8MsmFoursrJWjg%2F0nW0dw%3D%3D";
	private String srcUrlFImg = "https://apis.data.go.kr/B551011/KorService1/detailImage1";
	private String srcUrlDetailCommon = "https://apis.data.go.kr/B551011/KorService1/detailCommon1";
	private String srcUrlFDetailIntro = "https://apis.data.go.kr/B551011/KorService1/detailIntro1";
	
	private FestivalService festivalServiceImpl;
	
	@Autowired
	public FestivalAjaxController(FestivalService festivalServiceImpl) {
		this.festivalServiceImpl = festivalServiceImpl;
	}
	
	//지역과 날짜 선택에 따른 리스트 다시 불러오기
	@GetMapping("/getFestivalSelectList.do")
	public List<FestivalDBVO> getFestivalSelectList(@RequestParam String areaCode, @RequestParam String selectDate) {
		
		List<FestivalDBVO> festivalList = null;
		
		if(areaCode.equals("") && selectDate.equals("")) {
			festivalList = festivalServiceImpl.getFestivalList();
			
		}else if(areaCode.equals("")) {
			festivalList = festivalServiceImpl.getFestivalDateSelectList(selectDate);
			
		}else if(selectDate.equals("")) {
			festivalList = festivalServiceImpl.getFestivalAreaSelectList(areaCode);
			
		}else {
			festivalList = festivalServiceImpl.getFestivalAreaDateSelectList(selectDate, areaCode);
		}
		
		return festivalList;
	}
	
	//지역 선택에 따른 상세지역 표시하기
	@GetMapping("/getAreaList.do")
	public List<FestivalDBVO> getAreaList(@RequestParam String areaCode){
		
		List<FestivalDBVO> areaList = null;
		
		areaList = festivalServiceImpl.getAreaList(areaCode);
		
		return areaList;
	}
	
	//축제 상세보기 불러오기
	@GetMapping("/getFestival.do")
	public FestivalModalVO getFestival(@RequestParam String contentid) {
		
		FestivalModalVO vo = null;
		
		try {
			Class<FestivalImgVO> imgvo = FestivalImgVO.class;
			Class<DetailCommonVO> dcomvo = DetailCommonVO.class;
			Class<FDetailIntroVO> introvo = FDetailIntroVO.class;
			
			vo = festivalServiceImpl.getFestival(contentid);
		
			//img Api 불러오기
			FestivalImgVO imgdata = (FestivalImgVO)FestivalImgApiExplorer.getApiJsonData(serviceKey, srcUrlFImg, 
										contentid, imgvo);
			List<FestivalImg> imgList = imgdata.getResponse().getBody().getItems().getItem();
			//vo에 이미지 저장하기
			List<String> imageUrls = new ArrayList<>();
			for(FestivalImg img : imgList) {
				imageUrls.add(img.getOriginimgurl());
			}
			vo.setOriginimgurl(imageUrls);
			
			//detailcommon api 불러오기
			DetailCommonVO comdata = (DetailCommonVO)FDetailCommonApiExplorer.getApiJsonData(serviceKey, 
										srcUrlDetailCommon, contentid, dcomvo);
			List<DetailCommonVO.Item> comList = comdata.getResponse().getBody().getItems().getItem();
			//vo에 콘텐츠개요 저장하기
			DetailCommonVO.Item item = comList.get(0);
			vo.setOverview(item.getOverview());
			
			//detailIntro api 불러오기
			FDetailIntroVO intdata = (FDetailIntroVO)FDetailIntroApiExplorer.getApiJsonData(serviceKey, 
										srcUrlFDetailIntro, contentid, introvo);
			List<DetailIntro> intList = intdata.getResponse().getBody().getItems().getItem();
			//vo에 행사 상세정보 저장하기
			DetailIntro intItem = intList.get(0);
			vo.setBookingplace(intItem.getBookingplace());
			vo.setUsetimefestival(intItem.getUsetimefestival());
			vo.setDiscountinfofestival(intItem.getDiscountinfofestival());
			vo.setEventhomepage(intItem.getEventhomepage());
			vo.setEventplace(intItem.getEventplace());
			vo.setProgram(intItem.getProgram());
			vo.setSponsor1(intItem.getSponsor1());
			vo.setSponsor2(intItem.getSponsor2());
			vo.setSponsor1tel(intItem.getSponsor1tel());
			
		} catch (Exception e) {
			System.out.println("getFestival 컨트롤러 동작 중 오류 발생");
			e.printStackTrace();
		}
		
		return vo;
		
	}
	
	//진행예정 축제리스트 불러오기
	@GetMapping("/getFestivalSoonList.do")
	public List<FestivalDBVO> getFestivalSoonList(@RequestParam String areaCode, @RequestParam String selectDate) {
		
		List<FestivalDBVO> festivalList = null;
		
		if(areaCode.equals("")) {
			festivalList = festivalServiceImpl.getFestivalNoAreaSoonList(selectDate);
		}else {
			festivalList = festivalServiceImpl.getFestivalSoonList(areaCode, selectDate);
		}
		
		return festivalList;
	}
	
	//검색에 따른 축제리스트 불러오기
	@GetMapping("/getFestivalSearchTitle.do")
	public List<FestivalDBVO> getFestivalSearchTitle(@RequestParam String searchText){
		
		List<FestivalDBVO> festivalList = null;
		
		festivalList = festivalServiceImpl.getFestivalSearchTitle(searchText);
		
		return festivalList;
	}
	
	//이런축제는어때요 리스트 불러오기
	@GetMapping("/getFestivalRandomList.do")
	public List<FestivalDBVO> getFestivalRandomList(@RequestParam("contentid[]") List<String> contentid){
		
		List<FestivalDBVO> recommendList = null;

		if(contentid.size() == 0) {
			recommendList = festivalServiceImpl.getFestivalAllRandomList();
		    
		}else {
			recommendList = festivalServiceImpl.getFestivalRandomList(contentid);
		}
		
		
		
		return recommendList;
	}
	
	//무한스크롤 리스트 불러오기
	@GetMapping("/getMoreFestivalData.do")
	public List<FestivalDBVO> getMoreFestivalData(@RequestParam int page){
		
		List<FestivalDBVO> festivalList = null;
		
		festivalList = festivalServiceImpl.getMoreFestivalData(page);
		
		return festivalList;
	}
	
	//상세지역 리스트 불러오기
	@GetMapping("/getFestivaldetailSelectList.do")
	public List<FestivalDBVO> getFestivaldetailSelectList(@RequestParam String selectarea, @RequestParam String selectsigungu){
		
		List<FestivalDBVO> festivalList = null;
		
		festivalList = festivalServiceImpl.getFestivaldetailSelectList(selectarea, selectsigungu);
		
		return festivalList;
	}
	
	
}
