package com.human.cds.vo;

import java.sql.Timestamp;

public class CommunityLikeVO {
    private int like_id; 
    private int c_idx; 
    private String memberId;   
    private Timestamp liked_at;
    
	public int getLike_id() {
		return like_id;
	}
	public void setLike_id(int like_id) {
		this.like_id = like_id;
	}
	public int getC_idx() {
		return c_idx;
	}
	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Timestamp getLiked_at() {
		return liked_at;
	}
	public void setLiked_at(Timestamp liked_at) {
		this.liked_at = liked_at;
	} 
    
}
