package com.study.test.member.dto;

import java.util.Date;

public class MemberDTO {

	int memberNo;
	String memberId;
	String memberName;
	String memberPw;
	String memberPost;
	String memberAddr;
	String memberTel;
	String memberBirth;
	String memberSex;
	int memberAdminNo;
	String memberEmail;
	Date memberDate;
	String pinNum;
	String publicKey;
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberPost() {
		return memberPost;
	}
	public void setMemberPost(String memberPost) {
		this.memberPost = memberPost;
	}
	public String getMemberAddr() {
		return memberAddr;
	}
	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}
	public String getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}
	public String getMemberSex() {
		return memberSex;
	}
	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}
	public int getMemberAdminNo() {
		return memberAdminNo;
	}
	public void setMemberAdminNo(int memberAdminNo) {
		this.memberAdminNo = memberAdminNo;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public Date getMemberDate() {
		return memberDate;
	}
	public void setMemberDate(Date memberDate) {
		this.memberDate = memberDate;
	}	
	public String getPinNum() {
		return pinNum;
	}
	public void setPinNum(String pinNum) {
		this.pinNum = pinNum;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	@Override
	public String toString() {
		return "MemberDTO [memberNo=" + memberNo + ", memberId=" + memberId + ", memberName=" + memberName
				+ ", memberPw=" + memberPw + ", memberPost=" + memberPost + ", memberAddr=" + memberAddr
				+ ", memberTel=" + memberTel + ", memberBirth=" + memberBirth + ", memberSex=" + memberSex
				+ ", memberAdminNo=" + memberAdminNo + ", memberEmail=" + memberEmail + ", memberDate=" + memberDate
				+ "]";
	}
	
	
}
