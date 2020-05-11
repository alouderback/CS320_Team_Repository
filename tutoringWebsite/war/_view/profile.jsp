<!DOCTYPE html>

<html>
	<head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css" >
        <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&amp;display=swap" rel="stylesheet">
        <Title>Profile</Title>
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
            
	           Profile
	        </div>
	       <body>
	          
							<p class = "profile"> Email: 	<td>${user.email}</td></p>	           
			            	<p.profile> Password: <td>${user.password}</td></p>
			            	<p.profile> Name: <td>${user.name}</td></p>
			    
	          
          
            	<c:if test="${isAStudent}">
        					<p.profile> Major:  <td>${student.major}</td></p>
			            	<p.profile> Year:  <td>${student.year}</td></p>
				</c:if>
			</body>
        </form>
        <form action="${pageContext.servletContext.contextPath}/profile" method="post">
			<input class="myButton" type="Submit" name="deleteAccount" value="Delete Account">
			<input class="myButton" type="Submit" name="logOut" value="Log Out">
		</form>
    </body>
</html>|