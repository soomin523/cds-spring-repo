package com.human.cds.vo;

import java.sql.Date;

public class MemberVO {
	
	private int id; // 회원 ID
    private String member_id; // 아이디
    private String password; // 비밀번호
    private Date birth_date; // 생년월일
    private String phone; // 전화번호
    private String name; // 이름
    private String email; // 이메일
    private String gender; // 성별
    private boolean marketing_consent; // 마케팅 수신 동의 여부
    private String profile_image; // 프로필 이미지
    private String withdrawal_request; // 탈퇴 요청 여부
    private String membership_level; // 회원 등급
    private Date created_at; // 가입일
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public boolean isMarketing_consent() {
		return marketing_consent;
	}
	public void setMarketing_consent(boolean marketing_consent) {
		this.marketing_consent = marketing_consent;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public String getWithdrawal_request() {
		return withdrawal_request;
	}
	public void setWithdrawal_request(String withdrawal_request) {
		this.withdrawal_request = withdrawal_request;
	}
	public String getMembership_level() {
		return membership_level;
	}
	public void setMembership_level(String membership_level) {
		this.membership_level = membership_level;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
		
}
	

