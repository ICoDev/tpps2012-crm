/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Companies;
import domain.Contacts;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.CompaniesService;
import services.ContactsService;

/**
 *
 * @author KSU
 */
@WebServlet(name = "AddContact", urlPatterns = {"/AddContact"})
public class AddContact extends HttpServlet {

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
            out.println("<title>Servlet AddContact</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddContact at " + request.getContextPath() + "</h1>");
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
        Contacts contact = new Contacts();

        CompaniesService companyService = new CompaniesService();
        ContactsService contactsService = new ContactsService();
        
        Companies company = companyService.getCompanyByName(request.getParameter("companiesSelect"));
        
        contact.setCompanyId(company);
        contact.setContactsAddress(request.getParameter("contactAdress"));
        contact.setContactsEmail(request.getParameter("contactMail"));
        contact.setContactsName(request.getParameter("contactName"));
        contact.setContactsNote(request.getParameter("contactDescription"));
        contact.setContactsPost(request.getParameter("contactPost"));
        contact.setContactsTags(request.getParameter("contactTag"));
        contact.setContactsTelephon(Integer.parseInt(request.getParameter("contactPhone")));
        //contact.setDealsId(null);
        contact.setResponsible(request.getParameter("usersSelect"));
        
        contactsService.create(contact);
        
        response.sendRedirect("contacts.jsp");
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
