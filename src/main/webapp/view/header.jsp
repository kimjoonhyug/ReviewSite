<%-- 
    Document   : header
    Created on : 2018. 7. 20, 오후 1:14:59
    Author     : b003
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
            <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/review.css"/>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
            <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a9da8e5437bc7e9b1c025282c2d5a4ea&libraries=services"></script>
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
            <script src="${contextPath}/resources/js/MapSearch.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
            <script src="${contextPath}/resources/js/review.js"></script>
            <script src="${contextPath}/resources/js/Address.js"></script>
            <script src="${contextPath}/resources/js/nav-search.js"></script>
            <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
        </head>
        <body>
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
                                        <form action="${contextPath}/home" method="post" name="loginForm">
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
                                        <form action="${contextPath}/register" method="post" name="regform">
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

                            <sec:authorize access="hasAuthority('ADMIN')">
                                <a href="#" class="badge badge-light" data-toggle="modal"data-target="#adminModal">
                                    ADMIN
                                </a>
                            </sec:authorize>
                        
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
                                            <form action="${contextPath}/register" method="post" name="regform">
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
                            
                            <form action="${contextPath}/logout" method="post" id="logout" style="display:inline-block;">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <a href="#" onclick="$('#logout').submit()" class="badge badge-light">로그아웃</a>
                            </form>
                            <!-- 운영 관리 모달 -->

                            <div class="modal fade" id="adminModal">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">운영 관리</h4>
                                            <button type="button" class="close" data-dismiss="modal">
                                                &times;
                                            </button>
                                        </div>
                                        <div class="modal-body" style="text-align:center;">
                                            <button class="btn btn-lg" data-toggle="modal"data-target="#newPlace">가게 등록</button>

                                            <button class="btn btn-lg" data-toggle="modal"data-target="#deletePlace">가게 삭제</button>

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

                                            <form  enctype="multipart/form-data" action="${contextPath}/place" method="post" name="newPlaceForm">
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                <input type="text" name="name" class="form-control mb-2" placeholder=" 새 가게 이름" requried/>
                                                <input type="text" name="franchise_name" class="form-control mb-2" placeholder=" 프랜차이즈 이름" />
                                                <input type="file" name="file" accept=".jpg, .jpeg, .png, .gif"/>
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
                                                <div class="form-group">
                                                    <input type="text" name="phone" class="form-control mb-2" placeholder=" 전화번호 ex)000-0000-0000" required/>
                                                </div>
                                                <div class="form-group">
                                                    <input type="text" name="hours" class="form-control mb-2" placeholder=" 영업시간 ex)09:00 - 20:00" required/>
                                                </div>
                                                <input type="button" onclick="sample2_execDaumPostcode()" value="주소 검색"><br>
                                                <div class="form-inline">

                                                    <input type="hidden" name="lat" id="lat"/>
                                                    <input type="hidden" name="lng" id="lng"/>
                                                    <input type="hidden" class="form-control" name="zipcode" id="sample2_postcode">
                                                    <input type="text" class="col-md-2 form-control" name="sido" id="sido" readonly>
                                                    <input type="text" class="col-md-2 form-control" name="sigungu" id="sigungu" readonly>
                                                    <input type="text" class="col-md-3 form-control" name="dong" id="dong" readonly>
                                                    <input type="text" class="col-md-5 form-control" name="doro" id="doro" readonly>

                                                </div>
                                                <input type="text" class="form-control" name="detail" placeholder="상세주소를 입력해주세요"/>

                                                <!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
                                                <div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
                                                    <img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
                                                </div>
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
                            <a href='${contextPath}/home'>
                                <img class='navbar-brand' src="${contextPath}/resources/images/logo.png" style="width:100%;">
                            </a>
                        </li>
                        <li>
                            <form action="${contextPath}/search" method="get" class="form-inline" id="search-form">
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
                                    <a class="dropdown-item" href="${contextPath}/kind/한식">한식</a>
                                    <a class="dropdown-item" href="${contextPath}/kind/피자양식">양식</a>
                                    <a class="dropdown-item" href="${contextPath}/kind/중식">중식</a>
                                    <a class="dropdown-item" href="${contextPath}/kind/치킨">치킨</a>
                                    <a class="dropdown-item" href="${contextPath}/kind/족발보쌈">족발보쌈</a>
                                    <a class="dropdown-item" href="${contextPath}/kind/일식돈까스">일식</a>
                                    <a class="dropdown-item" href="${contextPath}/kind/야식">야식</a>
                                    <a class="dropdown-item" href="${contextPath}/kind/분식">분식</a>
                                    <a class="dropdown-item" href="${contextPath}/kind/프랜차이즈">프랜차이즈</a>
                                    <a class="dropdown-item" href="${contextPath}/kind/디저트">디저트</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <nav class='navbar navbar-expand-sm bg-light navbar-light justify-content-center'>
                    </nav>
                </div>
                <script>
                    var element_layer = document.getElementById('layer');

                    function closeDaumPostcode() {
                        element_layer.style.display = 'none';
                    }

                    function sample2_execDaumPostcode() {
                        new daum.Postcode({
                            oncomplete: function (data) {
                                document.getElementById('sample2_postcode').value = data.zonecode; //5자리 새우편번호 사용
                                document.getElementById('sido').value = data.sido;
                                document.getElementById('sigungu').value = data.sigungu;
                                document.getElementById('doro').value = data.roadname;
                                document.getElementById('dong').value = data.bname;
                                element_layer.style.display = 'none';
                                setLatLng(data.roadAddress);
                            },
                            width: '100%',
                            height: '100%',
                            maxSuggestItems: 5
                        }).embed(element_layer);

                        element_layer.style.display = 'block';

                        initLayerPosition();
                    }
                    function initLayerPosition() {
                        var width = 300; //우편번호서비스가 들어갈 element의 width
                        var height = 400; //우편번호서비스가 들어갈 element의 height
                        var borderWidth = 5; //샘플에서 사용하는 border의 두께

                        element_layer.style.width = width + 'px';
                        element_layer.style.height = height + 'px';
                        element_layer.style.border = borderWidth + 'px solid';
                        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width) / 2 - borderWidth) + 'px';
                        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height) / 2 - borderWidth) + 'px';
                    }
                </script>
