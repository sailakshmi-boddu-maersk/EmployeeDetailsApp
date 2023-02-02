<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
 
.form-input input{
	background: #0066A2;
	color: white;
	border-style: outset;
	border-color: #0066A2;
	border-radius: 5px;
	height: 35px;
	width: 300px;
	font: bold15px arial,sans-serif;
	text-shadow: none;
	margin: 5px 10px 15px 0;
}
.form-input input:hover {
	background: #016ABC;
	color: #fff;
	border: 1px solid #eee;
	border-radius: 5px;
	box-shadow: 3px 3px 3px white;
	text-shadow: none;
}
.options h2{
margin: 50px 10px 15px 0;
}
.header {
 overflow: hidden;
background-color:#333;
padding: 10px 0px;
color:white;
}

.header input{
float:right;
height:35px;
width:100px;
margin-top:0px;
margin-right:300px;
background-color: green;
color: white;
border: 3px solid white;
border-radius: 5px;
font: bold15px arial,sans-serif;
font-size:20px;

}
.header input:hover {
	background:#3CB043;
	color: #fff;
	border: 1px solid #eee;
	border-radius: 10px;
	box-shadow: 3px 3px 3px white;
	text-shadow: none;
}

.content
{
margin-top:0px;
width:100%;
position: fixed;
background:white;
}
.body{
width:100%;
}
</style>
</head>

<body class="options">
<% response.setHeader("Cache-control","no-cache,no-store,must-revalidate");
   response.setHeader("pragma","no-cache");
   response.setHeader("Expires","0");
   if(session.getAttribute("admin")==null || (!(session.getAttribute("admin")).equals("activeState")))
   {
	   response.sendRedirect("/index");
   }
 %>
<div class="body">
  <div class="header">
  <h1 align="center">Employee Management</h1>
  <form action="logout">
  <input type="submit" value="Log out">
  </form>
  </div>
  <div class="content">
    <h2 align="center">
      <form class="form-input" action="new">
      <input style="font-size:20px" type="submit" value="Add New Employee">
      </form>
      <form class="form-input" action="list">
      <input style="font-size:20px" type="submit" value="List Employees">
      </form>
      <form class="form-input" action="empRec">
      <input type="hidden" name="action" value="edit">
      <input style="font-size:20px" type="submit" value="Edit Employee">
      </form>
      <form class="form-input" action="empRec">
      <input type="hidden" name="action" value="delete">
      <input style="font-size:20px" type="submit" value="Delete Employee">
      </form>
    </h2>
    </div>
  </div>
</body>
</html>