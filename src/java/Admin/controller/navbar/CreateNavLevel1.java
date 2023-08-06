/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.navbar;

import Base.Dal.DaoDisplay;
import Base.Model.Account;
import Base.Model.News;
import Base.Model.NewsGroup;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 *
 * @author admin
 */
@WebServlet(name = "CreateNavLevel1", urlPatterns = {"/CreateNavLevel1"})
public class CreateNavLevel1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddNavLevel1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNavLevel1 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DaoDisplay dao = new DaoDisplay();
        String navName = request.getParameter("navName");
        String link = request.getParameter("link");
                
        News n = new News();
        n.setNewsGroupID(null);
        n.setNewTitle(navName);
        n.setNewsContent(link);
//        Account createdBy = Base.Util.Utilities.getLoggingInAccount(request, response);
//        Date createdDate = Base.Util.Formatter.getCurrentDate();
//        n.setCreateBy(createdBy);
//        n.setCreatedDate(createdDate);
        int id = dao.addNavLevel2(n);
        
        PrintWriter out = response.getWriter();
        out.print("<span onclick=\"removeNavLevel2("+ id+")\" class=\"close\">&times;</span>\n"
                + "<input type='hidden' name='temp[]' value='" + id + "'>");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DaoDisplay dao = new DaoDisplay();
        String navName = request.getParameter("navName");
        String link = request.getParameter("link");
        String pos = request.getParameter("pos");
        
//        Account createdBy = Base.Util.Utilities.getLoggingInAccount(request, response);
//        Date createdDate = Base.Util.Formatter.getCurrentDate();
//        n.setCreateBy(createdBy);
//        n.setCreatedDate(createdDate);
        NewsGroup ng = new NewsGroup();
        ng.setNewsGroupName(navName);
        
        News n = new News();
        n.setNewsGroupID(dao.getNavigation());
        n.setNewTitle(String.valueOf(dao.addNavLevel1(ng)));
        n.setNewsContent(link);
        n.setNewsHeading(pos);
        dao.addNavLevel2(n);
        
        response.sendRedirect("DisplayManagement");
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
