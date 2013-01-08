/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.Deals;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import services.DealsService;
import services.UsersService;
import views.DealsView;

/**
 *
 * @author KC
 */
@WebServlet(name = "FilterDeals", urlPatterns = {"/FilterDeals"})
public class FilterDeals extends HttpServlet {

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
        if (    !"true".equals(request.getParameter("primaryContract")) &&
                !"true".equals(request.getParameter("talk")) &&
                !"true".equals(request.getParameter("decide")) &&
                !"true".equals(request.getParameter("negotiation")) &&
                !"true".equals(request.getParameter("suxes")) &&
                !"true".equals(request.getParameter("failed")) &&
                "".equals(request.getParameter("usersSelect"))
            )
        {
            response.sendRedirect("deals.jsp");
        }
        else 
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Deals.class);
            
            //checking checkboxes;
            if ("true".equals(request.getParameter("primaryContract") ) )
            {
                orExpression = Restrictions.or( orExpression, Restrictions.eq( "dealsType", "primary contract") );
            }
            if ("true".equals(request.getParameter("talk") ) )
            {
                orExpression = Restrictions.or( orExpression, Restrictions.eq( "dealsType", "talks") );
            }
            if ("true".equals(request.getParameter("decide") ) )
            {
                orExpression = Restrictions.or( orExpression, Restrictions.eq( "dealsType", "deside") );
            }
            if ("true".equals(request.getParameter("negotiation") ) )
            {
                orExpression = Restrictions.or( orExpression, Restrictions.eq( "dealsType", "contract negotiation") );
            }
            if ("true".equals(request.getParameter("suxes") ) )
            {
                orExpression = Restrictions.or( orExpression, Restrictions.eq( "dealsType", "success") );
            }
            if ("true".equals(request.getParameter("failed") ) )
            {
                orExpression = Restrictions.or( orExpression, Restrictions.eq( "dealsType", "failure") );
            }
            
            criteria.add(orExpression);
            
            //check, if responsible was selected;
            if ( !"".equals(request.getParameter("usersSelect") ) )
            {
                criteria.add( Restrictions.eq( "responsible", request.getParameter("usersSelect")).ignoreCase() );
            }            

            List <Deals> list = criteria.list();
            session.close();
                    
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                
                DealsService dealsService = new DealsService();
                UsersService usersService = new UsersService();
                
                /* TODO output your page here. You may use following sample code. */
                out.println(
"<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"  <head>\n" +
"    <meta charset=\"utf-8\">\n" +
"    <title>CRM - Deals page</title>\n" +
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
"    \n" +
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
"              <li class=\"active\"><a href=\"deals.jsp\">Сделки</a></li>\n" +
"              <li><a href=\"companies.jsp\">Компании</a></li>\n" +
"              <li><a href=\"tasks.jsp\">Задачи</a></li>\n" +
"              <li><a href=\"contacts.jsp\">Контакты</a></li>\n" +
"            </ul>\n" +
"            <ul class=\"nav pull-right\">\n" +
"              <li id=\"fat-menu\" class=\"dropdown\">\n" +
"                  <a href=\"#\" id=\"drop3\" role=\"button\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Управление<b class=\"caret\"></b></a>\n" +
"                    <ul class=\"dropdown-menu\" role=\"menu\" aria-labelledby=\"drop3\">                     \n" +
"                        <li><a tabindex=\"-1\" href=\"#\">Личный аккаунт</a></li>\n" +
"                        <li><a tabindex=\"-1\" href=\"#\">Управление пользователями</a></li>\n" +
"                        <li class=\"divider\"></li>\n" +
"                        <li><a tabindex=\"-1\" href=\"#logout\">Выход</a></li>\n" +
"                    </ul>\n" +
"              </li>\n" +
"            </ul>\n" +
"          </div><!--/.nav-collapse -->\n" +
"        </div>\n" +
"      </div>\n" +
"    </div>\n" +
"    <div class=\"container\">\n" +
"      <div class=\"row\">\n" +
"        <div class=\"span3\">\n" +
"          <p class=\"lead\">Сделки</p>\n" +
"          <p><a href=\"add-deal.jsp\" class=\"btn btn-block\" target=\"_blank\">Добавить сделку</a></p>\n" +
"          <form action=\"SearchDeals\">\n" +
"          <div class=\"input-append\">\n" +
"            <input type=\"text\" placeholder=\"Поиск сделки\" name=\"name\">\n" +
"            <button class=\"btn btn-primary\" type=\"submit\"><i class=\"icon-search icon-white\"></i></button>\n" +
"          </div>\n" +
"          </form>\n" +
"          <ul class=\"nav nav-pills nav-stacked\">\n" +
"              <li class=\"active\"><a href=\"deals.jsp?status=opened\">Открытые сделки</a></li>\n" +
"              <li><a href=\"deals.jsp?status=my\">Только мои сделки</a></li>\n" +
"              <li><a href=\"deals.jsp?status=suxes\">Успешно завершенные</a></li>\n" +
"              <li><a href=\"deals.jsp?status=failed\">Нереализованные сделки</a></li>\n" +
"              <li><a href=\"deals.jsp?status=without_tasks\">Сделки без задач</a></li>\n" +
"              <!--<li><a href=\"#\">Сделки c просроченными задачами</a></li>-->\n" +
"          </ul>\n" +
"          <h5>Теги</h5>\n" +
"          \n" +
    DealsView.getTags( dealsService.findAll() ) +
"          \n" +
"<!--        <h5>Фильтр по сделкам</h5>\n" +
"        <form>\n" +
"        <select class=\"input-block-level\">\n" +
"            <option> </option>\n" +
"            <option>Создана</option>\n" +
"            <option>Закрыта</option>\n" +
"          </select>\n" +
"        <select class=\"input-block-level\">\n" +
"            <option>За все время</option>\n" +
"            <option>За сегодня</option>\n" +
"            <option>За 3 дня</option>\n" +
"            <option>За неделю</option>\n" +
"            <option>За месяц</option>\n" +
"            <option>За полгода</option>\n" +
"          </select>\n" +
"        </form>-->\n" +
"<form action=\"FilterDeals\">\n" +
"  <h5>Статус сделки</h5>\n" +
"  <label class=\"checkbox\">\n" +
"      <input type=\"checkbox\" value=\"true\" name=\"primaryContract\"><span class=\"label label-info\">Первичный контакт</span>\n" +
"  </label>\n" +
"  <label class=\"checkbox\">\n" +
"    <input type=\"checkbox\" value=\"true\" name=\"talk\"><span class=\"label label-warning\">Переговоры</span>\n" +
"  </label>\n" +
"  <label class=\"checkbox\">\n" +
"    <input type=\"checkbox\" value=\"true\" name=\"decide\"><span class=\"label label-inverse\">Принимают решение</span>\n" +
"  </label>\n" +
"  <label class=\"checkbox\">\n" +
"    <input type=\"checkbox\" value=\"true\" name=\"negotiation\"><span class=\"label label-important\">Согласование договора</span>\n" +
"  </label>\n" +
"  <label class=\"checkbox\">\n" +
"    <input type=\"checkbox\" value=\"true\" name=\"suxes\"><span class=\"label label-success\">Успешно реализовано</span>\n" +
"  </label>\n" +
"  <label class=\"checkbox\">\n" +
"    <input type=\"checkbox\" value=\"true\" name=\"failed\"><span class=\"label\">Закрыто и не реализовано</span>\n" +
"  </label>\n" +
"  <h5>Ответственный</h5>\n" +
"  \n" +
    views.UsersView.getUsersSelect(usersService.findAll(), "") + 
"  \n" +
"  <p>\n" +
"    <button type=\"submit\" class=\"btn btn-primary\">Найти</button>\n" +
"    <button type=\"button\" class=\"btn\">Отменить</button>\n" +
"  </p>\n" +
"</form>\n" +
"        </div>\n" +
"        <div class=\"span9\">\n" +
                        
    views.DealsView.getDealsTable( list ) +
                        
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
