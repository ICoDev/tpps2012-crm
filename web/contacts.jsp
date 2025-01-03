<%-- 
    Document   : contacts
    Created on : 13 груд 2012, 19:33:54
    Author     : KC
--%>

<%@page import="services.UsersService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="services.CompaniesService"%>
<%@page import="views.СompaniesView"%>
<%@page import="services.ContactsService"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>CRM - Contacts page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link href="css/doc.css" rel="stylesheet">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <!-- Fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  </head>
<body>
    <%UsersService usersService = new UsersService();%>
    <%CompaniesService companiesService = new CompaniesService();%>
    <%ContactsService contactsService = new ContactsService();%>
    
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
              <li><a href="events.jsp">События</a></li>
              <li><a href="deals.jsp">Сделки</a></li>
              <li><a href="companies.jsp">Компании</a></li>
              <li><a href="tasks.jsp">Задачи</a></li>
              <li class="active"><a href="contacts.jsp">Контакты</a></li>
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
          <p class="lead">Контакты</p>
          <p><a href="add-contact.jsp" class="btn btn-block" target="_blank">Добавить контакт</a></p>
          <form action="SearchContacts">
          <div class="input-append">
            <input type="text" name="name" placeholder="Поиск контактов">
            <button class="btn btn-primary" type="submit"><i class="icon-search icon-white"></i></button>
          </div>
          </form>
          <ul class="nav nav-pills nav-stacked">
              <li class="active"><a href="contacts.jsp">Все контакты</a></li>
              <li><a href="contacts.jsp?filter=withoutTasks">Контакты без задач</a></li>
              <!--<li><a href="contacts.jsp?filter=outTimeTasks">Контакты c просроченными задачами</a></li>-->
          </ul>
<!--        <h5>Дополнительная фильтрация</h5>
<form>-->
<!--<label>Создано</label>
      <select class="input-block-level">
            <option>За все время</option>
            <option>За сегодня</option>
            <option>За 3 дня</option>
            <option>За неделю</option>
            <option>За месяц</option>
            <option>За полгода</option>
      </select>
<label>Задачи</label>
   <select class="input-block-level">
      <option>Не учитывать</option>
      <option>На сегодня</option>
      <option>На завтра</option>
      <option>На этой неделе</option>
      <option>В этом месяце</option>
      <option>В этом квартале</option>
      <option>Нет задач</option>
      <option>Просрочены</option>        
   </select>-->
<!--<h5>Ответственный</h5>

  <%=views.UsersView.getUsersSelect(usersService.findAll(), "")%>

  <h5>Статус связанной сделки:</h5>
  <label class="checkbox">
    <input type="checkbox" value=""><span class="label label-null">Контакты без сделок</span>
  </label>
  <label class="checkbox">
    <input type="checkbox" value=""><span class="label label-info">Первичный контакт</span>
  </label>
  <label class="checkbox">
    <input type="checkbox" value=""><span class="label label-warning">Переговоры</span>
  </label>
  <label class="checkbox">
    <input type="checkbox" value=""><span class="label label-inverse">Принимают решение</span>
  </label>
  <label class="checkbox">
    <input type="checkbox" value=""><span class="label label-important">Согласование договора</span>
  </label>
  <label class="checkbox">
    <input type="checkbox" value=""><span class="label label-success">Успешно реализовано</span>
  </label>
  <label class="checkbox">
    <input type="checkbox" value=""><span class="label">Закрыто и не реализовано</span>
  </label>
  <h5>Ответственный</h5>

  <%=views.UsersView.getUsersSelect(usersService.findAll(), "")%>
  
  <p>
    <button type="submit" class="btn btn-primary">Найти</button>
    <button type="button" class="btn">Отменить</button>
  </p>
</form>-->
        </div>
        <div class="span9">
              
            <%if ( request.getParameter("id") != null )
            {
                out.print( views.ContactsView.printContactInfo( 
                        contactsService.getContactById( 
                        Integer.parseInt( request.getParameter("id") )
                        ) ) );
            }
            else if ( request.getParameter("filter") != null )
            {
                out.println( views.ContactsView.getContactsTable( contactsService.getContactsWithoutTasks() ) );
            }
            else
            {
                out.print(views.ContactsView.getContactsTable(contactsService.findAll()));
            }
            %>
              <%--<%=(views.ContactsView.getContactsTable(contactsService.findAll()))%>--%>
              
       </div>
      </div>
      <hr>
      <footer>
        <p>&copy; Company 2012</p>
      </footer>
    </div>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>

