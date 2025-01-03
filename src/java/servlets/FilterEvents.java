/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.Events;
import hibernate.HibernateUtil;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import services.TasksService;
import services.UsersService;
import views.EventsView;
import views.UsersView;

/**
 *
 * @author KC
 */
@WebServlet(name = "FilterEvents", urlPatterns = {"/FilterEvents"})
public class FilterEvents extends HttpServlet {

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
        //response.setContentType("text/html;charset=UTF-8");
        
        LogicalExpression orExpression = Restrictions.and( Restrictions.lt("id", null), Restrictions.lt("id", null) );
        
        if (    !"true".equals(request.getParameter("task")) &&
                !"true".equals(request.getParameter("contact")) &&
                !"true".equals(request.getParameter("deal")) &&
                !"true".equals(request.getParameter("noObject")) )
        {
            response.sendRedirect("events.jsp");
        }
        else 
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Events.class);
            
            if ("true".equals(request.getParameter("task") ) )
            {
                //restrictions.add( Restrictions.isNotNull("tasksId") );
                orExpression = Restrictions.or( orExpression, Restrictions.isNotNull("tasksId") );
            }
            if ("true".equals(request.getParameter("deal") ) )
            {
                //restrictions.add( Restrictions.isNotNull("dealsId") );
                orExpression = Restrictions.or( orExpression, Restrictions.isNotNull("dealsId") );
            }
            if ("true".equals(request.getParameter("contact") ) )
            {
                //restrictions.add( Restrictions.isNotNull("contaktsId") );
                orExpression = Restrictions.or( orExpression, Restrictions.isNotNull("contaktsId") );
            }
            if ("true".equals(request.getParameter("noObject") ) )
            {
//                criteria.add( Restrictions.and( Restrictions.isNull("tasksId"), 
//                                                Restrictions.and( Restrictions.isNull("contaktsId"), 
//                                                                Restrictions.isNull("dealsId"))) );
                orExpression = Restrictions.or( orExpression, Restrictions.and( Restrictions.isNull("tasksId"), 
                                                                Restrictions.and( Restrictions.isNull("contaktsId"), 
                                                                    Restrictions.isNull("dealsId"))) );
            }
            
            //Check if period was selected
            Calendar calendar = Calendar.getInstance();
//            <option>За все время</option>
//            <option>За сегодня</option>
//            <option>За 3 дня</option>
//            <option>За неделю</option>
//            <option>За месяц</option>
//            <option>За полгода</option>
            if ( "За все время".equals( request.getParameter("period") ) )
            {
                //do nothing
            }
            else if ( request.getParameter("period").contains( "сегодня" )  )
            {
                orExpression = Restrictions.and( orExpression, Restrictions.eq("eventsDate", calendar.getTime()) );
            }
            else if ( request.getParameter("period").contains("3") )
            {
                calendar.add( Calendar.DAY_OF_YEAR, -3);
                orExpression = Restrictions.and( orExpression, Restrictions.ge("eventsDate", calendar.getTime()) );
            }
            else if ( "За неделю".toString().equals( request.getParameter("period") ) )
            {
                calendar.add( Calendar.DAY_OF_YEAR, Calendar.DAY_OF_WEEK );
                orExpression = Restrictions.and( orExpression, Restrictions.ge("eventsDate", calendar.getTime()) );
            }
            else if ( "За месяц".equals( request.getParameter("period") ) )
            {
                calendar.add( Calendar.DAY_OF_YEAR, Calendar.DAY_OF_MONTH );
                orExpression = Restrictions.and( orExpression, Restrictions.ge("eventsDate", calendar.getTime()) );
            }
            else if ( "За полгода".equals( request.getParameter("period") ) )
            {
                calendar.add( Calendar.DAY_OF_YEAR, 150 );
                orExpression = Restrictions.and( orExpression, Restrictions.ge("eventsDate", calendar.getTime()) );
            }
            
