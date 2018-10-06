<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ㅇㅅㅇ</title>
</head>
<body>
확인용 : ${sessionScope.login.id }
	<h1>메인페이지</h1>
	<a href="${pageContext.request.contextPath }/join">회원가입</a>
	<a href="${pageContext.request.contextPath }/login">로그인</a>
	<a href="${pageContext.request.contextPath }/logout">로그아웃</a>
	<a href="${pageContext.request.contextPath }/memberPage/boardList">게시판가기</a>
	<a href="${pageContext.request.contextPath }/memberPage/game">게임하러가기</a>
	<a href="${pageContext.request.contextPath }/memberPage/payment">결제하러가기</a>
</body>
</html>