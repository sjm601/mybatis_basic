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

import com.ezen.mybatis.domain.board.dto.ArticleDTO;
import com.ezen.mybatis.domain.board.dto.BoardDTO;
import com.ezen.mybatis.domain.board.mapper.ArticleMapper;
import com.ezen.mybatis.domain.board.mapper.BoardMapper;
import com.ezen.mybatis.domain.common.web.PageParams;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArticleTest {

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
	@DisplayName("게시글 전체 개수 반환")
	@Disabled
	public void getCountAllTest(){
		PageParams pageParams = PageParams.builder()
									.elementSize(10)
									.pageSize(10)
									.requestPage(1)
									.rowCount(0)
									.boardId(10)
									.keyword("제목")
									.build();				
	    ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
	    int count = mapper.getCountAll(pageParams);
	    log.debug("전체 게시글 개수 : {}", count);
	}

	

	@Test
	@DisplayName("신규 게시글 등록")
	@Disabled
	public void CreateTest(){
		ArticleDTO articleDTO = ArticleDTO.builder()
											.boardId(50)
											.writer("monday")
											.subject("아스날 톱 좀 사라")
											.content("제주스 하나로는 믿음이 안가는데 톱좀 사주세요 제발")
											.passwd("1234")
											.levelNum(0)
											.orderNum(0)
											.build();
		ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
		mapper.create(articleDTO);
		sqlSession.commit();
		log.debug("게시판 등록 완료 : {}",articleDTO);
	
	}
	
	@Test
	@DisplayName("댓글 등록")
	public void CreateReplyTest(){
		ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
		ArticleDTO mainArticle = mapper.findArticle(groupNum);
		//댓글 생성
		ArticleDTO articleDTO = ArticleDTO.builder()
											.boardId(50)
											.writer("sjm601")
											.subject("오시멘 사주세요")
											.content("오시멘 오면 우승 진짜 가능합니다 돈좀 씁시다 우리")
											.passwd("1234")
											.groupNum(mainArticle.getGroupNum())	
											.levelNum(mainArticle.getLevelNum())
											.build();
		mapper.create(articleDTO);
		sqlSession.commit();
		log.debug("답글 등록 완료 : {}",articleDTO);
	
	}
	
	@Test
	@DisplayName("페이지당 보여지는 목록 반환")
	@Disabled
	public void findByAllTest() {
		PageParams pageParams = PageParams.builder()
				.elementSize(10)
				.pageSize(10)
				.requestPage(4)
				.rowCount(0)
				.boardId(10)
				.keyword("thursday")
				.build();				
		ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
		List<ArticleDTO> list = mapper.findByAll(pageParams);
		list.forEach(article->{
			log.debug("받은 목록 : {}",list);
		});	
	}
	
	
	
	@AfterEach
	public void destory() {
		sqlSession.close();
	}
}







