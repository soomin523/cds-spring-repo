package com.human.cds.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.CommunityContentVO;
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
			int content = sqlSession.selectOne(MAPPER+".getCommentCount", idx);
			vo.setCommentNum(content);
			int like = sqlSession.selectOne(MAPPER+".getLikeCount", idx);
			vo.setLikeNum(like);
		}
		return communityList;
	}

	//모달용 가져오기
	public CommunityVO getCommunity(int idx) {
		CommunityVO vo = sqlSession.selectOne(MAPPER+".getCommunity", idx);
		
		List<CommunityImgVO> imagePathList = sqlSession.selectList(MAPPER+".getImagePathList", idx);
		vo.setImagePaths(imagePathList);
		
		List<CommunityContentVO> contentList = sqlSession.selectList(MAPPER+".getCommentsByArticleId", idx);
		vo.setComments(contentList);
		
		int commentNum = sqlSession.selectOne(MAPPER+".getCommentCount", idx);
		vo.setCommentNum(commentNum);
		int like = sqlSession.selectOne(MAPPER+".getLikeCount", idx);
		vo.setLikeNum(like);
		
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
			int content = sqlSession.selectOne(MAPPER+".getCommentCount", idx);
			vo.setCommentNum(content);
			int like = sqlSession.selectOne(MAPPER+".getLikeCount", idx);
			vo.setLikeNum(like);
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
			int content = sqlSession.selectOne(MAPPER+".getCommentCount", idx);
			vo.setCommentNum(content);
			int like = sqlSession.selectOne(MAPPER+".getLikeCount", idx);
			vo.setLikeNum(like);
		}
		
		return communityList;
	}

	public List<CommunityVO> getSearchList(String search) {
		List<CommunityVO> communityList = sqlSession.selectList(MAPPER+".getSeachList",search); // 검색 결과 반환
		for(CommunityVO vo : communityList) {
			int idx = vo.getC_idx();
			List<CommunityImgVO> imagePathList = sqlSession.selectList(MAPPER+".getImagePathList", idx);
			vo.setImagePaths(imagePathList);
			int content = sqlSession.selectOne(MAPPER+".getCommentCount", idx);
			vo.setCommentNum(content);
			int like = sqlSession.selectOne(MAPPER+".getLikeCount", idx);
			vo.setLikeNum(like);
		}

	    return communityList;
	}

	public int insertComment(Map<String, String> map) {
		return sqlSession.insert(MAPPER+".insertComment", map);
	}

	public CommunityContentVO getComment(int c_idx) {
		return sqlSession.selectOne(MAPPER+".getComment", c_idx);
	}

	public int insertLike(Map<String, String> map) {
		int result = 0;
		int idx = Integer.parseInt(map.get("c_idx"));
		
		if((int)sqlSession.selectOne(MAPPER+".likeDouble", map) == 1) {
			result = sqlSession.delete(MAPPER+".deleteLike", map);
		}else {
			result = sqlSession.insert(MAPPER+".insertLike", map);
		}
		
		if(result == 1) {
			result = sqlSession.selectOne(MAPPER+".getLikeCount", idx);
		}
		
		return result;
	}
}