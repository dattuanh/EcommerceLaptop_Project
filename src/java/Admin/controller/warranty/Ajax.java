/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.warranty;

import Base.Dal.DaoCustomer;
import Base.Dal.DaoProduct;
import Base.Dal.DaoWarranties;
import Base.Model.Customer;
import Base.Model.Product;
import Base.Model.Warranty;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Giang Minh
 */
@WebServlet(name = "Ajax", urlPatterns = {"/Ajax"})
public class Ajax extends HttpServlet {

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
            out.println("<title>Servlet Ajax</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Ajax at " + request.getContextPath() + "</h1>");
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
        String search = request.getParameter("search");
        List<Product> listProduct = new DaoProduct().getSoldProduct(search);
        if (!listProduct.isEmpty()) {
            PrintWriter out = response.getWriter();
            for (Product p : listProduct) {
                if (p != null) {
                    out.println("<ul class=\"list-group \" style=\"width: 100%;\" >\n"
                            + "  <li class=\"list-group-item\" onclick=\"getData(" + p.getProductId() + ")\""
                            + "style=\"z-index: 3;\">"
                            + "Id:" + p.getProductId() + ", "
                            + "Name:" + p.getProductSerieId().getProductSeriName() + ", "
                            + "Customer Name: " + new DaoCustomer().getCustomerByProductId(search).getFullName()
                            + "</li>\n"
                            + "</ul>");
                }
            }
        }
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

        String productId = request.getParameter("productId");
        Product p = new DaoProduct().getProduct(productId);
        Customer c = new DaoCustomer().getCustomerByProductId(productId);

        int warrantyTime = Base.Util.Constants.WARRANTY_TIME;
        Date expiredDate = Base.Util.Utilities.plusMonth(new Date(), warrantyTime);

        List<Object> list = new ArrayList<>();
        Gson gson = new Gson();

        Warranty w = new DaoWarranties().getWarrantyByProductId(productId);
        boolean isExistedWarranty = false;
        
        if (w != null) {
            isExistedWarranty = true;
            Date d = Base.Util.Utilities.plusMonth(w.getCreatedDate(), w.getWarrantyTime());
            if (d.compareTo(new Date()) >= 0) {
                list.add("false");
                list.add("Bảo hành cho sản phẩm này đã tồn tại");
                response.getWriter().print(gson.toJson(list));
                return;
            }
        }

        list.add(c);
        list.add(p);
        list.add(Base.Util.Formatter.dateToString(expiredDate));
        list.add(Base.Util.Constants.WARRANTY_PRICE);
        if(isExistedWarranty) {
            list.add("Bảo hành hết hạn");
        }
        response.getWriter().print(gson.toJson(list));
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
