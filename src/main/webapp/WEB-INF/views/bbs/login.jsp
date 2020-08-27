<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>

<%-- <link rel="stylesheet" href="<%=cp%>/resources/css/style.css" type="text/css">
 --%>
<script type="text/javascript">
	function login(){
		
		var f = document.myForm;
		
		if(!f.memberId.value){
			alert("아이디를 입력하세요!");
			f.memberId.focus();
			return;
		}
		
		if(!f.memberPw.value){
			alert("패스워드를 입력하세요!");
			f.memberPw.focus();
			return;
		}
		
		f.action="<%=cp%>/bbs/login_ok";
		f.submit();
			
	}
	
</script>

</head>
<body>

<br/><br/>

<form action="" method="post" name="myForm">

<table align="center" cellpadding="0" cellspacing="0">
	<tr height="2" >
		<td colspan="2" bgcolor="#cccccc"></td>
	</tr>
	
	<tr height="30">
		<td colspan="2" align="center"><b>로그인</b></td>
	</tr>
	
	<tr height="2" >
		<td colspan="2" bgcolor="#cccccc"></td>
	</tr>
	
	<tr height="25">
		<td width="80" bgcolor="#e6e4e6" align="center">아이디</td>
		<td width="120" style="padding-left: 5px;">
		<input type="text" name="userId_kmk" maxlength="10" size="15">
		</td>
	</tr>
	
	<tr height="2" >
		<td colspan="2" bgcolor="#cccccc"></td>
	</tr>
	
	<tr height="25">
		<td width="80" bgcolor="#e6e4e6" align="center">패스워드</td>
		<td width="120" style="padding-left: 5px;">
		<input type="text" name="userPwd_kmk" maxlength="10" size="15">
		</td>
	</tr>

	<tr height="2" >
		<td colspan="2" bgcolor="#cccccc"></td>
	</tr>
	
	<tr height="30">
		<td colspan="2" align="center">
		<input type="button" value="로그인" class="btn2"
		onclick="login();"/>
		<input type="button" value="취소"  class="btn2"
		onclick="javascript:location.href='<%=cp%>/bbs/list';"/>
		<input type="button" value="회원가입"  class="btn2"
		onclick="javascript:location.href='<%=cp%>/bbs/join';"/>
		</td>
	</tr>		
		
	<tr height="30">
		<td colspan="2" align="center">
		<font color="red"><b> ${message } ${message4 } ${message3 }</b></font>
		</td>
	</tr>
	
</table>

<table align="center" cellpadding="0" cellspacing="0">
	
	<tr height="2" >
		<td colspan="2" bgcolor="#cccccc"></td>
	</tr>

<%-- 	<tr height="30">
		<td colspan="2" align="center">
		<a href="<%=cp%>/join/searchpw.do">${message2 }</a>
		</td>
	</tr> --%>
	
	<tr height="2" >
		<td colspan="2" bgcolor="#cccccc"></td>
	</tr>

</table>






</form>






<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>
</body>
</html>