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
 * 답글 DTO
 * @author 박상훈
 *
 */
public class ArticleDTO {
	private int articleId;
	private int boardId;
	private String writer;
	private String subject;
	private String content;
	private String regdate;
	private int hitcount;
	private String passwd;
	private int groupNum;
	private int levelNum;
	private int orderNum;
}
