package com.study.test.board.dto;

import java.util.Date;


public class BoardDTO {
	
	int boardNo;
	String boardSubject;
	String boardContent;
	String memberId;
	String boardDate;
	String boardCount;
	int listNum;
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardSubject() {
		return boardSubject;
	}
	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public String getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(String boardCount) {
		this.boardCount = boardCount;
	}
	public int getListNum() {
		return listNum;
	}
	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	@Override
	public String toString() {
		return "BoardDTO [boardNo=" + boardNo + ", boardSubject=" + boardSubject + ", boardContent=" + boardContent
				+ ", memberId=" + memberId + ", boardDate=" + boardDate + ", boardCount=" + boardCount + ", listNum="
				+ listNum + "]";
	}
	
	
	
	
	
	

}
