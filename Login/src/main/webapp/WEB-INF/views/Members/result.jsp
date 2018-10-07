<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${msg == 'fail'}">
		<h1>가입 실패 다시시도해주세요</h1>
		<a href="<c:url value='/join'/>">돌아가기</a>
	</c:if>
	<c:if test="${msg == 'success'}">
		<h1>가입성공 !!</h1>
		<h3>이메일 인증후 로그인 가능합니다.</h3>
		<a href="<c:url value='/login'/>">로그인 페이지</a>
	</c:if>
</body>
</html>