/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.Contacts;
import domain.Deals;
import domain.Tasks;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlElement;
import services.ContactsService;
import services.DealsService;
import services.TasksService;

/**
 *
 * @author KSU
 */
@WebServlet(name = "AddTask", urlPatterns = {"/AddTask"})
public class AddTask extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddTask</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddTask at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
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
        //processRequest(request, response);
        Tasks task = new Tasks();

        
        TasksService tasksService = new TasksService();
        ContactsService contactsService = new ContactsService();
        DealsService dealsService = new DealsService();
        
        task.setAutor(request.getParameter("usersSelect"));
        
        Deals deal = dealsService.getDealByName(request.getParameter("taskDeal"));
        
        Contacts contact = deal.getContactsSet().iterator().next(); //using first contact
        
        task.setContactsId(contact);
        //deal = dealsService.getdealByName(request.getParameter("taskDeal"));
        task.setDealsId(deal);

        task.setResponsible( task.getAutor() );
        task.setStatus(request.getParameter("taskStatus"));
        task.setTasksName(request.getParameter("taskName"));
        task.setTasksText(request.getParameter("taskText"));
        task.setTasksType(request.getParameter("taskType"));
       
        tasksService.create(task);
//        dealsService.update(deal);
//        contactsService.update(contact);
        
        response.sendRedirect("tasks.jsp");
        
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
