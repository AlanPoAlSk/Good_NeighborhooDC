<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Personal page</title>
<style>
        body {
            background-image: url('/images/gn2.png'); 
            background-size: cover; 
            background-position: top; 
        }

    </style>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body class="bg-light">
	<div class="container mt-5 bg-light">
		<div class="d-flex justify-content-around align-items-center">
			<div>
				<h1 class="text-center"> <c:out value="${user.username}"/>'s Personal Section</h1>
			</div>
			<div>
				<a href="/dashboard" class="btn btn-secondary">Back</a>
			</div>
		</div>
		<h1>Tasks</h1>
    	<table class="table">
        <thead>
            <tr>
            	<th>Tasks Owner</th>
                <th>Title</th>
                <th>Description</th>
                <th>Actions</th>
                <th>Who accepted</th>
                <!-- Add other table headers as needed -->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="task" items="${acceptedTasks}">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${task.user.id eq user.id}">
                                <c:out value="You" />
                            </c:when>
                            <c:when test="${task.acceptedUser.id eq user.id}">
                                <c:out value="${task.user.username}" />
                            </c:when>
                            
                        </c:choose>
                    </td>
                    <td><c:out value="${task.title}" /></td>
                    <td><c:out value="${task.description}" /></td>
                    <td><a href="/users/tasks/${task.id}">view</a></td>
                    <td>
                        <c:choose>
                            <c:when test="${task.user.id eq user.id}">
                                <c:out value="${task.acceptedUser.username}" />
                            </c:when>
                            <c:when test="${task.acceptedUser.id eq user.id}">
                                <c:out value="You" />
                            </c:when>
                            
                        </c:choose>
                    </td>
                    <!-- Add other table cells for additional task details -->
                </tr>
            </c:forEach>
        </tbody>
    </table>
	</div>
</body>
</html>