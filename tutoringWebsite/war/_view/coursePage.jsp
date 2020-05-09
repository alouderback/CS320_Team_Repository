
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
        </form>
        <form action="${pageContext.servletContext.contextPath}/coursePage" method="get"> 
            <h1>${title}</h1>
            <h3>Days of Week:</h3>
            <c:forEach items="${daysOfWeek}" var = "dayOfWeek">
                <h3>${dayOfWeek} </h3>            
            </c:forEach>
            <!--<h3>Start Time: ${startTime}</h3>
            <h3>End Time: ${endTime}</h3>//-->
            <h3>List of Tutors:</h3>
            <c:forEach items="${tutorList}" var = "tutor">
                <tr>
                    <td>${tutor.name}<br></td>            
                </tr>
            </c:forEach>
        </form>
    </body>
</html>|