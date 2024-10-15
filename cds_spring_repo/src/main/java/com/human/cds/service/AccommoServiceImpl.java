package com.human.cds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.AccommoDAO;
import com.human.cds.vo.AcommoImgVO;
import com.human.cds.vo.AcommointroVO.Item;
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

	@Override
	public void accomoupdate(String contentId, String overview) {
		accommoDAO.accomoupdate(contentId, overview);
		
	}

	@Override
    public void saveRoomInfo(Item item) {
        if (item != null && item.getContentid() != null) {
            accommoDAO.insertRoomInfo(item); // DAO 호출
        }
    }

	@Override
	public List<AcommoImgVO> getAccommodationsByRegion(int areacode, int page, int pageSize,String cat3) {
	    int offset = (page - 1) * pageSize; // OFFSET 계산
	    return accommoDAO.getAccommodationsByRegion(areacode, pageSize, offset, cat3);
	}

	
	
}
