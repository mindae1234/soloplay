package com.study.test.board.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.study.test.board.dto.BoardDTO;

@Repository
public interface BoardDAO {
	
	

	public int insertBoard(BoardDTO boardDTO);
	
	public int getDataCount();
	
	public List<BoardDTO> boardList();
	
	public BoardDTO article(int boardNo);
	
	public int updateBoard(BoardDTO boardDTO);
	
	
}
