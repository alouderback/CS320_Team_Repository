<!DOCTYPE html>

<html>
	<head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css" >
        <Title>Profile</Title>
    </head>
    <body>
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
	           <p>Your Profile</p>
	           
	           <tr>
			            	<td>${user.email}</td>
			            	<td>${user.password}</td>
			            	<td>${user.name}</td>
			            	<td>${user.userType}</td>			            
			    </tr>
	           
            </div>
        </form>
        <form action="${pageContext.servletContext.contextPath}/profile" method="post">
			<table>
				<tr>
					<td><input type="hidden" name="email" size="12" value="${user.email}" /></td>
				</tr>
				<tr>
					<td><input type="hidden" name="password" size="12" value="${user.password}" /></td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Delete Account">
		</form>
    </body>
</html>|