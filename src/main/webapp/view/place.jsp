<%-- 
    Document   : place.jsp
    Created on : 2018. 7. 19, 오후 3:30:25
    Author     : b003
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
        </head>
        <body>
            <div>
                <h1>name</h1><br>
                <h3>category</h3>
            </div>
            <div>
                <h2>별</h2><h3>평점</h3>
            </div>
            <div>
                <c:forEach var="c" items="#" varStatus="status">
                    <img src="#" style="float:left;">
                </c:forEach>
            </div>
            <div>
                <h3>map</h3>
            </div>
            <div>
                <table>
                <c:forEach var="c" items="#" varStatus="status">
                    <tr>
                        <td>${reply}</td>
                        <td><img src="#"></td>
                    </tr>
                </c:forEach>
                    <tr>
                        <td>
                            <textarea cols="15" rows="5" placeholder="댓글을 입력해주세요."></textarea>
                        </td>
                        <td>
                            <img src="#"><br>
                            <button>평점쓰기</button>
                        </td>
                    </tr>
                </table>
            </div>
        </body>
    </html>
</f:view>
