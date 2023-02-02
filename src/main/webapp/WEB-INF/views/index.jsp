<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.header{
	width:100%;
	overflow: hidden;
	background-color:#333;
	padding: 10px 0px;
	color:white;
	}
.submit input{

float:right;
height:35px;
width:100px;
background-color: green;
color: white;
border: 3px solid white;
border-radius: 10px;
font: bold15px arial,sans-serif;
font-size:20px;
margin-top:15px;
margin-right:550px;
}
.submit input:hover {
	background:#3CB043;
	color: #fff;
	border: 1px solid #eee;
	border-radius: 10px;
	box-shadow: 3px 3px 3px white;
	text-shadow: none;
}
.msg h3{
   float:right;
   margin-top:15px;
margin-right:440px;
color:red;
   
}
</style>
</head>
<body>

  <div class="header">
  <h1 align="center">Employee Management</h1>
  </div>
  
<h2 align=center >
User Log in <br><br>
<form action="login" method="post">
Username: <input type="text" name="uname"><br>
Password:<input type="password" name="password"><br>
<div class="submit">
<input type="submit" value="Log in">
</div>
</form>
</h2>
<div class="msg">
<c:set var="msg" value="${userMsg}"/>
<c:if test="${msg!=null}">
<h3 align="center">${msg}</h3><br>
</c:if>
</div>
</body>
</html>