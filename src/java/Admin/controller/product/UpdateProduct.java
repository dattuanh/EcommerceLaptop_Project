/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.product;

import Base.Dal.DAO;
import Base.Dal.DaoProduct;
import Base.Dal.DaoSerie;
import Base.Model.Product;
import Base.Model.ProductSeri;
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
@WebServlet(name = "UpdateProduct", urlPatterns = {"/UpdateProduct"})
public class UpdateProduct extends HttpServlet {

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
            out.println("<title>Servlet UpdateProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProduct at " + request.getContextPath() + "</h1>");
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
        DaoProduct daoProduct = new DaoProduct();
        DaoSerie daoSerie = new DaoSerie();
        int productID = Integer.parseInt(request.getParameter("productID"));
        Product p = daoProduct.getProduct(productID);
        ProductSeri s = daoSerie.getProductSeri(String.valueOf(p.getProductSerieId().getProductSeriId()));
        request.setAttribute("selectedSerie", s);
        request.setAttribute("selectedProduct", p);
        request.getRequestDispatcher("admin/product/updateProduct.jsp").forward(request, response);
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
        DaoProduct daoProduct = new DaoProduct();
        int productID = Integer.parseInt(request.getParameter("productID"));
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));
        String batch = request.getParameter("batch");
//        int modifiedBy = Integer.parseInt(request.getParameter("account"));
        Product p = new Product();
        p.setProductId(productID);
        p.setBatchSerial(batch);
        p.setStatus(status);
        p.setProductSerieId(daoProduct.getProduct(productID).getProductSerieId());
        p.setModifiedBy(Base.Util.Utilities.getLoggingInAccount(request, response));
        p.setModifiedDate(Base.Util.Formatter.getCurrentDate());
        String modifiedHistory = Base.Util.Formatter.getCurrentDate().toString() + " - " + Base.Util.Utilities.getLoggingInAccount(request, response).getUserName() + ": Update Product " + productID + " <br>";
        p.setModifiedHistory(modifiedHistory);
        daoProduct.updateProduct(p);
        response.sendRedirect("ViewProduct?productID=" + productID);
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
