package com.study.test.board.pageindex;

import org.springframework.stereotype.Repository;

@Repository
public class PageIndex {
//
	// 전체 페이지수 구하기
	// numPerPage : 한화면에 표시할 데이터의 갯수
	// dataCount : 전체데이터의 갯수

	public int getPageCount(int numPerPage, int dataCount) {

		int pageCount = 0;

		pageCount = dataCount / numPerPage;

		if (dataCount % numPerPage != 0)
			pageCount++;

		return pageCount;
	}

	// 페이징처리 메소드
	// currentPage : 현재 표시할 페이지
	// totalPage : 전체 페이지 수
	// listUrl : 링크를 설정할 url
	public String pageIndexList(int currentPage, int totalPage, String listUrl) {

		int numPerBlock = 5;

		int currentPageSetup;// numPerBlock의 첫번째 숫자에서 -1

		int page;

		StringBuffer sb = new StringBuffer();

		if (currentPage == 0 || totalPage == 0)
			return "";

		// list.jsp?pageNum=2
		// list.jsp?searchKey=subject&searchValue=3&pageNum=2
		if (listUrl.indexOf("?") != -1) {
			listUrl = listUrl + "&";
		} else {
			listUrl = listUrl + "?";
		}

		// currentPageSetup 구하기
		currentPageSetup = (currentPage / numPerBlock) * numPerBlock;
		// < 11 12 13 14 15>
		// currentPaget=13 -> 13/5(numPerBlock)*5(numPerBlock)
		// ->currentPageSetup =10 ->10번페이지로

		if (currentPage % numPerBlock == 0)
			currentPageSetup = currentPageSetup - numPerBlock;

		// ◀이전 만들기

		if (totalPage > numPerBlock && currentPageSetup > 0) {
			sb.append("<a href=\"" + listUrl + "pageNum=" + currentPageSetup
					+ "\">◀이전</a>&nbsp;");
			// <a href="list.jsp?pageNum=5>◀이전</a>&nbsp;
		}

		// 바로가기 page
		page = currentPageSetup + 1;
		while (page <= totalPage && page <= (currentPageSetup + numPerBlock)) {

			if (page == currentPage) {

				sb.append("<font color=\"Fuchsia\">" + page + "</font>&nbsp;");
				// <font color="Funchsia">9</font>&nbsp;

			} else {

				sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">"
						+ page + "</a>&nbsp;");
				// <a href = "list.jsp?pageNum=6">6</a>&nbsp;
			}
			page++;

		}
		//다음▶
		if(totalPage-currentPageSetup>numPerBlock){
			
			sb.append("<a href=\"" + listUrl + "pageNum=" +
					page + "\">다음▶</a>&nbsp;" );
			//<a href="list.jsp?pageNum=11">다음▶</a>&nbsp;
				
		}
		return sb.toString();
	}

}