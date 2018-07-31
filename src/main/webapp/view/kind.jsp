<%-- 
    Document   : category.jsp
    Created on : 2018. 7. 23, 오후 2:14:36
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
        </head>
        <body>
            <jsp:include page="header.jsp"/>
            <h2>${place.type}</h2>
            
        </body>
    </html>
</f:view>
