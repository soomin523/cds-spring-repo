package com.human.cds.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.human.cds.api.AreaCodeApiExplorer;
import com.human.cds.api.FestivalApiExplorer;
import com.human.cds.service.FestivalService;
import com.human.cds.vo.AreaCodeVO;
import com.human.cds.vo.AreaCodeVO.AreaCode;
import com.human.cds.vo.FestivalVO;
import com.human.cds.vo.FestivalVO.Festival;

@Controller
@RequestMapping("/festival") //공통으로 적용되는 URL 정의
public class FestivalController {
	
	
	private String serviceKey = "YftsgUs9AS%2BDd6iT8qdcCgPUmuzwaPf4v1c0HASM7b62%2FwJt8UIlMdcD6VlzGrBOq8MsmFoursrJWjg%2F0nW0dw%3D%3D";
	private String srcUrlFestival = "https://apis.data.go.kr/B551011/KorService1/searchFestival1";
	private String srcUrlAreaCode = "https://apis.data.go.kr/B551011/KorService1/areaCode1";
	private String numOfRows = "30";
	
	private final FestivalService festivalServiceImpl;
	@Autowired
	public FestivalController(FestivalService festivalServiceImpl) {
		this.festivalServiceImpl = festivalServiceImpl;
	}
	
	//redirect:/index.do
	
	//festival 리스트 DB에 저장하기
	@GetMapping("/festival.do")
	public String insertFestival(Model model) {
		
		try {
			
			int pageNo = 1;
			boolean morePages = true;
			int result = 0;
			
			Class<FestivalVO> vo = FestivalVO.class;
			
			while(true) {
				FestivalVO data = (FestivalVO)FestivalApiExplorer.getApiJsonData(serviceKey, srcUrlFestival, 
						String.valueOf(pageNo), numOfRows, vo);
				if(data.getResponse().getBody().getItems().getItem().equals("")) break;
				morePages = data.getResponse().getBody().getItems().getItem().size() > 0;
				if(!morePages) break;
				
				result += festivalServiceImpl.insertFestival(data);
				
				pageNo++;
				
				if(pageNo == 80) break;
				
			}
			
			final int[] result2 = {0};
			
			Class<AreaCodeVO> areavo = AreaCodeVO.class;
			AreaCodeVO area = (AreaCodeVO)AreaCodeApiExplorer.getApiJsonData(serviceKey, srcUrlAreaCode, 
						String.valueOf(1), numOfRows, "", areavo);
			List<AreaCode> areacode = area.getResponse().getBody().getItems().getItem();
			
			Map<String, String> areaCodeMap = areacode.stream()
				    .collect(Collectors.toMap(AreaCode::getCode, AreaCode::getName));
			
			
			for(AreaCode item : areacode) {
				try {
					AreaCodeVO areaName = (AreaCodeVO)AreaCodeApiExplorer.getApiJsonData(serviceKey, srcUrlAreaCode, 
							String.valueOf(1), numOfRows, item.getCode(), areavo);
					List<AreaCode> areacodeName = areaName.getResponse().getBody().getItems().getItem();
					
					for (AreaCode newItem : areacodeName) {
						String codeKey = item.getCode()+"_"+newItem.getCode();
			            areaCodeMap.put(codeKey, newItem.getName());
			        }
					
				} catch (Exception e) {
					System.out.println("지역 이름을 저장하던 중 오류가 발생했습니다.");
					e.printStackTrace();
				}
			}
			List<Festival> festivalList = festivalServiceImpl.getFestivalList();
			
			festivalList.parallelStream()
				.forEach(item -> {
					String areaCode = item.getF_areacode();
					String sigunguCode = item.getF_areacode()+"_"+item.getF_sigungucode();
					result2[0] += festivalServiceImpl.updateAreaName(areaCode, sigunguCode);
				});
			
			if(result >= 1 && result2[0] >= 1) {
				model.addAttribute("msg", "입력 성공");
			}else {
				model.addAttribute("msg", "축제 목록을 저장하던 중 오류가 발생했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("insertFestival 컨트롤러 동작 중 오류 발생");
			e.printStackTrace();
		}
		
		return "home";
	}
	
	
	//festival 리스트 불러오기
	@GetMapping("/getFestivalList.do")
	public String getFestivalList(Model model) {
		
		String pageName = "home";
		
		List<Festival> festivalList = festivalServiceImpl.getFestivalList();
		
		if(festivalList != null) {
			model.addAttribute("festivalList", festivalList);
			pageName = "festival/festival";
		}else {
			model.addAttribute("msg", "축제 목록을 불러오던 중 오류가 발생했습니다.");
		}
		
		return pageName;
	}
	
	//area 코드, 이름 저장하기
	@GetMapping("/getAreaName.do")
	public String getAreaName() {
		
		try {
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "home";

	}
	
}
