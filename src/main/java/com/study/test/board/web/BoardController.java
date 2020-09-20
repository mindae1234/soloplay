package com.study.test.board.web;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.MidiDevice.Info;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.test.board.customInfo.CustomInfo;
import com.study.test.board.dao.BoardDAO;
import com.study.test.board.dto.BoardDTO;
import com.study.test.board.dto.CommentDTO;
import com.study.test.board.pageindex.PageIndex;
import com.study.test.board.utill.SecurityUtil;
import com.study.test.member.dao.MemberDAO;
import com.study.test.member.dto.MemberDTO;
import com.study.test.util.CommonUtil;
import com.study.test.util.ECDSUtil;;

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
		System.out.println("join.");
		String pageNum =  request.getParameter("pageNum");
		if(pageNum ==null) {
			pageNum="1";
		}

		System.out.println("왔니?");

		request.setAttribute("pageNum", pageNum);
		System.out.println(pageNum+"create");
		return "bbs/join";
	}
	//회원가입 중
	@RequestMapping(value ="bbs/join_ok", method= { RequestMethod.GET, RequestMethod.POST})
	public String join_ok(Model model, HttpServletRequest request, MemberDTO memberDTO) {
		System.out.println("joinok.");
		String pageNum =  request.getParameter("pageNum");
		if(pageNum ==null) {
			pageNum="1";
		}

		SecurityUtil securityUtill = new SecurityUtil();
		String pw = securityUtill.encryptSHA256(request.getParameter("memberPw"));
		memberDTO.setMemberPw(pw);
		int count=memberDAO.insertMember(memberDTO);
		if(count != 0) {
			request.setAttribute("msg","성공" );
			
		}else {
			request.setAttribute("msg","오류 다시작성해주세요" );
			
			return "redirect:/bbs/join";
		}

		request.setAttribute("pageNum", pageNum);
		System.out.println(pageNum+"created_ok");

		return "redirect:/bbs/list?pageNum="+pageNum;
	}
	
	@ResponseBody
	@RequestMapping(value ="bbs/idChk", method= { RequestMethod.GET, RequestMethod.POST})
	public int idchk(Model model, HttpServletRequest request, MemberDTO memberDTO) {
		
		String memberId =  request.getParameter("memberId");
		System.out.println(memberId);
		int result = memberDAO.idChk(memberId);
		System.out.println(result);
		request.setAttribute("result", result);
		return result;
		
	}

	//게시판 글쓰기

	@RequestMapping(value ="bbs/created", method= { RequestMethod.GET, RequestMethod.POST})
	public String created(Model model, HttpServletRequest request, BoardDTO boardDTO) {
		System.out.println("created.");
		String cp = request.getContextPath();
		String pageNum =  request.getParameter("pageNum");
		HttpSession session = request.getSession();
		CustomInfo info = (CustomInfo) session.getAttribute("CustomInfo");


		request.setAttribute("mode", "insert");
		request.setAttribute("pageNum", pageNum);
		session.setAttribute("CustomInfo", info);

		return "bbs/created";
	}

	//게시판 글쓰기 중

	@RequestMapping(value ="bbs/created_ok", method= { RequestMethod.GET, RequestMethod.POST})
	public String created_ok(Model model, HttpServletRequest request, BoardDTO boardDTO) {
		System.out.println("createok.");
		String pageNum =  request.getParameter("pageNum");
		HttpSession session = request.getSession();
		CustomInfo info = (CustomInfo) session.getAttribute("CustomInfo");


		boardDAO.insertBoard(boardDTO);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("CustomInfo", info);

		return "redirect:/bbs/list?pageNum="+pageNum;
	}


	//메인?
	@RequestMapping(value ="bbs/list", method= { RequestMethod.GET, RequestMethod.POST})
	public String list(Model model, HttpServletResponse response , HttpServletRequest request, BoardDTO boardDTO, HttpSession session) {
		System.out.println("list.");
		String cp = request.getContextPath();
		//로그인처리
				session = request.getSession();
				CustomInfo info = (CustomInfo) session.getAttribute("CustomInfo");
				String insert = "insert";
		//현재페이지 받기
		String pageNum = request.getParameter("pageNum");
		
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
		int end = 5;

		String listUrl = cp + "/bbs/list";
		String pageIndexList = pageIndex.pageIndexList(currentPage, totalPage, listUrl);
		System.out.println(start+"얍얍ㅇ뱌야야뱌뱌");
		//리스트 가져오기
		Map<String, Object> param  = new HashMap<String, Object>();
		param.put("start", start-1);
		param.put("end", end);
		System.out.println(param.get("start"));
		System.out.println(param.get("end"));
		List<BoardDTO> lists = boardDAO.boardList(param);

		int listNum, n = 0;
		ListIterator<BoardDTO> it = lists.listIterator();
		while(it.hasNext()) {
			BoardDTO data = (BoardDTO) it.next();
			listNum = dataCount - (start + n -1);
			data.setListNum(listNum);

			n++;					
		}

		String articleUrl = cp + "/bbs/article?pageNum=" + currentPage;
		String loginUrl = cp + "/bbs/login?pageNum="+currentPage;
		String createdUrl= cp + "/bbs/created?pageNum="+currentPage;
		String logoutUrl = cp + "/bbs/logout";

		request.setAttribute("lists", lists);
		request.setAttribute("mode",insert);
		request.setAttribute("loginUrl",loginUrl);
		request.setAttribute("createdUrl", createdUrl);
		request.setAttribute("dataCount", dataCount);
		request.setAttribute("pageIndexList", pageIndexList);
		request.setAttribute("articleUrl", articleUrl);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("logoutUrl", logoutUrl);
		session.setAttribute("CustomInfo", info);
		

		return "bbs/list";
	}

	//로그인
	@RequestMapping(value ="bbs/login", method= { RequestMethod.GET, RequestMethod.POST})
	public String login(Model model, HttpServletRequest request, MemberDTO memberDTO) {
		System.out.println("login.");
		String cp = request.getContextPath();	
		String pageNum =  request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		request.setAttribute("pageNum", pageNum);
System.out.println(pageNum+"login");

		return "bbs/login";
	}
	//로그인처리
	@RequestMapping(value ="bbs/login_ok", method= { RequestMethod.GET, RequestMethod.POST})
	public String login_ok(Model model, HttpServletRequest request, MemberDTO memberDTO ,HttpSession session) {
		System.out.println("loginok.");
		String cp = request.getContextPath();	
		String pageNum =  request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		System.out.println("//넘어오셧나?");
		System.out.println(pageNum);


		SecurityUtil securityUtill = new SecurityUtil();
		String memberId = request.getParameter("memberId");
		String memberPw = securityUtill.encryptSHA256(request.getParameter("memberPw"));
		System.out.println(memberId);
		System.out.println(memberPw);

		memberDTO = memberDAO.userInfo(memberId);

		if (memberDTO == null || (!memberDTO.getMemberPw().equals(memberPw))) {

			session.invalidate();

			request.setAttribute("message", "아이디 또는 패스워드를 정확히 입력하세요!");
			request.setAttribute("message2", "비밀번호 찾기");
			request.setAttribute("pageNum", pageNum);


			return "bbs/login";

		} else if ((memberDTO.getMemberId().equals(memberId)) && (memberDTO.getMemberPw().equals(memberPw))) {

			// HttpSession session = req.getSession();//기본세션영역호출

			// 임의의세션을 여러개 만드는법
			/*
			 * HttpSession session2 = req.getSession(true); HttpSession session3 =
			 * req.getSession(true);
			 */

			// 세션에 올리기
			CustomInfo info = new CustomInfo();

			info.setMemberId(memberDTO.getMemberId());
			info.setMemberPw(memberDTO.getMemberPw());			
			System.out.println("3"+info.toString());

			session.setAttribute("CustomInfo", info);
			request.setAttribute("pageNum", pageNum);
			System.out.println("4444"+info.toString());


			return "redirect:/bbs/list?pageNum="+pageNum;

		}
		request.setAttribute("pageNum", pageNum);
		System.out.println(pageNum+"login_ok");

		return "redirect:/bbs/list?pageNum="+pageNum;
	}
	
	//로그아웃
	@RequestMapping(value ="bbs/logout", method= { RequestMethod.GET, RequestMethod.POST})
	public String logout(Model model, HttpServletRequest request, MemberDTO memberDTO) {

		System.out.println("로그아웃");
		String pageNum = request.getParameter("pageNum");
		HttpSession session = request.getSession();

		session.removeAttribute("CustomInfo");
		session.invalidate();		

		request.setAttribute("pageNum", pageNum);
		
		return "redirect:/bbs/login";
	}

	//게시글

	@RequestMapping(value ="bbs/article", method= { RequestMethod.GET, RequestMethod.POST})
	public String article(HttpServletRequest request, BoardDTO boardDTO) {
		System.out.println("article.");
		String cp = request.getContextPath();

		HttpSession	session = request.getSession();
		CustomInfo info = (CustomInfo) session.getAttribute("CustomInfo");

		String pageNum =  request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		System.out.println(boardNo);

		BoardDTO boardDTO2= boardDAO.article(boardNo);
		boardDTO2.setBoardContent(boardDTO2.getBoardContent().replaceAll("\n", "<br/>"));


		System.out.println(boardDTO2.toString());

		request.setAttribute("boardInfo", boardDTO2);
		request.setAttribute("pageNum", pageNum);
		session.setAttribute("CustomInfo", info);

		return "/bbs/article";
	}

	//수정
	@RequestMapping(value ="bbs/update", method= { RequestMethod.GET, RequestMethod.POST})
	public String update(Model model, HttpServletRequest request, BoardDTO boardDTO) {
		System.out.println("update.");
		HttpSession	session = request.getSession();
		CustomInfo info = (CustomInfo) session.getAttribute("CustomInfo");

		String pageNum =  request.getParameter("pageNum");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		boardDTO = boardDAO.article(boardNo);

		session.setAttribute("customInfo", info);
		request.setAttribute("ddddd", boardDTO);
		request.setAttribute("pageNum", pageNum);

		return "/bbs/update";
	}

	//수정중
	@RequestMapping(value ="bbs/update_ok", method= { RequestMethod.GET, RequestMethod.POST})
	public String update_ok(Model model, HttpServletRequest request, BoardDTO boardDTO) {
		System.out.println("updateok.");
		String pageNum =  request.getParameter("pageNum");
		HttpSession	session = request.getSession();
		CustomInfo info = (CustomInfo) session.getAttribute("CustomInfo");

		boardDAO.updateBoard(boardDTO);

		session.setAttribute("CustomInfo", info);
		request.setAttribute("pageNum", pageNum);
		return "redirect:/bbs/list?pageNum="+pageNum;
	}

	//삭제
	@RequestMapping(value ="bbs/delete_ok", method= { RequestMethod.GET, RequestMethod.POST})
	public String delete_ok(Model model, HttpServletRequest request, BoardDTO boardDTO) {
		System.out.println("delete");
		HttpSession	session = request.getSession();
		CustomInfo info = (CustomInfo) session.getAttribute("CustomInfo");
		String pageNum =  request.getParameter("pageNum");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));

		boardDAO.deleteBoard(boardNo);

		request.setAttribute("pageNum", pageNum);
		session.setAttribute("CustomInfo", info);

		return "redirect:/bbs/list?pageNum="+pageNum;
	}

	//댓글
	@ResponseBody
	@RequestMapping(value ="bbs/commentInsert", method=  RequestMethod.POST)
	public CommentDTO commentInsert(Model model, HttpServletRequest request,CommentDTO cDTO) {
		HttpSession	session = request.getSession();
		CustomInfo info = (CustomInfo) session.getAttribute("CustomInfo");
		//댓글입력
		System.out.println("---------------------------------------");
		
		System.out.println(request.getParameter("comment"));
		System.out.println(request.getParameter("boardNo"));
		System.out.println(request.getParameter("memberId"));
		System.out.println("---------------------------------------");

		
//2ro dw?
		
		int count = boardDAO.insertComment(cDTO);
		Map<String, Object> rmap = new HashMap<String, Object>();
		ArrayList<HashMap> rmaplist = new ArrayList<HashMap>();
		
		if(count != 0) {
			rmap.put("msg","성공" );
			Map<String, Object> cmap = new HashMap<String, Object>();
			cmap.put("boardNo", cDTO.getBoardNo());
			cmap.put("commNum", cDTO.getCommNum());		
						
		}else {
			rmap.put("msg", "실패");
		}

		session.setAttribute("CutsomInfo",info);

		return cDTO;
	}
	
	@ResponseBody
	@RequestMapping(value ="bbs/commentList", method=  RequestMethod.POST)
	public List<HashMap>  commentList(Model model, CommentDTO commentDTO , HttpServletRequest request){
		
		HttpSession	session = request.getSession();
		CustomInfo info = (CustomInfo) session.getAttribute("CustomInfo");
		
		List<HashMap> hmlist = new ArrayList<HashMap>();
		
		HashMap<String, Object> commentParam = new HashMap<String, Object>();
		String boardNO = request.getParameter("boardNo");
		System.out.println(boardNO+"보드넘보드넘");
		int boardNo= Integer.parseInt(request.getParameter("boardNo"));
		
		List<CommentDTO> lists = boardDAO.selectComment(boardNo);
		System.out.println("lists.size ="+lists.size());
		if(lists.size()>0) {
			for(int i=0; i<lists.size();i++) {
				HashMap<String, Object> hm = new HashMap<String, Object>();
				hm.put("comment", lists.get(i).getComment());
				hm.put("memberId", lists.get(i).getMemberId());
				
				hmlist.add(hm);
				
				
			}
		}
		session.setAttribute("CutsomInfo",info);
		return hmlist;
	}

	//댓글삭제
	@ResponseBody
	@RequestMapping(value = "/bbs/commentDelete", method = RequestMethod.POST)
	public String commentDelete(CommentDTO commentDTO, HttpServletRequest request) {
		
		HttpSession	session = request.getSession();
		CustomInfo info = (CustomInfo) session.getAttribute("CustomInfo");
		System.out.println(request.getParameter("boardNo"));
		System.out.println(request.getParameter("commNum"));
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("boardNo", request.getParameter("boardNo"));
		param.put("commNum", request.getParameter("commNum"));
		
		boardDAO.commentDelete(param);
		
		session.setAttribute("CutsomInfo",info);		
		
		return "/bbs/commentList";
	}
	//간편인증
	@ResponseBody
	@RequestMapping(value = "/bbs/eathAuthRegist", method = RequestMethod.POST)
	public Map<String, Object> rsaTest2(Model model, HttpServletRequest request,HttpSession session)
			throws NoSuchAlgorithmException, NoSuchProviderException, IOException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException {

		Map<String, Object> param = CommonUtil.requestToMap(request);
		boolean result =false;

		SecurityUtil securityUtill = new SecurityUtil();
		String memberId = request.getParameter("memberId");
		String memberPw = securityUtill.encryptSHA256(request.getParameter("memberPw"));
		String pinNum = request.getParameter("pinNum");

		System.out.println(pinNum);
		MemberDTO memberDTO = memberDAO.userInfo(memberId);
		Map<String, Object> rMap2 = new HashMap<String, Object>();


		if (memberDTO == null || (!memberDTO.getMemberPw().equals(memberPw))) {

			rMap2.put("msg", "ID 혹은 PW가 틀립니다.");




		} else if ((memberDTO.getMemberId().equals(memberId)) && (memberDTO.getMemberPw().equals(memberPw))) {

			result =true;
			Map<String, String> rmap = new HashMap<String, String>();
			Map<String, String> keyPair =ECDSUtil.getKeyPair();

			param.put("pinNum", pinNum);
			param.put("publicKey", keyPair.get("publicKey"));					
			param.put("memberNo", memberDTO.getMemberNo());

			memberDAO.pinNumInsert(param);
			rMap2.put("privateKey",keyPair.get("privateKey"));
			rMap2.put("memberId",param.get("memberId").toString());
		}

		rMap2.put("result", result);

		return rMap2;
	}
	//핀체크	
	@ResponseBody
	@RequestMapping(value = "/bbs/eathAuthCheck", method = RequestMethod.POST)
	public Map<String, Object>eathAuthCheck(Model model, HttpServletRequest request,HttpSession session)
			throws NoSuchAlgorithmException, NoSuchProviderException, IOException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException {
			
			Map<String, Object> param = CommonUtil.requestToMap(request);
			boolean result =false;
			String memberId = request.getParameter("id");
			
			System.out.println("come");
			System.out.println(memberId);
						
			MemberDTO memberDTO=memberDAO.userInfo(memberId);
			System.out.println("후");
			Map<String, Object> rMap2 = new HashMap<String, Object>();
			String publicKey =memberDTO.getPublicKey();
			String sign =param.get("sign").toString();
			System.out.println("퍼블릭키="+publicKey);
			System.out.println("사인="+sign);
			 //tcchang1
		      
		      byte[] bytes = CommonUtil.hexStringToByteArray(sign);
		      
		      byte[] bytes2 = CommonUtil.hexStringToByteArray(publicKey);
		   
		      sign = Base64.getEncoder().encodeToString(bytes);
		      publicKey = Base64.getEncoder().encodeToString(bytes2);   		      
		      
		      String pinNum =memberDTO.getPinNum();
		      
		      try {
				result=ECDSUtil.verify(publicKey, sign,pinNum);
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
		      if(result) {
		    	  System.out.println("result="+result);
		    	  CustomInfo info = new CustomInfo();
		    	  info.setMemberId(memberDTO.getMemberId());
		    	  info.setMemberPw(memberDTO.getMemberPw());		    	  
		    	  session.setAttribute("CustomInfo", info);
		    	  
		      }else {
		    	  rMap2.put("msg", "실패");
		      }
		      rMap2.put("result", result);

		return rMap2;
	}



	//---------------------------------------------절취선----------------------------------------------------------

	//ajax 연습
	@RequestMapping(value ="bbs/ajaxTest", method=  RequestMethod.GET)
	public String ajaxtest(Model model, HttpServletRequest request, BoardDTO boardDTO) {
		Map<String, Object> rmap = new HashMap<String, Object>();
		System.out.println("오냐??");
		rmap.put("msg", "test");

		return "/bbs/ajaxtest";
	}

	@ResponseBody
	@RequestMapping(value ="bbs/ajaxTest", method=  RequestMethod.POST)
	public BoardDTO ajax(Model model, HttpServletRequest request,BoardDTO bordDTO) {


		System.out.println("통신왓음");

		String ajaxview1 = request.getParameter("ajaxView1");
		String ajaxview2 = request.getParameter("ajaxView2");
		String ajaxview3 = request.getParameter("ajaxView3");

		System.out.println(ajaxview1   + ajaxview2  +  ajaxview3 );

		BoardDTO boardDTO= new BoardDTO();
		boardDTO.setBoardSubject(ajaxview1);
		boardDTO.setBoardContent(ajaxview2);
		boardDTO.setMemberId(ajaxview3);



		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("aaa", boardDTO.getBoardSubject());
		rMap.put("bbb", boardDTO.getBoardContent());
		rMap.put("ccc", boardDTO.getMemberId());

		System.out.println("aaa는=" + rMap.get("aaa").toString());
		System.out.println("bbb는=" + rMap.get("bbb").toString());
		System.out.println("ccc는=" + rMap.get("ccc").toString());



		return boardDTO;
	}




}
