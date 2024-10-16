package com.human.cds.vo;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
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
		
}
	

