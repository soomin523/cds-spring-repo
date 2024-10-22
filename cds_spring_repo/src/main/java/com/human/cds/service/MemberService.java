package com.human.cds.service;

import com.human.cds.vo.MemberVO;  // MemberVO가 정의된 패키지를 import

// MemberService 인터페이스 정의
public interface MemberService {
	
	
	boolean registerMember(MemberVO member); //회원등록

	boolean isEmailAvailable(String email); //이메일 사용가능 여부 확인
	
	MemberVO login(String memberId, String password); //로그인

	String authEmail(String email); //이메일 인증

	boolean checkId(String member_id); //아이디 중복

	boolean isPhoneDuplicate(String phone);//전화번호 중복

	String sendVerificationCode(String email); //이메일로 인증 코드 전송

	MemberVO findMemberId(String name, String email); //아이디 찾기

	MemberVO findMemberPassword(String member_id, String name, String email); //비밀번호 찾기

	int googleLogin(MemberVO member); //구글 로그인

	int kakaoLogin(MemberVO vo); //카카오 로그인

	int naverLogin(MemberVO member); //네이버 로그인

}
