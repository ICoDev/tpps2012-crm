/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.Tasks;
import hibernate.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import services.UsersService;
import views.TasksView;
import views.UsersView;

/**
 *
 * @author KC
 */
@WebServlet(name = "FilterTasks", urlPatterns = {"/FilterTasks"})
public class FilterTasks extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //some unreal restriction;
        LogicalExpression orExpression = Restrictions.and( Restrictions.lt("id", null), Restrictions.lt("id", null) );
        
        //nothing were selected, but submit button was pressed;
        if (    !"true".equals(request.getParameter("call")) &&
                !"true".equals(request.getParameter("meeting")) &&
                !"true".equals(request.getParameter("mail")) &&
                !"true".equals(request.getParameter("completed")) &&
                !"true".equals(request.getParameter("notCompleted")) &&
                "".equals(request.getParameter("usersSelect"))
            )
        {
            response.sendRedirect("tasks.jsp");
        }
        else 
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Tasks.class);
            
            //checking checkboxes;
            if ("true".equals(request.getParameter("call") ) )
            {
                orExpression = Restrictions.or( orExpression, Restrictions.eq( "tasksType", "call") );
            }
            if ("true".equals(request.getParameter("meeting") ) )
            {
                orExpression = Restrictions.or( orExpression, Restrictions.eq( "tasksType", "meeting") );
            }
            if ("true".equals(request.getParameter("mail") ) )
            {
                orExpression = Restrictions.or( orExpression, Restrictions.eq( "tasksType", "message") );
            }
            if ("true".equals(request.getParameter("completed") ) && 
                "true".equals(request.getParameter("notCompleted") )
                )
            {
                //ignoring
            }
            else
            {
                if ("true".equals(request.getParameter("completed") ) )
                {
                    orExpression = Restrictions.and( orExpression, Restrictions.eq( "status", "completed") );
                }
                else if ("true".equals(request.getParameter("notCompleted") ) )
                {
                    orExpression = Restrictions.and( orExpression, Restrictions.eq( "status", "not completed") );
                }
            }
            
            criteria.add(orExpression);
            
            //check, if responsible was selected;
            if ( !"".equals(request.getParameter("usersSelect") ) )
            {
                criteria.add( Restrictions.eq( "responsible", request.getParameter("usersSelect")).ignoreCase() );
            }            

            List <Tasks> list = criteria.list();
            session.close();
                    
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                UsersService usersService = new UsersService();
                /* TODO output your page here. You may use following sample code. */
                out.println(
"<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"  <head>\n" +
"    <meta charset=\"utf-8\">\n" +
"    <title>CRM - Tasks page</title>\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <meta name=\"description\" content=\"\">\n" +
"    <meta name=\"author\" content=\"\">\n" +
"    <link href=\"css/bootstrap.css\" rel=\"stylesheet\">\n" +
"    <link href=\"css/bootstrap-responsive.css\" rel=\"stylesheet\">\n" +
"    <link href=\"css/doc.css\" rel=\"stylesheet\">\n" +
"    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->\n" +
"    <!--[if lt IE 9]>\n" +
"      <script src=\"http://html5shim.googlecode.com/svn/trunk/html5.js\"></script>\n" +
"    <![endif]-->\n" +
"    <!-- Fav and touch icons -->\n" +
"    <link rel=\"shortcut icon\" href=\"../assets/ico/favicon.ico\">\n" +
"    <link rel=\"apple-touch-icon-precomposed\" sizes=\"144x144\" href=\"../assets/ico/apple-touch-icon-144-precomposed.png\">\n" +
"    <link rel=\"apple-touch-icon-precomposed\" sizes=\"114x114\" href=\"../assets/ico/apple-touch-icon-114-precomposed.png\">\n" +
"    <link rel=\"apple-touch-icon-precomposed\" sizes=\"72x72\" href=\"../assets/ico/apple-touch-icon-72-precomposed.png\">\n" +
"    <link rel=\"apple-touch-icon-precomposed\" href=\"../assets/ico/apple-touch-icon-57-precomposed.png\">\n" +
"  </head>\n" +
"<body>\n" +
"    <div class=\"navbar navbar-inverse navbar-fixed-top\">\n" +
"      <div class=\"navbar-inner\">\n" +
"        <div class=\"container\">\n" +
"          <a class=\"btn btn-navbar\" data-toggle=\"collapse\" data-target=\".nav-collapse\">\n" +
"            <span class=\"icon-bar\"></span>\n" +
"            <span class=\"icon-bar\"></span>\n" +
"            <span class=\"icon-bar\"></span>\n" +
"          </a>\n" +
"          <a class=\"brand\" href=\"index.jsp\">jCRM</a>\n" +
"          <div class=\"nav-collapse collapse\">\n" +
"            <ul class=\"nav\">\n" +
"              <li><a href=\"events.jsp\">События</a></li>\n" +
"              <li><a href=\"deals.jsp\">Сделки</a></li>\n" +
"              <li><a href=\"companies.jsp\">Компании</a></li>\n" +
"              <li class=\"active\"><a href=\"tasks.jsp\">Задачи</a></li>\n" +
"              <li><a href=\"contacts.jsp\">Контакты</a></li>\n" +
"            </ul>\n" +
"            <ul class=\"nav pull-right\">\n" +
"                    <li id=\"fat-menu\" class=\"dropdown\">\n" +
"                      <a href=\"#\" id=\"drop3\" role=\"button\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Управление<b class=\"caret\"></b></a>\n" +
"                      <ul class=\"dropdown-menu\" role=\"menu\" aria-labelledby=\"drop3\">                     \n" +
"                        <li><a tabindex=\"-1\" href=\"#\">Личный аккаунт</a></li>\n" +
"                        <li><a tabindex=\"-1\" href=\"#\">Управление пользователями</a></li>\n" +
"                        <li class=\"divider\"></li>\n" +
"                        <li><a tabindex=\"-1\" href=\"#logout\">Выход</a></li>\n" +
"                      </ul>\n" +
"                    </li>\n" +
"            </ul>\n" +
"          </div><!--/.nav-collapse -->\n" +
"        </div>\n" +
"      </div>\n" +
"    </div>\n" +
"    <div class=\"container\">\n" +
"      <div class=\"row\">\n" +
"        <div class=\"span3\">\n" +
"          <p class=\"lead\">Задачи</p>\n" +
"          <p><a href=\"add-task.jsp\" class=\"btn btn-block\" target=\"_blank\">Добавить задачу</a></p>\n" +
"<!--          <ul class=\"nav nav-pills nav-stacked\">\n" +
"              <li class=\"active\"><a href=\"tasks.jsp\">Все задачи</a></li>\n" +
"              <li><a href=\"#\">Мои задачи</a></li>\n" +
"              <li><a href=\"#\">Мои просроченные задачи</a></li>\n" +
"              <li><a href=\"#\">Мои завершенные задачи</a></li>\n" +
"          </ul>  -->\n" +
"  <form action=\"FilterTasks\">\n" +
"<!--  <h5>Фильтр по сделкам</h5>\n" +
"      <label>Период</label>\n" +
"      <select class=\"input-block-level\" name=\"period\">\n" +
"            <option>За все время</option>\n" +
"            <option>За сегодня</option>\n" +
"            <option>За 3 дня</option>\n" +
"            <option>За неделю</option>\n" +
"            <option>За месяц</option>\n" +
"            <option>За полгода</option>\n" +
"      </select>-->\n" +
"  <h5>Тип задачи</h5>\n" +
"  <label class=\"checkbox\"><i class=\"icon-bell\"></i>\n" +
"      <input type=\"checkbox\" value=\"true\" name=\"call\">Звонок\n" +
"  </label>\n" +
"  <label class=\"checkbox\"><i class=\"icon-glass\"></i>\n" +
"      <input type=\"checkbox\" value=\"true\" name=\"meeting\">Встреча\n" +
"  </label>\n" +
"  <label class=\"checkbox\"><i class=\"icon-envelope\"></i>\n" +
"      <input type=\"checkbox\" value=\"true\" name=\"mail\">Письмо\n" +
"  </label>\n" +
"  <h5>Статус задачи</h5>\n" +
"  <label class=\"checkbox\">\n" +
"    <input type=\"checkbox\" value=\"true\" name=\"notCompleted\">Не завершенные\n" +
"  </label>\n" +
"  <label class=\"checkbox\">\n" +
"    <input type=\"checkbox\" value=\"true\" name=\"completed\">Завершенные\n" +
"  </label>\n" +
"  <h5>Ответственный</h5>\n" +
"  \n" +
    UsersView.getUsersSelect(usersService.findAll(), "") +
"  \n" +
"  <p>\n" +
"    <button type=\"submit\" class=\"btn btn-primary\">Найти</button>\n" +
"    <button type=\"button\" class=\"btn\">Отменить</button>\n" +
"  </p>\n" +
"</form>\n" +
"        </div>\n" +
"        <div class=\"span9\">\n" +
"              \n" +
    TasksView.getTasksTable(list) + 
"              \n" +
"       </div>\n" +
"      </div>\n" +
"      <hr>\n" +
"      <footer>\n" +
"        <p>&copy; Company 2012</p>\n" +
"      </footer>\n" +
"    </div>\n" +
"    <script src=\"js/jquery.min.js\"></script>\n" +
"    <script src=\"js/bootstrap.min.js\"></script>\n" +
"  </body>\n" +
"</html>\n" +
"\n" +
"<!--<script type=\"text/javascript\" >\n" +
"    function callClicked()\n" +
"    {\n" +
"        document.getElementById(\"call\")\n" +
"    }\n" +
"</script>-->\n" +
"\n" +
"");
            } finally {            
                out.close();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
