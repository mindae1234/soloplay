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

<link rel="stylesheet" href="<%=cp%>/resources/css/style2.css" type="text/css">
<link rel="stylesheet" href="<%=cp%>/resources/css/created2.css" type="text/css">

<script type="text/javascript" src="<%=cp%>/resources/js/util/js"></script>



<script type="text/javascript">
	function sendIt(){
		
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
		
		f.action ="<%=cp%>/bbs/join_ok";
		f.submit();
	
	}
</script>



</head>
<body>

<br/><br/>

<div id="bbs">
	<div id="bbs_title">
	회원가입	
	</div>
	
	<form action="" method="post" name="myForm">
	
	<div id="bbsCreated">
	
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>아&nbsp;이&nbsp;디</dt>
				<dd>
				<input type="text" name="memberId" size="35" maxlength="20" class="boxTf"/>
				</dd>		
			</dl>		
		</div>
		
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>패스워드</dt>
				<dd>
				<input type="text" name="memberPw" size="35" maxlength="20" class="boxTf"/>
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
		
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>메&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;일</dt>
				<dd>
				<input type="text" name="memberEmail" size="35" maxlength="50" class="boxTf"/>
				</dd>		
			</dl>		
		</div>
		
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>생&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;일</dt>
				<dd>
				<input type="text" name="memberBirth" size="35" maxlength="50" class="boxTf"/>
				</dd>		
			</dl>		
		</div>
	
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;화</dt>
				<dd>
				<input type="text" name="memberTel" size="35" maxlength="50" class="boxTf"/>
				</dd>		
			</dl>		
		</div>	
		
		<input type="hidden" name="pageNum" value="${pageNum }"/>
				
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>주&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소</dt>
				<dd>
				<input type="text" name="memberAddr" size="35" maxlength="50" class="boxTf"/>
				</dd>		
			</dl>		
		</div>	
		
		
	
	</div>
	
	<div id="bbsCreated_footer">
	<input type="button" value="가입하기" class="btn2"
	onclick="sendIt();"/>
	<input type="reset" value="다시입력" class="btn2"
	onclick="document.myForm.memberId.focus()"/>
	<input type="button" value="가입취소" class="btn2"
	onclick="javascript:location.href='<%=cp%>/bbs/list';"/>
	

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