<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>

        
	<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<input name="index" type="submit" value="Kinsley Tutoring Service" /><br>
			Schedule
			<div>
				<ul>
					<li><input name="courses" type="submit" value="Courses" /></li>
					<li><input name="groups" type="submit" value="Study Groups" /></li>
					<li><input name="schedule" type="submit" value="Schedule" /></li>
					<li><input name="tutors" type="submit" value="Tutors" /></li>
					<li><input name="resources" type="submit" value="Resources" /></li>
					<li><input name="profile" type="submit" value="Profile" /></li>
				</ul>	
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
				<table>
					<tr>
	       				<td>Tutor</td>
	       				<td>Date</td>      
	       				<td>Time</td>
	       				<td>Room</td>   				
				    </tr>
					
					<c:forEach items="${sessions}" var="session">
			        	<tr>
			            	<td>${session.tutor}</td>
			            	<td>${session.date}</td>
			            	<td>${session.time}</td>
			            	<td>${session.room}</td>			            
			        	</tr>
			    	</c:forEach>
			    </table>
			</table>
    </form>
</html>