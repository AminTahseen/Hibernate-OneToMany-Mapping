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
<h3>New Company</h3>
<br/>
<form action="saveCompany">
<table>
<tr>
<td>Company Name</td>
<td><input type="text" name="cname"></td>
<td><input type="submit" value="Add"></td>
</tr>
</table>
</form>

</body>

</html>