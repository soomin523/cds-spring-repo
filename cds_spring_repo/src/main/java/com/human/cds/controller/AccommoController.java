package com.human.cds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.human.cds.api.ApiExplorerCourseinfo;
import com.human.cds.repository.AccommoDAO;
import com.human.cds.service.AccommoService;
import com.human.cds.vo.CourseInfoDTO;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/accommodations") // 공통 URL 정의
@AllArgsConstructor
public class AccommoController {
	
	
	private AccommoService accommoServiceImpl;
	private AccommoDAO accommoDAO;

	@GetMapping("/accommo.do")
	public String Course(Model model) {

		return "accommodations/accommo";
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
				viewName = "redirect:/home.do";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return viewName;
	}
	
}
