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
                #formtable{
                    width:500px;
                    margin:auto;
                }
                td{
                    text-align:center;
                    padding:20px 0px;
                }
                .forminput{
                    background:#EEF;
                    width:200px;
                    height:30px;
                    
                }
                .commandButton{
                    width:80;
                    height:20;
                }
                #top{
                    width:100%;
                    height:110px;
                }
                #searchinput{
                    width:450px;
                    height:30px;
                }
            </style>
        </head>
        <body>
            <div id="top">
                <table>
                    <tr>
                        <td>
                            <img src="resources/images/logo.png" width="300" height="80">
                        </td>
                        <td style="padding-left:28px;">
                            <select name="kind">
                                <option>이름</option>
                                <option>카테고리</option>
                            </select>
                            <input type="text" name="search" id="searchinput" placeholder="장소검색"/>
                            <button style="height:30px;">검색</button>
                        </td>
                        <td style="padding-left: 30px;">
                            <button style="width:100px; height:50px; background:#9ACD32">지도로 찾기</button>
                        </td>
                        <td style="padding-left:20px;">
                            name 님 환영합니다. <a href="#">정보 보기</a>
                        </td>
                    </tr>
                </table>
            </div>
            <hr>
            <div id="registerForm">
            <form action="/register" method="post" id="reg">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <table id="formtable">
                    <tr>
                        <td style="background:#EEF; height:40px; padding:5px;">
                            <h1 style="text-align:center;">회원 가입</h1>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="username" class="forminput" placeholder="아이디" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="email" name="email" class="forminput" placeholder="이메일" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="password" name="password" class="forminput" placeholder="비밀번호" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="password" name="password2" class="forminput" placeholder="비밀번호확인" required>
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
