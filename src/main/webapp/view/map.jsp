<%-- 
    Document   : map
    Created on : 2018. 7. 20, 오후 2:40:57
    Author     : b005
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a9da8e5437bc7e9b1c025282c2d5a4ea&libraries=services"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="resources/js/MapSearch.js"></script>
            
        
    </head>
    <body>
        <div id="map" style="width:100%;height:350px;"></div>
        <div id="address"></div>

        <button onclick="searchByMap()">Search Map</button>

    </body>
</html>
