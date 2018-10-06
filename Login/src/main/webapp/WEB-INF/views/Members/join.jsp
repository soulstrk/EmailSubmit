<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<title>ㅇㅅㅇ</title>
</head>
<body>
	<h1>회원가입 페이지</h1>
	<form action="${pageContext.request.contextPath}/insertMember" name="frm" method="post">
	이메일 : <input type="email" name="email" /><br>
	아이디 : <input type="text" name="id"/><br>
	비밀번호 : <input type="password" name="pwd" /><br>
	<input type="submit" value="가입" />
	</form>
</body>

<script type="text/javascript">
	
</script>
</html>