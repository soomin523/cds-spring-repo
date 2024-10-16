package com.human.cds.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class MypageLikeDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public static final String MAPPER = "com.human.cds.mapper.MypageLikeMapper";
	
}
 