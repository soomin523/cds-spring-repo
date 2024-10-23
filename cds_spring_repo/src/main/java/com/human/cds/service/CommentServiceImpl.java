package com.human.cds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.CommentDAO;
import com.human.cds.vo.CommentLikeVO;
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
		return commentDAO.getCommentsByContentId(contentId, offset,pageSize);
	}
	
	@Override
	public CommentLikeVO checkIfAlreadyLiked(int cIdx, String name) {
	    return commentDAO.getLikeStatus(cIdx, name);
	}

	@Override
	public void addLike(int cIdx, String name, String actionType) {
	    CommentLikeVO likeStatus = commentDAO.getLikeStatus(cIdx, name);

	    if (likeStatus == null) {
	        // 처음 좋아요 또는 싫어요를 누르는 경우
	        commentDAO.addLike(cIdx, name, actionType);
	        if ("like".equals(actionType)) {
	            commentDAO.incrementLikeCount(cIdx);
	        } else if ("dislike".equals(actionType)) {
	            commentDAO.incrementDislikeCount(cIdx);
	        }
	    } else {
	        // 이미 좋아요 또는 싫어요를 누른 상태인 경우
	        String currentAction = likeStatus.getActionType();
	        
	        if (currentAction.equals(actionType)) {
	            // 같은 액션을 다시 눌렀을 때: 취소 (none으로 변경)
	            commentDAO.updateLike(cIdx, name, "none");
	            if ("like".equals(actionType)) {
	                commentDAO.decrementLikeCount(cIdx);
	            } else if ("dislike".equals(actionType)) {
	                commentDAO.decrementDislikeCount(cIdx);
	            }
	        } else {
	            // 반대 액션을 눌렀을 때, 먼저 반대 액션이 활성화되어 있는지 확인 후 감소 처리
	            if ("like".equals(actionType)) {
	                commentDAO.incrementLikeCount(cIdx);  // 좋아요 +1
	                if (!"none".equals(currentAction)) {
	                    commentDAO.decrementDislikeCount(cIdx); // 싫어요 -1 (이미 활성화된 경우에만 감소)
	                }
	            } else {
	                commentDAO.incrementDislikeCount(cIdx); // 싫어요 +1
	                if (!"none".equals(currentAction)) {
	                    commentDAO.decrementLikeCount(cIdx); // 좋아요 -1 (이미 활성화된 경우에만 감소)
	                }
	            }
	            commentDAO.updateLike(cIdx, name, actionType); // 새로운 액션 업데이트
	        }
	    }
	}





	@Override
	public void removeLike(int cIdx, String name) {
	    // 좋아요/싫어요 상태 확인
	    CommentLikeVO likeStatus = commentDAO.getLikeStatus(cIdx, name);
	    
	    // 로그: likeStatus 확인
	    System.out.println("removeLike 호출됨");
	    
	    if (likeStatus != null) {
	        // actionType을 'none'으로 업데이트하여 취소 처리
	        commentDAO.updateLike(cIdx, name, "none");
	        if ("like".equals(likeStatus.getActionType())) {
	            commentDAO.decrementLikeCount(cIdx);
	        } else if ("dislike".equals(likeStatus.getActionType())) {
	            commentDAO.decrementDislikeCount(cIdx);
	        }
	    }
	}

	@Override
	public int deleteComment(int c_idx) {
		// TODO Auto-generated method stub
		return commentDAO.deleteComment(c_idx);
	}


}
