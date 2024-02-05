<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Task</title>
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
	<div class="container mt-3">
		<div class="container d-flex justify-content-around align-items-center bg-success">
			<div>
			    <h1 class="text-center">Add a Task</h1>
			</div>
			<div>
			    <a href="/dashboard" class="btn btn-secondary">Back</a>
			</div>
		</div>
	    <h2> </h2>
	    <div class="container bg-light p-5">
		    <form:form method="POST" action="/users/tasks/new" modelAttribute="newTask" enctype="multipart/form-data">
		    <%-- <form:hidden path="user.id" value="${user_id}"/> --%>
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
		        
		       
		        <input type="submit"class="btn btn-success" value="Create!"/>
		    </form:form>
	    </div>
	</div>
    
    
    
    
    
</body>
</html>