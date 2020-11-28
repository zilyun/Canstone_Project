<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>SSH 명령 결과</title>
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!-- Bootstrap core JS-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!-- Third party plugin JS-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<!-- Core theme JS-->
<script src="<c:url value="/resources/js/scripts.js" />"></script>
<script type="text/javascript"
	src="http://jqueryjs.googlecode.com/files/jquery-1.2.6.min.js"></script>
<script type="text/javascript"
	src="${path}/resources/js/jquery-ui-personalized-1.6rc2.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/inettuts.js"></script>
<!-- Google fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Saira+Extra+Condensed:500,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Muli:400,400i,800,800i"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link
	href="${pageContext.request.contextPath}/resources/css/styles_menu2.css"
	rel="stylesheet" type="text/css" />
<link href="${path}/resources/css/lodingbar.css" rel="stylesheet" />
<link href="${path}/resources/css/button.css" rel="stylesheet" />
<link href="${path}/resources/css/inettuts.css" rel="stylesheet" />
<link rel="icon" type="image"
	href="${pageContext.request.contextPath}/resources/assets/img/favicon.ico" />
<link rel="stylesheet"
	href=".${path}/resources/css/fontello-embedded.css">
<link rel="stylesheet" href=".${path}/resources/css/fontello-codes.css">
<!--로딩바-->
<script>
	$(document).ready(function() {
		$('#loading').hide();
		$('#loading1').hide();
		$('#next').submit(function() {
			$('#loading').show();
			return true;
		});
		$('#next1').submit(function() {
			$('#loading1').show();
			return true;
		});
	});
	
</script>
<!-- 툴팁 -->
<script>
	$(function() {
		$('[data-toggle="tooltip"]').tooltip()
	})
</script>
<style>
/*
    친효애드온 : 포스트잇 모듈 (마크1) 시작
    https://rgy0409.tistory.com
    e-mail : rgy0409@gmail.com
*/

.textarea_test {
	margin-top:10px;
    resize:none;
    line-height:30px;
    width:60%;
	margin-left:auto;
	margin-right:auto;
    overflow-y:hidden;
    height:30px;
    border:1px solid #E0E0E0;
    outline: none;
}
  .textarea_test:focus{
    border-color:dodgerBlue;
    box-shadow:0 0 8px 0 dodgerBlue;
  }





