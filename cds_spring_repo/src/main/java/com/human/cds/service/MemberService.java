package com.human.cds.service;

import com.human.cds.vo.MemberVO;  // MemberVO가 정의된 패키지를 import

// MemberService 인터페이스 정의
public interface MemberService {
	
	
	boolean registerMember(MemberVO member); //회원등록

	boolean isEmailAvailable(String email); //이메일 상요 가능 여부 확인 

	//MemberVO getMemberById1(String memberId); 원본1
	
	MemberVO login(String memberId, String password); //로그인

	String authEmail(String email); //이메일 인증

	boolean checkId(String member_id); //아이디 중복

	boolean isPhoneDuplicate(String phone);

	boolean isEmailDuplicate(String email); //이메일 중복

	String sendVerificationCode(String email); //이메일로 인증 코드 전송

	boolean verifyCode(String email, String code); //인증 코드 검증

	MemberVO findMemberId(String name, String email); //아이디 찾기

	MemberVO findMemberPassword(String member_id, String name, String email);

	int googleLogin(MemberVO member); //구글 로그인

	int kakaoLogin(MemberVO vo); //카카오 로그인
	
	
	
/*	
    // 회원 등록 메소드
    int registerMember(MemberVO member);

    // 회원 정보 수정 메소드
    void updateMember(MemberVO member);

    // 회원 ID로 회원 정보 조회 메소드
    MemberVO getMemberById(int id);

    // 필요한 메소드들 추가
    List<MemberVO> getAllMembers();


	

	MemberVO getMemberById1(int id);

	MemberVO getMemberByEmail(String email);

	String getExistsId(String name);

	boolean isUsernameTaken(String username);
*/

}
