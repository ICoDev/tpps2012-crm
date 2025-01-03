<%-- 
    Document   : events
    Created on : 13 груд 2012, 19:34:40
    Author     : KC
--%>

<%@page import="domain.Events"%>
<%@page import="java.util.List"%>
<%@page import="java.io.ObjectInputStream"%>
<%@page import="services.UsersService"%>
<%@page import="services.EventsService"%>
<%@page import="views.EventsView"%>
<%@page import="views.UsersView"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>CRM - Event page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link href="css/doc.css" rel="stylesheet">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    ;<![endif]-->
    <!-- Fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  </head>
<body>
    <%UsersService usersService = new UsersService();
    EventsService eventsService= new EventsService();
    %>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="index.jsp">jCRM</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="events.jsp">События</a></li>
              <li><a href="deals.jsp">Сделки</a></li>
              <li><a href="companies.jsp">Компании</a></li>
              <li><a href="tasks.jsp">Задачи</a></li>
              <li><a href="contacts.jsp">Контакты</a></li>
            </ul>
            <ul class="nav pull-right">
                    <li id="fat-menu" class="dropdown">
                      <a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">Управление<b class="caret"></b></a>
                      <ul class="dropdown-menu" role="menu" aria-labelledby="drop3">                     
                        <li><a tabindex="-1" href="#">Личный аккаунт</a></li>
                        <li><a tabindex="-1" href="#">Управление пользователями</a></li>
                        <li class="divider"></li>
                        <li><a tabindex="-1" href="#logout">Выход</a></li>
                      </ul>
                    </li>
            </ul>
            
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">
      <div class="row">
        <div class="span3">
          <p class="lead">Последние изменения</p>
          <ul class="nav nav-pills nav-stacked">
              <li class="active"><a href="events.jsp">Все</a></li>
              <li><a href="events.jsp?time=last3days">За последние 3 дня</a></li>
              <li><a href="events.jsp?time=lastweek">За последнюю неделю</a></li>
          </ul>
    <form class="form-inline!" action="FilterEvents">
      <label>Период</label>
      <select class="input-block-level" name="period">
            <option>За все время</option>
            <option>За сегодня</option>
            <option>За 3 дня</option>
            <option>За неделю</option>
            <option>За месяц</option>
            <option>За полгода</option>
      </select>
      
  <h5>Объект события</h5>
  <label class="checkbox"><i class="icon-fire"></i>
      <input type="checkbox" value="true" name="deal">Сделка
  </label>
  <label class="checkbox"><i class="icon-user"></i>
    <input type="checkbox" value="true" name="contact">Контакт
  </label>
  <label class="checkbox"><i class="icon-cog"></i>
    <input type="checkbox" value="true" name="task">Задача
  </label>
  <label class="checkbox">
    <input type="checkbox" value="true" name="noObject">Нет обьекта
  </label>
  <br/>
  <!--<h5>Инициатор</h5>
  
  <%=UsersView.getUsersSelect(usersService.findAll(), "")%>
  -->
  
  <p>
    <button type="submit" class="btn btn-primary">Найти</button>
    <button type="button" class="btn">Отменить</button>
  </p>
</form>

        </div>
        <div class="span9">
            
            <%
            //Have some parameters
            if ( request.getParameter("time") != null )
            {
                //Last 3 days href was clicked
                if ( request.getParameter("time").equals("last3days") )
                {
                    List<Events> list = eventsService.getEventsForLastNDays(3);
                    if ( list.isEmpty() )
                    {
                        out.println("<b>Последние 3 дня ничего не происходило...</b>");
                    }
                    else
                    {
                        out.println( EventsView.getEventsTable( list ) );
                    }
                }
                //Last 7 days href was clicked
                else if ( request.getParameter("time").equals("lastweek") )
                {
                    List<Events> list = eventsService.getEventsForLastNDays(7);
                    if ( list.isEmpty() )
                    {
                        out.println("<b>Последние 7 дней ничего не происходило...</b>");
                    }
                    else
                    {
                        out.println( EventsView.getEventsTable( list ) );
                    }
                }
            }
            //No parameters, or some shit
            else
            {
                out.println(EventsView.getEventsTable( eventsService.findAll() ));
            }
            %>
              <%--<%=EventsView.getEventsTable( eventsService.findAll() )%>--%>
              
       </div>
      </div>

      <hr>

      <footer>
        <p>&copy; Company 2012</p>
      </footer>

    </div> <!-- /container -->


    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>

