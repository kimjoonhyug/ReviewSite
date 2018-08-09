<%-- 
    Document   : category.jsp
    Created on : 2018. 7. 23, 오후 2:14:36
    Author     : b003
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp"/>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
<div class="container">
    <div class="row">
    <div class="col-md-12">
        <h4 id="searchResult"><b><span style="color:brown">${type}</span></b>으로 검색한 결과</h4>
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
