<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.textarea_test {
	margin-top: 10px;
	resize: none;
	line-height: 30px;
	width: 60%;
	margin-left: auto;
	margin-right: auto;
	overflow-y: hidden;
	height: 30px;
	border: 1px solid #E0E0E0;
	outline: none;
}

.textarea_test:focus {
	border-color: dodgerBlue;
	box-shadow: 0 0 8px 0 dodgerBlue;
}

<
style>.btn {
	text-decoration: none;
	font-size: 2rem;
	color: white;
	padding: 10px 20px 10px 20px;
	margin: 20px;
	display: inline-block;
	border-radius: 10px;
	transition: all 0.1s;
	text-shadow: 0px -2px rgba(0, 0, 0, 0.44);
	font-family: 'Lobster', cursive;
	<!--
	google
	font
	-->
}

.btn:active {
	transform: translateY(3px);
}

.btn.blue {
	background-color: #1f75d9;
	border-bottom: 5px solid #3c83ea;
}

.btn.blue:active {
	border-bottom: 2px solid #3c83ea;
}

h6 {
	text-align: center;
}

h5 {
	color: red;
}

input[type=text] {
	margin-left: 20px;
	width: 50%;
	border: 0.5px solid #E0E0E0;
	border-radius: 4px;
	margin: 8px 0;
	outline: none;
	padding: 8px;
	transition: .5s;
}

input[type=text]:focus {
	border-color: dodgerBlue;
	box-shadow: 0 0 8px 0 dodgerBlue;
}
</style>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Main Page</title>
<link rel="icon" type="image"
	href="${pageContext.request.contextPath}/resources/assets/img/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Saira+Extra+Condensed:500,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Muli:400,400i,800,800i"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link
	href="${pageContext.request.contextPath}/resources/css/styles_menu.css"
	rel="stylesheet" type="text/css" />
<!-- 부가적인 테마 -->
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="${path}/resources/css/lodingbar.css" rel="stylesheet" />
<link href="${path}/resources/css/button.css" rel="stylesheet" />
<link href="${path}/resources/css/home_remainder.css" rel="stylesheet" />
<link href="${path}/resources/css/style_main_chat.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:600'
	rel='stylesheet' type='text/css'>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${path}/resources/js/home.js"></script>
