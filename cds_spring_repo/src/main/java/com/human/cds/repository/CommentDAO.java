package com.human.cds.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}