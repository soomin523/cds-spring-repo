package com.human.cds.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.cds.api.ApiExplorerCourseinfo;
import com.human.cds.api.ApiExplorerDetail;
import com.human.cds.api.ApiExplorerDetail3;
import com.human.cds.repository.AccommoDAO;
import com.human.cds.service.AccommoService;
import com.human.cds.vo.AccommodationRoomVO;
import com.human.cds.vo.AccommodationVO;
import com.human.cds.vo.AcommoImgVO;
import com.human.cds.vo.AcommointroVO;
import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.CourseInfoDTO2;

@Controller
@RequestMapping("/accommodations") // 공통 URL 정의
public class AccommoController {
	
	
	private AccommoService accommoServiceImpl;
	private AccommoDAO accommoDAO;

	@Autowired
	public AccommoController(AccommoService accommoServiceImpl, AccommoDAO accommoDAO) {
		this.accommoServiceImpl = accommoServiceImpl;
		this.accommoDAO = accommoDAO;
	}
	
	@GetMapping("/accommo.do")
	public String accommo(Model model) {

		return "accommodations/accommo";
	}
	
	@GetMapping("/cityaccomo.do")
	public String cityaccomo(Model model) {
		return "accommodations/cityaccomo";		
	}
	
	@GetMapping("/Insertaccommo.do")
	public String getInsertCoursePage(Model model) {
		// JSP 파일을 반환
		return "accommodations/Insertaccommo"; // "WEB-INF/views/tourCourse/Insertcourse.jsp" 경로로 연결됨
	}

