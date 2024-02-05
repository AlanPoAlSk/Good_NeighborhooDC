<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<style>
        body {
            background-image: url('/images/gn.png'); 
            background-size: cover; 
            background-position: center; 
        }

       
    </style>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<h1 class="text-center mt-3 bg-success">Good NeighborhooDC</h1>
</head>
<body class="bg-light">
	<div class="container mt-5">
    	<div class="col-md-6 offset-md-3 bg-white p-4 shadow">
		    <c:if test="${logoutMessage != null}">
		        <c:out value="${logoutMessage}"></c:out>
		    </c:if>
    		<h1 class="text-center">Login</h1>
		    <c:if test="${errorMessage != null}">
		        <c:out value="${errorMessage}"></c:out>
		    </c:if>
		    <form method="POST" action="/login">
		        <p>
		            <label for="username">Username</label>
		            <input type="text" id="username" name="username"/>
		        </p>
		        <p>
		            <label for="password">Password</label>
		            <input type="password" id="password" name="password"/>
		        </p>
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		        <input type="submit" class="btn btn-primary" value="Login!"/>
		    </form>
		    <p class="text-center mt-3">Don't have an account? <a href="/register"> Register</a></p>
		</div>
    </div>
</body>
</html>