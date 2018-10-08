<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<html>
  <head>
    <title>네이버로그인</title>
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
  </head>
  <body>
  	<h1>gdgd</h1>
  </body>
 <script type="text/javascript">
		$.ajax({
			url : "naverlogin1?code=${code}&state=${state}",
			dataType : "json",
			success : function(data) {
				if(data.message == 'success'){
				console.log(data.response.id);
				console.log(data.response.email);
				}
			}
		});
	 
 </script>
</html>