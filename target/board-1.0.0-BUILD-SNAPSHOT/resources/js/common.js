

function ajaxCall(url,data, callback){
	
	
	
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		data : data,
		success : function(result) {
		
		/*	var flag = result.token;				// 요청결과
			var resultCode = result.RESULT_CODE;	// 결과코드
			var message = result.MESSAGE;			// 결과메시지
			*/
		
			eval(callback)(result); 
  
		}, error : function(xhr, textStatus, errorThrown){ // Error시, 처리 
			alert(xhr); alert(textStatus); alert(errorThrown); 
		
		}

	
	});
}


function signByPrivateKey(data, privateKey){
	var sig = new KJUR.crypto.Signature({'alg':'SHA256withECDSA'});
	
	 var parse_PKCS8PrvKey = encode => KEYUTIL.getKey(encode, null, "pkcs8prv").prvKeyHex;
	  
     
	  sig.init({d: parse_PKCS8PrvKey(privateKey), curve: 'secp256k1'});
	  sig.updateString(data); 
	  var sigVal = sig.sign(); 
	  
	  return sigVal;
	  
}