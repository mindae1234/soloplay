<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	request.setCharacterEncoding("utf-8");
	String cp = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게 시 판</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<link rel="stylesheet" href="<%=cp%>/board/css/style.css" type="text/css"/>
<link rel="stylesheet" href="<%=cp%>/board/css/article.css" type="text/css"/>
<script type="text/javascript">

	function deleteData() {
		
		var boardNum = "${dto.boardNum}";
		var searchKey ="${searchKey}";
		var searchValue ="${searchValue}";
		var pageNum = "${pageNum}";
		if(searchKey ==null){
			searchKey = "subject";
			searchValue = "";	
		}
		
		var params ="?boardNum=" + boardNum + "&pageNum=" + pageNum + "&searchKey=" + searchKey + "&searchValue=" + searchValue;
		var url = "<%=cp%>/bbs/deleted.action"+ params;
		
		location.replace(url);
	}

	function updateData() {
		
		var boardNum = "${dto.boardNum}";
		var pageNum = "${pageNum}";
		var searchKey ="${searchKey}";
		var searchValue ="${searchValue}";
		if(searchKey ==null){
			searchKey = "subject";
			searchValue = "";	
		}
		var params ="?boardNum=" + boardNum + "&pageNum=" + pageNum + "&searchKey=" + searchKey + "&searchValue=" + searchValue;
		var url = "<%=cp%>/bbs/updated.action"+ params;
		
		location.replace(url);
	}


</script>
</head>

<body>

<div id="bbs">
	<div id="bbs_title">
	게 시 판 (Spring 2.5)
	</div>
	<div id="bbsArticle">
		<div id="bbsArticle_header">
			${dto.subject }
		</div>
		<div class="bbsArticle_bottomLine">
			<dl>
				<dt>작성자</dt>
				<dd>
				${dto.name }
				<c:if test="${!empty dto.email}">
				(<a href="mailto:${dto.email }">${dto.email }</a>)
				</c:if>
				</dd>
				
				<dt>줄수</dt>
				<dd>${lineSu }</dd>
			</dl>
		</div>
		<div class="bbsArticle_bottomLine">
			<dl>
				<dt>등록일</dt>
				<dd>${dto.created }</dd>
				<dt>조회수</dt>
				<dd>${dto.hitCount }</dd>
			</dl>
		</div>
		<div id="bbsArticle_content">
			<table width="600" border="0">
			<tr><td style="padding: 20px 80px 20px 62px;" valign="top" height="200">
			${dto.content }////<br/>
		&&&&&&&&&&&&&&&&&&&&&&&&${searchKey }******${searchValue }&^^^^^^^^
			</td></tr>
			</table>
		</div>
		<div class="bbsArticle_bottomLine">
		이전글:
		<c:if test="${!empty preSubject }">
			<a href="<%=cp %>/bbs/article.action?${params}&boardNum=${preBoardNum}">
			${preSubject }</a>
		</c:if>		
		</div>
		<div class="bbsArticle_noLine">
		다음글:
		<c:if test="${!empty nextSubject }">
			<a href="<%=cp %>/bbs/article.action?${params}&boardNum=${nextBoardNum}">
			${nextSubject }</a>
		</c:if>		
		</div>
		
		
	</div>
	<div class="bbsArticle_noLine" style="text-align:right">
		    ${dto.ipAddr }
	</div>
	<div id="bbsArticle_footer">
		<div id="leftFooter">
               <input type="button" value=" 수정 " class="btn2" onclick="updateData();"/>
               <input type="button" value=" 삭제 " class="btn2" onclick="deleteData();"/>
		</div>
		<div id="rightFooter">
               <input type="button" value=" 리스트 " class="btn2" 
               onclick="javascript:location.href='<%=cp%>/bbs/list.action?${params}';"/>
		</div>
	</div>

</div>

<br/>&nbsp;<br/>
</body>

</html>