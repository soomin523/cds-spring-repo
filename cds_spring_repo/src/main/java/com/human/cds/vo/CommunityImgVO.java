package com.human.cds.vo;

public class CommunityImgVO {
    private int id;                  // 이미지 ID
    private int postId;              // 게시물 ID
    private String origin_filename;   // 원본 파일명
    private String save_filename;     // 저장된 파일명

    // Getters and Setters ...
    
    public void setImageDetails(String originFilename, String saveFilename) {
        this.origin_filename = originFilename; // 원본 파일명 설정
        this.save_filename = saveFilename;     // 저장된 파일명 설정
    }

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

	public String getOrigin_filename() {
		return origin_filename;
	}

	public void setOrigin_filename(String origin_filename) {
		this.origin_filename = origin_filename;
	}

	public String getSave_filename() {
		return save_filename;
	}

	public void setSave_filename(String save_filename) {
		this.save_filename = save_filename;
	}

	public void setImagePath(String imgName) {
	    this.save_filename = imgName; // 저장된 파일명을 설정
	}

}
