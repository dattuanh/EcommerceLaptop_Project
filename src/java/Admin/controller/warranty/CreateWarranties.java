/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.warranty;

import Base.Dal.DAO;
import Base.Dal.DaoError;
import Base.Dal.DaoWarranties;
import Base.Model.Account;
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
@WebServlet(name = "CreateWarranties", urlPatterns = {"/CreateWarranties"})
public class CreateWarranties extends HttpServlet {

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
            out.println("<title>Servlet CreateWarranties</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateWarranties at " + request.getContextPath() + "</h1>");
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
        DAO daoProduct = new DAO();
        List<Error> errorList = dao.getAllErrors();
        List<Product> allProducts = daoProduct.getAllProducts();
        Date currentDate = new Date();

        request.setAttribute("errorList", errorList);
        request.setAttribute("allProducts", allProducts);
        request.setAttribute("date", currentDate);
        request.getRequestDispatcher("admin/warranty/create.jsp").forward(request, response);
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
            DaoWarranties daoWarranty = new DaoWarranties();
            String productId = request.getParameter("productId");
            
            if(daoWarranty.getWarrantyByProductId(productId) != null) {
                throw new Exception("Bảo hành cho sản phẩm này hiện đang hoạt động");
            }
            
            Date createdDate = Base.Util.Formatter.getCurrentDate();
            Date warrantyDate = createdDate;
            Date modifiedDate = createdDate;
            Account createdBy = Base.Util.Utilities.getLoggingInAccount(request, response);
            Account modifiedBy = createdBy;
            String modifiedHistory = modifiedBy.getAccountId() + "; " + modifiedDate.toString() + "; " + "Create new Warranty&#13;&#10;";
            
            DAO daoProduct = new DAO();
            String content = "";
            int warrantyTime = Base.Util.Constants.WARRANTY_TIME;
            double warrantyPrice = Base.Util.Constants.WARRANTY_PRICE;

            Product productIdByProduct = daoProduct.getProduct(productId);
            Warranty a = new Warranty(Base.Util.Utilities.INT_DEFAUT, productIdByProduct, null, warrantyDate, true, createdDate,
                    modifiedDate, createdBy, modifiedBy, modifiedHistory, false, content, warrantyTime, warrantyPrice);
            
            daoWarranty.createWarranties(a);
            Base.Util.Utilities.noti(request, true, "Tạo mới bảo hành thành công");
            
        } catch (Exception e) {
            System.out.println("CreateWarranty: " + e.getMessage());
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
