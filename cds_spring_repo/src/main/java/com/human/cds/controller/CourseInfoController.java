package com.human.cds.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.cds.api.ApiExplorerCourseinfo;
import com.human.cds.api.ApiExplorerDetail;
import com.human.cds.api.ApiExplorerDetail2;
import com.human.cds.repository.CourseInfoDAO;
import com.human.cds.service.CommentService;
import com.human.cds.service.CourseInfoService;
import com.human.cds.vo.CommentLikeVO;
import com.human.cds.vo.CommentVO;
import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.CourseInfoDTO2;
import com.human.cds.vo.CourseInfoDTO3;
import com.human.cds.vo.CourseInfoVO;
import com.human.cds.vo.FestivalDBVO;
import com.human.cds.vo.MemberVO;

@Controller
@RequestMapping("/tourCourse") // 공통 URL 정의
public class CourseInfoController {

	private CourseInfoService courseInfoServiceImpl;
	private CourseInfoDAO courseInfoDAO;
	private CommentService commentServiceImpl;
	@Autowired
	public CourseInfoController(CourseInfoService courseInfoServiceImpl, CourseInfoDAO courseInfoDAO,CommentService commentServiceImpl) {
		this.courseInfoServiceImpl = courseInfoServiceImpl;
		this.courseInfoDAO = courseInfoDAO;
		this.commentServiceImpl = commentServiceImpl;
	}

	@GetMapping("/Course.do")
	public String Course(Model model) {

		return "tourCourse/Course";
	}

	@GetMapping("/Insertcourse.do")
	public String getInsertCoursePage(Model model) {
		// JSP 파일을 반환
		return "tourCourse/Insertcourse"; // "WEB-INF/views/tourCourse/Insertcourse.jsp" 경로로 연결됨
	}

