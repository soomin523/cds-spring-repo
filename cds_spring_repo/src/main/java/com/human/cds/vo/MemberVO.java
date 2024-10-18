package com.human.cds.vo;

import java.sql.Date;

public class MemberVO {
	
	private int m_id; // 회원 ID
    private String member_id; // 아이디
    private String password; // 비밀번호
    private Date birth_date; // 생년월일
    private String phone; // 전화번호
    private String name; // 이름
    private String email; // 이메일
    private String email_prefix;
    private String email_domain;
    private String gender; // 성별
    private boolean marketing_consent; // 마케팅 수신 동의 여부
    private String withdrawal_request; // 탈퇴 요청 여부
    private String membership_level; // 회원 등급
    private Date created_at; // 가입일
	
    
    public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
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
	public String getEmail_prefix() {
		return email_prefix;
	}
	public void setEmail_prefix(String email_prefix) {
		this.email_prefix = email_prefix;
	}
	public String getEmail_domain() {
		return email_domain;
	}
	public void setEmail_domain(String email_domain) {
		this.email_domain = email_domain;
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
	

