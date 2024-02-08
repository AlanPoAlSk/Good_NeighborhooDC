<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dashboard</title>
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
	<div class="container">
		<div class="container d-flex justify-content-between bg-danger">
			<div>
				<div class="d-flex align-items-center">
					<div>
					    <h1 class="text-start">Dashboard</h1>
					</div>
				</div>
			    <h2 class="text-start">Welcome , <a href="/users/tasks/personal/${currentUser.id}"><c:out value="${currentUser.username}"/></a>  </h2>
			</div>
		    <div class="mt-3">
		    	<h1> <c:out value="${currentUser.neighborhood.name}"/> </h1>
		    	<form id="logoutForm" method="POST" action="/logout">
			        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			        <input type="submit" value="Logout!" />
			    </form>
		    </div>
		</div>
	   <div>
	   		<a class="btn btn-primary mt-3" href="users/tasks/new">create task</a>
	   </div>
	    <table class="table table-bordered table-light mt-3">
		    <thead>
		        <tr class="table-dark">
		            <th>Neighbor</th>
		            <th>Task</th>
		            <th>Actions</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach var="task" items="${tasks}">
		            <c:if test="${empty task.acceptedUser}">
		                <tr>
		                    <td><c:out value="${task.user.username}" /></td>
		                    <td><c:out value="${task.title}" /></td>
		                    <c:choose>
		                        <c:when test="${task.user.id eq currentUser.id}">
		                            <td><a href="/users/tasks/${task.id}">view</a> | <a href="/users/tasks/${task.id}/edit">edit</a></td>
		                        </c:when>
		                        <c:otherwise>
		                            <td class="d-flex">
		                                <a href="/users/tasks/${task.id}">view</a> | | |
		                                <form action="/users/tasks/${task.id}/apply" method="POST">
		                                    <button type="submit" class="btn btn-success btn-sm">Apply</button>
		                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		                                </form>
		                            </td>
		                        </c:otherwise>
		                    </c:choose>
		                </tr>
		            </c:if>
		        </c:forEach>
		    </tbody>
		</table>
	</div>
</body>
</html>