	@PostMapping("/InsertCoursesProcess.do")
	public String insertCourses(String serviceKey, String srcUrl, String pageNo, String numOfRows) {
		String viewName = "tourCourse/Insertcourse"; // 저장 실패 시 결과값
		try {
			int totalInserted = 0;
			boolean moreData = true;
			int currentPage = Integer.parseInt(pageNo);

			while (moreData) {
				// 현재 페이지로 API에서 데이터 가져오기
				CourseInfoDTO data = ApiExplorerCourseinfo.getApiJsonData(serviceKey, srcUrl,
						String.valueOf(currentPage), numOfRows, "25");

				// 데이터 삽입
				int result = courseInfoServiceImpl.insertCourseInfo(data);
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

	@PostMapping("/updateOverview.do")
	public String updateOverview(@RequestParam("contentIds") List<String> contentIds) {
		try {
			String serviceKey = "cHMBzY2Ljo7k%2Fuc9cuO7pzwJoPCyA3zZM5rAV0c6bXxkV6dB66ov2nfRGgk%2F9P%2FA55kmN25hvQEB5rK116XY5w%3D%3D";
			for (String contentId : contentIds) {
				// ApiExplorerDetail 클래스를 통해 상세 데이터를 가져옴
				CourseInfoDTO2 detailData = ApiExplorerDetail.getDetailByContentId(serviceKey, contentId);

				// 해당 contentId에 대해 overview 업데이트
				if (detailData != null && detailData.getResponse().getBody().getItems().getItem().size() > 0) {
					String overview = detailData.getResponse().getBody().getItems().getItem().get(0).getOverview();
					courseInfoServiceImpl.updateOverview(contentId, overview); // overview 업데이트
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/index.do"; // 업데이트 후 리스트 페이지로 리다이렉트
	}

	@GetMapping("/Courseitems.do")
	public String showTitles(Model model) {
		// title과 content_id를 가져옴
		List<Map<String, Object>> courseList = courseInfoDAO.getTitleAndContentId();

		if (courseList.isEmpty()) {
			System.out.println("courseList가 비어 있습니다.");
		} else {
			System.out.println("courseList 데이터가 있습니다.");
		}
		model.addAttribute("courseList", courseList);
		return "tourCourse/Courseitems";
	}
	
	@GetMapping("/Courseitems2.do")
	public String showTitles2(Model model) {
		// title과 content_id를 가져옴
		List<Map<String, Object>> courseList = courseInfoDAO.getTitleAndContentId();

		if (courseList.isEmpty()) {
			System.out.println("courseList가 비어 있습니다.");
		} else {
			System.out.println("courseList 데이터가 있습니다.");
		}
		model.addAttribute("courseList", courseList);
		return "tourCourse/Courseitems2";
	}

	@GetMapping("/getCoursesByRegion.do")
	@ResponseBody
	public List<CourseInfoVO> getCoursesByRegion(@RequestParam(value = "areaCode", required = false) String areaCode,
	                                             @RequestParam(value = "cat2", required = false) String cat2) {
	    if ("all".equalsIgnoreCase(areaCode)) {
	        areaCode = null; // areaCode가 "all"인 경우 null로 설정하여 전체 조회
	    }
	    // 서비스 계층을 통해 데이터를 가져옴
	    return courseInfoServiceImpl.getCoursesByRegion(areaCode, cat2);
	}


	@GetMapping("/getCourseDetails.do")
	@ResponseBody
	public CourseInfoVO getCourseDetails(String contentid) {
		// 서비스 계층을 통해 데이터를 가져옴
		return courseInfoServiceImpl.getCourseByContentId(contentid);
	}

	
	//거리 입력
	@PostMapping("/updateCourseDetails.do")
	public String updateCourseDetails(@RequestParam("selectedItems") List<String> selectedItems) {
	    try {
	        String serviceKey = "y%2BM4KcA3dU54OMX03WyfG7Vgskk1N4ti1JPnqNLJgfSxfGZDGpJzCXttag92jy9eIo3XD6a89LQXwVwD%2BM9RyQ%3D%3D";  // 공공데이터 API의 서비스 키
	        
	        for (String item : selectedItems) {
	            String[] parts = item.split(",");  // contentId와 contentTypeId 분리
	            String contentId = parts[0];
	            String contentTypeId = parts[1];

	            // ApiExplorerDetail2 클래스를 통해 상세 데이터를 가져옴
	            CourseInfoDTO3 detailData = ApiExplorerDetail2.getDetailByContentId(serviceKey, contentId, contentTypeId);

	            // 해당 contentId에 대해 distance와 taketime 업데이트
	            if (detailData != null && detailData.getResponse().getBody() != null && detailData.getResponse().getBody().getItems() != null) {
	            	for (CourseInfoDTO3.Item apiItem : detailData.getResponse().getBody().getItems().getItem()) {
	            	    String distance = apiItem.getDistance();
	            	    String taketime = apiItem.getTaketime();
	            	    String contenttypeid = apiItem.getContenttypeid();  // contenttypeid 가져오기

	            	    // 서비스 계층에서 contentId와 contentTypeId를 비교한 후 distance와 taketime 업데이트
	            	    courseInfoServiceImpl.updateCourseDetails(contentId, contenttypeid, distance, taketime);
	            	}

	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return "redirect:/index.do";  // 업데이트 후 리스트 페이지로 리다이렉트
	}

	 @GetMapping("/getRandomFestival.do")
	    @ResponseBody
	    public FestivalDBVO getRandomFestival() {
	        return courseInfoServiceImpl.getRandomFestival();
	    }
	 
	 @PostMapping("/addComment.do")
	 @ResponseBody
	 public String addComment(@RequestBody CommentVO commentVO, HttpSession session) {
	     MemberVO member = (MemberVO) session.getAttribute("member"); // 세션에서 member 객체를 가져오기

	     // commentVO가 null인지 확인하는 로그
	     System.out.println("commentVO: " + commentVO.getContentId());

	     if (member == null) {
	         return "belogin";
	     }

	     String name = member.getName(); // member 객체에서 memberId 가져오기
	     String gender = member.getGender();
	     System.out.println("name: " + name);  // memberId 값도 확인
	     System.out.println("content_id: " + commentVO.getContentId());  // memberId 값도 확인	     
	     commentVO.setName(name); // 댓글 작성자 ID 설정
	     commentVO.setGender(gender);
	     boolean isAdded = commentServiceImpl.addComment(commentVO);
	     return isAdded ? "success" : "fail";
	 }
	 
	 
	 @PostMapping("/checkLoginStatus.do")
	 @ResponseBody
	 public String checkLoginStatus(HttpSession session) {
	     MemberVO member = (MemberVO) session.getAttribute("member"); // 세션에서 member 객체 가져오기
	     System.out.println(member);
	     return (member != null) ? "loggin" : "belogin";
	 }
	
	 @PostMapping("/getComments.do")
	 @ResponseBody
	 public Map<String, Object> getComments(@RequestParam("contentId") String contentId, 
	                                        @RequestParam("page") int page, 
	                                        HttpSession session) {
	     int pageSize = 10;
	     int offset = (page - 1) * pageSize;

	     // 코스 ID에 해당하는 댓글 목록 가져오기
	     List<CommentVO> comments = commentServiceImpl.getCommentsByContentId(contentId, offset, pageSize);

	     // 로그인된 사용자 정보 가져오기
	     MemberVO member = (MemberVO) session.getAttribute("member");

	     // 결과를 담을 Map 생성
	     Map<String, Object> result = new HashMap<>();
	     result.put("comments", comments); // 댓글 목록
	     result.put("member", member);     // 로그인된 사용자 정보 (없을 수 있음)

	     return result; // 댓글 리스트와 회원 정보를 JSON 형식으로 반환
	 }


	 @PostMapping("/toggleLike.do")
	 @ResponseBody
	 public String toggleLike(@RequestParam("c_idx") int cIdx,
	                          @RequestParam("actionType") String actionType, 
	                          HttpSession session) {
	     MemberVO member = (MemberVO) session.getAttribute("member");
	     if (member == null) {
	         return "belogin"; // 로그인되지 않은 상태일 경우
	     }
	     
	     String name = member.getName();
	     System.out.println("memberId: " + name);
	     
	     CommentLikeVO likeStatus = commentServiceImpl.checkIfAlreadyLiked(cIdx, name);
	     System.out.println("likeStatus: " + likeStatus);

	     if (likeStatus == null) {
	         // 처음 좋아요 또는 싫어요를 누르는 경우
	         commentServiceImpl.addLike(cIdx, name, actionType);
	         System.out.println("처음 " + actionType + "를 누름");
	         return "success";
	     } else {
	         String currentActionType = likeStatus.getActionType();
	         System.out.println("기존 actionType: " + currentActionType);
	         System.out.println("새로운 actionType: " + actionType);

	         // NullPointerException 방지: 현재 actionType이 null일 경우 처리
	         if (currentActionType == null) {
	             currentActionType = "none"; // 기본값으로 설정
	         }

	         // 이미 좋아요/싫어요를 누른 상태에서 취소 ('none'으로 처리)
	         if (currentActionType.equals(actionType)) {
	             // 동일한 액션을 취소하는 경우
	             commentServiceImpl.removeLike(cIdx, name);  // 좋아요/싫어요 삭제
	             System.out.println(actionType + " 취소");
	             return "cancel";
	         } else {
	             // 좋아요/싫어요 상태 변경 (좋아요 -> 싫어요, 싫어요 -> 좋아요)
	             commentServiceImpl.addLike(cIdx, name, actionType);
	             System.out.println("좋아요/싫어요 상태 변경: " + actionType);
	             return "success";
	         }
	     }
	 }

	 @PostMapping("/deleteComment.do")
	 @ResponseBody
	 public String deleteComment(int c_idx) {
		 String result = "Fail";//글삭제 실패시 뷰이름
			int res = commentServiceImpl.deleteComment(c_idx);
			if(res == 1) {//글삭제 성공
				result = "success";
			}
			return result;
		 
	 }




}

