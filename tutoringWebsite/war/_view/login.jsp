<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 Library Login</title>
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
	
		<form action="${pageContext.servletContext.contextPath}/login" method="post">
			<table>
				<tr>
					<td class="label">User Name:</td>
					<td><input type="text" name="email" size="12" value="${email}" /></td>
				</tr>
				<tr>
					<td class="label">Password:</td>
					<td><input type="text" name="password" size="12" value="${password}" /></td>
				</tr>
			</table>
     			<input type="Submit" name="submit" value="Login">
     			<input type="hidden" onclick="window.location.href='http://localhost:8081/tutoringWebsite/createAccount'"value =  "Create Account">
     			
     		
		<c:if test="${ validUser}">
            <input type="button" onclick="window.location.href='http://localhost:8081/tutoringWebsite/createAccount'"value =  "Create Account">
		</c:if>
        </form>
		
	</body>
</html>