package com.study.test.member.dao;

import org.springframework.stereotype.Repository;

import com.study.test.member.dto.MemberDTO;

@Repository
public interface MemberDAO {
	
	//회원가입
	
	public int insertMember(MemberDTO memberDTO);

}


