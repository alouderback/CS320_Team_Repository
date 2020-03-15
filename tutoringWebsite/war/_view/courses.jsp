
<!DOCTYPE html>


<html>
	<form action="${pageContext.servletContext.contextPath}/index" method="post">
		<input name="index" type="submit" value="Kinsley Tutoring Service" /><br>			
			Courses
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

		
		<form action="${pageContext.servletContext.contextPath}/courses" method="post">
			<table>
				<tr>
					<td class="label">Title:</td>
					<td><input type="text" name="title" size="12" value="${course.title}" /></td>
				</tr>
				<tr>
					<td class="label">Date:</td>
					<td><input type="text" name="date" size="12" value="${session.date}" /></td>
				</tr>
				<tr>
					<td class="label">Time:</td>
					<td><input type="text" name="time" size="12" value="${session.time}" /></td>
				</tr>
				<tr>
					<td class="label">Room:</td>
					<td><input type="text" name="room" size="12" value="${session.room}" /></td>
				</tr>
				<tr>
					<td class="label">Tutor:</td>
					<td><input type="text" name="tutor" size="12" value="${session.tutor}" /></td>
				</tr>
				<tr>
					<td class="label">Course:</td>
					<td>${course}</td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Create Course">
		</form>
</html>|