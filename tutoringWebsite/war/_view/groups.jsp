<!DOCTYPE html>

<html>
	<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<input name="index" type="submit" value="Kinsley Tutoring Service" /><br>
			Study Groups
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
        <form action="${pageContext.servletContext.contextPath}/groups" method="post">
            <h1>Study Groups</h1><br>
            <table>
                <td><input type="submit" name="all" value="Get All Study Groups" /></td>
                <td><input type ="submit" name = "ECE260" value="ECE260 Study Groups"/></td>
                <td><input type = "submit" name = "CS320" value = "CS320 Study Groups"/></td>
            </table>
            
            <table>
					<tr>
	       				<td>Date</td>
	       				<td>Time</td>      
	       				<td>Room</td>
	       				<td>Tutor</td>   				
				    </tr>
					
					<c:forEach items="${sessions}" var="session">
			        	<tr>
			            	<td>${session.date}</td>
			            	<td>${session.time}</td>
			            	<td>${session.room}</td>			            
			        	    <td>${session.tutor}</td>
                        </tr>
			    	</c:forEach>
			    </table>
        </form>
</html>|