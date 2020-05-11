<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

 
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css" >
        <Title>Schedule</Title>
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
	           <p>Schedule</p>
            </div>
        </form>
        <form action="${pageContext.servletContext.contextPath}/announcement" method="get">
	        <div class = "AnnouncementWindow">
	            <p id = "announcementTitle">Announcements</p>
	            	<table>
                        <c:forEach items="${announcements}" var="announcement">
			        		<p id = "pa1">${announcement.typeName} ${announcement.typeId} ${announcement.date} ${announcement.startTime} - ${announcement.endTime}</p>
			        		<p id = "pa2">${announcement.message}</p>
			    	</c:forEach>
	            	</table>
	        </div>
	    </form>
        <form action="${pageContext.servletContext.contextPath}/schedule" method="post">
            <table>
                    <tr>
                        <!-- <td class="label">Date of Desired Schedule (XX/XX/XXXX):</td> -->
                        <td><input type="submit" name="Submit" value="Get Schedule for Today" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="SubmitW" value="Get Schedule for Week"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="SubmitM" value="Get Schedule for Month"></td>
                    </tr>
                    
                    <c:if test = "${isFaculty}">
	                    <tr>
	                        <td><input type="submit" name="CreateSession" value="Add A New Tutoring Session"></td>
	                    </tr>
	                    <tr>
	                        <td><input type="submit" name="DeleteSession" value="Delete An Existing Tutoring Session"></td>
	                    </tr>
   					</c:if>
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
