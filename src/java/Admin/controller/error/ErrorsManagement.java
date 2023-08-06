/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.error;

import Base.Dal.DaoError;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author MSI
 */
@WebServlet(name = "ErrorsManagement", urlPatterns = {"/ErrorsManagement"})
public class ErrorsManagement extends HttpServlet {

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
            out.println("<title>Servlet ErrorsManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ErrorsManagement at " + request.getContextPath() + "</h1>");
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
        DaoError dao = new DaoError();
        int count = dao.countTableItems();
        int endPage = (count % 5 == 0) ? (count / 5) : (count / 5 + 1);
        int indexPage = request.getParameter("index") != null ? Integer.parseInt(request.getParameter("index")) : 1;
        List<Base.Model.Error> errorList  = dao.getAllErrorByFilter("", "", indexPage);
        request.setAttribute("currentPage", indexPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listSize", count);
        request.setAttribute("errorList", errorList);
        request.getRequestDispatcher("admin/error/index.jsp").forward(request, response);
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
        DaoError dao = new DaoError();
        String filter = request.getParameter("filter");
        String search = request.getParameter("search");
        List<Base.Model.Error> errorList = dao.getAllErrorByFilter(filter, search,0);
        
        request.setAttribute("filter", filter);
        request.setAttribute("search", search);
        request.setAttribute("errorList", errorList);
        request.getRequestDispatcher("admin/error/index.jsp").forward(request, response);
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
