package com.study.test.board.web;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.study.test.board.customInfo.CustomInfo;
import com.study.test.board.dao.BoardDAO;
import com.study.test.board.dto.BoardDTO;
import com.study.test.board.pageindex.PageIndex;
import com.study.test.member.dao.MemberDAO;
import com.study.test.member.dto.MemberDTO;;

@Controller
public class BoardController {
	
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	BoardDAO boardDAO;
	@Autowired
	PageIndex pageIndex;
	
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public String view(Model model, HttpServletRequest request, MemberDTO memberDTO ) {
		
		
		return "view";
	}
	//회원가입
	@RequestMapping(value ="bbs/join", method= { RequestMethod.GET, RequestMethod.POST})
	public String join(Model model, HttpServletRequest request, MemberDTO memberDTO) {
		
		System.out.println("왔니?");
		
		
		return "bbs/join";
	}
	//회원가입 중
	@RequestMapping(value ="bbs/join_ok", method= { RequestMethod.GET, RequestMethod.POST})
	public String join_ok(Model model, HttpServletRequest request, MemberDTO memberDTO) {
		
		System.out.println("????");
		memberDAO.insertMember(memberDTO);
		System.out.println("!!!");
		
		return "redirect:/bbs/list";
	}
	
	//메인?
	@RequestMapping(value ="bbs/list", method= { RequestMethod.GET, RequestMethod.POST})
	public String list(Model model, HttpServletResponse response , HttpServletRequest request, BoardDTO boardDTO, HttpSession session) {
		
		String cp = request.getContextPath();
		//현재페이지 받기
		String pageNum = request.getParameter("pageNum");
		String insert = "insert";
		String loginUrl = cp + "/bbs/login";
		String createdUrl= cp + "/bbs/created";
		//로그인처리
		session = request.getSession();
		CustomInfo info = (CustomInfo) session.getAttribute("CustomInfo");
		
		int currentPage = 1;

		if (pageNum != null)
			currentPage = Integer.parseInt(pageNum);

		// 전체 데이터 갯수
		int dataCount = boardDAO.getDataCount();
		System.out.println(dataCount);

		// 전체 페이지수
		int numPerPage = 5;
		int totalPage = pageIndex.getPageCount(numPerPage, dataCount);

		// 삭제로인해서 페이수변화
		if (currentPage > totalPage)
			currentPage = totalPage;

		int start = (currentPage - 1) * numPerPage + 1;
		int end = currentPage * numPerPage;
		
		String listUrl = cp + "/bbs/list";
		String pageIndexList = pageIndex.pageIndexList(currentPage, totalPage, listUrl);
		
		System.out.println(start);
		System.out.println(end);
		
		//리스트 가져오기
		List<BoardDTO> lists = boardDAO.boardList();
		System.out.println(start);
		System.out.println(end);
		System.out.println(dataCount);
		
	/*	HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);*/
		
		System.out.println("<<<<<<<<<<");
		
		int listNum, n = 0;
		ListIterator<BoardDTO> it = lists.listIterator();
		while(it.hasNext()) {
			System.out.println("AAAAAA");
			BoardDTO data = (BoardDTO) it.next();
			System.out.println("BBBBBBBB");
			listNum = dataCount - (start + n -1);
			data.setListNum(listNum);
			
			/*for(int i=0; i<lists.size(); i++) {
				System.out.println(lists.get(i));
				System.out.println(data.getBoardNo());
				System.out.println(data.getBoardDate());
				System.out.println(data.getBoardSubject());
				System.out.println(data.getBoardCount());
				System.out.println(data.getListNum());
				}*/
			
			n++;
					
		}
				
		String articleUrl = cp + "/bbs/article";
		
		request.setAttribute("lists", lists);
		request.setAttribute("mode",insert);
		request.setAttribute("loginUrl",loginUrl);
		request.setAttribute("createdUrl", createdUrl);
		request.setAttribute("dataCount", dataCount);
		request.setAttribute("pageIndexList", pageIndexList);
		request.setAttribute("articleUrl", articleUrl);

		return "bbs/list";
	}
	
	//로그인
	@RequestMapping(value ="bbs/login", method= { RequestMethod.GET, RequestMethod.POST})
	public String login(Model model, HttpServletRequest request, MemberDTO memberDTO) {
		
		String cp = request.getContextPath();		
		
		return "bbs/login";
	}
	
	//게시판 글쓰기
	
	@RequestMapping(value ="bbs/created", method= { RequestMethod.GET, RequestMethod.POST})
	public String created(Model model, HttpServletRequest request, BoardDTO boardDTO) {
		
		String cp = request.getContextPath();
		request.setAttribute("mode", "insert");
		
		return "bbs/created";
	}
	
	//게시판 글쓰기 중
	
	@RequestMapping(value ="bbs/created_ok", method= { RequestMethod.GET, RequestMethod.POST})
	public String created_ok(Model model, HttpServletRequest request, BoardDTO boardDTO) {
				
		boardDAO.insertBoard(boardDTO);
		
		return "redirect:/bbs/list";
	}
	
	//게시글
	
	@RequestMapping(value ="bbs/article", method= { RequestMethod.GET, RequestMethod.POST})
	public String article(Model model, HttpServletRequest request, BoardDTO boardDTO) {
		
		String cp = request.getContextPath();
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		System.out.println(boardNo);
		
		boardDTO= boardDAO.article(boardNo);
		System.out.println(boardNo);
		System.out.println(boardDTO.getBoardNo());
		System.out.println(boardDTO.getBoardSubject());
		System.out.println(boardDTO.getBoardDate());
		System.out.println(boardDTO.getBoardContent());
		
		request.setAttribute("boardDTO", boardDTO);
		System.err.println("게시글");
		return "/bbs/article";
	}
	
	//수정
	@RequestMapping(value ="bbs/update", method= { RequestMethod.GET, RequestMethod.POST})
	public String update(Model model, HttpServletRequest request, BoardDTO boardDTO) {
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		boardDTO = boardDAO.article(boardNo);
		
		request.setAttribute("boardDTO", boardDTO);
			
		return "/bbs/update";
	}
	
	//수정중
	@RequestMapping(value ="bbs/update_ok", method= { RequestMethod.GET, RequestMethod.POST})
	public String update_ok(Model model, HttpServletRequest request, BoardDTO boardDTO) {
				
		boardDAO.updateBoard(boardDTO);
		System.out.println("업데이트");
		return "redirect:/bbs/list";
	}
	
	//삭제
	@RequestMapping(value ="bbs/delete_ok", method= { RequestMethod.GET, RequestMethod.POST})
	public String delete_ok(Model model, HttpServletRequest request, BoardDTO boardDTO) {
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		System.out.println("삭제삭제"+ boardNo);
		
		boardDAO.deleteBoard(boardNo);
		
		System.out.println("삭제");
		
		return "redirect:/bbs/list";
	}
	
	

}
