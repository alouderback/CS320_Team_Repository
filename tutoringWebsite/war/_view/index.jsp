<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
	<head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css" >
        <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&amp;display=swap" rel="stylesheet">
        <Title>Home</Title>
        <!-- This is a test -->
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
	            <p>Welcome to the Kinsley Tutoring Service website! This site is used to display information and announcements for Engineering and Computer Science tutoring.</p>
	        </div>
	       </form>
	        <form action="${pageContext.servletContext.contextPath}/announcement" method="get">
	        <div class = "AnnouncementWindow">
	            <p id = "announcementTitle">Announcements</p>
	            	<table>
                        <c:forEach items="${announcements}" var="announcement">
			        		<p id = "pa1">${announcement.typeName} ${announcement.courseName} ${announcement.date} ${announcement.startTime} - ${announcement.endTime}</p>
			        		<p id = "pa2">${announcement.message}</p>
			    	</c:forEach>
	            	</table>
	        </div>
	        <c:if test = "${isFaculty}">
	                   <input name="createAnnouncement" type="submit" value="Create Announcement" class = "myButton"/>
	                   <input name="deleteAnnouncement" type="submit" value="Delete Announcement" class = "myButton"/>
   			</c:if>
	    </form>
    </body>
</html>