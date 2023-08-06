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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "addToCart", urlPatterns = {"/addToCart"})
public class AddToCart extends HttpServlet {

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
            out.println("<title>Servlet Cart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Cart at " + request.getContextPath() + "</h1>");
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
            DAO dao = new DAO();
            List<ProductSeri> list = dao.getAllProductSerie();
            Cookie[] arr = request.getCookies();
            String txt = "";
            if(arr != null){
                for (Cookie c : arr) {
                    if(c.getName().equals("cart")){
                        txt += c.getValue();
                        c.setMaxAge(0);
                        response.addCookie(c);
                    }
                }
            }
            
            Cart cart = new Cart(txt, list);
            String num = "1";
            String pSeriId = request.getParameter("pSeriId");
            ProductSeri productSeri = dao.getProductSeri(pSeriId);
            int maxQuantity = productSeri.getStock();
            int numInCart = 0;
            
            if(txt.isEmpty()){
                numInCart = 1;
                txt += pSeriId + ":" + num;
            } else{
                if(cart.getItemById(Integer.parseInt(pSeriId)) != null){
                    if(cart.getQuantityByProductSeriId(Integer.parseInt(pSeriId)) < maxQuantity){
                        numInCart = cart.getQuantityByProductSeriId(Integer.parseInt(pSeriId)) + 1;
                        txt = txt + "-" + pSeriId + ":" + num;
                    }
                } else{
                    numInCart = 1;
                    txt = txt + "-" + pSeriId + ":" + num;
                }
            }
            
            Cookie ck = new Cookie("cart", txt);
            ck.setMaxAge(2 * 24 * 60 * 60);
            response.addCookie(ck);
            Base.Util.Utilities.noti(request, true, getSplitProductName(productSeri.getProductSeriName()) +", số lượng: " + numInCart);
        } catch (Exception e) {
            System.out.println("Cart doGet: " + e.getMessage());
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
