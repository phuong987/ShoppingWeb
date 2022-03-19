<%-- 
    Document   : login
    Created on : Feb 26, 2022, 8:14:49 PM
    Author     : Dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-danger" >DANG NHAP</h1>

<c:if test="${param.error != null}" >
    <div class="alert alert-danger" >
        Da co loi xay ra! Vui long quay lai sau!
    </div>
</c:if>
<c:if test="${param.accessDenied != null}" >
    <div class="alert alert-danger" >
        Ban khong co quyen truy cap!!!
    </div>
</c:if>

<c:url value="/login" var="action" />

<form method="post" action="${action}" >
    <div class="form-group" >
        <label for="username" >Username</label>
        <input type="text" id="username" name="username" class="form-control" />
    </div>
    <div class="form-group">
        <label for="password" >Password</label>
        <input type="password" id="password" name="password" class="form-control" />
    </div>
    <div class="form-group">
        <input type="submit" value="DANG NHAP" class="btn btn-danger" />
    </div>
</form>
