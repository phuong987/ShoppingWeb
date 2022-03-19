<%-- 
    Document   : register
    Created on : Feb 27, 2022, 12:50:01 AM
    Author     : Dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-danger" >DANG KY</h1>
<c:if test="${errMsg != null}" >
    <div class="alert alert-danger" >
        ${errMsg}
    </div>
</c:if>

<c:url value="/register" var="action" />

<form:form method="post" action="${action}" enctype="multipart/form-data" modelAttribute="user" >
    <div class="form-group" >
        <label for="firstname" >First name</label>
        <form:input type="text" id="firstname" path="firstName" class="form-control" />
    </div>
    <div class="form-group" >
        <label for="lastname" >Last name</label>
        <form:input type="text" id="lastname" path="lastName" class="form-control" />
    </div>
    <div class="form-group" >
        <label for="email" >Email</label>
        <form:input type="email" id="email" path="email" class="form-control" />
    </div>
    <div class="form-group" >
        <label for="username" >Username</label>
        <form:input type="text" id="username" path="username" class="form-control" />
    </div>
    <div class="form-group">
        <label for="password" >Password</label>
        <form:input type="password" id="password" path="password" class="form-control" />
    </div>
    <div class="form-group">
        <label for="confirm-password" >Confirm Password</label>
        <form:input type="password" id="confirm-password" path="confirmPassword" class="form-control" />
    </div>
    <div class="form-group">
        <label for="avatar" >Avatar</label>
        <form:input type="file" id="avatar" path="file" class="form-control" />
    </div>
    <div class="form-group">
        <input type="submit" value="DANG KY" class="btn btn-danger" />
    </div>
</form:form>
