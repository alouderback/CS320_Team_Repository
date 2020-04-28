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
	           <h1>Profile</h1>
	        </div>
	       <body>
	          
							<p> Email: 	<td>${user.email}</td></p>	           
			            	<p> Password: <td>${user.password}</td>
			            	<p> Name: <td>${user.name}</td>
			            	<p> Type: <td>${user.userType}</td>			            
			    
	          
          
            	<c:if test="${ isStudent}">
        					 <td>Computer Engineering</td>
			            	 <td>2022</td>
				</c:if>
			</body>
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