package com.human.cds.service;

import com.human.cds.vo.MemberVO;  // MemberVO가 정의된 패키지를 import

// MemberService 인터페이스 정의
public interface MemberService {
	
	
	boolean registerMember(MemberVO member);

	boolean isEmailAvailable(String email);

	//MemberVO getMemberById1(String memberId); 원본1
	
	MemberVO login(String memberId, String password); //로그인

	String authEmail(String email); 

	boolean checkId(String member_id); //아이디 중복

	boolean isPhoneDuplicate(String phone);

	boolean isEmailDuplicate(String email);
	
	boolean getMemberById(String memberId); //수정1

	String sendVerificationCode(String email);

	boolean verifyCode(String email, String code);
	
	
/*	
    // 회원 등록 메소드
    int registerMember(MemberVO member);

    // 회원 정보 수정 메소드
    void updateMember(MemberVO member);

    // 회원 ID로 회원 정보 조회 메소드
    MemberVO getMemberById(int id);

    // 필요한 메소드들 추가
    List<MemberVO> getAllMembers();


	boolean isEmailAvailable(String email);

	void updateMember1(MemberVO member);

	MemberVO getMemberById1(int id);

	MemberVO getMemberByEmail(String email);

	String getExistsId(String name);

	boolean isUsernameTaken(String username);
*/

}
