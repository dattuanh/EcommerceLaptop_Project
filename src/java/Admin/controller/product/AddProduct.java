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
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddProduct", urlPatterns = {"/AddProduct"})
public class AddProduct extends HttpServlet {

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
            out.println("<title>Servlet CreateProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateProduct at " + request.getContextPath() + "</h1>");
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
        DaoSerie dao = new DaoSerie();
        if (request.getParameter("serie") == null) {
            List<ProductSeri> s = dao.getAllProductSerie();
            request.setAttribute("serieList", s);
        } else {
            String serieID = request.getParameter("serie");
            ProductSeri s = dao.getProductSeri(serieID);
        request.setAttribute("selectedSerie", s);
        }
        request.getRequestDispatcher("admin/product/addProduct.jsp").forward(request, response);
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
        String serieID = request.getParameter("serie");
        int stock = Integer.parseInt(request.getParameter("stock"));
        String batch = request.getParameter("batch");
//        int createdBy = Integer.parseInt(request.getParameter("account"));
        Date date = new Date();
        DaoProduct daoProduct = new DaoProduct();
        DaoSerie daoSerie = new DaoSerie();
//        Product p = new Product(dao.getProductSeries(serieID), batch, date, dao.getAccount(createdBy));
        Product p = new Product();
        p.setProductSerieId(daoSerie.getProductSeri(serieID));
        p.setBatchSerial(batch);
        p.setCreatedBy(Base.Util.Utilities.getLoggingInAccount(request, response));
        p.setCreatedDate(date);
        p.setModifiedBy(Base.Util.Utilities.getLoggingInAccount(request, response));
        p.setModifiedDate(date);
        
        for (int i = 0; i < stock; i++) {
            daoProduct.addProduct(p);
        }
        String action = p.getModifiedDate().toString() + " - " + p.getModifiedBy().getUserName() + ": Added new Batch of " + stock + " Products, Batch Serial: " + batch+ " <br>";
        p.setModifiedHistory(action);
        daoSerie.updateSerieByProduct(p, action);
        response.sendRedirect("UpdateSerie?serie=" + serieID);
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
