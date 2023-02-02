<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@page import="com.slb.model.Employee"%> --%>
<!DOCTYPE html>
<html>
<head>
<style>
input{
height:20px;
width:200px;
}
input:hover{
border-color:#03C04A;
border-radius: 5px;
}
.submit input:hover {
	background:#3CB043;
	color: #fff;
	border: 1px solid #eee;
	border-radius: 10px;
	box-shadow: 3px 3px 3px white;
	text-shadow: none;
}
.submit input{
float:right;
height:35px;
width:100px;
margin-top:10px;
margin-right:550px;
background-color: green;
color: white;
border: 3px solid white;
border-radius: 10px;
font: bold 15px arial,sans-serif;
font-size:20px;

}
.msg h3{
float:right;
margin-top:10px;
margin-right:450px;
color:red;
}

</style>
</head>
<body>
<% response.setHeader("Cache-control","no-cache,no-store,must-revalidate");
   response.setHeader("pragma","no-cache");
   response.setHeader("Expires","0");
   if(session.getAttribute("admin")==null || (!(session.getAttribute("admin")).equals("activeState")))
   {
	   response.sendRedirect("/index");
   }
 %>
<h2 align="center">Employee Form</h2>
<c:if test="${emp!= null}">
  <!--  <h2 align="center">Edit Employee Form</h2> -->
	<form align="center" action="update">
	<input type="hidden" name="id" value="${emp.id}">
</c:if>
<c:if test="${emp == null}">
    <!-- <h2 align="center"> New Employee Form</h2> -->
	<form align="center" action="insert">
	<input type="hidden" name="id" value="0">
</c:if>
<%-- <input type="hidden" name="id" value="${emp.id}"> --%>
 <table border="1" align="center" cellpadding="5"
      style="font-size: 150%; font-family: inherit; font-style: normal; background-color: window;"> 
 <tr>
 <td>Enter First Name</td>
 <td><input type="text" name="firstName" value="${emp.firstName}" pattern="[a-zA-Z\s]*" minlength="3" oninvalid="setCustomValidity('please enter alphabets and space only')" oninput="setCustomValidity('')"required ></td>
 </tr>
 <tr>
 <td>Enter Last Name</td>
 <td><input type="text" name="lastName" value="${emp.lastName}" pattern="[a-zA-Z\s]*" minlength="3" oninvalid="setCustomValidity('please enter alphabets and space only')" oninput="setCustomValidity('')"required ></td>
 </tr>
 <tr>
 <td>Enter Salary</td>
 <td><input type="number" name="salary" value="${emp.salary}" min="0" step="0.1" required></td>
 </tr>
 <tr>
 <td>Enter Address Id</td>
 <td><input type="number" name="addressId" value="${emp.addressId}" min="0" step="1"	 required></td>
 </tr>
 <tr>
 <td>Enter Address</td>
 <td><input type="text" name="address" value="${emp.address}" pattern="[a-zA-Z\s]*" oninvalid="setCustomValidity('please enter alphabets and space only')" oninput="setCustomValidity('')" minlength="3" required ></td>
 </tr>
 </table>
 <div class="submit">
 <input type="submit">
 </div>			
 </form> 
 <div align="center" class="msg">
 <c:set var="msg" value="${msg}"/>
<c:if test="${msg!=null}">
<h3 align="center">${msg}</h3>
</c:if>
</div>
</body>
</html>