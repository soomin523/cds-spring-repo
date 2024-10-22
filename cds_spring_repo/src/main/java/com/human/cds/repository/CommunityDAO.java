package com.human.cds.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.CommunityVO;

@Repository
public class CommunityDAO {

    @Autowired
    private SqlSession sqlSession;
    private static final String MAPPER = "com.human.cds.mapper.CommunityMapper";  // 적절한 매퍼로 변경

    // 댓글 삽입
    public int insertComment(CommunityVO commentVO) {
        return sqlSession.insert(MAPPER + ".insertComment", commentVO);
    }

    // 댓글 목록 조회 (페이징 처리)
    public List<CommunityVO> getCommentsByContentId(String contentId, int offset, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("contentId", contentId);
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        return sqlSession.selectList(MAPPER + ".getCommentsByContentId", params);
    }

    // 댓글 삭제
    public int deleteComment(String commentId) {
        return sqlSession.delete(MAPPER + ".deleteComment", commentId);
    }

    // 댓글 수 조회
    public int countCommentsByContentId(String contentId) {
        return sqlSession.selectOne(MAPPER + ".countCommentsByContentId", contentId);
    }

    // 좋아요 추가
    public int insertLike(int postId, String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        params.put("username", username);
        return sqlSession.insert(MAPPER + ".insertLike", params);
    }

    // 이미지 경로 삽입
    public void insertImagePath(int postId, String fileName) {
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        params.put("fileName", fileName);
        sqlSession.insert(MAPPER + ".insertImagePath", params);
    }

    // 게시물 삽입
    public void insertPost(CommunityVO communityVO) {
        sqlSession.insert(MAPPER + ".insertPost", communityVO);
    }

	public List<CommunityVO> getCommunityList() {
		return sqlSession.selectList(MAPPER+".getCommunityList");
	}
}
