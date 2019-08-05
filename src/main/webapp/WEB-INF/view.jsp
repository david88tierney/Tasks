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
		<h1>Task:  ${ oneTask.name }</h1>
		
		<p>Creator: ${ oneTask.user.name } </p>
		<p> Assignee: ${ oneTask.assignee.name }</p>
		<p> Priority: ${ oneTask.priority }</p>
		

		<br>
		<br>
		<br>
		<br>

		
</body>
</html>