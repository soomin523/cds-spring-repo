package com.human.cds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.CommentDAO;
import com.human.cds.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private CommentDAO commentDAO;  // DAO 주입

    @Override
    public boolean addComment(CommentVO commentVO) {
        return commentDAO.insertComment(commentVO) > 0; // 댓글 추가 성공 여부
    }

	@Override
	public List<CommentVO> getCommentsByContentId(String contentId,int offset,int pageSize) {
		// TODO Auto-generated method stub
		return commentDAO.getCommentsByContentId(contentId, offset,pageSize);
	}
}
