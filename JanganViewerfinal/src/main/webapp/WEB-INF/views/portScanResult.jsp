<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- @@@@@@@@@@@@@@@@@@@@@@@폐기 예정@@@@@@@@@@@@@@@@@@@@@@@@ -->
<!DOCTYPE html>
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
<link href="${path}/resources/css/lodingbar.css" rel="stylesheet" />
<link href="${path}/resources/css/button.css" rel="stylesheet" />
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
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top"
		id="sideNav">
		<a class="navbar-brand js-scroll-trigger" href="#"> <span
			class="d-block d-lg-none">Menu</span> <span class="d-none d-lg-block"><img
				class="img-fluid img-profile rounded-circle mx-auto mb-2"
				src="${pageContext.request.contextPath}/resources/assets/img/zzz.gif"
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
						<span data-toggle="tooltip" data-placement="left"
							title="검색할 아이피를 입력하세요.">아이피:</span> <input type="text"
							name="search_ip" id="search_ip" />
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
						<span data-toggle="tooltip" data-placement="left"
							title="SSH 서버에 명령할 명령어를 입력하세요.">명령어:</span> <input type="text"
							name="search_ssh_command" id="search_ssh_command" />
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



	<!-- Page Content-->
	<div class="container-fluid p-0">
		<!-- About-->
		<div id="collapseExample" class="collapse">
			<div class="well">
				<section class="resume-section" id="about">
					<div class="resume-section-content">
						<div class="subheading mb-5">포트 스캔 결과</div>
						<div>
							<ul>
								<c:forEach items="${result_cmd}" var="list">
									<p class="lead mb-5">${list}</p>
								</c:forEach>
							</ul>
							<ul>
								<c:forEach items="${result}" var="listS">
									<c:forEach items="${listS}" var="map">
										<p class="lead mb-5">${map}</p>
									</c:forEach>
								</c:forEach>
							</ul>
						</div>
					</div>
				</section>
			</div>
		</div>
		<hr class="m-0" />
		<section class="resume-section" id="experience">
			<div class="row text-center" style="width: 100%">
				<div style="width: 30%; float: none; margin: 0 auto">
					<a class="btn btn-primary" data-toggle="collapse"
						href="#collapseExample" aria-expanded="false"
						aria-controls="collapseExample"> OPEN </a>
				</div>
			</div>
		</section>
	</div>
</body>
</html>