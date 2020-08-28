<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	request.setCharacterEncoding("utf-8");
	String cp = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게 시 판 ${boardDTO.boardNo }++${boardNO }</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<link rel="stylesheet" href="<%=cp%>/resources/css/style.css" type="text/css"/>
<link rel="stylesheet" href="<%=cp%>/resources/css/article.css" type="text/css"/>

</head>

<body>

<div id="bbs">
	<div id="bbsArticle">
		<div id="bbsArticle_header">
			${boardDTO.boardSubject }
		</div>
		<div class="bbsArticle_bottomLine">
			<dl>
				<dt>작성자</dt>
				<dd>${boardDTO.memberId }</dd>
				<%-- <dt>mail</dt>
				<dd>${boardDTO.memberEmail}</dd> --%>
			</dl>
		</div>
		<div class="bbsArticle_bottomLine">
			<dl>
				<dt>등록일</dt>
				<dd>${boardDTO.boardDate }</dd>
			<%-- 	<dt>조회수</dt>
				<dd>${boardDTO.boardCount}</dd>
			</dl> --%>
		</div>
		<div id="bbsArticle_content">
			<table width="600" border="0">
			<tr><td style="padding: 20px 80px 20px 62px;" valign="top" height="200">
			${boardDTO.boardContent }
			</td></tr>
			</table>
		</div>
		
	</div>
	<div class="bbsArticle_noLine" style="text-align:right">
	</div>
	<div id="bbsArticle_footer">
	<%-- <c:choose> --%>
		<%-- <c:when test="${sessionScope.customInfo.userId_kmk == b_dto.userId_kmk }">	 --%><div id="leftFooter">
               <input type="button" value=" 수정 " class="btn2" 
               onclick="javascript:location ='<%=cp%>/bbs/update?boardNo=${boardDTO.boardNo }'"/>
               <input type="button" value=" 삭제 " class="btn2" 
               onclick="javascript:location ='<%=cp%>/bbs/delete_ok?boardNo=${boardDTO.boardNo }'"/>
		</div>
	<%-- 	</c:when> --%>
	<%-- </c:choose> --%>
		<div id="rightFooter">
               <input type="button" value=" 리스트 " class="btn2" 
               onclick="javascript:location ='<%=cp%>/bbs/list'"/>
		</div>
	</div>

</div>

<br/>&nbsp;<br/>
</body>

</html>