package com.human.cds.repository;

import java.util.List;

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
			System.out.println(vo.getImagePaths());
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
			for(CommunityImgVO imgvo : vo.getImagePaths()) {
				imgvo.setPostId(count+1);
				result += sqlSession.insert(MAPPER+".insertImg", imgvo);
			}
		}
		if(result > 1) {
			result = 1;
		}
		
		return 0;
		
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
}
