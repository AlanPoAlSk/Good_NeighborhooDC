<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Task</title>
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
	<div class="container mt-5">
	    <h1 class="text-center bg-success">Edit Task</h1>
	    
	    <a href="/dashboard" class="btn btn-secondary">Back</a>
	    <div class="bg-light d-flex justify-content-between p-4">
		    <div>
			    <form:form action="/users/tasks/${task.id}/edit" method="PUT" modelAttribute="task" enctype="multipart/form-data">
					<form:hidden path="user"/>
			    <form:errors path="*" cssClass="error" />
			    
			        <p>
			            <form:label path="title">Title:</form:label>
			            <form:input path="title"/>
			        </p>
			        <p>
			            <form:label path="description">Description:</form:label>
			            <form:textarea path="description"/>
			        </p>
			         <p>
				        <form:label path="imageFile">Upload Image:</form:label>
			        	<input type="file" id="imageFile" name="imageFile" accept="image/*" />
		    		</p>
		    		
			        <input type="submit" class="btn btn-success" value="Edit!"/>
			    </form:form>
		    </div>
		    <div class="d-flex align-items-center">
				<c:if test="${not empty task.imageBase64}">
			        <h3 style="margin-right:50px;">Picture:</h3>
			        <img src="${task.imageBase64}" alt="Task Image" class="img-fluid rounded" style="width: 250px; height: 250px;"/>
		    	</c:if>
	    	</div>
		    <div>
		    	<form action="/users/tasks/${task.id}" method="post">
		    		<input type="hidden" name="_method" value="delete" />
		    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		    		<button type="submit" class="btn btn-danger">Delete</button>
		    	</form>
		    </div>
	    </div>
	</div>
</body>
</html>