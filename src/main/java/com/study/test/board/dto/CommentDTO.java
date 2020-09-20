package com.study.test.board.dto;

public class CommentDTO {
	
	int boardNo;
	int commNum;
	int reCommNum;
	String comment;
	String memberId;
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getCommNum() {
		return commNum;
	}
	public void setCommNum(int commNum) {
		this.commNum = commNum;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getReCommNum() {
		return reCommNum;
	}
	public void setReCommNum(int reCommNum) {
		this.reCommNum = reCommNum;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	
	
	

}
