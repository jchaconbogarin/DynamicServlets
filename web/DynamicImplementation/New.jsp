<%-- 
    Document   : New.jsp
    Created on : Apr 25, 2015, 12:12:53 PM
    Author     : Boga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>This is the New Page</h1>
        <form method="post" action="DynamicServletImplementation">
            <input type="hidden" name="action" value="create">
            <button type="submit">Click here to post</button>
        </form>
    </body>
</html>
