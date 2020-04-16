<!DOCTYPE html>

<html>
	<head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css" >
        <Title>Home</Title>
    </head>
    
    <body>
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
	        <div id = "titleDiv">
	            <input id = "title" name="index" type="submit" value="Kinsley Tutoring Service" />
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
	            <p>Welcome to the Kinsley Tutoring Service website! This site is used to display information and announcements for Engineeing and Computer Science tutoring.</p>
	        </div>
	        
	        <div class = "announcements">
	            <p id = "annTitle">--- Announcements ---</p>
	        </div>
	      </form>
    </body>
</html>