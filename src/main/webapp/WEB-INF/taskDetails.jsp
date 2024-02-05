<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Details</title>
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
		<a href="javascript:history.back()">back</a>
	</div>
	<div class="container mt-5 bg-success d-flex justify-content-around p-3">
	
		<div class="d-flex justify-content-between align-items-center">
			<div>
			    <h1><c:out value="${task.title}"/></h1>
			    <p style="font-size:larger">Description: <c:out value="${task.description}"/></p>
			    <p style="font-size:larger">Contact: <c:out value="${task.user.email}"/></p>
			</div>
			
		</div>
	   
	    <br />
	    <div class="d-flex align-items-center">
			<c:if test="${not empty task.imageBase64}">
	        <h3 style="margin-right:50px;">Picture:</h3>
	        <img src="${task.imageBase64}" alt="Task Image" class="img-fluid rounded" style="width: 250px; height: 250px;"/>
	    	</c:if>
	    </div>
	</div>
</body>
</html>