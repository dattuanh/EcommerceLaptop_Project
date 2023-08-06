/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package client.controller.cart;

import Base.Dal.DAO;
import Base.Model.Cart;
import Base.Model.CartItem;
import Base.Model.ProductSeri;
import com.google.gson.JsonObject;
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
@WebServlet(name = "ViewCart", urlPatterns = {"/ViewCart"})
public class ViewCart extends HttpServlet {

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
            out.println("<title>Servlet ViewCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewCart at " + request.getContextPath() + "</h1>");
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
            if (arr != null) {
                for (Cookie c : arr) {
                    if (c.getName().equals("cart")) {
                        txt += c.getValue();
                    }
                }
            }

            Cart cart = new Cart(txt, list);
            request.setAttribute("cart", cart);
            request.getRequestDispatcher("client/cart.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("ViewCart doGet: " + e.getMessage());
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
        try {
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

            String productId = request.getParameter("productId");
            String quantity = request.getParameter("quantity");
            if (cart != null) {
                for (CartItem checkItem : cart.getListItem()) {
                    if (checkItem.getProductSeri().getProductSeriId() == Integer.parseInt(productId)) {
                        checkItem.setQuantity(Integer.parseInt(quantity));
                        break;
                    }
                }
                int dem = 1;
                for (CartItem write : cart.getListItem()) {

                    if (dem == 1) {
                        txt1 += write.getProductSeri().getProductSeriId() + ":" + write.getQuantity();
                    } else {
                        txt1 += "-" + write.getProductSeri().getProductSeriId() + ":" + write.getQuantity();
                    }
                    dem++;
                }
                double totalMoney = cart.getTotalMoney();
                JsonObject jsonOb = new JsonObject();
                jsonOb.addProperty("quantity", quantity);
                jsonOb.addProperty("totalMoney", totalMoney);

                // ghi đối tượng jsonObject vào phản hồi của ajax
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonOb.toString());
                
                Cookie ck = new Cookie("cart", txt1);
                ck.setMaxAge(2 * 24 * 60 * 60);
                response.addCookie(ck);

                request.setAttribute("cart", cart);
                response.setStatus(200);
            } else {
                response.setStatus(400);
            }
        } catch (Exception e) {
            System.out.println("ViewCartDoPost: " + e.getMessage());
        }
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
