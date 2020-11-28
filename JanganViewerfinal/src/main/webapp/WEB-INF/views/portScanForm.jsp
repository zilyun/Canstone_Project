<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<!-- 항상 최신버전의 JQuery를 사용가능하다. -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="${path}/resources/css/lodingbar.css" rel="stylesheet" />
<link href="${path}/resources/css/button.css" rel="stylesheet" />
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!--로딩바-->
<script>
	$(document).ready(function() {
		$('#loading').hide();
		$('#next').submit(function() {
			$('#loading').show();
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
	<div>
		<input type="button" value="회원가입"
			onclick="location.href='./memberJoinForm.do'">
		<c:if test="${member == null }">
			<input type="button" value="로그인"
				onclick="location.href='./login_form.do'">
		</c:if>
	</div>
	<div>
		<c:if test="${member != null }">
			<form action="./logout.do" method="POST">
				<button type="submit">로그아웃</button>
			</form>

			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary btn-lg"
				data-toggle="modal" data-target="#myModal">포트스캔</button>

			<!-- Modal -->
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
								<button type="button" class="btn btn-default"
									data-dismiss="modal">취소</button>
								<button type="submit" class="btn btn-primary">포트스캔 실행</button>
							</div>
						</form>
					</div>
				</div>

				<!--로딩바-->
				<div id="loading">
					<img src="<spring:url value='/resources/images/viewLoading.gif'/>"
						alt="loading">
				</div>
			</div>


		</c:if>
	</div>
</body>
</html>