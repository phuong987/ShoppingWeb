<%-- 
    Document   : header
    Created on : Feb 17, 2022, 12:31:05 PM
    Author     : Dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
     <a class="navbar-brand" href="<c:url value="/"/>"><i class="fas fa-home"></i><span>Trang chu</span></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
          <c:forEach var="cat" items="${categories}">
              <li class="nav-item">
                  <c:url value="/" var="catPath" >
                      <c:param name="CateId" value="${cat.id}" ></c:param>
                  </c:url>
                <a class="nav-link" href="${catPath}">${cat.name}</a>
              </li>
          </c:forEach>
          <c:if test="${pageContext.request.userPrincipal.name == null}" >
              <li class="nav-item active">
                  <a class="nav-link text-success" href="<c:url value="/login"/>" ><i class="fas fa-user-check"></i> <span>Dang nhap</span></a>
              </li>
              <li class="nav-item active">
                  <a class="nav-link text-success" href="<c:url value="/register"/>"><i class="fas fa-user-plus"></i> <span>Dang ky</span></a>
              </li>
          </c:if>
          <c:if test="${pageContext.request.userPrincipal.name != null}" >
              <c:if test="${currentUser.avatar != null}">
                  <li class="nav-item active">
                      <a class="nav-link text-success" href="<c:url value="/"/>">
                          <img src="${currentUser.avatar}" class="rounded-circle" style="width: 30px;" />
                          ${pageContext.request.userPrincipal.name}</a>
                  </li>
              </c:if>
              <c:if test="${currentUser.avatar == null}">
                  <li class="nav-item active">
                      <a class="nav-link text-success" href="<c:url value="/"/>">
                          <i class="fas fa-user"></i>
                          ${pageContext.request.userPrincipal.name}</a>
                  </li>
              </c:if>
              <li class="nav-item active">
                  <a class="nav-link text-danger" href="<c:url value="/logout"/>"><i class="fas fa-power-off"></i> <span>Dang xuat</span></a>
              </li>
          </c:if>
          <li class="nav-item">
              <a class="nav-link text-success" href="<c:url value="/cart"/>"><i class="fas fa-cart-plus" style="font-size: 20px;"></i> 
                  <span class="badge badge-danger" id="cart-counter" >${cartCounter}</span></a>
          </li>
        </ul>
        <form class="form-inline">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" name="kw">
            <button class="btn btn-success" type="submit">Search</button>
        </form>
    </div>
</nav>
