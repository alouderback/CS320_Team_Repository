<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
	<head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_view/main.css" >
        <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&amp;display=swap" rel="stylesheet">
        <Title>Tutors</Title>
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
             </form>
         <div id = "subTitle">
	           Tutors
	           </div>
        <div id = "tutorTables">       
        <form action="${pageContext.servletContext.contextPath}/tutors" method="get">
                
           
	           
               <div class = "profileDis">
	           <table class = "center">
                    <tr>
                     	<td>Tutor</td>
                        <td>Email</td>      	
                   </tr>
                   
                    <c:forEach items="${tutorList}" var="tutor">
			        	<tr class="scheduleListings">
				           	 <td>${tutor.name}</td>		
				            	<td>${tutor.email}</td>          
			        	</tr>
			    	</c:forEach>
			    	 </table>
			    	 </div>
			    	 </form>
			    	 <form action="${pageContext.servletContext.contextPath}/coursePage" method="get">
			    	  <div class = "tutorDis">
			    	 <table class = "center">
			    	  <tr>
                     	<td>Courses</td>  	
                   </tr>
                   
			    	 <c:forEach items="${courseList}" var="course">
			        	<tr>	
				           	 <td><input id="courseTitle" name="${course[1].courseId}" type="submit" value="${course[1].title}" class = "myButton"</td> 
				           	<c:if test="${ not empty course[2].title }">
				            	<td><input id="courseTitle" name="${course[2].courseId}" type="submit" value="${course[2].title}" class = "myButton"</td>
				            </c:if> 
				              	<c:if test="${ not empty course[3].title }">
				           	  <td><input id="courseTitle" name="${course[3].courseId}" type="submit" value="${course[3].title}" class = "myButton"</td> 
				           	  </c:if> 
			        	</tr>
			    	</c:forEach>
			    </table>
                    </div>
        
        </form> 
        </div>
    </body>

</html>