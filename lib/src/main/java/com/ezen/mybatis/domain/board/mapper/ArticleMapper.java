package com.ezen.mybatis.domain.board.mapper;

import java.util.List;

import com.ezen.mybatis.domain.board.dto.ArticleDTO;
import com.ezen.mybatis.domain.common.web.PageParams;

/**
 * article 테이블 관련 명세
 */
public interface ArticleMapper {
	/** 신규 게시글 등록 */
	public void create(ArticleDTO articleDTO);
	
	/** 전체 게시판 목록 반환 */
	//public List<ArticleDTO> findAll();
	
	// 페이징 계산에 필요한 게시글 전체 갯수 반환
	//public int getCountAll();
	
	// 페이징 계산(검색값 포함)에 필요한 게시글 전체 갯수 반환
	public int getCountAll(PageParams pageParams);
	
	// 요청 페이지, 페이지당 보여지는 목록 갯수에 따른 목록 반환
	public List<ArticleDTO> findByAll(PageParams pageParams);
	
	// 댓글, 대댓글 쓰기, 게시글 상세보기, 게시글 수정, 게시글 삭제 등 
	
	//게시글 찾기
	public int findArticle(int groupNum);
	// 댓글 등록
	public void createReply(ArticleDTO articleDTO);
	
	//기능 추가...

}
