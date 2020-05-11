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
            <div class = "pageDesc">
	           <p id = "announcementTitle">Delete an Announcement</p>
            </div>
        </form>
        <form action="${pageContext.servletContext.contextPath}/deleteAnnouncement" method="post">
       		<p>Please enter the information requested below in order to delete an Announcement.</p>
       		<table>
       			<tr>
					<td class="label">Please enter Announcement ID of the announcement you would like to delete:</td>
					<td><input type="text" name="announcementId" size="12" value="${announcementId}" /></td>
				</tr>
				
				<c:forEach items="${announcements}" var="announcement">
			        	<tr class="scheduleListings">
				            <p id = "pa1">${announcement.announcementId} ${announcement.typeName} ${announcement.typeId}</p>
			        		<p id = "pa2">${announcement.message}</p>	          
			        	</tr>
			    	</c:forEach>

  			</table>
  			<p id="error">${errorMessage}</p>
  			<input type="Submit" name="submit" value="Delete Announcement" class = "myButton">
    </form>
</html>
