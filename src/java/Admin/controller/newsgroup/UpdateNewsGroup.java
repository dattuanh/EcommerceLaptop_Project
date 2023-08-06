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

/**
 *
 * @author Giang Minh
 */
@WebServlet(name = "UpdateNewsGroup", urlPatterns = {"/UpdateNewsGroup"})
public class UpdateNewsGroup extends HttpServlet {

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
            out.println("<title>Servlet UpdateNewsGroup</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateNewsGroup at " + request.getContextPath() + "</h1>");
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
        String newsgroupid = request.getParameter("id");
        if (newsgroupid == null) {
            response.sendRedirect("NewsGroupManagement");
            return;
        }
        DAONewsGroup dao = new DAONewsGroup();
        NewsGroup c = dao.getNewsGroup(Integer.parseInt(newsgroupid));
        request.setAttribute("c", c);
        request.getRequestDispatcher("admin/newsgroup/update.jsp").forward(request, response);

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
        DAONewsGroup dao = new DAONewsGroup();
        String newsgroupid = request.getParameter("id");
        String newsgroupname = request.getParameter("newsGroupName");
        Date modifiedDate = Base.Util.Formatter.getCurrentDate();
        Account loggedInAccount = Base.Util.Utilities.getLoggingInAccount(request, response);
        Account modifiedBy = loggedInAccount;
        String modifiedHistory = request.getParameter("modifiedHistory");
        modifiedHistory += loggedInAccount.getAccountId() + ";"
                + modifiedDate.toString() + ";"
                + "Update NewsGroup|";
        NewsGroup c = new NewsGroup(Integer.parseInt(newsgroupid), newsgroupname, modifiedDate, modifiedBy, modifiedHistory, false);
        dao.updateNewsGroup(c);
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
