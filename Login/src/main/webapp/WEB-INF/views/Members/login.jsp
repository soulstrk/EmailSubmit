<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인 페이지</h1>
	<form action="${pageContext.request.contextPath}/login2" method="post">
	아이디 : <input type="text" name="id" /><br>
	비밀번호 : <input type="password" name="pwd" /><br>
	<input type="checkbox" name="auto" value="in" /><br>
	<input type="submit" value="로그인" />
	</form>
</body>
</html>