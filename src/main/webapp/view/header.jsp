<%-- 
    Document   : header
    Created on : 2018. 7. 20, 오후 1:14:59
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
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
        </head>
        <body>
            <div id="top">
                <nav class='navbar navbar-expand-lg navbar-light' style='background:#FFF'>

                    <a href='/home' style="padding-left:90px;">
                        <img class='navbar-brand' src="resources/images/logo.png" width="260" height="70">
                    </a>
                    <ul class='navbar-nav'>
                        <form class="form-inline">
                            <input class="form-control mr-sm-2" type="search" placeholder="주소를 입력하세요." aria-label="Search" id='search'>
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
                        </form>
                        <li class='nav-item'>
                            <button type="button" class="btn btn-primary">지도로 찾기</button>
                        </li>
                        <li class='nav-item'>
                            <span class="navbar-text">name님 환영합니다.</span>
                            <a href="#" class="badge badge-light">정보보기</a>
                            <a href="#" class="badge badge-light">로그아웃</a>
                        </li>
                    </ul>

                </nav>
                <nav class='navbar navbar-expand-lg navbar-light'>
                    <div id="category">
                    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                        <li class="nav-item active">
                            <a class="nav-link" href="#" id="korean">한식<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="#" id="chineses">중식<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="#" id="japanese">일식<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="#" id="western">양식<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="#" id="fastfood">패스트푸드<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="#" id="bunsik">분식<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="#" id="bakery">베이커리<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="#" id="cafeBar">카페/주점<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="#" id="other">기타/세계<span class="sr-only">(current)</span></a>
                        </li>
                    </ul>
                    </div>
                </nav>
            </div>
            <div>
                <nav></nav>
            </div>
        </body>
    </html>
</f:view>
