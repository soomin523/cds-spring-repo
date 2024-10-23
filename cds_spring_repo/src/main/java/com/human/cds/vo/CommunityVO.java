package com.human.cds.vo;

import java.sql.Timestamp;
import java.util.List;

public class CommunityVO {
    private int c_idx;          // 게시물 ID
    private String memberId;    // 작성자 ID
    private String title;       // 게시물 제목
    private String content;     // 게시물 내용
    private Timestamp created_at; // 게시물 작성 시간
    private Timestamp updated_at; // 게시물 수정 시간
    private boolean isDeleted;   // 삭제 여부
    private int likes;           // 좋아요 수
    private int comments;        // 댓글 수
    private String tag;          // 태그
    private String location;      // 지역 정보
    private float rating;        // 평점 (1~5)
    private List<CommunityImgVO> imagePaths; // 이미지 경로 배열

	// Getter 및 Setter 메소드
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments; // 댓글 수를 설정
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

	public List<CommunityImgVO> getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(List<CommunityImgVO> imagePaths) {
		this.imagePaths = imagePaths;
	}


}
