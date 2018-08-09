<%-- 
    Document   : search
    Created on : 2018. 7. 25, 오전 10:55:54
    Author     : b003
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
    <div class="row">
    <div class="col-md-12">
        <h4 id="searchResult"><b><span style="color:brown">${address.dong}</span></b>으로 검색한 결과</h4>
    <c:forEach var="place" items="${places}">
        <figure class="kindImage">
            <a href="${pageContext.request.contextPath}/place/${place.id}"><img src="${place.logo.location}" alt="${place.name}"/></a>
            <figcaption>
                <a href="${pageContext.request.contextPath}/place/${place.id}">
                    ${place.name}<br><span style="color:gold;">★</span><f:formatNumber value= "${place.rating}" maxFractionDigits="1"/>
                </a>
            </figcaption>
        </figure>
    </c:forEach>
    </div>
    </div>
</div>
</body>
</html>
