package com.human.cds.vo;

public class CommunityImgVO {
    private int id;          // 게시물 ID
    private int postId;    // 작성자 ID
    private String imagePath;       // 게시물 제목
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
