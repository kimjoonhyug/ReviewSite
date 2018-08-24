<%-- 
    Document   : map
    Created on : 2018. 7. 20, 오후 2:40:57
    Author     : b005
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a9da8e5437bc7e9b1c025282c2d5a4ea&libraries=services"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/MapSearch.js"></script>
        <script>
            $(document).ready(function () {
                getLocation();

                console.log(MapSearch.currentPosition);

            });
        </script>
        <style>
            .hover {
                background-color: gray;
            }
        </style>


    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <div class='row'>
                <div class='col-lg-5' id="map"></div>  
                <div class='offset-lg-1 col-lg-5' id="results">

                </div>
            </div>
            <div class="row mb-2">
            <button class="btn btn-success offset-md-4" id="mapbutton" onclick="searchByMap(0)">Search Map</button>
            
            <div id="mapPlace" class="offset-md-2">
                <ul id="pagination-ul" class="pagination">
                </ul>
            </div>
            </div>
            <div id="address"></div>
        </div>
    </body>
</html>
