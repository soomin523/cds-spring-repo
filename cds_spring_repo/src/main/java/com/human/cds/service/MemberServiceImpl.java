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

	@Autowired
    private MemberDAO memberDAO;

    // 회원가입 처리
    @Override
    public boolean registerMember(MemberVO member) {
        return memberDAO.insertMember(member);
    }

	@Override
	public boolean isEmailAvailable(String email) {
		return false;
	}
	
	
	//아이디 중복
	@Override
	public boolean checkId(String member_id) {
        return memberDAO.checkId(member_id);
	}



	
	//메일인증 관련 메일전송 객체 의존자동주입 받기
		@Autowired
		private JavaMailSenderImpl mailSender;

			//메일인증
		@Override
		public String authEmail(String email) {
			//메일인증 코드 6자리 생성하기: Math.random() (111111 <= r < 1000000)
			int authNumber = (int)(Math.random()*888889)+111111;
			
			String setFrom = "humandev007@gmail.com";//송신자의 메일주소
			String toMail = email;//수신자의 메일주소
			String title = "회원가입 인증 이메일입니다";//제목
			String content = "홈페이지를 방문해주셔서 감사합니다. <br><br> "
					+ "인증번호: "+authNumber+"<br>"
					+ "해당 인증번호를 인증번호 확인란에 입력해 주세요";
			mailSend(setFrom, toMail, title, content);
			
			return Integer.toString(authNumber);
		}

		//이메일 전송 메소드
		private void mailSend(String setFrom, String toMail, String title, String content) {
			MimeMessage message = mailSender.createMimeMessage();
			
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
				//true: multipart 형식의 메시지 전달
				helper.setFrom(setFrom);
				helper.setTo(toMail);
				helper.setSubject(title);
				helper.setText(content, true);//true: html형식으로 전송, true를 입력하지 않으면 text로 전송
				mailSender.send(message);
				
			} catch (Exception e) {
				System.out.println("메일전송 중 예외발생");
			}
		}



		//로그인
		/*@Override
		 * public MemberVO login(String memberId, String password) { try (Connection
		 * conn = ConnectionProvider.getConnection()) { MemberVO member =
		 * memberDAO.selectById(memberId);
		 * 
		 * // 회원 정보가 없는 경우 예외 처리 if (member == null) { throw new
		 * LoginFailException("Member not found"); }
		 * 
		 * // 비밀번호가 일치하지 않는 경우 예외 처리 if (!member.getPassword().equals(password)) { throw
		 * new LoginFailException("Invalid password"); }
		 * 
		 * // 로그인 성공 시, 회원 객체 반환 return member; } catch (SQLException e) { throw new
		 * RuntimeException(e); } }
		 */
		
		
		/* public MemberVO login(String memberId, String password) {
		    try {
		    	MemberVO member = new MemberVO();

		        // 회원 정보가 없는 경우 예외 처리
		        if (member == null) {
		            System.out.println("Member not found");
		            
		        }

		        // 비밀번호가 일치하지 않는 경우 예외 처리
		        if (!member.getPassword().equals(password)) {
		            System.out.println("Member not found");
		        }

		        // 로그인 성공 시, 회원 객체 반환
				return member;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return null;
		}
		
	*/
		
		// 로그인 처리
		public MemberVO login(String memberId, String password) {
			
			Map<String, String> map = new HashMap<>();
			map.put("memberId", memberId);
			map.put("password", password);
			
			return memberDAO.login(map);
		}




		@Override
		public boolean isPhoneDuplicate(String phone) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isEmailDuplicate(String email) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean getMemberById(String memberId) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String sendVerificationCode(String email) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean verifyCode(String email, String code) {
			// TODO Auto-generated method stub
			return false;
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
