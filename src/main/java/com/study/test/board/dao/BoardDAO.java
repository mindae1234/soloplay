package com.study.test.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.study.test.board.dto.BoardDTO;
import com.study.test.board.dto.CommentDTO;

@Repository
public interface BoardDAO {
	
	

	public int insertBoard(BoardDTO boardDTO);
	
	public int getDataCount();
	
	public List<BoardDTO> boardList(Map<String, Object> param);
	
	public BoardDTO article(int boardNo);
	
	public int updateBoard(BoardDTO boardDTO);
	
	public int deleteBoard(int boardNo);
	
	public int insertComment(CommentDTO commentDTO);
	
	public int reReply(int commNum);
	
	public List<CommentDTO> selectComment(int boardNo);
	
	public int commentDelete(Map<String, Object> param);
}
