<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>

<script type="text/javascript">
	function ajaxTest() {
		alert('22')

		$.ajax({
			url : "${pageContext.request.contextPath}/bbs/ajaxTest",
			type : 'post',
			dataType : 'json',
			data : $('#ajaxFrom').serialize(),
			success : function(result) {
				console.log('22');
				alert('??')
			 	console.log(result);
				
				
				alert(result); 
				var row="";
				row+="<div>"+result.boardSubject+"</div>"
				var row1="";
				row+="<div>"+result.boardContent+"</div>"
				var row2="";
				row2+="<div>"+result.memberId+"</div>"
				
				$('#ajaxTest').html(row);
				$('#ajaxTest2').html(row1);
				$('#ajaxTest3').html(row2);

			},
			error : function(xhr, textStatus, errorThrown) { // Error시, 처리 
				alert(xhr);
				alert(textStatus);
				alert(errorThrown);

			}

		});
	}
</script>

<body>
	<button onclick="ajaxTest()">ajax테스트</button>
</body>

<form id="ajaxFrom">
ajax1 : <input name="ajaxView1"> </br>
ajax2 : <input name="ajaxView2"> </br>
ajax3 : <input name="ajaxView3"> </br>
</form>

<div id="ajaxTest">

</div>
<div id="ajaxTest2">

</div>
<div id="ajaxTest3">

</div>


<script type="text/javascript">

</script>

</html>