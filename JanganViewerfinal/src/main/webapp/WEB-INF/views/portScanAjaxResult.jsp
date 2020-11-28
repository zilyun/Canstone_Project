<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="${path}/resources/js/port_ajax.js"></script>
<%-- <link href="${path}/resources/css/portResultTable.css" rel="stylesheet" /> --%>
<link href="${path}/resources/css/loadingbar2.css" rel="stylesheet" />
<%-- <link rel="stylesheet" media="screen and (max-width:767px)" href="${path}/resources/css/portResultTable.css" /> --%>
<link rel="stylesheet"
	href=".${path}/resources/css/fontello-embedded.css">
<link rel="stylesheet" href=".${path}/resources/css/fontello-codes.css">

<style>
/* Google fonts - Open Sans */
@import url(https://fonts.googleapis.com/css?family=Open+Sans:400,700);

/* body */
body {
	background-color: #2c7ac9;
  	font-family: 'Open Sans', sans-serif;
  	display: flex;
}

/* links */
.nfl a, .mlb a, .nhl a, .pga a {text-decoration:none;transition: color 0.2s ease-out;}
.nfl a {color:rgb(0, 128, 64);}
.nfl a:hover {color:darken(rgb(0, 128, 0), 20%);}
.mlb a {color:rgb(128, 0, 128);}
.mlb a:hover {color:darken(rgb(128, 0, 255), 20%);}
.nhl a {color:rgba(231,196,104,0.7);}
.nhl a:hover {color:darken(rgba(231,196,104,0.7), 20%);}
.pga a {color:rgba(235,118,85,1);}
.pga a:hover {color:darken(rgba(235,118,85,1), 20%);}

/* wrapper */
.wrapper {
  width:100%;
  max-width:1200px;
  margin:20px auto 20px auto;
  padding:0;
  background:rgba(255,255,255,0.1);
  color:rgba(255,255,255,0.9);
  overflow:hidden;
  position:relative;
  text-align: center;	
}
.container {
	box-shadow: 0px 0px 2px #000;
	border-radius: 10px;
	top:50px;
	width:95%;
	left: 0;
	right: 0;
	text-align: center;
	margin-left: auto;
	margin-right: auto;
	background-color: #fff;
	position: absolute;
}

/* lists */
.row ul {
  margin:0;
  padding:0;
}
.row ul li  {
  margin:0;
  font-size:16px;
  font-weight:normal;

  list-style:none;
  display:inline-block;
  width:20%;
	box-sizing:border-box;
}

@media screen and (max-width: 767px) and (min-width: 480px) {
	.row ul li  {
    	font-size:10px;
    }
}
 @media screen and (max-width: 479px) {
  	.row ul li  {
    	font-size:15px;
    }
}


.title ul li {
  padding:15px 13px;
}
.row ul li {
  padding:5px 10px;
}



/* rows */
.row {
  padding:20px 0;
  height:30px;
  font-size:0;
  position:relative;
  overflow:hidden;
  transition:all 0.2s ease-out;
  background-color:#62abf3;
  border-top:1px solid darken(rgba(255,255,255,0.1), 100%);
  
}

@media only screen and (max-width: 767px) {
	.row {
    	font-size:15px;
    }
}
  
.row:hover {
  background-color:#7ab7f5;
  height:45px;
 
}

@media screen and (max-width: 600px) {
 	.row:hover {
    	height:85px;
    }
}
@media screen and (max-width: 359px) {
    .row:hover {
    	height:105px;
    }
}
  
.title {
  padding:25px 0 5px 0;
  height:45px;
  font-size:0;
  background-color:#3391f0;
  border-left:3px solid lighten(rgba(255,255,255,0.1), 100%);
  @media screen and (max-width: 767px) {
    font-size:12px;
  }
}
.title:hover {
  height:45px;
  background-color:#4b9ef2;
  border-left:3px solid lighten(rgba(255,255,255,0.1), 100%);
}

.title-hide {
  @media screen and (max-width: 767px) {
    display:none;
  }
}

.nfl {border-left:3px solid darken(rgb(0, 128, 64), 30%);}
.nfl:hover {border-left:3px solid rgb(0, 128, 0);}
.mlb {border-left:3px solid darken(rgb(128, 0, 128), 30%);}
.mlb:hover {border-left:3px solid rgb(128, 0, 255);}
.nhl {border-left:3px solid darken(rgba(231,196,104,0.7), 30%)}
.nhl:hover {border-left:3px solid rgba(231,196,104,0.7);}
.pga {border-left:3px solid darken(rgba(235,118,85,1), 30%);}
.pga:hover {border-left:3px solid rgba(235,118,85,1);}

/* row one - fadeIn */
.row-fadeIn-wrapper {
  opacity:0;
  font-size:0;
  height:0;
  overflow:hidden;
  position:relative;
  transition:all 0.2s ease-out;
  animation:fadeIn 1s ease-out 0.2s 1 alternate;
  animation-fill-mode:forwards;
}
.row-fadeIn-wrapper:hover {
  height:80px;
  @media screen and (max-width: 767px) {
    height:110px;
  }
  @media screen and (max-width: 359px) {
    height:140px;
  }
}

.fadeIn {
  padding:20px 0;
  font-size:0;
  position:absolute;
  transition:all 0.2s ease-out;
  width:100%;
}
.fadeIn:hover {
  background-color:lighten(rgba(0,0,0,0.9), 10%);
}

/* row two - fadeOut */
.row-fadeOut-wrapper {
  font-size:0;
  overflow:hidden;
  position:relative;
  transition:all 0.2s ease-out;
  animation:fadeOut 0.4s ease-out 8s 1 alternate;
  animation-fill-mode:forwards;
  opacity:1;
  height:100px;
}
.row-fadeOut-wrapper:hover {
  height:100px;
}

/* update content */
.update-row {
  animation:update 0.5s ease-out 12s 1 alternate;
}
.update1 {
  position:absolute;
  top:25px;
  display:inline-block;
  opacity:1;
  animation:update1 1s ease-out 12s 1 alternate;
  animation-fill-mode:forwards;
}
.update2 {
  position:absolute;
  top:25px;
  display:inline-block;
  opacity:0;
  animation:update2 4s ease-out 13s 1 alternate;
  animation-fill-mode:forwards;
}

/* more content */
ul.more-content li { /* 내부 내용 */
  position:relative;
  top:22px;
  font-size:15px; /*  */
  margin:0;
  padding:10px 13px;
  display:block;
  height:70px;
  width:100%;
  color:darken(rgba(255,255,255,0.9),50%);
  
}

@media screen and (max-width: 767px) {
	ul.more-content li { /* 내부 내용 */
    	font-size:10px;
    }
}


/* small content */
.small {
  color:darken(rgba(255,255,255,0.9),60%);
  font-size:10px;
  padding:0 10px;
  margin:0;
  @media screen and (max-width: 767px) {
    display:none;
  }
}

/* keyframe animations */
@keyframes fadeIn {
  from {background:rgba(255,255,255,0.1);opacity:0;padding:0;}
  to {background:darken(rgba(255,255,255,0.1),80%);opacity:1;padding:0 0 30px 0;} /*처음 보여지는 곳 */
}
@keyframes fadeOut {
  from {background:darken(rgba(255,255,255,0.1),80%);opacity:1;height:65px;}
  to {background:rgba(255,255,255,0.1);opacity:0;height:0;}
}
@keyframes update {
  0% {background:darken(rgba(255,255,255,0.1),80%);}
  50% {background:rgba(255,255,255,0.1);}
  100% {background:darken(rgba(255,255,255,0.1),80%);}
}
@keyframes update1 {
  0% {opacity:0;}
  0% {opacity:1;}
  100% {opacity:0;}
}
@keyframes update2 {
  0% {opacity:0;color:rgba(255,255,255,0.9);}
  20% {opacity:1;color:rgba(82,210,154,1);}
  100% {opacity:1;color:rgba(255,255,255,0.9);}
}
</style>
<script>
	$(document).ready(function() {

		setInterval(function() {
			if ($(".test2").css("display") == "none") {
				$(".test2").fadeIn(500);
				$(".test1").fadeOut(500);
			} else {
				$(".test2").fadeOut(500);
				$(".test1").fadeIn(500);
			}
		}, 20000); // 원래는 7천

	});
</script>
</head>
<body>
	<div class="container">
		<h1>스캔 결과</h1>

		<div id="target"></div>

		<div class="loading-container">
			<div class="loading"></div>
			<div id="loading-text" class='test1' style='display: none;'>loading</div>
			<div id="loading-text" class='test2'>0% 진행 중</div>
			<input type='hidden' value='0' id='compare' />
		</div>
			<hr>
			<button class="btn1" onclick="history.go(-1)">
				ⓒ<i class="icon-logo_jv">&nbsp;&nbsp;&nbsp;Corp</i>
			</button>
	</div>



	<c:forEach items="${result}" var="list_ip">
		<p class="list_ip">${list_ip}</p>
	</c:forEach>
</body>
</html>