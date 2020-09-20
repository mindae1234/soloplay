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
<title>회원가입</title>

<link rel="stylesheet" href="<%=cp%>/resources/css/style.css" type="text/css"/>
<link rel="stylesheet" href="<%=cp%>/resources/css/created.css" type="text/css"/>
<%-- <script type="text/javascript" src="<%=cp%>/resources/js/util/js"></script> --%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>




<script type="text/javascript">
	var count=0;
	function sendIt(){
		var f = document.myForm;
		
		if(!f.memberId.value){
			alert("아이디를 입력하세요!");
			f.memberId.focus();
			return;
		}
		
		if(count<1){
			alert("아이디 중복확인 하세요");
			return;
		}
		
		if(!f.memberPw.value){
			alert("패스워드를 입력하세요!");
			f.memberPw.focus();
			return;
		}	
		if(!f.memberName.value){
			alert("이름를 입력하세요!");
			f.memberName.focus();
			return;
		}	
		
		
		f.action ="<%=cp%>/bbs/join_ok";
		f.submit();
		
		alert("회원가입 성공");
	
	}

function idChk(){
 
	count=1;
 
	var f = document.myForm;
	var memberId = {memberId : f.memberId.value};
	
	if(!f.memberId.value){
		alert("아이디를 입력하세요!");
		f.memberId.focus();
		return;
	}
 $.ajax({
  url : "<%=cp%>/bbs/idChk",
  type : "post",
  dataType : 'json',
  data : memberId,
  success : function(data) {
  console.log(data);
   if(data == 1) {
    $(".result .msg").text("사용 불가");
    $(".result .msg").attr("style", "color:#f00");  
	f.memberId.focus();
	f.memberId.value=null;
	count=0;
	
   } else {
    $(".result .msg").text("사용 가능");
    $(".result .msg").attr("style", "color:#00f");
    conut=1;
   }
  }
 });  // ajax 끝
}
</script>



</head>
<body>

<br/><br/>

<div id="bbs">
	<div id="bbs_title ">
	회원가입	
	</div>
	
	<form action="" method="post" name="myForm">
	
	<div id="bbsCreated">
	
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>아&nbsp;이&nbsp;디</dt>
				<dd>
				<input type="text" name="memberId" size="35" maxlength="20" class="boxTf"/>				
				<input type="button" id="idchk" name="idchk" value="아이디중복확인" onclick="idChk()"/>
				</dd>
			</dl>			
		</div>
		<div align="center">
		<p class="result">
			<span class="msg" style="color: red;">아이디를 확인해 주세요</span>
		</p>
		</div>
	
	
		
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>패스워드</dt>
				<dd>
				<input type="password" name="memberPw" size="35" maxlength="20" class="boxTf"/>
				</dd>		
			</dl>		
		</div>
		
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</dt>
				<dd>
				<input type="text" name="memberName" size="35" maxlength="50" class="boxTf"/>
				</dd>		
			</dl>		
		</div>
		
		<input type="hidden" name="pageNum" value="${pageNum }"/>
		
		<div id="bbsCreated_footer">
		<input type="button" value="가입하기" class="btn2"
		onclick="sendIt();"/>
		<input type="reset" value="다시입력" class="btn2"
		onclick="document.myForm.memberId.focus()"/>
		<input type="button" value="가입취소" class="btn2"
		onclick="javascript:location.href='<%=cp%>/bbs/list';"/>
		</div>
		
	</div>
	</form>
</div>

	






<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>
</body>
</html>