/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package client.controller.cart;

import Base.Dal.DAO;
import Base.Model.Cart;
import Base.Model.CartItem;
import Base.Model.Customer;
import Base.Model.ProductSeri;
import Base.Util.Utilities;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ViewCheckout", urlPatterns = {"/ViewCheckout"})
public class ViewCheckout extends HttpServlet {

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
            out.println("<title>Servlet ViewCheckout</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewCheckout at " + request.getContextPath() + "</h1>");
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
        try {
            Customer cus = Utilities.getLoggingInCustomer(request, response);
            DAO dao = new DAO();
            List<ProductSeri> list = dao.getAllProductSerie();

            Cookie[] arr = request.getCookies();
            String txt = "";
            boolean checkCart = false;
            if (arr != null) {
                for (Cookie c : arr) {
                    if (c.getName().equals("cart")) {
                        txt += c.getValue();
                        if(c.getValue() != null && !c.getValue().isEmpty()){
                            System.out.println("checkCart true");
                            checkCart = true;
                        }
                    }
                }
            }

            if (checkCart) {
                Cart cart = new Cart(txt, list);

                if (cus != null) {
                    request.setAttribute("RequireCustomerInfo", false);
                    request.setAttribute("cus", cus);
                } else {
                    request.setAttribute("RequireCustomerInfo", true);
                }
                
                request.setAttribute("cart", cart);
                request.getRequestDispatcher("client/checkout.jsp").forward(request, response);
            }
            else{
                Base.Util.Utilities.noti(request, false, "Can't Checkout. There's nothing in cart");
                response.sendRedirect("ViewCart");
            }
        } catch (Exception e) {
            System.out.println("ViewCheckoutDoGet: " + e.getMessage());
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
        processRequest(request, response);
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
