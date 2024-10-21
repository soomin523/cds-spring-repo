package com.human.cds.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.CommentLikeVO;
import com.human.cds.vo.CommentVO;

@Repository
public class CommentDAO {

    @Autowired
    private SqlSession sqlSession;
    private static final String MAPPER = "com.human.cds.mapper.CourseInfoMapper";

    // 댓글 삽입
    public int insertComment(CommentVO commentVO) {
        return sqlSession.insert(MAPPER + ".insertComment", commentVO);
    }

    public List<CommentVO> getCommentsByContentId(String contentId, int offset, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("contentId", contentId);
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        return sqlSession.selectList(MAPPER+".getCommentsByContentId", params);
    }

    // 좋아요/싫어요 상태 확인
    public CommentLikeVO getLikeStatus(int cIdx, String memberId) {
        return sqlSession.selectOne(MAPPER + ".getLikeStatus", Map.of("c_idx", cIdx, "member_id", memberId));
    }

    // 좋아요/싫어요 추가
    public void addLike(int cIdx, String memberId, String actionType) {
        sqlSession.insert(MAPPER + ".addLike", Map.of("c_idx", cIdx, "member_id", memberId, "action_type", actionType));
    }

    // 좋아요 수 증가
    public void incrementLikeCount(int cIdx) {
        sqlSession.update(MAPPER + ".incrementLikeCount", cIdx);
    }

    // 싫어요 수 증가
    public void incrementDislikeCount(int cIdx) {
        sqlSession.update(MAPPER + ".incrementDislikeCount", cIdx);
    }

    // 좋아요 수 감소
    public void decrementLikeCount(int cIdx) {
        sqlSession.update(MAPPER + ".decrementLikeCount", cIdx);
    }

    // 싫어요 수 감소
    public void decrementDislikeCount(int cIdx) {
        sqlSession.update(MAPPER + ".decrementDislikeCount", cIdx);
    }

    // 좋아요/싫어요 업데이트
    public void updateLike(int cIdx, String memberId, String actionType) {
        Map<String, Object> params = new HashMap<>();
        params.put("c_idx", cIdx);
        params.put("member_id", memberId);
        params.put("action_type", actionType); // 취소 시 'none'으로 업데이트

        sqlSession.update(MAPPER + ".updateLike", params);
    }


    public void removeLike(int cIdx, String memberId) {
        sqlSession.delete(MAPPER + ".removeLike", Map.of("c_idx", cIdx, "member_id", memberId));
    }
}
