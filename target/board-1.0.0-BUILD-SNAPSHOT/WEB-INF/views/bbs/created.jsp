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

<link rel="stylesheet" href="<%=cp%>/resources/css/style.css" type="text/css"/>
<link rel="stylesheet" href="<%=cp%>/resources/css/created.css" type="text/css"/>

<script type="text/javascript" src="<%=cp%>/resources/js/util.js"></script>
<script type="text/javascript">

    function sendIt() {
       
		f = document.myForm;

    	str = f.boardSubject.value;
    	str = str.trim();
        if(!str) {
            alert("\n제목을 입력하세요. ");
            f.boardSubject.focus();
            return;
        }
    	f.boardSubject.value = str;

	    str = f.memberId.value;
    	str = str.trim();
        if(!str) {
            alert("\n이름을 입력하세요. ");
            f.memberId.focus();
            return;
        }
		/*
        if(!isValidKorean(str))  {
            alert("\n이름을 정확히 입력하세요");
            f.name.focus();
            return;
        }
		*/
	    f.memberId.value = str;

       /*  if(f.email.value) {
	    	if(!isValidEmail(f.email.value)) {
                alert("\n정상적인 E-Mail을 입력하세요. ");
                f.email.focus();
                return;
	    	}
        } */

    	str = f.boardContent.value;
	    str = str.trim();
        if(!str) {
            alert("내용을 입력하세요. ");
            f.boardContent.focus();
            return;
        }
    	f.boardContent.value = str;

    /* 	str = f.pwd.value;
	    str = str.trim();
        if(!str) {
            alert("\n패스워드를 입력하세요. ");
            f.pwd.focus();
            return;
        }
    	f.pwd.value = str; */
    	f.action = "<%=cp%>/bbs/created_ok?pageNum=${pageNum}";   
        
        f.submit();
    }

</script>

</head>

<body>

<div id="bbs">
	<div id="bbs_title">
	게 시 판 
	</div>

	<form name="myForm" method="post" action="">
	<div id="bbsCreated">
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>제&nbsp;&nbsp;&nbsp;&nbsp;목</dt> 
				<dd>
				      <input type="text" name="boardSubject" size="50" maxlength="100"  class="boxTF" value="${dto.subject }" />
				</dd>
			</dl>
		</div>

		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>작성자</dt>
				<dd>
				      <input type="text" name="memberId" size="35" maxlength="20" class="boxTF" value="${sessionScope.CustomInfo.memberId }" readonly="readonly" />
				</dd>
			</dl>
		</div>

	<%-- 	<div class="bbsCreated_bottomLine">
			<dl>
				<dt>E-Mail</dt>
				<dd>
				      <input type="text" name="email" size="35" maxlength="50" class="boxTF" value="${dto.email }"/>
				</dd>
			</dl>
		</div> --%>

		<div id="bbsCreated_content">
			<dl>
				<dt>내&nbsp;&nbsp;&nbsp;&nbsp;용</dt>
				<dd>
				      <textarea name="boardContent" cols="63" rows="12" class="boxTA"></textarea>
				</dd>
			</dl>
		</div>

		<%-- <div class="bbsCreated_noLine">
			<dl>
				<dt>패스워드</dt>
				<dd>
				      <input type="password" name="pwd" size="35" maxlength="7" class="boxTF" value="${dto.pwd }" />&nbsp;(게시물 수정 및 삭제시 필요 !!!)
				</dd>
			</dl>
		</div> --%>
	</div>

	<div id="bbsCreated_footer">
		
				
			<input type="button" value=" 등록하기 " class="btn2" 
	        onclick="sendIt();"/>    
	        <input type="reset" value=" 다시입력 " class="btn2" 
	       	onclick="document.myForm.boardSubject.focus();"/>
		    <input type="button" value=" 작성취소 " class="btn2" 
		    onclick="javascript:location.href='<%=cp%>/bbs/list'"/>
	</div>

    </form>
</div>

</body>

</html>