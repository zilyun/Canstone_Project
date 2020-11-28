<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="https://kit.fontawesome.com/64d58efce2.js"
	crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<title>Sign in & Sign up Form</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style_login.css" />
<script src="${path}/resources/js/memberjoin.js"></script>

</head>
<body>
	<div class="container">
		<div class="forms-container">
			<div class="signin-signup">
				<form action="./login.do" class="sign-in-form" method="post">
					<h2 class="title">로그인</h2>
					<div class="input-field">
						<i class="fas fa-user"></i> <input type="text"
							placeholder="Username" id="id1" name="id" required />
					</div>
					<div class="input-field">
						<i class="fas fa-lock"></i> <input type="password"
							placeholder="Password" id="pw1" name="pw" required />
					</div>
					<p class="social-text"></p>
					<input type="submit" value="로그인" class="btn solid" /> <input
						type="button" id="find_id_btn" class="btn" value="아이디 찾기" />
					<p class="social-text">Or Sign in with social platforms</p>
					<div class="social-media">
						<a href="#" class="social-icon"> <i class="fab fa-facebook-f"></i>
						</a> <a href="#" class="social-icon"> <i class="fab fa-twitter"></i>
						</a> <a
							href="https://accounts.google.com/ServiceLogin/signinchooser?hl=ko&passive=true&continue=https%3A%2F%2Fwww.google.com%2F%3Fhl%3Dko&ec=GAZAAQ&flowName=GlifWebSignIn&flowEntry=ServiceLogin"
							class="social-icon"> <i class="fab fa-google"></i>
						</a> <a href="#" class="social-icon"> <i
							class="fab fa-linkedin-in"></i>
						</a>
					</div>
				</form>
				<form action="./join_member.do" class="sign-up-form" id="joinForm"	method="post">
					<h2 class="title">회원가입</h2>
					<div class="input-field">
						<i class="fas fa-user"></i> <input type="text"
							placeholder="Username" id="id" name="id" required />
					</div>
					<div class="input-field">
						<i class="fas fa-envelope"></i> <input type="email"
							placeholder="Email" id="email" name="email" required />
					</div>
					
					<div class="input-field">
						<i class="fas fa-lock"></i> <input type="password"
							placeholder="Password" id="pw" name="pw" required />
					</div>
					<div class="input-field">
						<i class="fas fa-lock"></i> <input type="password"
							placeholder="Password-check" id="pw2" name="pw2" required />
					</div>
					<p id="id_email_check"></p>
					<input type="submit" class="btn" id="joinBtn" value="회원가입" /><!-- <input
						type="button" onclick="history.go(-1);" class="btn" value="취소" /> -->
					 <!-- <p class="social-text">Or Sign up with social platforms</p> -->
										<br/><br/>
					<div class="social-media">
						<a href="#" class="social-icon"> <i class="fab fa-facebook-f"></i>
						</a> <a href="#" class="social-icon"> <i class="fab fa-twitter"></i>
						</a> <a
							href="https://accounts.google.com/ServiceLogin/signinchooser?hl=ko&passive=true&continue=https%3A%2F%2Fwww.google.com%2F%3Fhl%3Dko&ec=GAZAAQ&flowName=GlifWebSignIn&flowEntry=ServiceLogin"
							class="social-icon"> <i class="fab fa-google"></i>
						</a> <a href="#" class="social-icon"> <i
							class="fab fa-linkedin-in"></i>
						</a>
					</div>
				</form>
			</div>
		</div>

		<div class="panels-container">
			<div class="panel left-panel">
				<div class="content">
					<h3>처음이신가요?</h3>
					<p>처음이시라면 회원가입을 진행하셔야 합니다. 회원가입을 하시게 된다면 다양한 서비스들을 누릴 수 있습니다.</p>
					<button class="btn transparent" id="sign-up-btn">회원가입</button>
				</div>
				<img src="img/log.svg" class="image" alt="" />
			</div>
			<div class="panel right-panel">
				<div class="content">
					<h3>이미 회원이신가요?</h3>
					<p>회원이시라면 로그인을 진행해주세요. 로그인을 하시게 된다면 다양한 서비스들을 누릴 수 있습니다.</p>
					<button class="btn transparent" id="sign-in-btn">로그인</button>
				</div>
				<img src="<c:url value="/resources/img2/jangan_jv.png" />" class="image"
					alt="" />
			</div>
		</div>
	</div>

	<script src="<c:url value="/resources/js/app_login.js" />"></script>
</body>
</html>
