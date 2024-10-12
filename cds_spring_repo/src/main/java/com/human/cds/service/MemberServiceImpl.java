package com.human.cds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.MemberDAO;
import com.human.cds.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberDAO memberDAO;
    
    @Autowired
    public MemberServiceImpl(MemberDAO memberDAO) {
    	this.memberDAO = memberDAO;
    }

    // 회원가입 처리
    @Override
    public boolean registerMember(MemberVO member) {
        return memberDAO.insertMember(member);
    }

	@Override
	public boolean isEmailAvailable(String email) {
		return false;
	}

	@Override
	public MemberVO getMemberById1(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}

 /*   
    // 회원 정보 수정 처리
    @Override
    public void updateMember1(MemberVO member) {
        memberDAO.updateMember(member);
    }

    // 회원 ID로 회원 정보 조회
    @Override
    public MemberVO getMemberById(int id) {
        return memberDAO.getMemberById(id);
    }

    // 이메일로 회원 정보 조회
    @Override
    public MemberVO getMemberByEmail(String email) {
        return memberDAO.selectMemberByEmail(email);
    }

    // 이메일 중복 여부 확인
    @Override
    public boolean isEmailAvailable(String email) {
        return memberDAO.isEmailAvailable(email);
    }

    // 회원 정보 수정 처리
    @Override
    public void updateMember(MemberVO member) {
        memberDAO.updateMember(member);
    }

    // 전체 회원 목록 조회
    @Override
    public List<MemberVO> getAllMembers() {
        return mapper.selectAllMembers();  
    }

    // 회원 ID로 회원 정보 조회 (다른 방법으로 사용될 수 있는 메서드)
    @Override
    public MemberVO getMemberById1(int id) {
        return memberDAO.getMemberById(id);  
    }

    // 회원 아이디 존재 여부 확인
    @Override
    public String getExistsId(String memberId) {
        return mapper.selectExistsId(memberId);  
    }

    // 회원 등록 처리
    @Override
    public int registerMember(MemberVO member) {
        return mapper.insertMember(member); 
    }

	@Override
	public boolean isUsernameTaken(String username) {
		// TODO Auto-generated method stub
		return false;
	}
*/
}
