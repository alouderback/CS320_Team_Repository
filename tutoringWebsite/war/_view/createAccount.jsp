<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 Create Account</title>
		<style type="text/css">
		.error {
			color: red;
		}
		
		td.label {
			text-align: right;
		}
		</style>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/createAccount" method="post">
			<table>
				<tr>
					<td class="label">User Name:</td>
					<td><input type="text" name="email" size="12" value="${email}" /></td>
				</tr>
				<tr>
					<td class="label">Password:</td>
					<td><input type="text" name="password" size="12" value="${password}" /></td>
				</tr>
				<tr>
					<td class="label">Class Year:</td>
					<td><input type="text" name="year" size="12" value="${year}" /></td>
				</tr>
				<tr>
					<td class="label">Major:</td>
					<td><input type="text" name="major" size="12" value="${major}" /></td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Create Account">
		</form>
	</body>
</html>