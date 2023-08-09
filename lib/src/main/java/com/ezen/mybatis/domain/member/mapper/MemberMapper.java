package com.ezen.mybatis.domain.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.mybatis.domain.member.dto.Member;

/**
 * 데이터 베이스 회원 관리 명세
 *  @author 박상훈
 *
 */
public interface MemberMapper {
	
	/** 회원 생성 */
	public boolean create(Member member);
	/** 회원 검색 */
	public Member findByUser(@Param("id") String id,@Param("passwd") String passwd);
	/** 전체 회원 출력 */
	public List<Member> findByAll();
	/** 아이디로 회원 검색 */
	public Member findById(String id);
	
	
}
