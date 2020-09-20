package com.study.test.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.study.test.member.dto.MemberDTO;

@Repository
public interface MemberDAO {
	
	//회원가입
	
	public int insertMember(MemberDTO memberDTO);
	public MemberDTO userInfo(String memberId);
	public int pinNumInsert (Map<String, Object> param);
	public int idChk(String memberId);

}


