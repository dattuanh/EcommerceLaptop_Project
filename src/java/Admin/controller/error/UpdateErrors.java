/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.error;

import Base.Dal.DaoError;
import Base.Dal.DaoProduct;
import Base.Model.Account;
import Base.Model.Error;
import Base.Model.Product;
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
 * @author MSI
 */
@WebServlet(name = "UpdateErrors", urlPatterns = {"/UpdateErrors"})
public class UpdateErrors extends HttpServlet {

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
            out.println("<title>Servlet UpdateErrors</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateErrors at " + request.getContextPath() + "</h1>");
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
        String errorsId = request.getParameter("id");
        DaoError dao = new DaoError();
        Error e = dao.getErrors(Integer.parseInt(errorsId));
        List<Product> products = new DaoProduct().getAllSoldProducts();

        request.setAttribute("e", e);
        request.setAttribute("products", products);
        request.getRequestDispatcher("admin/error/update.jsp").forward(request, response);
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
        try {
            DaoError dao = new DaoError();
            int errorId = Integer.parseInt(request.getParameter("id"));
            Error oldError = dao.getErrors(errorId);

            Date repairTime = Base.Util.Formatter.formatDate(request.getParameter("repairTime"));
            double repairPrice = Base.Util.Utilities.formatNumber(request.getParameter("repairPrice"));
            int repairStatus = Integer.parseInt(request.getParameter("repairStatus"));
            String content = request.getParameter("content");
            Product repairProduct = new DaoProduct().getProduct(request.getParameter("repairProduct"));

            String errorMessage = request.getParameter("errorsMessage");
            String modifiedHistory = oldError.getModifiedHistory();
            Date modifiedDate = Base.Util.Formatter.getCurrentDate();
            Account modifiedBy = Base.Util.Utilities.getLoggingInAccount(request, response);
            modifiedHistory += modifiedBy.getAccountId() + "; " + modifiedDate.toString() + "; " + "Update Error&#13;&#10;";
            
            Error e = new Error(errorId, errorMessage, modifiedDate, modifiedBy, modifiedHistory,
                    content, repairTime, repairPrice, repairStatus, repairProduct);
            dao.updateError(e);
            Base.Util.Utilities.noti(request, true, "Thông tin phiếu sửa chữa được đổi mới");

        } catch (Exception e) {
            System.out.println("UpdateErrorsServlet: " + e.getMessage());
            Base.Util.Utilities.noti(request, false, e.getMessage());
        }

        response.sendRedirect("ErrorsManagement");
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
