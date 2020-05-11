<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>

		<title>Create Account</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css"> 
	    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&amp;display=swap" rel="stylesheet">
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
            <div id = "subTitle">
	           Create an Account
            </div>
        </form>

		<form action="${pageContext.servletContext.contextPath}/createAccount" method="post">
			<table>
				<tr>
					<td class="label">Email:</td>
					<td><input type="text" name="email" size="12" value="${email}" /></td>
				</tr>
				<tr>
					<td class="label">Password:</td>
					<td><input type="text" name="password" size="12" value="${password}" /></td>
				</tr>
				<tr>
					<td class="label">Name:</td>
					<td><input type="text" name="name" size="12" value="${name}" /></td>
				</tr>
				<tr>
					
  					<input type="radio" id="student" name="userType" value="student">
  					<label for="student">Student</label><br>
  					
					<input type="radio" id="tutor" name="userType" value="tutor">
					<label for="tutor">Tutor</label><br>
			
  				</tr>
  						
		
            	<tr>
					<td class="label">Major:</td>
					<td><input type="text" name="major" size="12" value="${major}" /></td>
				</tr>
				<tr>
					<td class="label">Year:</td>
					<td><input type="text" name="year" size="12" value="${year}" /></td>
				</tr>
				<tr>
				</table>
			<input class="myButton" type="Submit" name="submit" value="Create Account">
		</form>
	</body>
</html>