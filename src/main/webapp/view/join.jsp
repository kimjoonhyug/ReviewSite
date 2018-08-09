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
            <link rel="stylesheet" type="text/css" href="resources/css/review.css"/>
            
        </head>
        <body>
            <div id="registerForm">
            <form action="/register" method="post" name="regform">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <table id="formtable">
                    <tr>
                        <td style="background:#EEF; height:40px; padding:5px;">
                            <h1 style="text-align:center;">회원 가입</h1>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="username" class="forminput" placeholder=" 아이디" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="email" name="email" class="forminput" placeholder=" 이메일" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="password" name="password" class="forminput" 
                                   id="pw1" placeholder=" 6자리 이상 16자리 이하의 비밀번호" required/><br>
                            <span id="pw1span"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="password" name="password2" class="forminput"
                                   id="pw2" placeholder=" 비밀번호확인" required/><br>
                            <span id="pw2span"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <button onclick="javascript:passwordCheck();" 
                                    style="width:200px; height:50px; background:#F88;">가입하기</button><br><br>
                            이미 아이디가 있습니까? <a href="javascript:history.back()">돌아가기</a>
                        </td>
                    </tr>
                </table>
            </form>
            </div>
        </body>
    </html>
</f:view>
