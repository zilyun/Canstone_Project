/*@@@@@@@@@@@@@@@@@@@@@@@@*/
/*home.jsp*/

/*<!-- 인증 메시지 -->*/

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

/*<!--로딩바-->*/

	$(document).ready(function() {
		$('#loading').hide();
		$('#loading1').hide();
		$('#loading2').hide();
		$('#next').submit(function() {
			$('#loading').show();
			return true;
		});
		$('#next1').submit(function() {
			$('#loading1').show();
			return true;
		});
		$('#sendPhoneNumber').submit(function() {
			$('#loading2').show();
			return true;
		});
	});

/*<!-- 툴팁 -->*/

	$(function() {
		$('[data-toggle="tooltip"]').tooltip()
	})

/*<!--채팅-->*/
	var ws;

	function wsOpen() {
		ws = new WebSocket("ws://" + location.host + "/chating");
		wsEvt();
	}

	function wsEvt() {
		ws.onopen = function(data) {
			//소켓이 열리면 초기화 세팅하기
		}

		ws.onmessage = function(data) {
			var msg = data.data;
			if (msg != null && msg.trim() != '') {
				$("#chating").append("<p>" + msg + "</p>");
			}
		}

		document.addEventListener("keypress", function(e) {
			if (e.keyCode == 13) { //enter press
				send();
			}
		});
	}

	function chatName() {
		var userName = $("#userName").val();
		if (userName == null || userName.trim() == "") {
			alert("사용자 이름을 입력해주세요.");
			$("#userName").focus();
		} else {
			wsOpen();
			$("#yourName").hide();
			$("#yourMsg").show();
		}
	}

	function send() {
		var uN = $("#userName").val();
		var msg = $("#chatting").val();
		ws.send(uN + " : " + msg);
		$('#chatting').val("");
	}