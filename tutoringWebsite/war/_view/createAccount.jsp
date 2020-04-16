<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Create Account</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css"> 
	</head>

	<body>
        <c:if test="${! empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </c:if>
	    <form action="${pageContext.servletContext.contextPath}/index" method="post">
            <div id = "titleDiv">
                <input id = "title" name="index" type="submit" value="Kinsley Tutoring Service" /><br>			
            </div>  
            <div class = "navBar">
                <input id="navBarItem" name="courses" type="submit" value="Courses" />
                <input id="navBarItem" name="groups" type="submit" value="Study Groups" />
                <input id="navBarItem" name="schedule" type="submit" value="Schedule" />
                <input id="navBarItem" name="tutors" type="submit" value="Tutors" />
                <input id="navBarItem" name="resources" type="submit" value="Resources" />
                <input id="navBarItem" name="profile" type="submit" value="Profile" />
                <input id="navBarItem" name="login" type="submit" value="Login" />
                <input id="navBarItem" name="createAccount" type="submit" value="Create Account" />
            </div>
            <div class = "pageDesc">
	           <p>Create an Account</p>
            </div>
        </form>
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