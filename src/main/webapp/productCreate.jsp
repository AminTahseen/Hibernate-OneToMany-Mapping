<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>New Products</h3>
<br/>
<form action="saveProducts">

<table>

<tr>
<td>Product Name</td>
<td><input type="text" name="pname"></td>
</tr>

<tr>
<td>Product Company</td>
<td>
<select name="cname">

<c:forEach var="s" items="${cname}">

<option value="${s}">${s}</option>

</c:forEach>

</select>
</td>
</tr>

<tr>
<td><input type="submit" value="Add New"></td>
<td><a href="/">Go Back</a></td>
</tr>

</table>

</form>
<p>${msg}</p>

</body>
</html>