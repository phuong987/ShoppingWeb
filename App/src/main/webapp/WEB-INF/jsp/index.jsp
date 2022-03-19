<%-- 
    Document   : Index.jsp
    Created on : Jan 29, 2022, 1:33:37 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1 class="text-center text-danger">DANH MUC SAN PHAM</h1>
<sec:authorize access="hasRole('ROLE_ADMIN')" >
<div>
    <a href="<c:url value="/admin/products" />" class="btn btn-danger" >QUAN LY SAN PHAM</a>
</div>
</sec:authorize>

<div>
    <ul class="pagination">
        <c:forEach begin="1" end="${Math.ceil(counter/2)}" var="i">
        <li class="page-item"><a class="page-link" href="<c:url value="/"/>?page=${i}">${i}</a></li>
        </c:forEach>
    </ul>
</div>
<div class="row">
    <c:forEach var="p" items="${products}">
      <div class="col-md-4" style="padding: 10px">
          <div class="card">
            <div class="card-body text-center">
                <a href="<c:url value="/products/${p.id}" />">
                    <c:if test="${p.image != null && p.image.startsWith('https') == true}">
                        <img class="img-fluid" style="height: 200px; width: 450px;" src="<c:url value="${p.image}"/>" alt="${p.name}" />
                    </c:if>
                    <c:if test="${p.image == null || p.image.startsWith('https') == false}">
                        <img class="img-fluid" style="height: 200px; width: 450px;" src="<c:url value="/images/MAC.jpg"/>" alt="${p.name}" />
                    </c:if>    
                </a>
                <h3 class="card-title">${p.name}</h3>
                <p class="card-text text-success">${p.price} VND</p>
            </div>
            <div class="card-footer text-center">
                <a href="javascript:;" class="btn btn-danger" onclick="addToCart(${p.id})" >Them vao gio</a>
                <a href="#" class="btn btn-info">Mua ngay</a>
            </div>
          </div>
      </div>
    </c:forEach>
</div>

<hr>
<div class="alert alert-success text-center">
<h1>CAC SAN PHAM BINH LUAN NHIEU NHAT</h1>
</div>
<hr>
<div class="row">
    <c:forEach var="p" items="${disProducts}">
      <div class="col-md-4 col-xs-12" style="padding: 10px">
          <div class="card">
                <a href="<c:url value="/products/${p[0]}" />">
                    <c:if test="${p[3] != null && p[3].startsWith('https') == true}">
                        <img class="img-fluid" style="height: 200px; width: 450px;" src="<c:url value="${p[3]}"/>" alt="Card image" />
                    </c:if>
                    <c:if test="${p[3] == null || p[3].startsWith('https') == false}">
                        <img class="img-fluid" style="height: 200px; width: 450px;" src="<c:url value="/images/MAC.jpg"/>" alt="Card image" />
                    </c:if>    
                </a>
                    
                <div class="card-body">
                    <h4 class="card-title">${p[1]}</h4>
                    <p class="card-text text-success">${p[2]} VND</p>
                    <p class="text-danger">So luot thao luan: ${p[4]}</p>
                    <a href="#" class="btn btn-primary">Dat hang</a>
                </div>
          </div>
      </div>
    </c:forEach>
</div>