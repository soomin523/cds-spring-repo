package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.CommentLikeVO;
import com.human.cds.vo.CommentVO;

public interface CommentService {
	boolean addComment(CommentVO commentVO); // 댓글 추가

	List<CommentVO> getCommentsByContentId(String contentId,int offset,int pageSize);

	CommentLikeVO checkIfAlreadyLiked(int cIdx, String name);

	void addLike(int cIdx, String name, String actionType);

	void removeLike(int cIdx, String name);

	int deleteComment(int c_idx);
}
