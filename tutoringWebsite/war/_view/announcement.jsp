<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css" >
        <Title>Announcement</Title>
    </head>
    <body>   
    <form action="${pageContext.servletContext.contextPath}/announcement.jsp" method="get">
	        <div class = "AnnouncementWindow">
	            <p id = "announcementTitle">Announcements</p>
	            	<table>
	            		<tr>
                            <td>ID</td>
                            <td>Type</td>      
                            <td>Date</td>
                            <td>Time</td>
                            <td>Message</td>   				
                        </tr>
                        <c:forEach items="${announcements}" var="announcement">
			        	<tr>
			            	<td>${announcement.typeId}</td>
			            	<td>${announcement.announcementType}</td>
			            	<td>${announcement.date}</td>
			            	<td>${announcement.time}</td>		
			            	<td>${announcement.message}</td>	            
			        	</tr>
			    	</c:forEach>
	            	</table>
	        </div>
	    </form>
    </body>
</html>