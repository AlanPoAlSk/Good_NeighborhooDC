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
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body class="bg-light">
	<div class="container mt-5">
	    <h1 class="text-center">Edit Task</h1>
	    <h2> </h2>
	   
	    <br />
	    <a href="/dashboard" class="btn btn-secondary">Back</a>
	    
	    <form:form action="/users/tasks/${task.id}/edit" method="PUT" modelAttribute="task">
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
	        <%-- <p>
	        	<form:label path="neighborhood"> Category: </form:label>
	            <form:select path="neighborhood">
	            <c:forEach var="neighborhood" items="${neighborhoods}">
	            	<option value="${neighborhood.id}"><c:out value="${neighborhood.name}" /></option>
	        	</c:forEach>
	            </form:select>
	            
	        </p> --%>
	       
	        <input type="submit" class="btn btn-success" value="Edit!"/>
	    </form:form>
	    <div>
	    	<form action="/users/tasks/${task.id}" method="post">
	    		<input type="hidden" name="_method" value="delete" />
	    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	    		<button type="submit" class="btn btn-danger">Delete</button>
	    	</form>
	    </div>
	</div>