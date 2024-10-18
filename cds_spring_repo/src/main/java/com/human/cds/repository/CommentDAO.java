package com.human.cds.repository;

import java.util.List;

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

	public List<CommentVO> getCommentsByContentId(String contentId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(MAPPER+".getCommentsByContentId",contentId);
	}

}