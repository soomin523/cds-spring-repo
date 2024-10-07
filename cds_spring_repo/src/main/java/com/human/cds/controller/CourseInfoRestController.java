package com.human.cds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.human.cds.service.CourseInfoService;
import com.human.cds.vo.CourseInfoDTO.Item;

@RestController
@RequestMapping("/tourCourse")
public class CourseInfoRestController {

    @Autowired
    private CourseInfoService courseInfoService;

    @GetMapping("/getCoursesByRegion")
    public List<Item> getCoursesByRegion(@RequestParam String areaCode) {
    	System.out.println("지역 코드: " + areaCode);
        return courseInfoService.getCoursesByRegion(areaCode);
    }
}
