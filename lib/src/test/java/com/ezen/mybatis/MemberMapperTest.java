package com.ezen.mybatis;


import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ezen.mybatis.domain.employee.dto.Employee;
import com.ezen.mybatis.domain.employee.mapper.EmployeeMapper;
import com.ezen.mybatis.domain.member.dto.Member;
import com.ezen.mybatis.domain.member.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberMapperTest {
	SqlSession sqlSession;	
	
	@BeforeEach
	public void setUp() {
		String resource = "mybatis-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		sqlSession = sqlSessionFactory.openSession();
	}
	
	@Test
	@Disabled
	public void findByAllTest(){
		System.out.println("==================== 전체회원 조회 ========================");
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		log.debug("{}",mapper);
		List<Member> list = mapper.findByAll();
		for (Member member : list) {
			System.out.println(member);
		}
	}
	
	@Test
	@Disabled
	public void findByIdTest(){
		System.out.println("==================== 아이디로 회원조회 ========================");
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		Member member = mapper.findById("sjm601");
		System.out.println(member);
	}
	
	@Test
	@Disabled
	public void findByUserTest(){
		log.debug("==================== 아이디 비번으로 회원조회 ========================");
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		Member member = mapper.findByUser("hong4829", "won4829");
		log.debug("해당하는 회원: {}", member);
	}
	
	@Test
	@DisplayName("사원 등록")
	public void CreateTest(){
		Member member = new Member();
		member.setId("aaa1234");
		member.setPasswd("1234");
		member.setEmail("sjm12345@naver.com");
		member.setName("이용용");
		member.setRegdate("2023-08-08");
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		mapper.create(member);
		sqlSession.commit();
		log.debug("회원등록 완료 : {}",member);
	
	}
	
	@AfterEach
	public void destory() {
		sqlSession.close();
	}
}







