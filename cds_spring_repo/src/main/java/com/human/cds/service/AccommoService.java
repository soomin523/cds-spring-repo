package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.AccommodationRoomVO;
import com.human.cds.vo.AccommodationVO;
import com.human.cds.vo.AcommoImgVO;
import com.human.cds.vo.AcommointroVO.Item;
import com.human.cds.vo.CourseInfoDTO;


public interface AccommoService {

	int insertAccommoInfo(CourseInfoDTO data);

	void accomoupdate(String contentId, String overview);

	void saveRoomInfo(Item item);

	List<AcommoImgVO> getAccommodationsByRegion(int areacode, int page, int pageSize, String cat3,String search);

	AccommodationVO getAccommodationByContentId(String contentId);

	List<AccommodationRoomVO> getRoomsByContentId(String contentId);

	void incrementcnt(String contentId);




    	
}
