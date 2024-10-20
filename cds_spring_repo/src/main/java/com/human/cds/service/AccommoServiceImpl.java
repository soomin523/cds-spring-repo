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

	
	//모달숙소 띄우기 위한 데이터 불러오기 VO두개를 사용하는데 리스트를 사용해서 contentid가 같은값은 list에 저장
	@Override
	public AccommodationVO getAccommodationDetails(String contentId) {
		// AccommodationVO 가져오기
        AccommodationVO accommodation = accommoDAO.getAccommodationByContentId(contentId);
        if (accommodation != null) {
            // AccommodationRoomVO 리스트 가져오기
            List<AccommodationRoomVO> rooms = accommoDAO.getRoomsByContentId(contentId);
            accommodation.setRooms(rooms); // AccommodationVO에 방 정보 설정
        }
        return accommodation;
    }
	
	
}
