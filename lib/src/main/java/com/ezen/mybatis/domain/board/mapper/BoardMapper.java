package com.ezen.mybatis.domain.board.mapper;

import java.util.List;

import com.ezen.mybatis.domain.board.dto.BoardDTO;

/**
 * board 테이블 관련 명세
 */
public interface BoardMapper {
	/** 신규 게시판 생성 */
	public void create(BoardDTO boardDTO);
	/** 전체 게시판 목록 반환 */
	public List<BoardDTO> findAll();

}
