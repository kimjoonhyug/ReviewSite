<%-- 
    Document   : join
    Created on : 2018. 7. 18, 오후 3:18:47
    Author     : b003
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
            <style>
                #register{
                    width:500px;
                    margin:auto;
                }
                
            </style>
        </head>
        <body>
            <div id="register">
            <form action="/register" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
                    <tr>
                        <td colsapn="2" style="display:block; margin:auto;">
                            <input type="submit" value="회원가입">&nbsp;&nbsp;
                            <input type="reset" value="취소">
                        </td>
                    </tr>
                </table>
            </form>
            </div>
        </body>
    </html>
</f:view>
