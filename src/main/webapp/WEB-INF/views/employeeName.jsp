<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
h3{
margin-top:15px;
margin-bottom:0px;
color:red;
}
input{
margin-top:20px;
height:20px;
width:200px;
}
input:hover{
border-color:#03C04A;
border-radius: 5px;
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

}
.submit input:hover {
	background:#3CB043;
	color: #fff;
	border: 1px solid #eee;
	border-radius: 10px;
	box-shadow: 3px 3px 3px white;
	text-shadow: none;
}
a:hover{
color:blue;
}
</style>
</head>
<body>

<form align="center" action="getEmps" id="form1">
<input type="hidden" name="action" value="${actionEditDelete}">
<table border="1" align="center" cellpadding="5"
      style="font-size: 150%; font-family: inherit; font-style: normal; background-color: window;">
<tr>
<td>Enter Employee Name</td>
<td><input type="text" name="firstName" pattern="[a-zA-Z\s]*" minlength="3"  oninvalid="setCustomValidity('please enter alphabets and space only')" oninput="setCustomValidity('')" required ></td>
</tr>
 <tr>
<td><td>
<div class="submit">
 <input type="submit">
 </div>
 </td>
</td>
<!-- <td><input type="submit"></td> -->
</tr>

</table>
</form>

 <c:set var="msg" value="${msg}"/>
<c:if test="${msg!=null}">
<h3 align="center">${msg}</h3><br>
<h2 align="center">
<a href="new">Do you want to create new employee </a><br>
</h2>
</c:if>
<h2 align="center">
      <a href="home">Get back to home page</a><br>
</h2>

</body>
</html>