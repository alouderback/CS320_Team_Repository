
<!DOCTYPE html>


<html>
	<head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css" >
        <Title>Courses</Title>
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
	           <p>Courses</p>
            </div>
        </form>
        <form action="${pageContext.servletContext.contextPath}/courses" method="post">
	            <table>
	                <tr>
	                    <td class="label">Title:</td>
	                    <td><input type="text" name="date" size="12" value="${course.title}" /></td>
	                </tr>
	                <tr>
	                    <td class="label">Date:</td>
	                    <td><input type="text" name="date" size="12" value="${session.date}" /></td>
	                </tr>
	                <tr>
	                    <td class="label">Time:</td>
	                    <td><input type="text" name="time" size="12" value="${session.time}" /></td>
	                </tr>
	                <tr>
	                    <td class="label">Room:</td>
	                    <td><input type="text" name="room" size="12" value="${session.room}" /></td>
	                </tr>
	                <tr>
	                    <td class="label">Tutor:</td>
	                    <td><input type="text" name="tutor" size="12" value="${session.tutor}" /></td>
	                </tr>
	                <tr>
	                    <td class="label">Course:</td>
	                    <td>${course}</td>
	                </tr>
	            </table>
            <input type="Submit" name="submit" value="Create Course">
        	</form>
        </body>
</html>|