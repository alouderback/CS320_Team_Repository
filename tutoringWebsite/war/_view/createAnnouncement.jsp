<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>

		<title>Create Account</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css">
        <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&amp;display=swap" rel="stylesheet">
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
	           <p id = "announcementTitle">Create an Announcement</p>
            </div>
        </form>

		<form action="${pageContext.servletContext.contextPath}/createAnnouncement" method="post">
			<table>
				<tr>
					<td class="label">Date (MM-DD-YYYY):</td>
					<td><input type="text" name="date" size="12" value="${date}" /></td>
				</tr>
				<tr>
					<td class="label">Start Time (ex. 18:00):</td>
					<td><input type="text" name="startTime" size="12" value="${startTime}" /></td>
				</tr>
				<tr>
					<td class="label">End Time (ex. 18:00):</td>
					<td><input type="text" name="endTime" size="12" value="${endTime}" /></td>
				</tr>
				<tr>
					<td class="label">Message:</td>
					<td><input type="text" name="message" size="12" value="${message}" /></td>
				</tr>
				<tr>
  					<td class="label"><label for="course">Select Course:</label></td>
  					<!-- Going to have the dropdown box here -->
  					<td><select id="course" name="course">
						<option value="1">CS101: Fundamentals of Computer Science</option>
						<option value="2">CS201: Fundamentals of Computer Science</option>
						<option value="3">CS290: Computer Science Career Training Prep</option>
						<option value="4">CS320: Software Engineering and Design</option>
						<option value="5">CS335: Cybersecuirty Analysis and Applications</option>
						<option value="6">CS360: Analysis of Algorithms</option>
						<option value="7">CS456: Social and Professional Issues</option>
						<option value="8">CS482: Senior Software Project II</option>
						<option value="9">CS496: Special Topics</option>
						<option value="10">ECE100: Introduction to Electrical Engineering</option>
						<option value="11">ECE260: Fundamentals of Computer Engineering</option>
						<option value="12">ECE280: Fundamentals of Electrical Engineering</option>
						<option value="13">ECE360: Power Systems</option>
						<option value="14">ECE420: Embedded System Design</option>
						<option value="15">CVE280: Civil Engineering Site Design</option>
						<option value="16">CVE350: Introduction to Environmental Engineering</option>
						<option value="17">CVE380: Construction Engineering</option>
						<option value="18">CVE405: Civil Engineering Professional Practice Seminar</option>
						<option value="19">CVE442: Design of Steel Structures</option>
						<option value="20">CVE444: Design of Concrete Structures</option>
						<option value="21">EGR150: Computational Methods</option>
						<option value="22">EGR240: Mathematical Methods</option>
						<option value="23">EGR264: Strength of Materials</option>
						<option value="24">EGR290: Engineering Career Training</option>
						<option value="25">ME100: Introduction to Mechanical Engineering</option>
						<option value="26">ME270: Mechatronics</option>
						<option value="27">ME320: Thermodynamics</option>
						<option value="28">ME410: Heat Transfer</option>
						<option value="29">ME411: Thermal System Design</option>
						<option value="30">ME452: Advanced Dynamics and Vibration</option>
						<option value="31">ME462: Applied Mechanics and Materials</option>
						<option value="32">PHY160: Engineering Physics: Mechanics</option>
					</select></td>
  				</tr>
				<tr>
					
  					<input type="radio" id="session" name="announcementType" value="Session">
  					<label for="session">Session</label><br>
  					
					<input type="radio" id="studyGroup" name="announcementType" value="Study Group">
					<label for="studyGroup">Study Group</label><br>
			
  				</tr>
				</table>
			<input type="Submit" name="submit" value="Create Announcement" class = "myButton">
		</form>
	</body>
</html>