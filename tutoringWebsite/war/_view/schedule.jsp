<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

 
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css" >
        <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&amp;display=swap" rel="stylesheet">
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
            <div id = "subTitle">
	           Schedule
            </div>
        </form>
        <form action="${pageContext.servletContext.contextPath}/announcement" method="get">
	        <div class = "AnnouncementWindow">
                <div id = "announcementTitle">Announcements</div>
	            	<table>
                        <c:forEach items="${announcements}" var="announcement">
			        		<p id = "pa1">${announcement.typeName} ${announcement.courseName} ${announcement.date} ${announcement.startTime} - ${announcement.endTime}</p>
			        		<p id = "pa2">${announcement.message}</p>
			    	</c:forEach>
	            	</table>
	        </div>
	    </form>
        <form action="${pageContext.servletContext.contextPath}/schedule" method="post">
            
            <table class="center">
                    <tr>
                        <!-- <td class="label">Date of Desired Schedule (XX/XX/XXXX):</td> -->
                        <input class="myButton" type="submit" name="Submit" value="Get Schedule for Today" />
                    </tr>
                    <tr>
                        <input class="myButton" type="submit" name="SubmitW" value="Get Schedule for Week">
                    </tr>
                    <tr>
                        <input class="myButton" type="submit" name="SubmitM" value="Get Schedule for Month">
                    </tr>
                    
                    <c:if test = "${isFaculty}">
	                    <tr>
	                        <td><input class="myButton" type="submit" name="CreateSession" value="Add A New Tutoring Session"></td>
	                    </tr>
	                    <tr>
	                        <td><input class="myButton" type="submit" name="DeleteSession" value="Delete An Existing Tutoring Session"></td>
	                    </tr>
   					</c:if>
                        <tr>
                            <td id="loopTitle">Tutor</td>
                            <td id="loopTitle">Days of Week</td>      
                            <td id="loopTitle">Time</td>
                            <td id="loopTitle">Room</td>
                            <td id="loopTitle">Course</td>   				
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
