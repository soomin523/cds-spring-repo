package com.human.cds.service;

import com.human.cds.vo.AcommointroVO.Item;
import com.human.cds.vo.CourseInfoDTO;


public interface AccommoService {

	int insertAccommoInfo(CourseInfoDTO data);

	void accomoupdate(String contentId, String overview);

	void saveRoomInfo(Item item);


    	
}
