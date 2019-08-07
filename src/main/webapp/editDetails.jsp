<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>        

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Edit Company</h1>
<form action="findCompany">
<table>
<tr>
<td><input type="text" name="cname" placeholder="Company Name"></td>
<td><input type="submit" value="find"></td>
</tr>
</table>
</form>

<form action="EditCompany">
<table>
<tr>
<td>Company ID</td>
<td><input type="text" name="cid" value="${cid}" readonly="readonly"></td>
</tr>
<tr>
<td>Company Name</td>
<td><input type="text" name="compname" value="${cnam}"></td>
</tr>
<tr>
<td><input type="submit" value="Edit"></td>
</tr>
</table>
</form>

<h1>Edit Product</h1>
<form action="findProduct">
<table>
<tr>
<td><input type="text" name="pname" placeholder="Product Name"></td>
<td><input type="submit" value="find"></td>
</tr>
</table>
</form>


<form action="EditProduct">

<table>
<tr>
<td>Product ID</td>
<td><input type="text" name="pid" value="${pid}" readonly="readonly"></td>
</tr>

<tr>
<td>Product Name</td>
<td><input type="text" name="pname" value="${pnam}"></td>
</tr>

<tr>
<td>Product Company</td>
<td>
<input type="text" value="${pcomp}" placeholder="Default Company" readonly="readonly"> 
<select name="cname">

<c:forEach var="s" items="${cname}">

<option value="${s}">${s}</option>

</c:forEach>

</select>
</td>
</tr>

<tr>
<td><input type="submit" value="Edit"></td>
<td><a href="/">Go Back</a></td>
</tr>

</table>

</form>
<p>${msg}</p>
</body>
</html>