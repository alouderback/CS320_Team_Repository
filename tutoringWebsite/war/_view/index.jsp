<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
	<head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css" >
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
			        		<p id = "pa1">${announcement.typeName} ${announcement.typeId} ${announcement.date} ${announcement.time}</p>
			        		<p id = "pa2">${announcement.message}</p>
			    	</c:forEach>
	            	</table>
	        </div>
	    </form>
    </body>
</html>