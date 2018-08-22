<%-- 
    Document   : header
    Created on : 2018. 7. 20, 오후 1:14:59
    Author     : b003
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
            <script src="/resources/js/review.js"></script>
            <script src="/resources/js/nav-search.js"></script>
        </head>
        <body>
            <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
            <div class="container">

                <div style="text-align:right; margin:4px;">
                    <sec:authorize access="!isAuthenticated()">
                        <a href="#" class="badge badge-light" data-toggle="modal"data-target="#loginModal">
                            로그인
                        </a>
                        <a href='#' class="badge badge-light" data-toggle="modal"data-target="#joinModal">
                            회원가입
                        </a>
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
                                        <form action="/home" method="post" name="loginForm">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
                                            <input type="password" name="password" class="form-control mb-2" id="joinpw" placeholder=" 비밀번호" required/>
                                            <input type="password" name="password2" class="form-control" id="joinpw2" placeholder=" 비밀번호확인" required/>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button class="btn btn-success" onclick="javascript:regform.submit()">가입하기</button>
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </sec:authorize>

                    <sec:authorize access="isAuthenticated()"> <span><sec:authentication property="principal.username" />님 환영합니다.</span> 
                        <a href='#' class="badge badge-light" data-toggle="modal"data-target="#modifyModal">
                            정보수정
                        </a>
                        <div class="modal fade" id="modifyModal">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">비밀번호 변경</h4>
                                        <button type="button" class="close" data-dismiss="modal">
                                            &times;
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="/register" method="post" name="regform">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <input type="password" name="password" class="form-control mb-2"  placeholder=" 비밀번호" required/>
                                            <input type="password" name="password2" class="form-control"  placeholder=" 비밀번호확인" required/>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button class="btn btn-success" onclick="javascript:passwordCheck()">가입하기</button>
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <form action="/logout" method="post" id="logout">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <a href="#" onclick="$('#logout').submit()" class="badge badge-light">로그아웃</a>
                        </form>
                        <!-- 운영 관리 모달 -->
                        <sec:authorize access="hasAuthority('ADMIN')">
                            <a href="#" class="badge badge-light" data-toggle="modal"data-target="#adminModal">
                                ADMIN
                            </a>
                        </sec:authorize>
                        <div class="modal fade" id="adminModal">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">운영 관리</h4>
                                        <button type="button" class="close" data-dismiss="modal">
                                            &times;
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <button class="btn" data-toggle="modal"data-target="#newPlace">가게 등록</button>

                                        <button class="btn" data-toggle="modal"data-target="#deletePlace">가게 삭제</button>

                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 가게 등록하기 모달 -->
                        <div class="modal fade" id="newPlace">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">가게 등록</h4>
                                        <button type="button" class="close" data-dismiss="modal">
                                            &times;
                                        </button>
                                    </div>
                                    <div class="modal-body">

                                        <form action="${contextPath}/place" method="post" name="newPlaceForm">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <input type="text" name="name" class="form-control mb-2" placeholder=" 새 가게 이름" requried/>
                                            <input type="text" name="franchise_name" class="form-control mb-2" placeholder=" 프랜차이즈 이름" />
                                            <select multiple class="form-control" name="type">
                                                <option>한식</option>
                                                <option>양식</option>
                                                <option>중식</option>
                                                <option>치킨</option>
                                                <option>족발보쌈</option>
                                                <option>일식</option>
                                                <option>야식</option>
                                                <option>분식</option>
                                                <option>프랜차이즈</option>
                                                <option>디저트</option>
                                            </select><br>
                                            <input type="text" name="phone" class="form-control mb-2" placeholder=" 전화번호 ex)000-0000-0000" required/>
                                            <input type="text" name="hours" class="form-control mb-2" placeholder=" 영업시간 ex)09:00 - 20:00" required/>
                                            <input type="text" name="fullAddress" class="form-control" placeholder=" 주소" required/>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-success" onclick="newPlaceForm.submit()">등록</button>
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 가게 삭제하기 모달 -->
                        <div class="modal fade" id="deletePlace">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">삭제할 가게 id를 입력하세요</h4>
                                        <button type="button" class="close" data-dismiss="modal">
                                            &times;
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="${contextPath}/place/delete" method="post" name="deletePlace">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <input class="form-control" type="text" name="id"/>
                                        </form>
                                        
                                    </div>
                                    <div class="modal-footer">
                                        <button class="btn btn-success" onclick="deletePlace.submit()">삭제하기</button>
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </sec:authorize>
                </div>



                <nav class='navbar navbar-expand-sm bg-light navbar-light justify-content-center'>
                </nav>
                <ul>
                    <li style="width:35%;">
                        <a href='/home'>
                            <img class='navbar-brand' src="/resources/images/logo.png" style="width:100%;">
                        </a>
                    </li>
                    <li>
                        <form action="/search" method="get" class="form-inline" id="search-form">
                            <input class="form-control mr-sm-2" id='search-input' name='search' type="search" placeholder="동을 입력하세요." aria-label="Search" />
                            <button class="btn btn-outline-success my-2 my-sm-0" type='submit' id="search-button">검색</button>
                        </form>
                    </li>
                    <li>
                        <button class="btn btn-success" onclick="javascript:location.href = '${pageContext.request.contextPath}/map'">지도보기</button>
                    </li>
                    <li>
                        <div class="dropdown">
                            <button type="button" class="btn btn-info btn-lg dropdown-toggle" data-toggle="dropdown">
                                menu
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="/kind/한식">한식</a>
                                <a class="dropdown-item" href="/kind/피자양식">양식</a>
                                <a class="dropdown-item" href="/kind/중식">중식</a>
                                <a class="dropdown-item" href="/kind/치킨">치킨</a>
                                <a class="dropdown-item" href="/kind/족발보쌈">족발보쌈</a>
                                <a class="dropdown-item" href="/kind/일식돈까스">일식</a>
                                <a class="dropdown-item" href="/kind/야식">야식</a>
                                <a class="dropdown-item" href="/kind/분식">분식</a>
                                <a class="dropdown-item" href="/kind/프랜차이즈">프랜차이즈</a>
                                <a class="dropdown-item" href="/kind/디저트">디저트</a>
                            </div>
                        </div>
                    </li>
                </ul>
                <nav class='navbar navbar-expand-sm bg-light navbar-light justify-content-center'>
                </nav>
            </div>

