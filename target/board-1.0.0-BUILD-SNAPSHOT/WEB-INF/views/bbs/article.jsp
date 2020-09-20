<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	request.setCharacterEncoding("utf-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게 시 판</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<link rel="stylesheet" href="<%=cp%>/resources/css/style.css"
	type="text/css" />
<link rel="stylesheet" href="<%=cp%>/resources/css/article.css"
	type="text/css" />
<link rel="stylesheet" href="<%=cp%>/resources/css/created.css" type="text/css"/>

<script type="text/javascript">

	function commentInsert() {
	
		$.ajax({
			url : "${pageContext.request.contextPath}/bbs/commentInsert",
			type : 'post',
			dataType : 'json',
			data : $('#reply').serialize(),
			success : function(result) {	
				
				 alert("댓글 입력 완료");
				 $("#comment").val("");
				 getCommentList();

			},
			error : function(xhr) {  
				alert(xhr);
				alert(reuslt.msg);
				alert("오류다");

			}

		});		
	}
	
	$(function(){
	    
	    getCommentList();
	    
	});

	function getCommentList(){
		var boardNo='${boardInfo.boardNo}';
		var data =  { "boardNo": '${boardInfo.boardNo}' };
		var id = '${sessionScope.CustomInfo.memberId}';
		$.ajax({
	        type:'post',
	        url : "${pageContext.request.contextPath}/bbs/commentList",
	        dataType : "json",
	        data:  data ,
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
	        success : function(data){
	        	
	           	console.log(data)
	            
	            var html = "";
	           // var cCnt = data.length;
	            
	            if(data.length > 0){
	            	
	                for(i=0; i<data.length; i++){
	                    html += "<div id='bbsComment' >";
	                    html += "<div id='bbsComment_header'><strong>"+data[i].memberId+"</strong></div>";
	                    html += "<div id='bbsComment_content'>"+data[i].comment +"</div>";	                   
	                    html += "<tr><td><tr><td>"
	                   	
	                }
	                
	          /*       for(i=0; i<data.length; i++){
	                    html += "<form id='comment'  method='post'><div id='bbsComment' >";
	                    html += "<div id='bbsComment_header'><strong>"+data[i].memberId+"</strong></div>";
	                    html += "<div id='bbsComment_content'>"+data[i].comment +"</div>";	                   
	                    html += "<input type='hidden' name='commNum' value=''"+data[i].commNum+  "'><input type='hidden' name='boardNo' value=''"+data[i].boardNo  +"'></form>"
	                   	if( id ==data[i].memberId){	                   
	                   	html += "<input type='button' value=' 삭제 '  onclick = 'commentDelete()'><tr><td></td></tr>";
	                   	}
	                } */
	                
	            } else {
	                
	                html += "<div>";
	                html += "<div><table class='table'><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
	                html += "</table></div>";
	                html += "</div>";
	                
	            }
	            
	           // $("#cCnt").html(cCnt);
	            $("#commentList").html(html);
	            
	        },
	        error:function(xhr){
	        	
	        	alert(xhr);
				alert("오류다");
	            
	       }
	        
	    });
	}
	
	function commentDelete(cno){
		
	    $.ajax({
	        url : "${pageContext.request.contextPath}/bbs/commentDelete",
	        type : 'post',	        
	        dataType: "json",
	        data: $('#comment').serialize(),
	        success : function(data){
	        	
	        	 getCommentList();
	        }
	    });
	}



	


</script>

</head>





<body>
	<div id="bbs">
		<div id="bbsArticle">
			<div id="bbsArticle_header">${boardInfo.boardSubject}</div>
			<div class="bbsArticle_bottomLine">
				<dl>
					<dt>작성자</dt>
					<dd>${boardInfo.memberId}</dd>				
				</dl> 
			</div>
			<div class="bbsArticle_bottomLine">
				<dl>
					<dt>등록일</dt>
					<dd>${boardInfo.boardDate}</dd>
					<%-- 	<dt>조회수</dt>
				<dd>${boardDTO.boardCount}</dd>
			</dl> --%>
			</div>
			<div id="bbsArticle_content">
				<table width="600" border="0">
					<tr>
						<td style="padding: 20px 80px 20px 62px;" valign="top"
							height="200">${boardInfo.boardContent}</td>
					</tr>
				</table>
			</div>

		</div>
		<div class="bbsArticle_noLine" style="text-align: right"></div>
		<div id="bbsArticle_footer">
			<div id="leftFooter">
			<c:choose >
				<c:when test="${sessionScope.CustomInfo.memberId == boardInfo.memberId}">
					<input type="button" value=" 수정 " class="btn2"
					onclick="javascript:location ='<%=cp%>/bbs/update?boardNo=${boardInfo.boardNo }&pageNum=${pageNum }'" />
					<input type="button" value=" 삭제 " class="btn2"
					onclick="javascript:location ='<%=cp%>/bbs/delete_ok?boardNo=${boardInfo.boardNo }&pageNum=${pageNum }'" />
				</c:when>
			</c:choose>
			</div>
			
				<div id="rightFooter">
				<input type="button" value=" 리스트 " class="btn2" 
					onclick="javascript:location ='<%=cp%>/bbs/list'" />
				</div>
		</div>
	</div>

	<!-- <button onclick="ajaxtTest">ajaxTest</button> -->





	<br />&nbsp;
	<br />
	<div align="center">
	<form id="reply"  method="post">		
			<input type="text" name="comment" id="comment" style="width: 400px; height: 20px;" >			    
	    	<input type="hidden" name="boardNo" id="boardNo" value="${boardInfo.boardNo }">
	    	<input type="hidden" name="memberId" id="memberId" value="${sessionScope.CustomInfo.memberId }">
	    	<input type="button" value=" 등록하기 " style="margin-bottom: 20px;" onclick="commentInsert();" />
	    	<br>	    	
    </form>
    </div>
    
   <div class="bbs" align="center">
    	<form id="commentListForm" name="commentListForm" method="post">
    	    	<input type="hidden" name="boardNo" id="boardNo" value="${boardInfo.boardNo }">
       	 <div id="commentList" class="bbsArticle">
        	</div>
   	 </form>
	</div>
    

</body>

</html>