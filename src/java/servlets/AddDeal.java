/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Contacts;
import domain.Deals;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.CompaniesService;
import services.ContactsService;
import services.DealsService;

/**
 *
 * @author KSU
 */
@WebServlet(name = "AddDeal", urlPatterns = {"/AddDeal"})
public class AddDeal extends HttpServlet {

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
            out.println("<title>Servlet AddDeal</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddDeal at " + request.getContextPath() + "</h1>");
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
        Deals deal = new Deals();
        
        DealsService dealsService = new DealsService();
        ContactsService contactsService = new ContactsService();
        //CompaniesService companiesService = new CompaniesService();
        
        Contacts contact = contactsService.getContactByName( request.getParameter("contactsSelect") );

        deal.setDealsName(request.getParameter("dealName"));
        deal.setDealsTag(request.getParameter("dealTag"));
               
        deal.setDealsType( request.getParameter("dealStatus") );        
        deal.setMoney(Integer.parseInt(request.getParameter("dealMoney")));
        deal.setResponsible(request.getParameter("usersSelect"));
        
        dealsService.create(deal);
        
        contact.setDealsId(deal);
        contactsService.update(contact);
        
        response.sendRedirect("deals.jsp");
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
