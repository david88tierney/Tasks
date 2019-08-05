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
		<h1>Create a new task</h1>
		
		
		<form:form action="/users/create" method="POST" modelAttribute="task">
    
        <p><form:label path="name"> Task: </form:label></p>
		<p><form:errors path="name"></form:errors> </p>
        <p><form:input path="name"></form:input></p>
    	<p> Assignee: </p>
        
        
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
    	
		<p><input type="submit" value="Create" /></p>
		
		</form:form>
</body>
</html>