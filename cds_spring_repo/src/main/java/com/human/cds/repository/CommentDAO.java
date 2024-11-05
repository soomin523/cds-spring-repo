package com.human.cds.repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.cds.vo.CommentLikeVO;
import com.human.cds.vo.CommentVO;

@Repository
public class CommentDAO {

    @Autowired
    private SqlSession sqlSession;
    private static final String MAPPER = "com.human.cds.mapper.CourseInfoMapper";

    // 댓글 삽입
    public int insertComment(CommentVO commentVO) {
        return sqlSession.insert(MAPPER + ".insertComment", commentVO);
    }

    public List<CommentVO> getCommentsByContentId(String contentId, int offset, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("contentId", contentId);
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        List<CommentVO> commentVOList = sqlSession.selectList(MAPPER+".getCommentsByContentId", params);
        for(CommentVO vo : commentVOList) {
        	correctMySQLDate(vo);//시간 조정
        }
        return commentVOList;
    }
    
    
    

    // 좋아요/싫어요 상태 확인
    public CommentLikeVO getLikeStatus(int cIdx, String name) {
        return sqlSession.selectOne(MAPPER + ".getLikeStatus", Map.of("c_idx", cIdx, "name", name));
    }

    // 좋아요/싫어요 추가
    public void addLike(int cIdx, String name, String actionType) {
        sqlSession.insert(MAPPER + ".addLike", Map.of("c_idx", cIdx, "name", name, "action_type", actionType));
    }

    // 좋아요 수 증가
    public void incrementLikeCount(int cIdx) {
        sqlSession.update(MAPPER + ".incrementLikeCount", cIdx);
    }

    // 싫어요 수 증가
    public void incrementDislikeCount(int cIdx) {
        sqlSession.update(MAPPER + ".incrementDislikeCount", cIdx);
    }

    // 좋아요 수 감소
    public void decrementLikeCount(int cIdx) {
        sqlSession.update(MAPPER + ".decrementLikeCount", cIdx);
    }

    // 싫어요 수 감소
    public void decrementDislikeCount(int cIdx) {
        sqlSession.update(MAPPER + ".decrementDislikeCount", cIdx);
    }

    // 좋아요/싫어요 업데이트
    public void updateLike(int cIdx, String name, String actionType) {
        Map<String, Object> params = new HashMap<>();
        params.put("c_idx", cIdx);
        params.put("name", name);
        params.put("action_type", actionType); // 취소 시 'none'으로 업데이트

        sqlSession.update(MAPPER + ".updateLike", params);
    }


    public void removeLike(int cIdx, String name) {
        sqlSession.delete(MAPPER + ".removeLike", Map.of("c_idx", cIdx, "name", name));
    }

	public int deleteComment(int c_idx) {
		// TODO Auto-generated method stub
		return sqlSession.delete(MAPPER + ".deleteComment", c_idx);
	}
	
	
	//MySQL에 입력된 시간을 Java에서 가져다 사용할 때 시간을 조정해주는 메소드
	public void correctMySQLDate(CommentVO vo) { //매개변수는 시간 필드를 가지는 VO객체
		
		//Date객체를 LocalDateTime객체로 변경하기
		
		//1. 등록일: reg_date
		//System.out.println("등록일: "+vo.getReg_date());
		LocalDateTime localDateTime = vo.getCreatedAt().toInstant() // Date -> Instant
				.atZone(ZoneId.systemDefault()) // Instant -> ZonedDateTime 
				.toLocalDateTime(); // ZonedDateTime -> LocalDateTime
		
		//MySQL에 저장된 날짜 그대로 사용하기 위해서 가져온 날짜값에서 9시간 빼기
		LocalDateTime updatedLocalDateTime = localDateTime.minusHours(9);
		
		//변경된 LocalDateTime객체를 다시 Date객체로 변경해서 reg_date값을 다시 세팅해줌
		Instant instant = updatedLocalDateTime.atZone(ZoneId.systemDefault()).toInstant();
		Timestamp correctReg_date = Timestamp.from(instant);
		//Date correctReg_date = Date.from(instant); //java.util.Date클래스
		
		vo.setCreatedAt(correctReg_date);
		//System.out.println("정정된 등록일: "+vo.getReg_date());
		
		//2. 수정일: update_date
		localDateTime = vo.getUpdatedAt().toInstant() // Date -> Instant
				.atZone(ZoneId.systemDefault()) // Instant -> ZonedDateTime 
				.toLocalDateTime(); // ZonedDateTime -> LocalDateTime
		
		//MySQL에 저장된 날짜 그대로 사용하기 위해서 가져온 날짜값에서 9시간 빼기
		updatedLocalDateTime = localDateTime.minusHours(9);
		
		//변경된 LocalDateTime객체를 다시 Date객체로 변경해서 reg_date값을 다시 세팅해줌
		instant = updatedLocalDateTime.atZone(ZoneId.systemDefault()).toInstant();
		Timestamp correctUpdate_date = Timestamp.from(instant);
		//Date correctUpdate_date = Date.from(instant); //java.util.Date클래스
		
		vo.setUpdatedAt(correctUpdate_date);
	}
	
}
