package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.CommentVO;

public interface CommentService {
	boolean addComment(CommentVO commentVO); // 댓글 추가

	List<CommentVO> getCommentsByContentId(String contentId,int offset,int pageSize);
}
