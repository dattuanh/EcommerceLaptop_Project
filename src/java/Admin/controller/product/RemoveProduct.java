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
@WebServlet(name = "RemoveProduct", urlPatterns = {"/RemoveProduct"})
public class RemoveProduct extends HttpServlet {

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
            out.println("<title>Servlet RemoveProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RemoveProduct at " + request.getContextPath() + "</h1>");
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
        String serieID = request.getParameter("serie");
        DAO dao = new DAO();
        ProductSeri s = dao.getProductSeri(serieID);
        request.setAttribute("selectedSerie", s);
        int productID = Integer.parseInt(request.getParameter("productID"));
        Product p = dao.getProduct(productID);
        request.setAttribute("selectedProduct", p);
        request.getRequestDispatcher("admin/product/removeProduct.jsp").forward(request, response);
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
        DaoProduct dao = new DaoProduct();
        DaoSerie daoSerie = new DaoSerie();
        String serieID = request.getParameter("id");
        String[] Ids = request.getParameterValues("Ids[]");
        if (Ids != null) {
            // xóa từng IDs
            for (String Id : Ids) {
                int productID = Integer.parseInt(Id);
                Product p = new Product();
                p.setProductId(productID);
                p.setProductSerieId(daoSerie.getProductSeri(serieID));
                p.setModifiedBy(Base.Util.Utilities.getLoggingInAccount(request, response));
                p.setModifiedDate(Base.Util.Formatter.getCurrentDate());
//                String modifiedHistory = dao.getProduct(productID).getModifiedHistory();
//                modifiedHistory += Base.Util.Formatter.getCurrentDate().toString() + " - " + Base.Util.Utilities.getLoggingInAccount(request, response).getUserName() + ": Remove Product " + productID + " <br>";
                dao.removeProduct(p);
            }
        } else {
            Product p = new Product();
            int productID = Integer.parseInt(request.getParameter("productID"));
            p.setProductId(productID);
            p.setProductSerieId(daoSerie.getProductSeri(serieID));
            p.setModifiedBy(Base.Util.Utilities.getLoggingInAccount(request, response));
            p.setModifiedDate(Base.Util.Formatter.getCurrentDate());
            dao.removeProduct(p);
        }
        response.sendRedirect("ViewSerie?serie=" + serieID);
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
