<%@page import="like.controller.LikeServiceServlet"%>
<%@page import="user.controller.ServiceServlet"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>hello world</h1>

<%
Context context = new InitialContext();

String str = (String) context.lookup("java:comp/env/daymusicDB");
%>
<h1><%=str %></h1>

</body>
</html>