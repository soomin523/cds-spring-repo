package com.human.cds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.AccommoDAO;
import com.human.cds.vo.AccommodationRoomVO;
import com.human.cds.vo.AccommodationVO;
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
	public List<AcommoImgVO> getAccommodationsByRegion(int areacode, int page, int pageSize,String cat3,String search) {
	    int offset = (page - 1) * pageSize; // OFFSET 계산
	    
	    if(search !=null && !search.isEmpty()) {
	    	search = "%" + search.toLowerCase() + "%"; // 검색어를 위한 LIKE 패턴
	        return accommoDAO.getFilteredAccommodationsByRegion(areacode, offset, pageSize, cat3, search);    	    	
	    }
	    
	    return accommoDAO.getAccommodationsByRegion(areacode, pageSize, offset, cat3);
	}

	@Override
    public AccommodationVO getAccommodationByContentId(String contentId) {
        // DAO 호출하여 숙소 정보 가져오기
        return accommoDAO.getAccommodationByContentId(contentId);
    }

    @Override
    public List<AccommodationRoomVO> getRoomsByContentId(String contentId) {
        // DAO 호출하여 방 정보 가져오기
        return accommoDAO.getRoomsByContentId(contentId);
    }

	@Override
	public void incrementcnt(String contentId) {
		accommoDAO.incrementcnt(contentId);
		
	}

	
	
}