//            criteria.add(Restrictions.and( orExpression, Restrictions.ge("eventsDate", calendar.getTime()) ) )
//                    .addOrder(Order.desc("eventsDate")).addOrder(Order.desc("eventsTime"));
            criteria.add(orExpression).addOrder(Order.desc("eventsDate")).addOrder(Order.desc("eventsTime"));
            
            List <Events> list = criteria.list();
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
"    <title>CRM - Event page</title>\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <meta name=\"description\" content=\"\">\n" +
"    <meta name=\"author\" content=\"\">\n" +
"    <link href=\"css/bootstrap.css\" rel=\"stylesheet\">\n" +
"    <link href=\"css/bootstrap-responsive.css\" rel=\"stylesheet\">\n" +
"    <link href=\"css/doc.css\" rel=\"stylesheet\">\n" +
"    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->\n" +
"    <!--[if lt IE 9]>\n" +
"      <script src=\"http://html5shim.googlecode.com/svn/trunk/html5.js\"></script>\n" +
"    ;<![endif]-->\n" +
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
"              <li class=\"active\"><a href=\"events.jsp\">События</a></li>\n" +
"              <li><a href=\"deals.jsp\">Сделки</a></li>\n" +
"              <li><a href=\"companies.jsp\">Компании</a></li>\n" +
"              <li><a href=\"tasks.jsp\">Задачи</a></li>\n" +
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
"            \n" +
"          </div><!--/.nav-collapse -->\n" +
"        </div>\n" +
"      </div>\n" +
"    </div>\n" +
"\n" +
"    <div class=\"container\">\n" +
"      <div class=\"row\">\n" +
"        <div class=\"span3\">\n" +
"          <p class=\"lead\">Последние изменения</p>\n" +
"          <ul class=\"nav nav-pills nav-stacked\">\n" +
"              <li class=\"active\"><a href=\"events.jsp\">Все</a></li>\n" +
"              <li><a href=\"events.jsp?time=last3days\">За последние 3 дня</a></li>\n" +
"              <li><a href=\"events.jsp?time=lastweek\">За последнюю неделю</a></li>\n" +
"          </ul>\n" +
"    <form class=\"form-inline!\" action=\"FilterEvents\">\n" +
"      <label>Период</label>\n" +
"      <select class=\"input-block-level\" name=\"period\">\n" +
"            <option>За все время</option>\n" +
"            <option>За сегодня</option>\n" +
"            <option>За 3 дня</option>\n" +
"            <option>За неделю</option>\n" +
"            <option>За месяц</option>\n" +
"            <option>За полгода</option>\n" +
"      </select>\n" +
"      \n" +
"  <h5>Объект события</h5>\n" +
"  <label class=\"checkbox\"><i class=\"icon-fire\"></i>\n" +
"      <input type=\"checkbox\" value=\"true\" name=\"deal\">Сделка\n" +
"  </label>\n" +
"  <label class=\"checkbox\"><i class=\"icon-user\"></i>\n" +
"    <input type=\"checkbox\" value=\"true\" name=\"contact\">Контакт\n" +
"  </label>\n" +
"  <label class=\"checkbox\"><i class=\"icon-cog\"></i>\n" +
"    <input type=\"checkbox\" value=\"true\" name=\"task\">Задача\n" +
"  </label>\n" +
"  <label class=\"checkbox\">\n" +
"    <input type=\"checkbox\" value=\"true\" name=\"noObject\">Нет обьекта\n" +
"  </label>\n" +
"  <br/>\n" +
"  <!--<h5>Инициатор</h5>\n" +
"  \n" +
    UsersView.getUsersSelect(usersService.findAll(), "") +
"  -->\n" +
"  \n" +
"  <p>\n" +
"    <button type=\"submit\" class=\"btn btn-primary\">Найти</button>\n" +
"    <button type=\"button\" class=\"btn\">Отменить</button>\n" +
"  </p>\n" +
"</form>\n" +
"\n" +
"        </div>\n" +
"        <div class=\"span9\">\n" +
"            \n" +
    EventsView.getEventsTable(list) +
"              \n" +
"       </div>\n" +
"      </div>\n" +
"\n" +
"      <hr>\n" +
"\n" +
"      <footer>\n" +
"        <p>&copy; Company 2012</p>\n" +
"      </footer>\n" +
"\n" +
"    </div> <!-- /container -->\n" +
"\n" +
"\n" +
"    <!-- Le javascript\n" +
"    ================================================== -->\n" +
"    <!-- Placed at the end of the document so the pages load faster -->\n" +
"    <script src=\"https://code.jquery.com/jquery-1.12.4.min.js\" integrity=\"sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=\" crossorigin=\"anonymous\"></script>\n" +
"    <script src=\"js/bootstrap.min.js\"></script>\n" +
"  </body>\n" +
"</html>\n" +
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
