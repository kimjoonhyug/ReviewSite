<%-- 
    Document   : join
    Created on : 2018. 7. 18, 오후 3:18:47
    Author     : b003
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
        </head>
        <body>
            <form action="/register" method="post">
                <table>
                    <tr>
                        <td><label for="username">아이디</label></td>
                        <td><input type="text" name="username" id="username" required/></td>
                    </tr>
                    <tr>
                        <td><label for="email">이메일</label></td>
                        <td><input type="email" name="email" id="email"/></td>
                    </tr>
                    <tr>
                        <td><label for="password">비밀번호</label></td>
                        <td><input type="password" name="password" id="password" required/></td>
                    </tr>
                </table>
            </form>
        </body>
    </html>
</f:view>
