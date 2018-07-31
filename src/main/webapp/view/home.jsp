<%-- 
    Document   : home
    Created on : 2018. 7. 20, 오전 10:49:44
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
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
            <link rel="stylesheet" type="text/css" href="resources/css/review.css">
        </head>
        <body>
            <jsp:include page="header.jsp"/>
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <ul class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ul>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="resources/images/t1.jpg" style="width:1100; height:500;"/>
                    </div>
                    <div class="carousel-item">
                        <img src="resources/images/t2.jpg" style="width:1100; height:500;"/>
                    </div>
                    <div class="carousel-item">
                        <img src="resources/images/t3.jpg" style="width:1100; height:500;"/>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#myCarousel" data-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </a>
            </div>
            <div id="recommend" class="mt-5"">
                <p style="padding-left:20px; font-size:1.6em;"><i><b><span style="color: crimson">오늘의</span></b> 추천맛집</i></p>
                <figure>
                    <a href="#"><img src="resources/images/test.png" class="rounded"/></a>
                    <figcaption>짜장집</figcaption>
                </figure>
                <figure>
                    <a href="#"><img src="resources/images/test.png" class="rounded"/></a>
                    <figcaption>갈비탕</figcaption>
                </figure>
                <figure>
                    <a href="#"><img src="resources/images/test.png" class="rounded"/></a>
                    <figcaption>맥도날드</figcaption>
                </figure>
                <figure>
                    <a href="#"><img src="resources/images/test.png" class="rounded"/></a>
                    <figcaption>롯데리아</figcaption>
                </figure>
                <figure>
                    <a href="#"><img src="resources/images/test.png" class="rounded"/></a>
                    <figcaption>맘스터치</figcaption>
                </figure>
                <figure>
                    <a href="#"><img src="resources/images/test.png" class="rounded"/></a>
                    <figcaption>맘스터치</figcaption>
                </figure>
                <figure>
                    <a href="#"><img src="resources/images/test.png" class="rounded"/></a>
                    <figcaption>맘스터치</figcaption>
                </figure>
                <figure>
                    <a href="#"><img src="resources/images/test.png" class="rounded"/></a>
                    <figcaption>맘스터치</figcaption>
                </figure>
                <figure>
                    <a href="#"><img src="resources/images/test.png" class="rounded"/></a>
                    <figcaption>
                        감자탕
                    </figcaption>
                </figure><figure>
                    <a href="#"><img src="resources/images/test.png" class="rounded"/></a>
                    <figcaption>피자나라치킨공주</figcaption>
                </figure>
            </div>
            
        </body>
    </html>
</f:view>