	@PostMapping("/InsertAccommoProcess.do")
	public String insertCourses(String serviceKey, String srcUrl, String pageNo, String numOfRows) {
		String viewName = "accommodations/InsertAccommo"; // 저장 실패 시 결과값
		try {
			int totalInserted = 0;
			boolean moreData = true;
			int currentPage = Integer.parseInt(pageNo);

			while (moreData) {
				// 현재 페이지로 API에서 데이터 가져오기
				CourseInfoDTO data = ApiExplorerCourseinfo.getApiJsonData(serviceKey, srcUrl,
						String.valueOf(currentPage), numOfRows, "32");

				// 데이터 삽입
				int result = accommoServiceImpl.insertAccommoInfo(data);
				totalInserted += result;

				// API 응답에서 가져온 데이터 개수가 numOfRows보다 적으면 더 이상 데이터가 없는 것
				if (data.getResponse().getBody().getItems().getItem().size() < Integer.parseInt(numOfRows)) {
					moreData = false; // 더 이상 페이지가 없으므로 종료
				} else {
					currentPage++; // 다음 페이지로 넘어가서 계속 데이터 가져오기
				}
			}

			if (totalInserted != 0) { // 저장 성공
				viewName = "redirect:/index.do";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return viewName;
	}
	
	
	
	@GetMapping("/accommoitems.do")
	public String showTitles(Model model) {
		// title과 content_id를 가져옴
		List<Map<String, Object>> accommoList = accommoDAO.getTitleAndContentId();

		if (accommoList.isEmpty()) {
			System.out.println("accommoList가 비어 있습니다.");
		} else {
			System.out.println("accommoList 데이터가 있습니다.");
		}
		model.addAttribute("accommoList", accommoList);
		return "accommodations/accommoitems";
	}
	
	@GetMapping("/accommoitems2.do")
	public String showTitles2(Model model) {
		// title과 content_id를 가져옴
		List<Map<String, Object>> accommoList = accommoDAO.getTitleAndContentId();

		if (accommoList.isEmpty()) {
			System.out.println("accommoList가 비어 있습니다.");
		} else {
			System.out.println("accommoList 데이터가 있습니다.");
		}
		model.addAttribute("accommoList", accommoList);
		return "accommodations/accommoitems2";
	}
	
	@PostMapping("/accomoupdate.do")
	public String accomoupdate(@RequestParam List<String> contentIds) {
		try {
			//문배 apikey
			String serviceKey = "cHMBzY2Ljo7k%2Fuc9cuO7pzwJoPCyA3zZM5rAV0c6bXxkV6dB66ov2nfRGgk%2F9P%2FA55kmN25hvQEB5rK116XY5w%3D%3D";
			
			//선호 apikey
			//String serviceKey = "Kw%2BbWob0mUGRN8FWR2ORdZCaU94yAQKwmxuwVTcBFhWwkRqcSzJKM%2FZr56KCIYm8Ly9O%2F6eSz8pdP1cfMxObWA%3D%3D";
			for (String contentId : contentIds) {
				// ApiExplorerDetail 클래스를 통해 상세 데이터를 가져옴
				CourseInfoDTO2 detailData = ApiExplorerDetail.getDetailByContentId(serviceKey, contentId);

				// 해당 contentId에 대해 overview 업데이트
				if (detailData != null && detailData.getResponse().getBody().getItems().getItem().size() > 0) {
					String overview = detailData.getResponse().getBody().getItems().getItem().get(0).getOverview();
					accommoServiceImpl.accomoupdate(contentId, overview); // overview 업데이트
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/home"; // 업데이트 후 리스트 페이지로 리다이렉트
	}
	
	//숙소 데이터
	@PostMapping("/accomoroomupdate.do")
	public String updateAccommodationDetails(String[] selectedItems) {
	    String serviceKey = "y%2BM4KcA3dU54OMX03WyfG7Vgskk1N4ti1JPnqNLJgfSxfGZDGpJzCXttag92jy9eIo3XD6a89LQXwVwD%2BM9RyQ%3D%3D"; // API 서비스 키
	    try {
	        for (String selectedItem : selectedItems) {
	            String[] ids = selectedItem.split(",");
	            String contentId = ids[0];
	            String contentTypeId = ids[1];

	            // API에서 상세 데이터를 가져옴
	            AcommointroVO data = ApiExplorerDetail3.getDetailByContentId(serviceKey, contentId, contentTypeId);

	            // items 필드에 빈 문자열이 들어올 때 강제로 빈 객체로 처리
	            if (data.getResponse().getBody().getItems() == null || data.getResponse().getBody().getItems().equals("")) {
	                data.getResponse().getBody().setItems(new AcommointroVO.Items());
	            }

	            // 데이터가 비어 있지 않다면 데이터를 처리
	            if (data != null && data.getResponse().getBody().getItems().getItem() != null) {
	                for (AcommointroVO.Item item : data.getResponse().getBody().getItems().getItem()) {
	                    accommoServiceImpl.saveRoomInfo(item); // 서비스 호출
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return "redirect:/accommodationList"; // 완료 후 목록 페이지로 리다이렉트
	}

	@GetMapping("accoImg.do")
	@ResponseBody
	public List<AcommoImgVO> accoImg(){
		List<AcommoImgVO> accList = accommoDAO.accoImg();
		
		return accList;
		
	}
	
	@GetMapping("/getRegionAccommodations.do")
    @ResponseBody
    public List<AcommoImgVO> getAccommodations(
            @RequestParam("area_code") int areacode,
            @RequestParam("page") int page,           // 페이지 번호
            @RequestParam("pageSize") int pageSize,
            @RequestParam(value = "cat3", required = false, defaultValue = "all") String cat3,
            @RequestParam(value = "search", required = false, defaultValue = "") String search) {  // 페이지당 데이터 수
		
		if ("all".equals(cat3)) {
	        cat3 = null;
	    }
		
		if (!search.isEmpty()) {
	        System.out.println("Search term: " + search);
	    }
		
		System.out.println("Page: " + page + ", Page Size: " + pageSize);
        return accommoServiceImpl.getAccommodationsByRegion(areacode, page, pageSize, cat3,search);
    }
	
	
	//모달에 띄울 숙소정보를 가져오기 위한 컨트롤러
	@GetMapping("/getAccommodationDetails.do")
	@ResponseBody
	public AccommodationVO getAccommodationDetails(@RequestParam(value = "contentId", required = false) String contentId) {
		if (contentId == null) {
	        throw new IllegalArgumentException("contentId is required");
	    }
		
		accommoServiceImpl.incrementcnt(contentId);
	    
	    AccommodationVO accommodation = accommoServiceImpl.getAccommodationByContentId(contentId);
	    if (accommodation != null) {
	        List<AccommodationRoomVO> rooms = accommoServiceImpl.getRoomsByContentId(contentId);
	        accommodation.setRooms(rooms); // 방 정보 설정
	    } else {
	        throw new IllegalArgumentException("Accommodation data not found for contentId: " + contentId);
	    }
	    
	    return accommodation;
	}

}
