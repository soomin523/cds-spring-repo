package com.human.cds.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

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
			List<CommunityImgVO> attachedList = sqlSession.selectList(MAPPER+".getImagePathList", idx);
			vo.setAttachedList(attachedList);
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
		
		List<CommunityImgVO> attachedList = sqlSession.selectList(MAPPER+".getImagePathList", idx);
		vo.setAttachedList(attachedList);
		
		List<CommunityContentVO> contentList = sqlSession.selectList(MAPPER+".getCommentsByArticleId", idx);
		vo.setComments(contentList);
		
		int commentNum = sqlSession.selectOne(MAPPER+".getCommentCount", idx);
		vo.setCommentNum(commentNum);
		int like = sqlSession.selectOne(MAPPER+".getLikeCount", idx);
		vo.setLikeNum(like);
		
		return vo;
	}

	// 게시물 추가하기
	public int uploadPost(CommunityVO vo) {
	    int result = 0;
	    
	    // 게시물 추가
	    if (sqlSession.insert(MAPPER + ".insertPost", vo) == 1) {
	        int c_idx = sqlSession.selectOne(MAPPER + ".getCIdx");//게시물 ID 가져오기

	        // 이미지 정보 추가
	        for (CommunityImgVO attached : vo.getAttachedList()) { 
	            if (attached != null) {
	                CommunityImgVO imgvo = new CommunityImgVO();
	                System.out.println("uploadPost 호출됨: " + imgvo);
	                imgvo.setPostId(c_idx); // 게시물 ID 설정
	                imgvo.setOrigin_filename(attached.getOrigin_filename()); // 원본 파일명 설정
	                imgvo.setSave_filename(attached.getSave_filename()); // 저장된 파일명 설정, 이 함수는 파일 저장 로직을 구현해야 함
	                result += sqlSession.insert(MAPPER + ".insertImg", imgvo); // 이미지 정보 추가
	            }
	        }
	    }
	    
	    return result >= 1 ? 1 : 0; // 성공적으로 추가된 경우 1 반환
	}

//	public int updatePost1(CommunityVO vo) {
//	    int result = sqlSession.update(MAPPER + ".updatePost", vo);
//	    
//	    if (result == 1) {
//	        // 기존 이미지 삭제 로직 추가 필요 (예: deleteImg)
//
//	        // 새로운 이미지 추가
//	        for (MultipartFile file : vo.getUploadFiles()) { // MultipartFile 사용
//	            if (!file.isEmpty()) {
//	                CommunityImgVO imgvo = new CommunityImgVO();
//	                imgvo.setPostId(vo.getC_idx()); // 게시물 ID 설정
//	                imgvo.setOrigin_filename(file.getOriginalFilename()); // 원본 파일명 설정
//	                imgvo.setSave_filename(saveFile(file)); // 저장된 파일명 설정
//	                sqlSession.insert(MAPPER + ".insertImg", imgvo); // 이미지 정보 추가
//	            }
//	        }
//	    }
//	    
//	    return result; // 수정 결과 반환
//	}


	public List<CommunityVO> getLocationList(String location) {
		
		List<CommunityVO> communityList =  sqlSession.selectList(MAPPER+".getLocationList", location);
		for(CommunityVO vo : communityList) {
			int idx = vo.getC_idx();
			List<CommunityImgVO> attachedList = sqlSession.selectList(MAPPER+".getImagePathList", idx);
			vo.setAttachedList(attachedList);
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
			List<CommunityImgVO> attachedList = sqlSession.selectList(MAPPER+".getImagePathList", idx);
			vo.setAttachedList(attachedList);
			int content = sqlSession.selectOne(MAPPER+".getCommentCount", idx);
			vo.setCommentNum(content);
			int like = sqlSession.selectOne(MAPPER+".getLikeCount", idx);
			vo.setLikeNum(like);
		}
		
		return communityList;
	}

	public List<CommunityVO> getSearchList(String search) {
		List<CommunityVO> communityList = sqlSession.selectList(MAPPER+".getSearchList",search); // 검색 결과 반환
		for(CommunityVO vo : communityList) {
			int idx = vo.getC_idx();
			List<CommunityImgVO> attachedList = sqlSession.selectList(MAPPER+".getImagePathList", idx);
			vo.setAttachedList(attachedList);
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

	public int deletePost(int c_idx) {
		return sqlSession.delete(MAPPER+".deletePost", c_idx);
	}

	public int deleteComment(int comment_id) {
		return sqlSession.delete(MAPPER+".deleteComment", comment_id);
	}

	public int updatePost(CommunityVO vo) {
		int result = 0;
		result = sqlSession.update(MAPPER+".updatePost", vo);
		System.out.println(result);
		
		if( result == 1) {
			
			int count = vo.getC_idx();
			System.out.println(count);
			
			CommunityImgVO imgvo = new CommunityImgVO();
//			for(String imgName : vo.getImagenames()) {
//				imgvo.setPostId(count);
//				imgvo.setImagePath(imgName);
//				result += sqlSession.insert(MAPPER+".insertImg", imgvo);
//			}
		}
		
		if(result >= 1) {
			result = 1;
		}
		
		return result;
	}

	//커뮤니티 게시글 목록에 업로드된 파일들을 포함해서 가져오기
	public List<CommunityVO> getCommunityList(int c_idx) {
		List<CommunityVO> communityList =  sqlSession.selectList(MAPPER+".getCommunityList");
		for(CommunityVO vo : communityList) {
			int idx = vo.getC_idx();
			List<CommunityImgVO> attachedList = sqlSession.selectList(MAPPER+".getImagePathList", idx);
			vo.setAttachedList(attachedList);
			int content = sqlSession.selectOne(MAPPER+".getCommentCount", idx);
			vo.setCommentNum(content);
			int like = sqlSession.selectOne(MAPPER+".getLikeCount", idx);
			vo.setLikeNum(like);
		}
		return communityList;
	}
}