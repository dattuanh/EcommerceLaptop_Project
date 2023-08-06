/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.newsgroup;

import Base.Dal.DAONewsGroup;
import Base.Model.Account;
import Base.Model.NewsGroup;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Giang Minh
 */
@WebServlet(name = "CreateNewsGroup", urlPatterns = {"/CreateNewsGroup"})
public class CreateNewsGroup extends HttpServlet {

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
            out.println("<title>Servlet Create</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Create at " + request.getContextPath() + "</h1>");
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
        DAONewsGroup dao = new DAONewsGroup();
        List<NewsGroup> newsGroupList = dao.getAllNewsGroup();
        request.setAttribute("newsGroupList", newsGroupList);
        request.getRequestDispatcher("admin/newsgroup/create.jsp").forward(request, response);
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
        String newsgroupname = request.getParameter("name");
        
        if(newsgroupname == null || newsgroupname.isEmpty()) {
            response.sendRedirect("NewsGroupManagement");
        }
        
        Date createdDate = Base.Util.Formatter.getCurrentDate();
        Date modifiedDate = createdDate;
        Account loggedInAccount = Base.Util.Utilities.getLoggingInAccount(request, response);
        Account createdBy = loggedInAccount;
        Account modifiedBy = loggedInAccount;
        String modifiedHistory = loggedInAccount.getAccountId() + ";"
                + createdDate.toString() + ";"
                + "Create new NewsGroup|";
        NewsGroup c = new NewsGroup(newsgroupname, createdDate, modifiedDate, createdBy, modifiedBy, modifiedHistory, false);

        DAONewsGroup dao = new DAONewsGroup();
        dao.createNewsGroup(c);
        response.sendRedirect("NewsGroupManagement");
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
