<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>        

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>




<table>
<tr>
<td><a href="create">Add New Company</a> |</td>
<td><a href="createProd">Add New Products</a> |</td>
<td><a href="gotoEdit">Edit</a> |</td>
<td><a href="gotoDelete">Delete</a></td>
</tr>
</table>
<br/>

<table>
<thead>
<tr>
<th>Company ID</th>
<th>Company Name</th>
<th>Company Products</th>
</tr>
</thead>

<c:forEach var="s" items="${list}">
<tbody>
<tr>
<td>${s.cid}</td>
<td>${s.cname}</td>
<td>${s.products}</td>

</tr>
</tbody>

</c:forEach>

</table>

</body>
</html>