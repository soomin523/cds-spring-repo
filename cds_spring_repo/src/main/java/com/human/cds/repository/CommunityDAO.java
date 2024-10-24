package com.human.cds.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.CommunityImgVO;
import com.human.cds.vo.CommunityVO;

@Repository
public class CommunityDAO {

    @Autowired
    private SqlSession sqlSession;
    private static final String MAPPER = "com.human.cds.mapper.CommunityMapper";  // 적절한 매퍼로 변경


    //리스트 불러오기
	public List<CommunityVO> getCommunityList() {
		List<CommunityVO> communityList =  sqlSession.selectList(MAPPER+".getCommunityList");
		for(CommunityVO vo : communityList) {
			int idx = vo.getC_idx();
			List<CommunityImgVO> imagePathList = sqlSession.selectList(MAPPER+".getImagePathList", idx);
			vo.setImagePaths(imagePathList);
		}
		return communityList;
	}

	//모달용 가져오기
	public CommunityVO getCommunity(int id) {
		CommunityVO vo = sqlSession.selectOne(MAPPER+".getCommunity", id);
		
		List<CommunityImgVO> imagePathList = sqlSession.selectList(MAPPER+".getImagePathList", id);
		vo.setImagePaths(imagePathList);
		
		return vo;
	}

	//게시물 추가하기
	public int uploadPost(CommunityVO vo) {
		int result = 0;
		
		if(sqlSession.insert(MAPPER+".insertPost", vo) == 1) {
			
			int count = sqlSession.selectOne(MAPPER+".getCommunityCount");
			
			CommunityImgVO imgvo = new CommunityImgVO();
			for(String imgName : vo.getImagenames()) {
				imgvo.setPostId(count);
				imgvo.setImagePath(imgName);
				result += sqlSession.insert(MAPPER+".insertImg", imgvo);
			}
		}
		
		if(result >= 1) {
			result = 1;
		}
		
		return result;
		
	}

	public List<CommunityVO> getLocationList(String location) {
		
		List<CommunityVO> communityList =  sqlSession.selectList(MAPPER+".getLocationList", location);
		for(CommunityVO vo : communityList) {
			int idx = vo.getC_idx();
			List<CommunityImgVO> imagePathList = sqlSession.selectList(MAPPER+".getImagePathList", idx);
			vo.setImagePaths(imagePathList);
		}
		return communityList;
	}

	public List<CommunityVO> getCommupost(Map<String, String> map) {
		
		List<CommunityVO> communityList = null;
		String select = map.get("select");
		String area = map.get("area");
		
		if(select.equals("latest") && !area.equals("all")) {
			communityList = sqlSession.selectList(MAPPER+".getlatestarea", map);//최신순
		}else if(select.equals("rating") && !area.equals("all")) {
			communityList = sqlSession.selectList(MAPPER+".getratingarea", map);//평점순
		}else if(select.equals("latest")){
			communityList = sqlSession.selectList(MAPPER+".getlatest", select);//최신순
		}else {
			communityList = sqlSession.selectList(MAPPER+".getrating", select);//평점순
		}
		
		for(CommunityVO vo : communityList) {
			int idx = vo.getC_idx();
			List<CommunityImgVO> imagePathList = sqlSession.selectList(MAPPER+".getImagePathList", idx);
			vo.setImagePaths(imagePathList);
		}
		
		return communityList;
	}

	public List<CommunityVO> getSearchList(String search) {
		List<CommunityVO> communityList = sqlSession.selectList(MAPPER+".getSeachList",search); // 검색 결과 반환
		for(CommunityVO vo : communityList) {
			int idx = vo.getC_idx();
			List<CommunityImgVO> imagePathList = sqlSession.selectList(MAPPER+".getImagePathList", idx);
			vo.setImagePaths(imagePathList);
		}

	    return communityList;
	}
}