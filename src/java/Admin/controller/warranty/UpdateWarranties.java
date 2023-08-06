/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.warranty;

import Base.Dal.DAO;
import Base.Dal.DaoError;
import Base.Dal.DaoWarranties;
import Base.Model.Account;
import Base.Model.Customer;
import Base.Model.Error;
import Base.Model.Product;
import Base.Model.Warranty;
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
@WebServlet(name = "UpdateWarranties", urlPatterns = {"/UpdateWarranties"})
public class UpdateWarranties extends HttpServlet {

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
            out.println("<title>Servlet UpdateWarranties</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateWarranties at " + request.getContextPath() + "</h1>");
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
        String warrantyId = request.getParameter("id");
        DaoWarranties daoWarranty = new DaoWarranties();
        DAO daoProduct = new DAO();
        DaoError daoError = new DaoError();
        List<Product> allProducts = daoProduct.getAllProducts();
        List<Error> allErrors = daoError.getAllErrors();
        Warranty w = daoWarranty.getWarranties(Integer.parseInt(warrantyId));
        Customer c = daoWarranty.getCustomerByWarrantyId(warrantyId);

        request.setAttribute("w", w);
        request.setAttribute("c", c);
        request.setAttribute("allProducts", allProducts);
        request.setAttribute("allErrors", allErrors);

        request.getRequestDispatcher("admin/warranty/update.jsp").forward(request, response);

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
            DaoWarranties dao = new DaoWarranties();
            String warrantyId = request.getParameter("warrantyId");
            Warranty w = dao.getWarranties(Integer.parseInt(warrantyId));

            String errorId = request.getParameter("error");
            Error e = new DaoError().getErrors(Integer.parseInt(errorId));

            Date modifiedDate = Base.Util.Formatter.getCurrentDate();
            Account modifiedBy = Base.Util.Utilities.getLoggingInAccount(request, response);
            String modifiedHistory = request.getParameter("modifiedHistory");
            modifiedHistory += modifiedBy.getAccountId() + "; " + modifiedDate.toString() + "; " + "Update Warranty&#13;&#10;";

            w.setErrorId(e);
            w.setModifiedHistory(modifiedHistory);
            w.setModifiedDate(modifiedDate);
            w.setModifiedBy(modifiedBy);

            dao.updateWarranty(w);
            Base.Util.Utilities.noti(request, true, "Cập nhật bảo hành thành công");
        } catch (Exception e) {
            System.out.println("Update Warranty: " + e.getMessage());
            Base.Util.Utilities.noti(request, false, e.getMessage());
        }

        response.sendRedirect("WarrantiesManagement");
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
