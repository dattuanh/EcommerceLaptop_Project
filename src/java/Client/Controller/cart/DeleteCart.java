/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package client.controller.cart;

import Base.Dal.DAO;
import Base.Model.Cart;
import Base.Model.CartItem;
import Base.Model.ProductSeri;
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
@WebServlet(name = "DeleteCart", urlPatterns = {"/DeleteCart"})
public class DeleteCart extends HttpServlet {

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
            out.println("<title>Servlet DeleteCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteCart at " + request.getContextPath() + "</h1>");
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
            String pSeriId = request.getParameter("pSeriId");
            int pSeriIdInt = Integer.parseInt(pSeriId);
            DAO dao = new DAO();
            List<ProductSeri> list = dao.getAllProductSerie();

            Cookie[] arr = request.getCookies();
            String txt = "";
            String txt1 = "";
            if (arr != null) {
                for (Cookie c : arr) {
                    if (c.getName().equals("cart")) {
                        txt += c.getValue();
                        c.setMaxAge(0);
                        response.addCookie(c);
                    }
                }
            }

            Cart cart = new Cart(txt, list);

            CartItem selectedItem = null;
            for (CartItem item : cart.getListItem()) {
                if (item.getProductSeri().getProductSeriId() == pSeriIdInt) {
                    selectedItem = item;
                }
            }
            cart.getListItem().remove(selectedItem);

            int dem = 1;
            for (CartItem write : cart.getListItem()) {

                if (dem == 1) {
                    txt1 += write.getProductSeri().getProductSeriId() + ":" + write.getQuantity();
                } else {
                    txt1 += "-" + write.getProductSeri().getProductSeriId() + ":" + write.getQuantity();
                }
                dem++;
            }
            Cookie ck = new Cookie("cart", txt1);
            ck.setMaxAge(2 * 24 * 60 * 60);
            response.addCookie(ck);
            request.setAttribute("cart", cart);
            Base.Util.Utilities.noti(request, true, "Delete " + getSplitProductName(selectedItem.getProductSeri().getProductSeriName()) + " from Cart");
            //response.sendRedirect("");
            //request.getRequestDispatcher("client/cart.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("DeleteCartDoGet: " + e.getMessage());
        }
    }
    
    private String getSplitProductName(String txt){
        String result = txt.split("\\(")[0];
        return result;
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
