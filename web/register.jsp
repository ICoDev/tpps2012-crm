<%-- 
    Document   : register
    Created on : 13 груд 2012, 18:53:30
    Author     : KC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>CRM - Register Page</title>
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
              <li class="active"><a href="register.jsp">Регистрация</a></li>
          </ul>
            <form class="navbar-form pull-right">
              <input class="span2" type="text" placeholder="Email">
              <input class="span2" type="password" placeholder="Password">
              <button type="submit" class="btn btn-inverse">Вход</button>
            </form>
            
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
    <div class="container-fluid">
      <div class="row-fluid">
        <form class="centerr form-horizontal">
          <input class="input-block-level margb20" type="text" placeholder="Имя Фамилия">
          <div class="control-group">
            <label class="control-label">Логин</label>
            <div class="controls">
              <input type="text" placeholder="Введите логин" class="input-block-level">
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">Email</label>
            <div class="controls">
              <div class="input-prepend">
                <span class="add-on">@</span>
                <input class="input-block-level" id="prependedInput" type="text" style="width:120%">
              </div>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">Пароль</label>
            <div class="controls">
              <input type="text" placeholder="Введите пароль" class="input-block-level">
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">Повторите пароль</label>
            <div class="controls">
              <input type="password" placeholder="Введите пароль еще раз" class="input-block-level">
            </div>
          </div>
          <div class="form-actions">
            <button type="submit" class="btn btn-primary">Зарегистрироватся</button>
            <button type="button" class="btn">Отмена</button>
          </div>
          </form>
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

