<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Page</title>
<style>
        body {
            background-image: url('/images/gn.png'); 
            background-size: cover; 
            background-position: center; 
        }

    </style>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<h1 class="text-center mt-3 bg-success">Good NeighborhooDC</h1>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="col-md-6 offset-md-3 bg-white p-4 shadow">
            <h1 class="text-center">Register!</h1>
    
		    <p><form:errors path="user.*"/></p>
		    
		    <form:form method="POST" action="/register" modelAttribute="user">
		        <p>
		            <form:label path="username">Username:</form:label>
		            <form:input path="username"/>
		        </p>
		        <p>
		            <form:label path="email">Email:</form:label>
		            <form:input path="email"/>
		        </p>
		        <p>
		        	<form:label path="neighborhood"> Neighborhood: </form:label>
		            <form:select path="neighborhood">
		            <c:forEach var="neighborhood" items="${neighborhoods}">
		            	<option value="${neighborhood.id}"><c:out value="${neighborhood.name}" /></option>
		        	</c:forEach>
		            </form:select>
		            
		        </p>
		        <p>
		            <form:label path="password">Password:</form:label>
		            <form:password path="password"/>
		        </p>
		        <p>
		            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
		            <form:password path="passwordConfirmation"/>
		        </p>
		        <input type="submit" class="btn btn-primary" value="Register!"/>
		    </form:form>
    			<p class="text-center mt-3">Already have an account? <a href="/login">Sign in</a></p>
         </div>
    </div>
</body>
</html>