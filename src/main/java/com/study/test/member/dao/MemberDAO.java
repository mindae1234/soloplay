package com.study.test.member.dao;

import org.springframework.stereotype.Repository;

import com.study.test.member.dto.MemberDTO;

@Repository
public interface MemberDAO {
	
	//ȸ������
	
	public int insertMember(MemberDTO memberDTO);

}


