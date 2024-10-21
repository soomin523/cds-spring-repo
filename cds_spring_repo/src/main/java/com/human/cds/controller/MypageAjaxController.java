package com.human.cds.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.human.cds.repository.MypageDAO;
import com.human.cds.service.MypageService;
import com.human.cds.vo.CommentVO;
import com.human.cds.vo.CourseInfoVO;
import com.human.cds.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageAjaxController {
	
	
	private MypageDAO mypageDAO;
	private MypageService mypageServiceImpl;
	
	@Autowired
	public MypageAjaxController(MypageDAO mypageDAO,MypageService mypageServiceImpl) {
		this.mypageServiceImpl = mypageServiceImpl;
		this.mypageDAO = mypageDAO;
	}

	//즐겨찾기 목록 ajax컨트롤러
	@GetMapping("/mypagelike_course.do")//js에서 입력받는 값(요청받은값)
	public String redirectToCoursePage() {
	    return "/cds/mypagelike.do"; // ajax 응답결과값
	}
	
	@GetMapping("/mypagelike_festival.do")
	public String redirectToFestivalPage() {
	    return "/cds/mypagelike.do"; // ajax 응답결과값
	}
	
	@GetMapping("/mypagelike_product.do")
	public String redirectToProductPage() {
	    return "/cds/mypagelike.do"; // ajax 응답결과값
	}
	
	//작성내역 목록 ajax 컨트롤러
	@GetMapping("/mypagewrite_post.do")
	public String redirectToPostPage() {
		return "/cds/mypagewrite.do";
	}
	
	@PostMapping("/mypagewrite_comment.do")
	@ResponseBody
	public List<CommentVO> getMemberComments(HttpSession session) {
	    MemberVO member = (MemberVO) session.getAttribute("member");
	    String memberId = member.getMember_id();

	    // 댓글 목록 가져오기
	    List<CommentVO> comments = mypageServiceImpl.getCommentsByMemberId(memberId);

	    // 각 댓글에 contentId로 여행 코스 정보를 가져옴
	    for (CommentVO comment : comments) {
	        String contentId = comment.getContentId();
	        
	        // contentId에 해당하는 코스 정보 조회
	        CourseInfoVO courseInfo = mypageServiceImpl.getCourseInfoByContentId(contentId);
	        if (courseInfo != null) {
	            comment.setFirstimage(courseInfo.getFirst_image());
	            comment.setTitle(courseInfo.getTitle());
	        }
	    }

	    return comments;
	}

}
