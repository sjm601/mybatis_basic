package com.ezen.mybatis.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

/**
 * 게시판 DTO
 * @author 박상훈
 *
 */
public class BoardDTO {
	private int boardId;
	private int category;
	private String title;
	private String description;
}
