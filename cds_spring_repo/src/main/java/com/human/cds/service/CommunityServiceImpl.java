package com.human.cds.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.human.cds.repository.CommunityDAO;
import com.human.cds.vo.CommunityContentVO;
import com.human.cds.vo.CommunityImgVO;
import com.human.cds.vo.CommunityVO;

@Service
public class CommunityServiceImpl implements CommunityService {

    private CommunityDAO communityDAO;

    @Autowired
    public CommunityServiceImpl(CommunityDAO communityDAO) {
    	this.communityDAO = communityDAO;
    }

    @Override
    public List<CommunityVO> getCommunityList() {
    	 return communityDAO.getCommunityList();
    }

	@Override
	public CommunityVO getCommunity(int id) {
		return communityDAO.getCommunity(id);
	}

	@Override
	public int uploadPost(CommunityVO vo) {
		return communityDAO.uploadPost(vo);
	}

	@Override
	public List<CommunityVO> getLocationList(String location) {
		return communityDAO.getLocationList(location);
	}

	@Override
	public List<CommunityVO> getCommupost(String select, String area) {
		Map<String, String> map = new HashMap<>();
		map.put("select", select);
		map.put("area", area);
		
		return communityDAO.getCommupost(map);
	}

	@Override
	public List<CommunityVO> getSearchList(String search) {
		return communityDAO.getSearchList(search);
	}

	@Override
	public int insertComment(String memberId, String content, String c_idx) {
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("content", content);
		map.put("c_idx", c_idx);
		
		return communityDAO.insertComment(map);
	}

	@Override
	public CommunityContentVO getComment(int c_idx) {
		return communityDAO.getComment(c_idx);
	}

	@Override
	public int insertLike(String memberId, String c_idx) {
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("c_idx", c_idx);
		
		return communityDAO.insertLike(map);
	}

	@Override
	public int deletePost(int c_idx) {
		return communityDAO.deletePost(c_idx);
	}

	@Override
	public int deleteComment(int comment_id) {
		return communityDAO.deleteComment(comment_id);
	}

	@Override
	public int updatePost(CommunityVO vo) {
		return communityDAO.updatePost(vo);
	}

	@Override
	public void saveImage(CommunityImgVO imgVO) {
		// TODO Auto-generated method stub
		
	}

	//게시글 추가
	@Override
	public int uploadPost(MultipartFile[] uploadFiles, HttpServletRequest request, CommunityVO vo) {
		int result = 0; //게시글 추가 실패시 결과값
		
		String uploadDir = request.getServletContext().getRealPath("resources/uploads/");// 파일 저장 경로
		System.out.println("uploadPost 서비스 호출");

        //업로드된 파일들을 실제 파일과 저장파일로 구분해서 저장하기 위한 List객체 생성
        List<CommunityImgVO> attachedList = new ArrayList<>();
        
        for (MultipartFile file : uploadFiles) {
            if (!file.isEmpty()) { // 파일이 비어있지 않은 경우
                try {
                    // 파일 저장
                    String originalFilename = file.getOriginalFilename();
                    String saveFilename = generateUniqueFilename(originalFilename); // 고유 파일명 생성
                    File destinationFile = new File(uploadDir + saveFilename);
                    file.transferTo(destinationFile); // 파일 저장

                    // CommunityImgVO 객체 생성 및 DB 저장
                    CommunityImgVO imgVO = new CommunityImgVO();
                    imgVO.setPostId(vo.getC_idx()); // 게시물 ID
                    imgVO.setOrigin_filename(originalFilename);
                    imgVO.setSave_filename(saveFilename); // 저장된 파일명
                    attachedList.add(imgVO); // DB에 이미지 정보 저장

                } catch (IOException e) {
                	System.out.println("커뮤니티 게시글 추가 중 예외발생: " + e);
                }
            }
        }//end of for
        //업로드 파일 List를 attachedList 필드에 설정
        vo.setAttachedList(attachedList);
        
        result = communityDAO.uploadPost(vo);
        
		return result;
	}
	
    // 고유 파일명 생성 메서드
    private String generateUniqueFilename(String originalFilename) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return timestamp + "_" + originalFilename; // 예시로 타임스탬프와 원본 파일명 결합
    }

}

