<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Blog</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/style.css">

<!-- cdn -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<!-- font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300&family=Jua&display=swap"
	rel="stylesheet">

</head>
<body>

	<!-- 네브바 시작 -->
 	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
 		<a class="navbar-brand" href="/">블로그</a>
 		<button class="navbar-toggler" type="button" data-toggle="collapse"
 			data-target="#collapsibleNavbar">
 			<span class="navbar-toggler-icon"></span>
 		</button>
 		<div class="collapse navbar-collapse" id="collapsibleNavbar">
 		<c:choose>
 		<c:when test="${empty sessionScope.principal}">
 			<ul class="navbar-nav">
 				<li class="nav-item"><a class="nav-link" href="/loginForm">로그인</a></li>
 				<li class="nav-item"><a class="nav-link" href="/joinForm">회원가입</a></li>
 			</ul>
 		</c:when>
 		<c:otherwise>
 			<ul class="navbar-nav">
 				<li class="nav-item"><a class="nav-link" href="/board/saveForm">글쓰기</a></li>
 				<li class="nav-item"><a class="nav-link" href="/user/${sessionScope.principal.id}">회원정보</a></li>
 				<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
 			</ul>
 		</c:otherwise>
 		
 		</c:choose>
 			
 		</div>
 	</nav>
 	<br>
 	<!-- 네브바 끝 --> 