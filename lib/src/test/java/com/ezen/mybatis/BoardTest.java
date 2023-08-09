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

import com.ezen.mybatis.domain.board.dto.BoardDTO;
import com.ezen.mybatis.domain.board.mapper.BoardMapper;
import com.ezen.mybatis.domain.member.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardTest {

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
	@DisplayName("전체 게시판 조회")
	@Disabled
	public void findAllTest(){
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		log.debug("{}",mapper);
		List<BoardDTO> list = mapper.findAll();
		list.forEach(board->{
			log.debug("검색된 전체 게시판 : {}",board);
		});
	}

	

	@Test
	@DisplayName("게시판 등록")
	@Disabled
	public void CreateTest(){
		BoardDTO boardDTO = BoardDTO.builder()
											.boardId(0)
											.category(1)
											.title("아스날 우승 기원 게시판")
											.description("아스날의 23-24 시즌 우승을 바라는 게시판입니다..")
											.build();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		mapper.create(boardDTO);
		sqlSession.commit();
		log.debug("게시판 등록 완료 : {}",boardDTO);
	
	}
	
	@AfterEach
	public void destory() {
		sqlSession.close();
	}
}







