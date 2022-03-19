<%-- 
    Document   : product-detail
    Created on : Mar 3, 2022, 3:44:28 PM
    Author     : Dell
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-danger text-center" >CHI TIET SAN PHAM</h1>
<div class="row">
    <div class="col-md-6">
        <c:if test="${product.image != null && product.image.startsWith('https') == true}">
            <img class="img-fluid" src="<c:url value="${product.image}"/>" alt="${product.name}" />
        </c:if>
        <c:if test="${product.image == null || product.image.startsWith('https') == false}">
            <img class="img-fluid" src="<c:url value="/images/MAC.jpg"/>" alt="${product.name}" />
        </c:if>
    </div>
    <div class="col-md-6">
        <h1>${product.name}</h1>
        <h3 class="text-danger" >${product.price} VND</h3>
        <p>${product.description}</p>
        <div>
            <input type="button" value="Dat hang" class="btn btn-danger" />
        </div>
    </div>
</div>
<br><br>
<form>
    <div class="form-group" >
        <textarea class="form-control" id= "commentId" placeholder="Nhap danh gia cua ban..." ></textarea>
<!--        <br>-->
        <input type="button" onclick="addComment(${product.id})"
               value="Gui binh luan" class="btn btn-danger" />
    </div>
</form>

<div id="commentArea">
<c:forEach items="${product.comment}" var="comment" >
<div class="row">
    <div class="col-md-2" style="padding:10px; width: 100px;" >
        <c:if test="${comment.user.avatar != null}">
            <img class="rounded-circle img-fluid" src="${comment.user.avatar}" />
        </c:if>
        <c:if test="${comment.user.avatar == null}">
            <img class="rounded-circle img-fluid"
                 src="https://res.cloudinary.com/dhdw0zzon/image/upload/v1647157330/avatar_tztgqp.jpg" />
        </c:if>
    </div>
    <div class="col-md-10 my-date">
        <p>${comment.content}</p>
        <i>${comment.createdDate}</i>
    </div>
</div>
</c:forEach>
</div>

<script>
    window.onload = function () {
        let dates = document.querySelectorAll(".my-date > i")
        for (let i = 0; i < dates.length; i++){
            let d = dates[i]
            d.innerText = moment(d.innerText).fromNow()
        }
    }
</script>