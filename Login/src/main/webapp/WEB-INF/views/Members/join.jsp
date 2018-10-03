<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ㅇㅅㅇ</title>
</head>
<body>
	<h1>회원가입 페이지</h1>
	<form action="${pageContext.request.contextPath }/insertMember" method="post">
	이메일 : <input type="email" name="email" /><br>
	아이디 : <input type="text" name="id"/><br>
	비밀번호 : <input type="password" name="pwd" /><br>
	<input type="submit" value="가입" />
	</form>
</body>
</html>