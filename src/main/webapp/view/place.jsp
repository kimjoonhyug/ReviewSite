<%-- 
    Document   : place.jsp
    Created on : 2018. 7. 19, 오후 3:30:25
    Author     : b003
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">



<jsp:include page="header.jsp"/>
<script>
    $(document).ready(function () {
        setupAndDisplayMap(${place.lat}, ${place.lng});
    });
</script>
<div class="container">
    <div class="row">
        <div class="placeLogo col-md-4 offset-md-1">
            <img src="${place.logo.location}" class="img-thumbnail"/>
        </div>

        <div class="placeInfo col-md-5 offset-md-1">
            <table class="table table-striped" style="margin:40px 0">
                <tr>
                    <td><h3>${place.name}</h3></td>
                </tr>
                <tr>
                    <td>
                        <p style="font-size:1.5em;">
                            <span style="color:brown; font-weight:bold;">${place.type}</span> / 
                            <span style="color:gold;">★</span><i><f:formatNumber value= "${place.rating}" maxFractionDigits="1"/></i>
                        </p>
                    </td>
                </tr>
                <tr>
                    <td><h4>Tel . ${phone}</h4></td>
                </tr>
                <tr>
                    <td><h4>${place.hours}</h4></td>
                </tr>

            </table>
        </div>
    </div>
    <div>
        <c:forEach var="image" items="${place.images}" varStatus="status">
            <img src="${image.location}" alt="no image">
        </c:forEach>
    </div>
    <div class='row'>
        <div id="map" class='col-lg-5' style="height:350px; margin:auto;">
            <p id="lat" hidden>${place.lat}</p>
            <p id="lng" hidden>${place.lng}</p>
        </div>
        <table class="table table-hover table-condensed col-lg-5">
            <c:if test="${place.menu.items == null}">No Menu Items</c:if>
            <c:forEach var="menu" items="${place.menu.items}" begin='0' end='5' varStatus="status">
                <tr>
                    <td><img src="${menu.image.location}"  style="display:inline-block; width:40px; height:40px;"/></td>
                    <td>${menu.name}<td>
                    <td>${menu.price}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div>
        
    </div>

    <div class="replyArea">
        <div class="replys">
            <table>
                <tr>
                    <td colspan="3" style="text-align:center;"><h4>한줄평</h4></td>
                </tr>
                <c:forEach var="review" items="${place.reviews}" varStatus="status">
                    <tr>
                        <td class="reviewMem"><span class="review-background">${review.reviewer.username}</span></td>
                        <td class="reviewContent">${review.content}</td>
                        <td class="reviewRating"><span class="review-background">${review.rating}</span></td>
                        <td>${review.reviewDate}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="reply-form col-s-12 col-md-8 offset-md-2">
            <form action="/new" method="post" name="replyForm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type='hidden' name='placeId' value='${place.id}'/>
                <div class="row">
                    <div class="col-md-8" style="background:#DDD; border:1px solid #DDD; border-radius:5px;">
                        <textarea name="content" class="form-control " rows="2"></textarea>
                    </div>
                    <div class="col-md-4" style="text-align:center;">
                        <p>평점을 선택하세요</p>
                        <div class="col-md-10 offset-md-1">
                            <fieldset class="rating" style="margin:-20px 0px 20px;">
                                <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
                                <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                                <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
                                <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                                <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                            </fieldset>
                        </div>
                        <button class="btn btn-primary" onclick="javascript:replyForm.submit();" id="reviewBtn">평점쓰기</button>
                    </div>
            </form>
        </div>
    </div>
</div>
</div>
</body>
</html>
