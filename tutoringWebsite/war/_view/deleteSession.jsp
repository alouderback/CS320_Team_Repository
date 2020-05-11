<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

 
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css" >
        <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&amp;display=swap" rel="stylesheet">
        <Title>Delete Session</Title>
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
            <div id = "subTitle">
	           Delete a tutoring session
            </div>
        </form>
        <form action="${pageContext.servletContext.contextPath}/deleteSession" method="post">
       		<p>Please enter the information requested below in order to delete a session.</p>
       		<table>
       			<tr>
					<td class="label">Please enter session ID of the session you would like to delete:</td>
					<td><input type="text" name="sessionId" size="12" value="${sessionId}" /></td>
				</tr>
				<tr>
					<td id="loopTitle">Session ID</td>
					<td id="loopTitle">Tutor Name</td>
					<td id="loopTitle">Course Name</td>
					<td id="loopTitle">Days Of Week</td>
					<td id="loopTitle">Start Time</td>
				</tr>
				
				<c:forEach items="${sessions}" var="session">
			        	<tr class="scheduleListings">
				            	<td>${session.sessionId}</td>
				            	<td>${session.adminName}</td>
				            	<td>${session.courseName}</td>
				            	<td>${session.daysOfWeekString}</td>	
				            	<td>${session.startTime}</td>          
			        	</tr>
			    	</c:forEach>

  			</table>
  			<p id="error">${errorMessage}</p>
  			<input type="Submit" name="submit" value="Delete Tutoring Session">
    </form>
</html>
