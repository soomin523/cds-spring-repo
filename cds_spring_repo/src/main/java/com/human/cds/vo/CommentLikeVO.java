package com.human.cds.vo;
public class CommentLikeVO {
    private int c_idx; // 댓글 ID
    private String name; // 좋아요/싫어요를 누른 회원 ID
    private String actionType; // 'like' 또는 'dislike'
    private String createdAt; // 기록 생성 시간

    // 기본 생성자
    public CommentLikeVO() {}

    // 필드에 대한 Getter와 Setter
    public int getC_idx() {
        return c_idx;
    }

    public void setC_idx(int c_idx) {
        this.c_idx = c_idx;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
