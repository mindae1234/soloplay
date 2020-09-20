<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jsrsasign/8.0.20/jsrsasign-all-min.js"></script>
<script type="text/javascript" src="<%=cp %>/resources/js/common.js"></script>
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
<script type="text/javascript">
	$(document).ready(
			function() {
				//localStorage.Test = "Sample";
				var easyAuth = localStorage.easyAuth;

				if (typeof easyAuth == "undefined" || easyAuth == null
						|| easyAuth == "" || easyAuth == "null") {

					$('#eashAuth').text('간편로그인 등록');
					$('#eashAuth').attr('onclick', 'easyAuthRegist()');

				} else {
					$('#eashAuth').text('간편로그인');
					$('#eashAuth').attr('onclick', 'easyAuth()');

				}
			})
//pin등록창 등장
	function easyAuthRegist() {
		$("#easyAuthRegistForm").show();
	}
//pin인증창 등당
	function easyAuth(){
		   $("#easyAuthForm").show(); 
		}
//핀등록
	function easyAuthRegist2() {
		$.ajax({
			url : "<%=cp%>/bbs/eathAuthRegist",
			type : 'post',
			dataType : 'json',
			data : $('#easyAuthRegistForm').serialize(),
			success : function(result) {
				if (result.result) {
					console.log(result);
					
					
					localStorage.easyAuth = result.privateKey;
					localStorage.easyAuthId = result.memberId;

					alert('핀 등록 성공');
					location.reload()

				} else {
					alert(result.msg);
				}

			},
			error : function(xhr, textStatus, errorThrown) { // Error시, 처리 
				alert(xhr);
				alert(textStatus);
				alert(errorThrown);

			}

		});
	}
	
	//핀인증
	function easyAuth2(){
		   
	      var id=localStorage.easyAuthId;
	      var privateKey=localStorage.easyAuth;
	      var pinNum = $('#authPinNum').val();
	      var sign  = signByPrivateKey(pinNum,privateKey);	 
	      
	      /* alert(id);
	      alert(privateKey);
	      alert(pinNum);
	      alert(sign); */
	       
	      var url  =   "<%=cp%>/bbs/eathAuthCheck"
	      var data =  { "id": id, "sign": sign,"pinNum": pinNum};
	      ajaxCall(url,data,"ajaxCallBack");   
	}
	
	function eashAuthDelete(){
		   localStorage.easyAuth ='';
		   localStorage.easyAuthId='';
		   location.reload()
		}
//간편등록 삭제	
	function ajaxCallBack(res){
		   if(res.result){
		      location.href="<%=cp%>/bbs/list"
		   }else{
		      alert(res.msg)
		   }
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
		<td colspan="2" align="center"><b>로그인  </b></td>
	</tr>
	
	<tr height="2" >
		<td colspan="2" bgcolor="#cccccc"></td>
	</tr>
	
	<tr height="25">
		<td width="80" bgcolor="#e6e4e6" align="center">아이디</td>
		<td width="120" style="padding-left: 5px;">
		<input type="text" name="memberId" maxlength="10" size="15">
		</td>
	</tr>
	
	<tr height="2" >
		<td colspan="2" bgcolor="#cccccc"></td>
	</tr>
	
	<tr height="25">
		<td width="80" bgcolor="#e6e4e6" align="center">패스워드</td>
		<td width="120" style="padding-left: 5px;">
		<input type="password" name="memberPw" maxlength="10" size="15">
		</td>
	</tr>

	<tr height="2" >
		<td colspan="2" bgcolor="#cccccc"></td>
	</tr>
	
	
	
	<tr height="30">
		<td colspan="2" align="center">
		<input type="button" value="로그인" class="btn2"
		onclick="login();"/>
		
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
	<tr height="2" >
		<td colspan="2" bgcolor="#cccccc"></td>
	</tr>
</table>

<input type="hidden" name="pageNum" value="${pageNum }">

</form>
<form  id="easyAuthForm" name="easyAuthForm" style="display: none ; align-items: center;" onsubmit="return false" >
   PIN : <input type="text" id="authPinNum" name="authPinNum" placeholder=" PIN번호를 입력하세요.">
         <input id="privateKey" name="privateKey"  type="hidden">
         <button type="button" onclick="easyAuth2()">PIN인증</button>
</form> 
<table align="center">
	<tr>
	<td>
	<button onclick="" id="eashAuth" >간편로그인 </button>
	<button onclick="eashAuthDelete()" id="eashAuthDelete">간편로그인 삭제 </button>
	</td>
	</tr>
</table>
<form  id="easyAuthRegistForm" name="easyAuthRegistForm"  style="display: none; align-items: center;" onsubmit="return false" >
   	아이디:<input type="text" name="memberId" value="" >
  	 패스워드:<input type="password" name="memberPw" value="" >
 	 PIN : <input type="text" id="pinNum" name="pinNum" placeholder="등록할 PIN번호를 입력하세요.">
         <button type="button" onclick="easyAuthRegist2()">PIN등록</button>
</form> 






<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>
</body>
</html>