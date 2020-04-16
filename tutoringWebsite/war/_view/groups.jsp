<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <form action="${pageContext.servletContext.contextPath}/groups" method="post">
            <table>
                <td><input type="submit" name="all" value="Get All Study Groups" /></td>
                <td><input type ="submit" name = "ECE260" value="ECE260 Study Groups"/></td>
                <td><input type = "submit" name = "CS320" value = "CS320 Study Groups"/></td>
            </table>

            <table>
                    <tr>
                        <td>Date</td>
                        <td>Time</td>      
                        <td>Room</td>
                        <td>Course</td>   				
                    </tr>

                    <c:forEach items="${sessions}" var="session">
                        <tr>
                            <td>${session.date}</td>
                            <td>${session.time}</td>
                            <td>${session.room}</td>			            
                            <td>${session.course.title}</td>
                            <!--  <td>${session.course.title} </td> -->
                        </tr>
                    </c:forEach>
                </table>
        </form>
    </body>
</html>|