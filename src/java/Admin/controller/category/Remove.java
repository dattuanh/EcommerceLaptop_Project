/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.category;

import Base.Dal.DaoCategory;
import Base.Model.Category;
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
@WebServlet(name = "RemoveCategory", urlPatterns = {"/RemoveCategory"})
public class Remove extends HttpServlet {

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
            out.println("<title>Servlet Remove</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Remove at " + request.getContextPath() + "</h1>");
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
        String categoryID = request.getParameter("CategoryID");
        DaoCategory dao = new DaoCategory();
        Category c = dao.getCategory(Integer.parseInt(categoryID));
        request.setAttribute("selectedCategory", c);
        request.getRequestDispatcher("admin/category/remove.jsp").forward(request, response);
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
        DaoCategory dao = new DaoCategory();

        String[] Ids = request.getParameterValues("Ids[]");
        if (Ids != null) {
            // xóa từng IDs
            for (String Id : Ids) {
                int categoryID = Integer.parseInt(Id);
                Date date = Base.Util.Formatter.getCurrentDate();
                Category c = new Category();
                c.setCategoryID(categoryID);
                c.setModifiedBy(Base.Util.Utilities.getLoggingInAccount(request, response));
                c.setModifiedDate(date);
                String modifiedHistory = Base.Util.Formatter.getCurrentDate().toString() + " - " + Base.Util.Utilities.getLoggingInAccount(request, response).getUserName() + ": Remove Category <br>";
                c.setModifiedHistory(modifiedHistory);
                dao.removeCategory(c);
            }
        } else {
            int categoryID = Integer.parseInt(request.getParameter("id"));
            Date date = Base.Util.Formatter.getCurrentDate();
            Category c = new Category();
            c.setCategoryID(categoryID);
            c.setModifiedBy(Base.Util.Utilities.getLoggingInAccount(request, response));
            c.setModifiedDate(date);
            String modifiedHistory = Base.Util.Formatter.getCurrentDate().toString() + " - " + Base.Util.Utilities.getLoggingInAccount(request, response).getUserName() + ": Remove Category <br>";
            c.setModifiedHistory(modifiedHistory);
            dao.removeCategory(c);
        }
        response.sendRedirect("CategoryManagement");
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
