<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task</title>
</head>
<body>
	<h1>Edit ${ task.name }</h1>
	<br>
	<form:form action="/users/update/${task.id}" method="POST" modelAttribute="task">
        <c:if test="${taskError != null}">
	        <p class="error"> ${taskError}</p> 
	        </c:if>
	        
	        <p><form:label path="name"> Task: </form:label></p>
			<p><form:errors path="name"></form:errors> </p>
	        <p><form:input path="name"></form:input></p>

	        
	        <form:select path="assignee">
	            <c:forEach items= "${users}" var="user">
	                <option value="${user.id}" > ${user.name}</option>
	            </c:forEach>
         	</form:select>
         
	  		<form:label path="priority">Priority:</form:label>
	         <form:select path="priority">
	         	<option> Low</option>
	         	<option> Medium</option>
	         	<option> High</option>
	         </form:select>
	
	        <p><input type="submit" value="Update" /></p>
    </form:form>
    <hr>
       	<form action= "/users/${task.id}/destroy" method="post">
			<input type="submit" value="delete" />
		</form>
</body>
</html>