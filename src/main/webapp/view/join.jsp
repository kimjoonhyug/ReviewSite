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
                #registerForm{
                    width:500px;
                    margin:auto;
                    background:#DDF;
                    border:1px solid #99F;
                }
                table{
                    width:500px;
                    margin:auto;
                }
                td{
                    text-align:center;
                    padding:20px 0px;
                }
                input{
                    background:#EEF;
                    width:200px;
                    height:30px;
                }
                .commandButton{
                    width:80;
                    height:20;
                }
            </style>
        </head>
        <body>
            <div>
                <img src="resources/images/logo.png" width="150" height="100">
            </div>
            <div id="registerForm">
                
            <form action="/register" method="post" id="reg">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <table>
                    <tr>
                        <td style="background:#EEF; width:100px;"><h1 style="text-align:center;">회원 가입</h1></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="username" id="username" placeholder="아이디" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="email" name="email" id="email" placeholder="이메일" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="password" name="password" id="password" placeholder="비밀번호" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="password" name="password2" id="password2" placeholder="비밀번호확인" required>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <button onclick="javascript:document.reg.submit()" 
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
