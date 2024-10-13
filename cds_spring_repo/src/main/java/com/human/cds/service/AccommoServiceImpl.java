package com.human.cds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.AccommoDAO;
import com.human.cds.vo.CourseInfoDTO;

@Service
public class AccommoServiceImpl implements AccommoService {

	@Autowired
	private AccommoDAO accommoDAO;

	@Override
	public int insertAccommoInfo(CourseInfoDTO data) {
		// TODO Auto-generated method stub
		return accommoDAO.insertAccommo(data);
	}
	
	
}
