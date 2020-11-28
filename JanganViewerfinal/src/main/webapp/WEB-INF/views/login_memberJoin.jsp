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
					<h2 class="title">�α���</h2>
					<div class="input-field">
						<i class="fas fa-user"></i> <input type="text"
							placeholder="Username" id="id1" name="id" required />
					</div>
					<div class="input-field">
						<i class="fas fa-lock"></i> <input type="password"
							placeholder="Password" id="pw1" name="pw" required />
					</div>
					<p class="social-text"></p>
					<input type="submit" value="�α���" class="btn solid" /> <input
						type="button" id="find_id_btn" class="btn" value="���̵� ã��" />
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
					<h2 class="title">ȸ������</h2>
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
					<input type="submit" class="btn" id="joinBtn" value="ȸ������" /><!-- <input
						type="button" onclick="history.go(-1);" class="btn" value="���" /> -->
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
					<h3>ó���̽Ű���?</h3>
					<p>ó���̽ö�� ȸ�������� �����ϼž� �մϴ�. ȸ�������� �Ͻð� �ȴٸ� �پ��� ���񽺵��� ���� �� �ֽ��ϴ�.</p>
					<button class="btn transparent" id="sign-up-btn">ȸ������</button>
				</div>
				<img src="img/log.svg" class="image" alt="" />
			</div>
			<div class="panel right-panel">
				<div class="content">
					<h3>�̹� ȸ���̽Ű���?</h3>
					<p>ȸ���̽ö�� �α����� �������ּ���. �α����� �Ͻð� �ȴٸ� �پ��� ���񽺵��� ���� �� �ֽ��ϴ�.</p>
					<button class="btn transparent" id="sign-in-btn">�α���</button>
				</div>
				<img src="<c:url value="/resources/img2/jangan_jv.png" />" class="image"
					alt="" />
			</div>
		</div>
	</div>

	<script src="<c:url value="/resources/js/app_login.js" />"></script>
</body>
</html>
