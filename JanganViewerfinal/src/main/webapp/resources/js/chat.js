	var ws;

	window.onload = function() {
		var userName = $("#userName").val();
		setInterval(function() {
			$(".chating").removeClass('chatingr');
		}, 7000);
		wsOpen()
	}

	$(function() {
		$(".chating").on('mousewheel DOMMouseScroll', function(e) {
			var E = e.originalEvent;

			if (E.detail) {
				$('.chating').addClass('chatingr');
			} else {
				$('.chating').addClass('chatingr');
			}

		});
	});

	function wsOpen() {
		//웹소켓 전송시 현재 방의 번호를 넘겨서 보낸다.
		ws = new WebSocket("ws://" + location.host + "/chating/"
				+ $("#roomNumber").val() + "&" + $("#userName").val());
		wsEvt();
	}

	function wsEvt() {
		ws.onopen = function(data) {
			//소켓이 열리면 동작
		}

		ws.onmessage = function(data) {
			//메시지를 받으면 동작
			var msg = data.data;
			if (msg != null && msg.type != '') {
				//파일 업로드가 아닌 경우 메시지를 뿌려준다.
				var d = JSON.parse(msg);
				if (d.type == "getId") {
					var si = d.sessionId != null ? d.sessionId : ""; // sessionId 가 널과 다르면 ""로 하라
					if (si != '') { // 가져오기 
						if (d.userName == $("#userName").val()) {

							$("#chating").append(
									"<div class='block'><p class='balloon_semo'></p><p class='me balloon_02'>"
											+ d.msg + "</p></div>");
							$("#chating").scrollTop(
									$("#chating")[0].scrollHeight);
						} else {
							$("#chating")
									.append(
											"<div class='block'><p class='float circle1' style='background-image: url(<c:url value='/resources/img2/lionprofile.gif'/>);background-repeat:no-repeat;background-position:bottom center; background-size: 35px;'>"
													+ d.userName
															.substring(0, 1)
													+ "</p><p class='others balloon_03'>"
													+ d.msg + "</p></div>");
							$("#chating").scrollTop(
									$("#chating")[0].scrollHeight);
						}
					}
				} else if (d.type == "message") { // 평소 처리
					if (d.userName == $("#userName").val()) {
						$("#chating").append(
								"<div class='block'><p class='balloon_semo'></p><p class='me balloon_02'>"
										+ d.msg + "</p></div>");
						$("#chating").scrollTop($("#chating")[0].scrollHeight);
					} else {
						$("#chating")
								.append(
										"<div class='block'><p class='float circle1' style='background-image: url(<c:url value='/resources/img2/lionprofile.gif'/>);background-repeat:no-repeat;background-position: center; background-size: 35px;'>"
												+ d.userName.substring(0, 1)
												+ "</p><p class='others balloon_03'>"
												+ d.msg + "</p></div>");
						$("#chating").scrollTop($("#chating")[0].scrollHeight);
					}

				} else {
					console.warn("unknown type!")
				}
			} else {
				//파일 업로드한 경우 업로드한 파일을 채팅방에 뿌려준다.
				var url = URL.createObjectURL(new Blob([ msg ]));
				$("#chating")
						.append(
								"<div class='img'><img class='msgImg' src="+url+"></div><div class='clearBoth'></div>");
			}
		}

		document.addEventListener("keypress", function(e) {
			if (e.keyCode == 13) { //enter press
				send();
			}
		});
		
		document.addEventListener("keyup", function(e) {
			if (e.keyCode != 13) { //enter press
				adjustHeight();
			}else{
				$('.form-control').val("");
				adjustHeight();
			}
		});
	}

	function send() {
		if ($('#chatting').val() == '') {
			alert('test');
		} else {
			var option = {
				type : "message",
				roomNumber : $("#roomNumber").val(),
				userName : $("#userName").val(),
				msg : $("#chatting").val()
			}
			ws.send(JSON.stringify(option))
			$('.form-control').val("");
		}

	}

	function adjustHeight() {
		var textEle = $('textarea');
		textEle[0].style.height = 'auto';
		var textEleHeight = textEle.prop('scrollHeight');
		textEle.css('height', textEleHeight);
	};

	/* var textEle = $('textarea');
	textEle.on('keyup', function() {
		adjustHeight();
	}); */

	/* function fileSend(){
		var file = document.querySelector("#fileUpload").files[0];
		var fileReader = new FileReader();

		fileReader.onload = function() {
			var param = {
				type: "fileUpload",
				file: file,
				roomNumber: $("#roomNumber").val(),
				sessionId : $("#sessionId").val(),
				msg : $("#chatting").val(),
				userName : $("#userName").val()
			}
			

			ws.send(JSON.stringify(param)); //파일 보내기전 메시지를 보내서 파일을 보냄을 명시한다.

		    arrayBuffer = this.result;
			ws.send(arrayBuffer); //파일 소켓 전송
		};
		fileReader.readAsArrayBuffer(file);

	} */