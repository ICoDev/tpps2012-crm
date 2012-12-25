<%-- 
    Document   : add-company
    Created on : 13 груд 2012, 19:06:07
    Author     : KC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>CRM - Add Company page</title>
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
            <ul class="nav pull-right">
<!--              <form class="navbar-form pull-left" name="addCompanyForm">
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
          <form class="centerr form-horizontal" action="AddCompany" name="addCompanyForm">
          <div class="control-group">
            <label class="control-label">Название компании</label>
            <div class="controls">
                <input type="text" placeholder="Введите название" name="companyName">
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">Телефон</label>
            <div class="controls">
              <input type="text" placeholder="Введите телефон" name="companyPhone">
            </div>
          </div>
          <h5>Адрес</h5>
          <textarea rows="3" class="input-block-level" placeholder="Введите адрес компании" name="companyAddress"></textarea> 
          
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
  </body>
</html>

