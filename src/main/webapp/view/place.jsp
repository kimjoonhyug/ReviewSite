<%-- 
    Document   : place.jsp
    Created on : 2018. 7. 19, 오후 3:30:25
    Author     : b003
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">



<jsp:include page="header.jsp"/>
<script>
    $(document).ready(function () {
        setupAndDisplayMap(${place.lat}, ${place.lng});
    });
</script>
<div class="placeLogo"
    
    <img src="${place.logo.location}" class="img-thumbnail"/>
    
</div>
<div class="placeInfo">
    <h1>${place.name}</h1><br>
    <h3>${place.type}</h3>
    <h2>${place.rating}</h2>
</div>
<div>
    <c:forEach var="image" items="${place.images}" varStatus="status">
        <img src="${image.location}">
    </c:forEach>
</div>
<div id="map" style="width:80%;height:350px; margin:100px auto;"></div>
<div class="replyArea">
    <div class="replys">
    <table>
        <tr>
            <td colspan="3" style="text-align:center;"><h4>한줄평</h4></td>
        </tr>
        <tr>
            <td class="reviewMem"><span class="review-background">member</span></td>
            <td class="reviewContent">굿입니다.</td>
            <td class="reviewRating"><span class="review-background">4</span></td>
            <td>2014-02-03</td>
        </tr>
        <c:forEach var="review" items="${place.reviews}" varStatus="status">
            <tr>
                <td>${review.content}</td>
            </tr>
        </c:forEach>
    </table>
    </div>
    <div class="reply-form">
    <form action="/reply" method="post" name="replyForm">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <textarea name="content" class="form-control" rows="3"></textarea>&nbsp;
        <fieldset class="rating">
            <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
            <input type="radio" id="star4half" name="rating" value="4 and a half" /><label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>
            <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
            <input type="radio" id="star3half" name="rating" value="3 and a half" /><label class="half" for="star3half" title="Meh - 3.5 stars"></label>
            <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
            <input type="radio" id="star2half" name="rating" value="2 and a half" /><label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>
            <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
            <input type="radio" id="star1half" name="rating" value="1 and a half" /><label class="half" for="star1half" title="Meh - 1.5 stars"></label>
            <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
            <input type="radio" id="starhalf" name="rating" value="half" /><label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
        </fieldset><br><br>
        <button class="btn btn-primary" onclick="javascript:replyForm.submit();" id="reviewBtn">평점쓰기</button>
    </form>
    </div>
</div>
</body>
</html>
</f:view>