div.rgyPostIt {
	position: relative;
	display: inline-block;
	padding: 20px 45px 20px 15px;
	margin: 5px 0;
	border: 1px solid #f8f861;
	border-left: 30px solid #f8f861;
	border-bottom-right-radius: 60px 10px;
	font-family: 'Nanum Pen Script';
	text-align: center;
	font-size: 27px;
	color: #555;
	word-break: break-all;
	background: #ffff88; /* Old browsers */
	background: -moz-linear-gradient(-45deg, #ffff88 81%, #ffff88 82%, #ffff88 82%,
		#ffffc6 100%); /* FF3.6+ */
	background: -webkit-gradient(linear, left top, right bottom, color-stop(81%, #ffff88
		), color-stop(82%, #ffff88), color-stop(82%, #ffff88),
		color-stop(100%, #ffffc6)); /* Chrome,Safari4+ */
	background: -webkit-linear-gradient(-45deg, #ffff88 81%, #ffff88 82%, #ffff88 82%,
		#ffffc6 100%); /* Chrome10+,Safari5.1+ */
	background: -o-linear-gradient(-45deg, #ffff88 81%, #ffff88 82%, #ffff88 82%,
		#ffffc6 100%); /* Opera 11.10+ */
	background: -ms-linear-gradient(-45deg, #ffff88 81%, #ffff88 82%, #ffff88 82%,
		#ffffc6 100%); /* IE10+ */
	background: linear-gradient(135deg, #ffff88 81%, #ffff88 82%, #ffff88 82%, #ffffc6
		100%); /* W3C */
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffff88',
		endColorstr='#ffffc6', GradientType=1);
	/* IE6-9 fallback on horizontal gradient */
	transition: all 0.2s;
	-webkit-transition: all 0.2s;
	font-size: 27px;
}

div.rgyPostIt::after {
	content: " ";
	position: absolute;
	z-index: -1;
	right: 0;
	bottom: 35px;
	width: 250px;
	height: 30px;
	background-color: rgba(0, 0, 0, 0);
	box-shadow: 2px 35px 5px rgba(0, 0, 0, 0.4);
	-webkit-box-shadow: 2px 35px 5px rgba(0, 0, 0, 0.4);
	transform: matrix(-1, -0.1, 0, 1, 0, 0);
	-webkit-transform: matrix(-1, -0.1, 0, 1, 0, 0);
	-moz-transform: matrix(-1, -0.1, 0, 1, 0, 0);
	-ms-transform: matrix(-1, -0.1, 0, 1, 0, 0);
	-o-transform: matrix(-1, -0.1, 0, 1, 0, 0);
	transition: all 0.2s;
	-webkit-transition: all 0.2s;
}

div.rgyPostIt:hover {
	border-bottom-right-radius: 75px 30px;
}

div.rgyPostIt:hover::after {
	box-shadow: 2px 37px 7px rgba(0, 0, 0, 0.37);
	-webkit-box-shadow: 2px 37px 7px rgba(0, 0, 0, 0.37);
}

div.rgyPostIt>p {
	padding: 5px 0 !important;
}

div.rgyPostIt>p::before {
	content: "\f198";
	margin-right: 7px;
	font-family: "FontAwesome";
	font-weight: normal;
	font-size: 20px;
	vertical-align: middle;
}

div.rgyPostIt>p>a {
	color: #555;
}
/* 포스트잇 모듈 (마크1) 끝 */
body {
	max-width: 100%;
	font-size: 20px;
	font-family: Arial, Verdana, Sans-Serif;
	background: #f3eadd;
	width: 100%;
	height: 100%;
}

nav {
	width: 100%;
}

.pricing_table {
	border: 1px solid #c4cbcc;
	border-radius: 4px;
	-moz-border-radius: 4px;
	-webkit-border-radius: 4px;
	outline: 7px solid #f2f3f3;
	float: left;
}

p {
	margin: 10px 0;
}

@font-face {
	font-family: 'KOMACON';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_seven@1.2/KOMACON.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

@font-face {
	font-family: 'S-CoreDream-8Heavy';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-8Heavy.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

.mainContainer {
	position: relative;
	height: 100%;
	width: 80%;
	margin-left: 300px;
	margin-right: auto;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

@media screen and (max-width:1002px) {
	.mainContainer {
		margin-left: auto;
		margin-right: auto;
		margin-top: 70px;
		width: 100%;
	}
}

.main {
	color: #1E2022;
	height: 98%;
	width: 90%;
	background-color: #f0e9e2;
	border-radius: 3px;
	margin-top: 0px;
	box-shadow: rgba(0, 0, 0, 0.55) 0px 20px 68px;
	font-family: 'KOMACON';
	font-weight: bold;
	text-align: center;
	font-size: 14pt;
	padding-top: 20px;
}

.clipboard {
	background-color: #62abf3;
	display: flex;
	flex-direction: column;
	align-items: center;
	flex: 1 1 90%;
	width: 93%;
	margin-left: auto;
	margin-right: auto;
	justify-content: center;
	border-radius: 8px;
	margin-bottom: 10px;
}

.clip {
	position: relative;
	top: 18px;
	z-index: 999;
	width: 150px;
	height: 20px;
	border: solid 4px #b9b9b9;
	border-radius: 4px;
}

#clip1 {
	position: absolute;
	top: 13px;
	left: -5px;
	height: 5px;
	width: 15px;
	border-left: 7px solid black;
	border-bottom: 7px solid black;
	border-top: 0px;
	border-right: 0px;
}

#clip2 {
	position: absolute;
	top: 13px;
	right: -5px;
	height: 5px;
	width: 15px;
	border-right: 7px solid black;
	border-bottom: 7px solid black;
	border-top: 0px;
	border-left: 0px;
}

@media screen and (max-width:460px) {
	h3 {
		display: flex;
		font-size: 25px;
	}
}

@media screen and (max-width:380px) {
	h3 {
		display: flex;
		font-size: 20px;
	}
}

@media screen and (max-width:322px) {
	h3 {
		display: flex;
		font-size: 11px;
	}
}

@media screen and (max-width:460px) {
	#columns .widget .widget-head {
		height: 30px;
	}
}

@media screen and (max-width:380px) {
	#columns .widget .widget-head {
		height: 25px;
	}
}

@media screen and (max-width:322px) {
	#columns .widget .widget-head {
		height: 15px;
	}
}

@media screen and (max-width:1400px) {
	.text-list {
		text-align: center;
		font-size: 22px;
	}
}

@media screen and (max-width:1200px) {
	.text-list {
		text-align: center;
		font-size: 17px;
	}
}

@media screen and (max-width:992px) {
	.text-list {
		text-align: center;
		font-size: 15px;
	}
}

@media screen and (max-width:900px) {
	.text-list {
		text-align: center;
		font-size: 12px;
	}
}

@media screen and (max-width:770px) {
	.text-list {
		text-align: center;
		font-size: 10px;
	}
}

@media screen and (max-width:770px) {
	.text-list {
		text-align: center;
		font-size: 9px;
	}
}

@media screen and (max-width:480px) {
	.text-list {
		text-align: center;
		font-size: 9px;
	}
}

@media screen and (max-width:480px) {
	div.rgyPostIt {
		padding: 20px 5px 20px 0px;
	}
}

@media screen and (max-width:460px) {
	#columns .widget {
		margin: 20px 10px 0 10px;
	}
}
</style>

</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top"
		id="sideNav"> <a class="navbar-brand js-scroll-trigger"
		href="./"> <span class="d-block d-lg-none"><img
			src="<c:url value='/resources/img2/logo_jv_white.png'/>" alt="ssh"
			width="70" height="40"></span> <span class="d-none d-lg-block"><img
			class="img-fluid img-profile rounded-circle mx-auto mb-2"
			src="${pageContext.request.contextPath}/resources/img2/lionass.gif"
			alt="" /></span>
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav">

			<li class="nav-item"><c:if test="${member == null }">
					<input type="button" id="join"
						style="font-size: 1.05em; font-weight: bolder"
						class="btn btn-primary" value="회원가입"
						onclick="location.href='./memberJoinForm.do'" target="_blank" />
				</c:if></li>

			<li class="nav-item"><c:if test="${member == null }">
					<input type="button" id="log"
						style="font-size: 1.05em; font-weight: bolder"
						class="btn btn-primary" value="로그인"
						onclick="location.href='./login_form.do'" />
				</c:if> <c:if test="${member != null }">
					<form action="./logout.do" method="POST">
						<button type="submit"
							style="font-size: 1.05em; font-weight: bolder"
							class="btn btn-primary">로그아웃</button>
						<br>
						<!-- Button trigger modal -->
						<button type="button"
							style="font-size: 1.05em; font-weight: bolder"
							class="btn btn-primary" data-toggle="modal"
							data-target="#myModal">포트스캔</button>
						<br>
						<button type="button"
							style="font-size: 1.05em; font-weight: bolder"
							class="btn btn-primary" data-toggle="modal"
							data-target="#myModal1">SSH서버</button>
					</form>


				</c:if></li>
		</ul>
	</div>
	</nav>

	<!-- Modal-포트스캔 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">포트 스캔</h4>
				</div>
				<form action="./portScan.do" method="POST" id="next">
					<div class="modal-body text-center">
						<div data-toggle="tooltip" data-placement="left"
							title="검색할 아이피를 입력하세요.">아이피</div> <textarea class="textarea_test" name="search_ssh_command" id="search_ssh_command" onkeyup="this.style.height='26px'; this.style.height = this.scrollHeight + 'px';"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
						<button type="submit" class="btn btn-primary">포트스캔 실행</button>
					</div>
				</form>
			</div>
		</div>

		<!--로딩바-->
		<div id="loading">
			<img src="<c:url value='/resources/images/viewLoading.gif'/>"
				alt="loading">
		</div>
	</div>



	<!-- Modal-SSHcmd -->
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">SSH서버 명령 실행</h4>
				</div>
				<form action="./ssh_service_command.do" method="POST" id="next1">
					<div class="modal-body text-center">
						<div data-toggle="tooltip" data-placement="left"
							title="SSH 서버에 명령할 명령어를 입력하세요.">명령어</div><textarea class="textarea_test" name="search_ssh_command" id="search_ssh_command" onkeyup="this.style.height='26px'; this.style.height = this.scrollHeight + 'px';"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
						<button type="submit" class="btn btn-primary">SSH서버 명령 실행</button>
					</div>
				</form>
			</div>
		</div>

		<!--로딩바-->
		<div id="loading1"
			style="width: 100%; height: 100%; top: 0; left: 0; position: fixed; display: block; opacity: 0.8; background: white; z-index: 99; text-align: center;">
			<img style="position: absolute; top: 50%; left: 50%; z-index: 100;"
				src="<c:url value='/resources/images/viewLoading.gif'/>"
				alt="loading">
		</div>
	</div>


	<div class="mainContainer">
		<div class="clipboard">
			<div class="clip">
				<div
					style="position: relative; width: 80px; height: 10px; background-color: #b9b9b9; top: -10px; left: 50%; transform: translateX(-50%)"></div>
				<div id="clip1"></div>
				<div id="clip2"></div>
			</div>
			<div class="main">
				<div id="columns">
					<ul id="column2" class="column">
						<li class="widget color-blue">
							<div class="widget-head">
								<h3>SSH 명령 처리 결과</h3>
							</div>
							<div class="rgyPostIt">

								<c:forEach items="${result_cmd}" var="list">

									<span class="text-list">${list}</span>
									<br />

								</c:forEach>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="logo_btn">
				<hr>
				<button class="btn1" onclick="history.go(-1)">
					ⓒ<i class="icon-logo_jv">&nbsp;&nbsp;&nbsp;Corp</i>
				</button>
			</div>
		</div>
	</div>
</body>
</html>