<script src="${path}/resources/js/alert.js"></script>



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
					<input type="button" id="log"
						style="font-size: 1.05em; font-weight: bolder"
						class="btn btn-primary" value="로그인"
						onclick="location.href='./login_memberJoin.do'" />
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
							data-target="#myModal1">방화벽설정</button>
						<br>
						<button type="button"
							style="font-size: 1.05em; font-weight: bolder"
							class="btn btn-primary" data-toggle="modal"
							data-target="#myModal2">원격제어</button>
						<br>
						<button type="button"
							style="font-size: 1.05em; font-weight: bolder"
							class="btn btn-primary" onclick="location.href='./room'">문의하기</button>
					</form>


				</c:if></li>
			<hr class="m-0" />
			<li class="nav-item"><a class="nav-link js-scroll-trigger"
				href="#about">개요</a></li>
			<li class="nav-item"><a class="nav-link js-scroll-trigger"
				href="#experience">작업과정</a></li>
			<li class="nav-item"><a class="nav-link js-scroll-trigger"
				href="#education">실행영상</a></li>
			<li class="nav-item"><a class="nav-link js-scroll-trigger"
				href="#skills">사용툴</a></li>


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
				<form action="./portIpList.do" method="POST" id="next">
					<div class="modal-body text-center">
						<div data-toggle="tooltip" data-placement="left"
							title="검색할 아이피를 입력하세요.">아이피</div>
						<input type="text" name="search_ip" id="search_ip"
							placeholder="아이피를 입력하세요." />
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



	<!-- Modal-SSHcmd(방화벽) -->
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">방화벽 설정</h4>
				</div>
				<form action="./ssh_service_command.do" method="POST" id="next1">
					<div class="modal-body text-center">
						<div data-toggle="tooltip" data-placement="left"
							title="SSH 서버에 명령할 명령어를 입력하세요.">명령어</div>
						<textarea class="textarea_test" name="search_ssh_command"
							id="search_ssh_command"
							onkeyup="this.style.height='26px'; this.style.height = this.scrollHeight + 'px';"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
						<button type="submit" class="btn btn-primary">설정하기</button>
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

	<!-- Modal-원격제어 -->
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">메시지 인증</h4>
				</div>
				<div class="modal-body text-center">
					<span data-toggle="tooltip" data-placement="left"
						title="인증을 위한 전화번호를 입력하세요.">전화번호:</span><input type="text"
						name="inputPhoneNumber" id="inputPhoneNumber"
						placeholder="전화번호 입력" />

				</div>
				<h6>원격프로그램을 다운로드--실행 후 인증해주십시오!</h6>
				<div class="modal-footer">
					<a
						href="${pageContext.request.contextPath}/resources/download/ClientRmtCtrlProgram.zip"
						class="btn blue">다운로드</a>

					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="submit" id="sendPhoneNumber"
						onclick="button_click();" class="btn btn-primary">인증하기</button>
				</div>
			</div>
		</div>

		<!--로딩바-->
		<div id="loading2"
			style="width: 100%; height: 100%; top: 0; left: 0; position: fixed; display: block; opacity: 0.8; background: white; z-index: 99; text-align: center; margin-left: 1px;">
			<img style="position: absolute; top: 50%; left: 50%; z-index: 100;"
				src="<c:url value='/resources/images/viewLoading.gif'/>"
				alt="loading">
		</div>
	</div>




	<!-- Page Content-->
	<div class="container-fluid p-0">
		<!-- main-->
		<section class="resume-section" id="main">
		<div class="resume-section-content" style="text-align: center;">
			<h1 class="mb-0">
				JANGAN <span class="text-primary"> VIEWER</span>
			</h1>
		</div>


		</section>


		<hr class="m-0" />

		<!-- about -->
		<section class="resume-section" id="about">
		<div class="resume-section-content">
			<h2 class="mb-5">프로젝트 개요</h2>
			<div class="subheading mb-3">
				실제 보안툴과 유사한 <span class="text-primary2">모의 보안 툴 제작</span>
			</div>

			<ul class="fa-ul mb-0">
				<li><span class="fa-li"><i class="fas fa-check"></i></span> 보안
					툴에 적용할만한 기능 구현</li>
				<li><span class="fa-li"><i class="fas fa-check"></i></span>
					사용자들이 쉽게 사용할 수 있도록 UI 구현</li>
				<li><span class="fa-li"><i class="fas fa-check"></i></span>
					개인PC or 서버의 취약점 확인</li>
				<li><span class="fa-li"><i class="fas fa-check"></i></span> 방화벽
					구축</li>
				<li><span class="fa-li"><i class="fas fa-check"></i></span>
					원격제어 프로그램</li>
			</ul>
		</div>
		</section>
		<hr class="m-0" />



		<!-- Experience-->
		<section class="resume-section" id="experience">
		<div class="resume-section-content">
			<h2 class="mb-5">작업 과정</h2>
			<div
				class="d-flex flex-column flex-md-row justify-content-between mb-5">
				<div class="flex-grow-1">
					<h3 class="mb-0">회원가입 폼 구성</h3>
					<p>네이버 인증을 통한 안정적인 기능을 구현</p>
					<br>
				</div>
				<div class="flex-shrink-0">
					<h4>
						<span class="text-primary"> 10월 초 </span>
					</h4>
				</div>
			</div>
			<div
				class="d-flex flex-column flex-md-row justify-content-between mb-5">
				<div class="flex-grow-1">
					<h3 class="mb-0">포트스캔 구성</h3>
					<p>열려있는 포트를 확인하는 기능을 구현</p>
				</div>
				<div class="flex-shrink-0">
					<h4>
						<span class="text-primary"> 10월 중 </span>
					</h4>
				</div>
			</div>
			<div
				class="d-flex flex-column flex-md-row justify-content-between mb-5">
				<div class="flex-grow-1">
					<h3 class="mb-0">SSH 구성</h3>
					<p>SSH로 접근하기 위한 기능을 구현</p>
				</div>
				<div class="flex-shrink-0">
					<h4>
						<span class="text-primary"> 10월 말 </span>
					</h4>
				</div>
			</div>
			<div class="d-flex flex-column flex-md-row justify-content-between">
				<div class="flex-grow-1">

					<h3 class="mb-0">Iptables 구현 예정</h3>
					<p>iptables를 이용한 방화벽 구성을 예정 중</p>
					<p>명령을 내릴 수 있도록 SSH 세션을 할 수 있는</p>
					<p>자바라이브러리를 구현하고 관리 서버를 구축함.</p>
				</div>
				<div class="flex-shrink-0">
					<h4>
						<span class="text-primary">현재 진행 중</span>
					</h4>
				</div>
			</div>
		</div>
		</section>
		<hr class="m-0" />
		<!-- Education-->
		<section class="resume-section" id="education">
		<div class="resume-section-content">
			<h4 class="mb-5">실행 영상</h4>
			<div
				class="d-flex flex-column flex-md-row justify-content-between mb-5">
				<div class="flex-grow-1">
					<br>
					<h3 class="mb-0">회원 가입</h3>
					<div class="embed-container">
						<iframe src="<c:url value='/resources/video/join_start.mp4'/>"
							allowfullscreen="" height="360" width="640" frameborder="0">
						</iframe>
					</div>
					<br>
					<h3 class="mb-0">SSH 원격 제어</h3>
					<div class="embed-container">
						<iframe src="<c:url value='/resources/video/SSH_start.mp4'/>"
							allowfullscreen="" height="360" width="640" frameborder="0">
						</iframe>
					</div>
					<br>
					<h3 class="mb-0">포트 스캔</h3>
					<div class="embed-container">
						<iframe src="<c:url value='/resources/video/port_start.mp4'/>"
							allowfullscreen="" height="360" width="640" frameborder="0">
						</iframe>
					</div>
				</div>
			</div>
		</div>
		</section>
		<hr class="m-0" />


		<!-- Skills-->
		<section class="resume-section" id="skills">
		<div class="resume-section-content">
			<h2 class="mb-5">사용 툴</h2>

			<ul class="list-inline dev-icons">
				<h3 class="mb-0">Back-end tool</h3>
				<img src="<c:url value='/resources/img2/ssh.png'/>" alt="ssh"
					width="130" height="100" hspace=20>
				<img src="<c:url value='/resources/img2/coolsms.png'/>"
					alt="coolsms" width="130" height="100" hspace=20>
				<img src="<c:url value='/resources/img2/socketio.png'/>"
					alt="socketio" width="130" height="100" hspace=20>
				<img src="<c:url value='/resources/img2/nmap.png'/>" alt="nmap"
					width="130" height="100" hspace=20>
				<img src="<c:url value='/resources/img2/springboot.png'/>"
					alt="springboot" width="130" height="100" hspace=20>
				<img src="<c:url value='/resources/img2/navermail.png'/>"
					alt="navermail" width="130" height="100" hspace=20>
				<img src="<c:url value='/resources/img2/ajax.png'/>" alt="ajax"
					width="130" height="100" hspace=20>
				<img src="<c:url value='/resources/img2/sqldevelop.png'/>"
					alt="sqldevelop" width="130" height="100" hspace=20>

				<br />
				<br />
				<h3 class="mb-0">Front-end tool</h3>
				<img src="<c:url value='/resources/img2/bootstrap.png'/>"
					alt="bootstrap" width="130" height="100" hspace=20>
				<img src="<c:url value='/resources/img2/nodejs.png'/>" alt="nodejs"
					width="130" height="100" hspace=20>
				<img src="<c:url value='/resources/img2/web.png'/>" alt="web"
					width="130" height="100" hspace=20>


			</ul>
		</section>
		<hr class="m-0" />

	</div>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
	<!-- Third party plugin JS-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
	<!-- Core theme JS-->
	<script src="<c:url value="/resources/js/scripts.js" />"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</body>
</html>