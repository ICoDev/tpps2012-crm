<%-- 
    Document   : 1
    Created on : 19 груд 2012, 17:08:33
    Author     : KC
--%>

<%@page import="com.sun.jersey.spi.inject.ClientSide"%>
<%@page import="domain.Events"%>
<%@page import="services.EventsService"%>
<%@page import="java.util.Date"%>
<%@page import="services.TasksService"%>
<%@page import="domain.Tasks"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.hibernate.Session"%>
<%@page import="domain.Deals"%>
<%@page import="services.DealsService"%>
<%@page import="domain.Users"%>
<%@page import="services.UsersService"%>
<%@page import="domain.Companies"%>
<%@page import="java.util.List"%>
<%@page import="services.CompaniesService"%>
<%@page import="javax.jws.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>        
    </head>
    <body>
        <h1>Hello World!</h1>

        <div id="ass"></div>
        <form action="http://localhost:8080/crm/CompaniesService?hello" method="get">
            <button type="submit">ass</button>
        </form>
    </body>
</html>
