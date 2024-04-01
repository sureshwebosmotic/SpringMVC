<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List.*"%>
<html>
<head> <%@ page isELIgnored="false" %>
<title>Employee Management App</title>
</head>
<body>
	<div>
		<a href="<%=request.getContextPath()%>/getAll">View Users</a>
		<h1>Employee Register Form</h1>
		
		<c:if test="${employee != null}">
			<form modelAttribute="employee" action="<%=request.getContextPath()%>/update" method="post">
		</c:if>
		<c:if test="${employee == null}">
			<form modelAttribute="employee" action="<%=request.getContextPath()%>/save" method="post">
		</c:if>
		<c:if test="${errorMessage != null}">
			<script type="text/javascript">
				alert("${errorMessage}");
			</script>
		</c:if>
		<h2>
			<c:if test="${employee != null}"> Edit User </c:if>
			<c:if test="${employee == null}"> Add New User </c:if>
		</h2>
		<fieldset>
			<c:if test="${employee != null}"> <input type="hidden" name="employeeId" value="<c:out value='${employee.employeeId}' />" /> </c:if>
			<label>Name</label> <input type="text" value="<c:out value='${employee.name}' />" name="name" required="required" pattern="[A-Za-z]+"> 
			<br> 
			<br>
			<label>Skills</label> 
			<br>

<%
List<String> masterSkills = new ArrayList<>();
masterSkills.add("Java");
masterSkills.add("Spring");
masterSkills.add("React");
masterSkills.add("Angular");
masterSkills.add("Node");
masterSkills.add("PHP");
%>
		<c:forEach var="masterskill" items="<%=masterSkills %>">
			<% boolean hasSkill = false; %>

			<c:forEach var="skill" items="${employee.skills}">
				<c:if test="${masterskill == skill}">
					<% hasSkill = true; %>
				</c:if>
			</c:forEach>
			<c:choose>
				<c:when test="<%=hasSkill%>">
					<input type="checkbox" value="<c:out value="${masterskill}" />" name="skills" checked="checked"> ${masterskill}
                </c:when>
				<c:otherwise>
					<input type="checkbox" value="<c:out value="${masterskill}" />"	name="skills"> ${masterskill}
                </c:otherwise>
			</c:choose>
				<br>
		</c:forEach>
			<br> 
		<label>Age</label> 
		<input type="number" id="age" min="18" max="60" value="<c:out value='${employee.age}' />" name="age"	required="required"> 
		<br> 
		<br> 
		<label>Salary</label>
			<input type="number" value="<c:out value='${employee.salary}' />" name="salary" required="required"> 
			<br> 
			<br> 
		<label>BirthDate</label>
			<input type="date" onfocus="this.max=new Date().toISOString().split('T')[0]" value="<c:out value='${employee.birthDate}' />" name="birthDate" required="required" > <br> <br> 
			<input type="submit" value="Submit" />
		</fieldset>
		</form>
	</div>
</body>
</html>