<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>

		<title>Create Account</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css"> 
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
            <div class = "pageDesc">
	           <p>Create an Announcement</p>
            </div>
        </form>

		<form action="${pageContext.servletContext.contextPath}/createAnnouncement" method="post">
			<table>
				<tr>
					<td class="label">Date:</td>
					<td><input type="text" name="Date" size="12" value="${date}" /></td>
				</tr>
				<tr>
					<td class="label">Time:</td>
					<td><input type="text" name="Time" size="12" value="${time}" /></td>
				</tr>
				<tr>
					<td class="label">Message:</td>
					<td><input type="text" name="Message" size="12" value="${message}" /></td>
				</tr>
				<tr>
					
  					<input type="radio" id="session" name="announcementType" value="Session">
  					<label for="session">Session</label><br>
  					
					<input type="radio" id="studyGroup" name="announcementType" value="Study Group">
					<label for="studyGroup">Study Group</label><br>
			
  				</tr>
				</table>
			<input type="Submit" name="submit" value="Create Annoucement">
		</form>
	</body>
</html>