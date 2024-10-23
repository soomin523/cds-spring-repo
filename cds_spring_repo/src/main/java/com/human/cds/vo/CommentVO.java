package com.human.cds.vo;

import java.sql.Timestamp;

public class CommentVO {
    private int c_idx;          // 댓글 ID
    private String name;     // 작성자 ID (문자열로 변경)
    private String content;      // 댓글 내용
	private Timestamp createdAt; // 댓글 작성 시간
    private Timestamp updatedAt; // 댓글 수정 시간
    private boolean isDeleted;   // 삭제 여부
    private int clike;           // 좋아요 수
    private int unlike;          // 싫어요 수
    private String contentId;    // 코스 ID (문자열)
    private String gender;
    private String title;
    private String firstimage;
    
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstimage() {
		return firstimage;
	}
	public void setFirstimage(String firstimage) {
		this.firstimage = firstimage;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getC_idx() {
		return c_idx;
	}
	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getClike() {
		return clike;
	}
	public void setClike(int clike) {
		this.clike = clike;
	}
	public int getUnlike() {
		return unlike;
	}
	public void setUnlike(int unlike) {
		this.unlike = unlike;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	

    // Getters and Setters
}
