package com.human.cds.repository;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.cds.vo.MemberVO;

@Repository
public class MemberDAO {

	// MemberMapper.xml파일을 구분하기 위한 네임스페이스
	private static final String MAPPER = "com.human.cds.mapper.MemberMapper";
	private int number = 1;

	// MyBatis를 이용해서 DB작업을 하는데 핵심적인 역할을 하는 객체: SqlSession
	private SqlSession sqlSession;

	@Autowired
	public MemberDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 회원 가입
	public boolean insertMember(MemberVO member) {
		System.out.println(member.getMember_id());
		
		member.setEmail(member.getEmail_prefix()+member.getEmail_domain());

		try {
			if (sqlSession.insert(MAPPER + ".insertMember", member) == 1)
				return true;
		} catch (Exception e) {
			System.out.println("회원가입 중 예외발생");
			e.printStackTrace();
		}

		return false;
	}

	// 아이디 중복검사: ajax통신
	@ResponseBody
	public boolean checkId(String member_id) {
		int result = 0;
		result = (Integer)sqlSession.selectOne(MAPPER + ".checkId", member_id);
		try {
			if (result == 1)
				return true;
		} catch (Exception e) {
			System.out.println("아이디 중복검사 중 예외 발생");
		}

		return false;
	}

	public MemberVO login(Map<String, String> map) {
		MemberVO vo = null;
		try {
			vo = sqlSession.selectOne(MAPPER+".login", map);
		} catch (Exception e) {
			System.out.println("로그인 중 예외 발생");
			e.printStackTrace();
		}

		return vo;
	}

	public MemberVO findMemberId(Map<String, String> map) {
		MemberVO vo = null;
		try {
			vo = sqlSession.selectOne(MAPPER+".idFind", map);
		} catch (Exception e) {
			System.out.println("아이디 찾기 중 예외 발생");
			e.printStackTrace();
		}

		return vo;
	}

	public MemberVO findMemberPassword(Map<String, String> map) {
		MemberVO vo = null;
		try {
			vo = sqlSession.selectOne(MAPPER+".passwordFind", map);
		} catch (Exception e) {
			System.out.println("비밀번호 찾기 중 예외 발생");
			e.printStackTrace();
		}

		return vo;
	}

	public int googleLogin(MemberVO member) {
		
		member.setMember_id("google"+number);
		number++;
		
		return sqlSession.insert(MAPPER+".googleLogin", member);
	}

	public int kakaoLogin(MemberVO vo) {
		vo.setMember_id("kakao"+number);
		number++;
		
		return sqlSession.insert(MAPPER+".kakaoLogin", vo);
	}
}
/*
 * @Repository public class MemberVO implements MemberDAO {
 * 
 * @Autowired private EntityManager entityManager; // JPA를 사용하기 위한 EntityManager
 * 
 * @Override public MemberVO getEmail(String email) { // JPQL 또는 Native Query를
 * 사용하여 이메일로 회원 조회 try { return
 * entityManager.createQuery("SELECT m FROM MemberVO m WHERE m.email = :email",
 * MemberVO.class) .setParameter("email", email) .getSingleResult(); } catch
 * (NoResultException e) { return null; // 결과가 없을 경우 null 반환 } }
 * 
 * }
 * 
 * }
 */




	/*
	 * // 회원 정보 조회 public MemberVO getMemberById(int id) { String sql =
	 * "SELECT * FROM members WHERE id = ?"; try (Connection conn =
	 * dataSource.getConnection(); PreparedStatement pstmt =
	 * conn.prepareStatement(sql)) {
	 * 
	 * pstmt.setInt(1, id); try (ResultSet rs = pstmt.executeQuery()) { if
	 * (rs.next()) { MemberVO member = new MemberVO();
	 * member.setId(rs.getInt("id")); member.setMember_id(rs.getString("memberId"));
	 * member.setPassword(rs.getString("password"));
	 * member.setBirthDate(rs.getDate("birthDate"));
	 * member.setPhone(rs.getString("phone")); member.setName(rs.getString("name"));
	 * member.setEmail(rs.getString("email"));
	 * member.setGender(rs.getString("gender"));
	 * member.setMarketingConsent(rs.getBoolean("marketingConsent"));
	 * member.setProfileImage(rs.getString("profileImage"));
	 * member.setWithdrawalRequest(rs.getBoolean("withdrawalRequest"));
	 * member.setMembershipLevel(rs.getString("membershipLevel"));
	 * member.setCreatedAt(rs.getDate("createdAt")); return member; } } } catch
	 * (SQLException e) { e.printStackTrace(); } return null; }
	 * 
	 * // 회원 정보 수정 public boolean updateMember(MemberVO member) { String sql =
	 * "UPDATE members SET password = ?, phone = ?, email = ?, membershipLevel = ? WHERE id = ?"
	 * ;
	 * 
	 * try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt =
	 * conn.prepareStatement(sql)) {
	 * 
	 * pstmt.setString(1, member.getPassword()); pstmt.setString(2,
	 * member.getPhone()); pstmt.setString(3, member.getEmail()); pstmt.setString(4,
	 * member.getMembershipLevel()); pstmt.setInt(5, member.getId());
	 * 
	 * int result = pstmt.executeUpdate(); return result > 0;
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); return false; } }
	 * 
	 * // 회원 삭제 (탈퇴 요청) public boolean deleteMember(int id) { String sql =
	 * "DELETE FROM members WHERE id = ?";
	 * 
	 * try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt =
	 * conn.prepareStatement(sql)) {
	 * 
	 * pstmt.setInt(1, id); int result = pstmt.executeUpdate(); return result > 0;
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); return false; } }
	 * 
	 * public boolean isEmailAvailable(String email) { // TODO Auto-generated method
	 * stub return false; }
	 * 
	 * public MemberVO selectMemberByEmail(String email) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * public boolean selectExsitsId(String memberId) {
	 * 
	 * 
	 * String sql = "SELECT 'Y' FROM members WHERE member_id = ?";
	 * 
	 * try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt =
	 * conn.prepareStatement(sql)) {
	 * 
	 * pstmt.setString(1, memberId);
	 * 
	 * try (ResultSet rs = pstmt.executeQuery()) { if (rs.next()) {
	 * 
	 * return StringUtils.isEmpty(rs.getString("1")) ? false : true; } } catch
	 * (Exception e) { return false; } } catch (Exception e) { e.printStackTrace();
	 * return false; }
	 * 
	 * return false; }
	 */

