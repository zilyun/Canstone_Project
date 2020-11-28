<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@폐기@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="${path}/resources/js/alert.js"></script>
<!-- 인증 메시지 -->
<script>
	function button_click() {
		let phoneNumber = $('#inputPhoneNumber').val();

		$.ajax({
			url : "./check/sendSMS_test", // test 실제 운영 시 test 삭제
			type : "GET",
			data : {
				"phoneNumber" : phoneNumber
			},
			success : function(res) {
				
				
			}
		})
		
		swal("인증 메시지","메시지가 전송되었습니다.","success").then((value) =>{
			if(value){
				location.href='./';
			}
		});
	}
</script>

</head>
<body>
	<input id="inputPhoneNumber" type="text" placeholder="전화번호 입력" />
	<button id="sendPhoneNumber" onclick="button_click();">인증</button>

</body>
</html>