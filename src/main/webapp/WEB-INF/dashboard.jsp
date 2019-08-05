<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<h1>Welcome, ${ user.name }</h1>
	
	<h2>Tasks</h2>
	<table border="1">
		<thead>
			<th>Tasks</th>
			<th>Edit Task</th>
			<th>Creator</th>
			<th>Assignee</th>
			<th>Priority Level</th>
		</thead>
		<tbody>
    	<c:forEach var="task" items="${ tasks }" >
        <tr> 
            <td><a href="tasks/${ task.id }">${  task.name } </a></td>
            <td><a href="edit/${ task.id }">Edit Task</a></td>
          	<td> ${task.user.name}</td>
          	
          	<td> ${task.assignee.name} </td>
          	<td>  ${task.priority}</td>
        </tr>
    	</c:forEach>

		</tbody>
	</table>
	<br>
	<button><a href="/users/add">Create an Task</a></button>
</body>
</html>