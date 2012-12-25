<%-- 
    Document   : companies
    Created on : 13 груд 2012, 19:33:32
    Author     : KC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="services.CompaniesService"%>
<%@page import="views.СompaniesView"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>CRM - Companies page</title>
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
    <%CompaniesService companiesServices = new CompaniesService();%>
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
              <li class="active"><a href="companies.jsp">Компании</a></li>
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
          <p class="lead">Компании</p>
          <p><a href="add-company.jsp" class="btn btn-block" target="_blank">Добавить компанию</a></p>
          <form>
            <div class="input-append">
              <input type="text" placeholder="Поиск компании" data-provide="typeahead" data-items="4" data-source='["JBS","Beatom","Litera Corp","Nork","Daota","Ohio","Rhodeands","Utaha INC","Vermont","Wyomin"]'>
              <button class="btn btn-primary" type="submit"><i class="icon-search icon-white"></i></button>
            </div>
          </form>
        </div>
        <div class="span9">
            <table class="table">
              <thead>
                <tr>
                  <th>Название компании</th>
                  <th>Телефон</th>
                  <th>Адрес</th>
                </tr>
              </thead>
              
              <%=(views.СompaniesView.getCompaniesTable(companiesServices.findAll()))%>
              
            </table>
       </div>
      </div>
      <hr>
      <footer>
        <p>&copy; Company 2012</p>
      </footer>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>

