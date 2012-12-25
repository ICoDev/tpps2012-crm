<%-- 
    Document   : add-contact
    Created on : 13 груд 2012, 19:19:33
    Author     : KC
--%>

<%@page import="services.ContactsService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="services.CompaniesService"%>
<%@page import="views.СompaniesView"%>

<%@page import="services.UsersService"%>
<%@page import="views.UsersView"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>CRM - Add Contacts page</title>
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
    <style type="text/css">
      .centerr {
        max-width: 600px;
        margin: 0 auto;
      }
      .centerr .control-group {
        margin-bottom: 10px;
      }
      .margb10 {
        margin-bottom: 10px !important;
      }
    </style>
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
            <ul class="nav pull-right">
<!--              <form class="navbar-form pull-left">
                  <button type="submit" class="btn btn-inverse">Сохранить</button>
                  <button type="submit" class="btn">Отмена</button>
              </form>-->
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
    <div class="container-fluid">
      <div class="row-fluid">
        <form class="centerr form-horizontal" action="AddContact">
        <input class="input-block-level margb10" type="text" placeholder="Имя Фамилия" name="contactName">            
        
          <%=views.СompaniesView.getCompaniesSelect(companiesService.findAll(), "Компания")%>    
        
          <div class="control-group">
            <label class="control-label" for="inputPassword">Должность</label>
            <div class="controls">
                <input class="input-block-level" type="text" name="contactPost">
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="inputPassword">Телефон</label>
            <div class="controls">
                <input class="input-block-level" id="appendedInput" type="text" name="contactPhone">
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="inputPassword">Email</label>
            <div class="controls">
              <div class="input-prepend">
                <span class="add-on">@</span>
                <input class="input-block-level" id="prependedInput" type="text" style="width:130%" name="contactMail">
              </div>
            </div>
          </div>
          
          <%=views.UsersView.getUsersSelect(usersService.findAll(), "Ответственный")%>
          
          <h5>Адрес</h5>
          <textarea rows="3" class="input-block-level" placeholder="Введите адрес" name="contactAdress"></textarea>
          <h5>Дополнительная информация</h5>
          <textarea rows="2" class="input-block-level" placeholder="Введите описание" name="contactDescription"></textarea> 
          <h5>Теги</h5>
          <input type="text" class="input-block-level" placeholder="Теги" name="contactTags">
          
          <div style="float: right; margin-top: 20px;">
            <button type="submit" class="btn btn-inverse">Сохранить</button>
            <button type="submit" class="btn" name="cancel">Отмена</button>
          </div>
          <div style="clear: both"></div>
          </form>
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
