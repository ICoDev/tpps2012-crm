<%-- 
    Document   : add-task
    Created on : 13 груд 2012, 19:32:10
    Author     : KC
--%>
<%@page import="services.DealsService"%>
<%@page import="services.UsersService"%>
<%@page import="services.CompaniesService"%>
<%@page import="views.СompaniesView" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>CRM - Add Task page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link href="css/doc.css" rel="stylesheet">
    <link href="css/datepicker.css" rel="stylesheet">
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
      .margb20 {
        margin-bottom: 20px !important;
      }
      .margt20 {
        margin-top: 20px !important;
      }
      .date {
        margin-right: 10px;
      }
      @media (min-width: 767px) and (max-width: 979px){
        .margb0 {
          margin-bottom: 0 !important;
        }
      }
    </style>
  </head>
<body>
    <%DealsService dealsService = new DealsService();
    UsersService usersService = new UsersService();
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
        <form class="centerr form-horizontal" action="AddTask"> 
          <div class="control-group">
            <label class="control-label">Название Задачи</label>
            <div class="controls">
                <input type="text" placeholder="Введите название" name="taskName">
            </div>
          </div>
          <div class="control-group margb0">
            <!--<label class="control-label" for="">Дата</label>-->
            <div class="controls">
<!--              <div class="input-append date pull-left margb20" id="dp" data-date="30-11-2012" data-date-format="dd-mm-yyyy">
                <input class="input-block-level" size="16" type="text" value="30-11-2012" name="taskDate">
                <span class="add-on"><i class="icon-calendar"></i></span>
              </div>-->
<!--              <div class="input-append !margt20 clearfix">
                <input class="input-block-level" id="appendedDropdownButton" type="text">
                  <div class="btn-group">
                    <button class="btn dropdown-toggle" data-toggle="dropdown">
                      Время
                      <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                      <li><a href="">Весь день</a></li>
                      <li class="divider"></li>
                      <li><a href="">08:00</a></li>
                      <li><a href="">09:00</a></li>
                      <li><a href="">10:00</a></li>
                      <li><a href="">11:00</a></li>
                      <li><a href="">12:00</a></li>
                      <li><a href="">13:00</a></li>
                      <li><a href="">14:00</a></li>
                      <li><a href="">15:00</a></li>
                      <li><a href="">16:00</a></li>
                      <li><a href="">17:00</a></li>
                      <li><a href="">18:00</a></li>
                      <li><a href="">19:00</a></li>
                      <li><a href="">20:00</a></li>
                      <li><a href="">21:00</a></li>
                    </ul>
                  </div>
              </div>-->
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="inputEmail">Сделка</label>
            <div class="controls">
                <input type="text" placeholder="Поиск компании" data-provide="typeahead" data-items="4" data-source='<%=views.DealsView.getDealsDataSource(dealsService.findAll())%>' name="taskDeal">
            </div>
            <div class="controls">
                <input type="text" name="taskType" style="width: 0px; height: 0px; visibility: hidden;" />
            </div>
            
            <label class="control-label" for="inputEmail">Статус задачи</label>            
            <div class="controls">
            <select name="taskStatus">
                <option>not completed</option>
                <option>completed</option>
            </select>
            </div>            
          </div>            
          
          <div class="control-group">
              
            <%=views.UsersView.getUsersSelect( usersService.findAll(), "Ответственный")%>
            
            <label class="control-label">Тип задачи</label>
            <div class="controls">
              <div class="btn-group" data-toggle="buttons-radio">
              <button type="button" class="btn btn-primary" name="buttonCall" onclick="setTaskType('call')"><i class="icon-bell"></i> Звонок</button>
                <button type="button" class="btn btn-success" name="buttonMeeting" onclick="setTaskType('meeting')"><i class="icon-glass"></i> Встреча</button>
                <button type="button" class="btn btn-warning" name="buttonMail" onclick="setTaskType('mail')"><i class="icon-envelope"></i> Письмо</button>
              </div>
            </div>
          </div>
          <h5>Задача</h5>
          <textarea rows="3" class="input-block-level" placeholder="Введите текст задачи" name="taskText"></textarea> 
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

    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>
    <script>
    $(function(){
      var sdate = new Date();
      var dd = sdate.getDate();
      var mm = sdate.getMonth();
      var yyyy = sdate.getFullYear();
     
      $('#dp').datepicker({
        format: 'dd-mm-yyyy'
      });
    });
  </script>
  <script>
      function setTaskType(type)
      {
          document.getElementsByName("taskType")[0].value = type;
      }
  </script>
  </body>
</html>

