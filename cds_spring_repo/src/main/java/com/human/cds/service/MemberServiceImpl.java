package com.human.cds.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.human.cds.repository.MemberDAO;
import com.human.cds.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	//static Logger logger = Logger.getLogger("MemberServiceImpl");

    private MemberDAO memberDAO;

    @Autowired
    public MemberServiceImpl(MemberDAO memberDAO, JavaMailSenderImpl mailSender) {
    	this.memberDAO = memberDAO;
    	this.mailSender = mailSender;
    }
    
    // 회원가입 처리
    @Override
    public boolean registerMember(MemberVO member) {
    	System.out.println("회원가입서비스");
        return memberDAO.insertMember(member);
    }
	
	//아이디 중복
	@Override
	public boolean checkId(String member_id) {
        return memberDAO.checkId(member_id);
	}
	
	//전화번호 중복
	@Override
	public boolean isPhoneDuplicate(String phone) {
		return memberDAO.isPhoneDuplicate(phone);
	}

	// 메일 인증 관련 메일 전송 객체 의존 자동 주입 받기
	private JavaMailSenderImpl mailSender;
	
	//이메일 중복 확인
	@Override
	public boolean isEmailAvailable(String email) {
		return memberDAO.isEmailAvailable(email);
	}

	@Override
	public String sendVerificationCode(String email) {
	    if (!isEmailAvailable(email)) {
	        return "EMAIL_ALREADY_EXISTS"; // 이메일이 이미 존재하는 경우
	    }
	    String verificationCode = authEmail(email);        
	    return verificationCode;
	}

	// 메일 인증
	@Override
	public String authEmail(String email) {
	    int authNumber = (int)(Math.random()*888889)+111111;
	    
	    String setFrom = "songseonho1235@gmail.com"; // 송신자의 메일주소
	    String toMail = email; // 수신자의 메일주소
	    String title = "회원가입 인증 이메일입니다"; // 제목
	    String content = "홈페이지를 방문해주셔서 감사합니다. <br><br> "
	            + "인증번호: " + authNumber + "<br>"
	            + "해당 인증번호를 인증번호 확인란에 입력해 주세요";
	    mailSend(setFrom, toMail, title, content);
	    
	    return Integer.toString(authNumber);
	}

	// 이메일 전송 메소드
	private void mailSend(String setFrom, String toMail, String title, String content) {
	    System.out.println("메일주소: " + toMail);
	    
	    MimeMessage message = mailSender.createMimeMessage();
	    
	    try {
	        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
	        helper.setFrom(setFrom);
	        helper.setTo(toMail);
	        helper.setSubject(title);
	        helper.setText(content, true); // true: html형식으로 전송
	        mailSender.send(message);
	        
	    } catch (Exception e) {
	        System.out.println("메일 전송 중 예외 발생");
	        e.printStackTrace();
	    }
	}

	
	
	// 로그인 처리
	public MemberVO login(String memberId, String password) {
		
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("password", password);
		
		return memberDAO.login(map);
	}
	
	//아이디 찾기
	@Override
	public MemberVO findMemberId(String name, String email) {
	    
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("email", email);
		
		return memberDAO.findMemberId(map);
		
	}
	
	//비밀번호 찾기
	@Override
	public MemberVO findMemberPassword(String member_id, String name, String email) {
		
		Map<String, String> map = new HashMap<>();
		map.put("member_id", member_id);
		map.put("name", name);
		map.put("email", email);
		
		return memberDAO.findMemberPassword(map);
	}
	
	//구글 간편 회원가입
	@Override
	public int googleLogin(MemberVO member) {
		return memberDAO.googleLogin(member);
	}
	//카카오 간편 회원가입
	@Override
	public int kakaoLogin(MemberVO vo) {
		return memberDAO.kakaoLogin(vo);
	}
	//네이버 간편 회원가입
	@Override
	public int naverLogin(MemberVO member) {
		return memberDAO.naverLogin(member);
	}
		
}
