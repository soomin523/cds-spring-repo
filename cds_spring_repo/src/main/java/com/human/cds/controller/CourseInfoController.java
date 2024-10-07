package com.human.cds.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.human.cds.api.ApiExplorerCourseinfo;
import com.human.cds.api.ApiExplorerDetail;
import com.human.cds.repository.CourseInfoDAO;
import com.human.cds.service.CourseInfoService;
import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.CourseInfoDTO2;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/tourCourse")  // 공통 URL 정의
@AllArgsConstructor
public class CourseInfoController {

    private CourseInfoService courseInfoServiceImpl;
    private CourseInfoDAO courseInfoDAO;
    
    @GetMapping("/Course.do")
    public String Course(Model model) {
        
        return "tourCourse/Course";
    }
    
    
    
    @GetMapping("/Courseitems.do")
    public String showTitles(Model model) {
        // title과 content_id를 가져옴
        List<Map<String, Object>> courseList = courseInfoDAO.getTitleAndContentId();
        
        if(courseList.isEmpty()) {
            System.out.println("courseList가 비어 있습니다.");
        } else {
            System.out.println("courseList 데이터가 있습니다.");
        }
        model.addAttribute("courseList", courseList);
        return "tourCourse/Courseitems";
    }
    
    
    @GetMapping("/Insertcourse.do")
    public String getInsertCoursePage(Model model) {
        // JSP 파일을 반환
        return "tourCourse/Insertcourse";  // "WEB-INF/views/tourCourse/Insertcourse.jsp" 경로로 연결됨
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
                CourseInfoDTO data = ApiExplorerCourseinfo.getApiJsonData(serviceKey, srcUrl, String.valueOf(currentPage), numOfRows, "25");
                
                // 데이터 삽입
                int result = courseInfoServiceImpl.insertCourseInfo(data);
                totalInserted += result;

                // API 응답에서 가져온 데이터 개수가 numOfRows보다 적으면 더 이상 데이터가 없는 것
                if (data.getResponse().getBody().getItems().getItem().size() < Integer.parseInt(numOfRows)) {
                    moreData = false;  // 더 이상 페이지가 없으므로 종료
                } else {
                    currentPage++;  // 다음 페이지로 넘어가서 계속 데이터 가져오기
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

    @PostMapping("/updateOverview.do")
    public String updateOverview(@RequestParam("contentIds") List<String> contentIds) {
        try {
            String serviceKey = "Kw%2BbWob0mUGRN8FWR2ORdZCaU94yAQKwmxuwVTcBFhWwkRqcSzJKM%2FZr56KCIYm8Ly9O%2F6eSz8pdP1cfMxObWA%3D%3D";
            for (String contentId : contentIds) {
                // ApiExplorerDetail 클래스를 통해 상세 데이터를 가져옴
                CourseInfoDTO2 detailData = ApiExplorerDetail.getDetailByContentId(serviceKey, contentId);

                // 해당 contentId에 대해 overview 업데이트
                if (detailData != null && detailData.getResponse().getBody().getItems().getItem().size() > 0) {
                    String overview = detailData.getResponse().getBody().getItems().getItem().get(0).getOverview();
                    courseInfoServiceImpl.updateOverview(contentId, overview);  // overview 업데이트
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/tourCourse/home";  // 업데이트 후 리스트 페이지로 리다이렉트
    }
    
    

}
