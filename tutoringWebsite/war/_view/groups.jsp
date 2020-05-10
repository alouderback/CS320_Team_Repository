<!DOCTYPE html>

<html>
	<head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css" >
        <Title>Study Groups</Title>
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
	           <p>Study Groups</p>
            </div>
        </form>

        <form action="${pageContext.servletContext.contextPath}/groups" method = "get">
            <table>
                <tr>
                    <td>Tutor</td>
                    <td>Days of Week</td>      
                    <td>Time</td>
                    <td>Room</td>
                    <td>Course</td>   				
                </tr>

                <c:forEach items="${sessions}" var="session">
                    <tr class="scheduleListings">
                            <td>${session.adminName}</td>
                            <td>${session.daysOfWeekString}</td>
                            <td>${session.startTime} - ${session.endTime}</td>
                            <td>${session.room}</td>		
                            <td>${session.courseName}</td>	          
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>