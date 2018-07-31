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
            <link rel="stylesheet" type="text/css" href="/resources/css/review.css"/>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
            <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a9da8e5437bc7e9b1c025282c2d5a4ea&libraries=services"></script>
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
            <script src="/resources/js/MapSearch.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
        </head>
        <body>
            <div id="top">
                <nav class='navbar navbar-expand-lg justify-content-center'>
                    <a href='/home'>
                        <img class='navbar-brand' src="/resources/images/logo.png" width="250" height="70">
                    </a>
                    <ul class='navbar-nav'>
                        <form class="form-inline">
                            <input class="form-control mr-sm-2" type="search" placeholder="주소를 입력하세요." aria-label="Search" id='search'>
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
                        </form>
                        <li class='nav-item' style='padding-left:40px;'>
                            <button type="button" class="btn btn-primary">지도로 찾기</button>
                        </li>
                        <li class='nav-item' style='padding-left:30px;'>
                            <%boolean isLogin = session.getAttribute("userid") != null;%>
                            <%if(!isLogin){%>
                            <button type="button" class="btn" data-toggle="modal"data-target="#loginModal">
                                로그인
                            </button>
                            <button type="button" class="btn" data-toggle="modal"data-target="#joinModal">
                                회원가입
                            </button>

                            <div class="modal fade" id="loginModal">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">Login</h4>
                                            <button type="button" class="close" data-dismiss="modal">
                                                &times;
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="/login" method="post" name="loginForm">
                                                <input type="text" name="username" class="form-control mb-2" placeholder=" 아이디" required/>
                                                <input type="password" name="password" class="form-control" placeholder=" 비밀번호" required/>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn btn-success" onclick="javascript:loginForm.submit()">로그인</button>
                                            <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="modal fade" id="joinModal">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">회원가입</h4>
                                            <button type="button" class="close" data-dismiss="modal">
                                                &times;
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="/register" method="post" name="regform">
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                <input type="text" name="username" class="form-control mb-2" placeholder=" 아이디" required autocomplete="off"/>
                                                <input type="email" name="email" class="form-control mb-2" placeholder=" 이메일" required autocomplete="off"/>
                                                <input type="password" name="password" class="form-control mb-2" id="pw1" placeholder=" 6자리 이상 16자리 이하의 비밀번호" required/>
                                                <input type="password" name="password2" class="form-control" id="pw2" placeholder=" 비밀번호확인" required/>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn btn-success" onclick="javascript:regform.submit()">가입하기</button>
                                            <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%}else{%>
                            <span>${session.getAttribute("userid")}님 환영합니다.</span>
                            <button type="button" class="btn" data-toggle="card"data-target="#profile">
                                정보수정
                            </button>
                            <div class="card" style="width:400px" id="profile">
                                <div class="card-body">
                                    <h4 class="card-title">${session.getAttribute("userid")}</h4>
                                    <p class="card-text">비밀번호 변경</p>
                                    <form>
                                        <input class="form-control" type="password" name="oldpassword" placeholder="원래 비밀번호를 입력하세요">
                                        <input class="form-control" type="password" name="password" placeholder="바꿀 비밀번호를 입력하세요"/>
                                        <input class="form-control" type="password" name="password2" placeholder="바꿀 비밀번호를 다시 입력하세요"/>
                                    </form>
                                    
                                </div>
                            </div>
                            <a href="#" class="badge badge-light">로그아웃</a>
                            <%}%>
                        </li>
                    </ul>

                </nav>
                <nav class='navbar navbar-expand-sm bg-light navbar-light justify-content-center' style="width:90%; margin:auto;">
                    <ul class="navbar-nav" id='category'>
                        <li class="nav-item">
                            <a class="nav-link" href="#">한식<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">피자<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">양식<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">중식<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">치킨<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">족발·보쌈<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">일식<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">야식<span class="sr-only">(current)</span></a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="#">분식<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">프렌차이즈<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">디저트<span class="sr-only">(current)</span></a>
                        </li>
                    </ul>
                </nav>
            </div>
        
