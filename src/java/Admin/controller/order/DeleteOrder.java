/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.order;

import Base.Dal.DAOOrder;
import Base.Model.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeleteOrder", urlPatterns = {"/DeleteOrder"})
public class DeleteOrder extends HttpServlet {

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
            out.println("<title>Servlet Delete</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Delete at " + request.getContextPath() + "</h1>");
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
            String orderId = request.getParameter("id");
            System.out.println(orderId);
            if (orderId == null || orderId.isEmpty()) {
                response.sendRedirect("OrderManagement");
                return;
            }
            //DAO dao = new DAO();
            DAOOrder odao = new DAOOrder();
            Order order = odao.getOrder(orderId); //dao.getOrder(orderId);
            request.setAttribute("order", order);
            request.getRequestDispatcher("admin/order/delete.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("DeleteOrderDoGet: " + e.getMessage());
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
            DAOOrder odao = new DAOOrder();

            String[] orderIds = request.getParameterValues("Ids[]");
            if (orderIds != null) {
                for (String orderId : orderIds) {
                    odao.deleteOrderItem(orderId); //dao.deleteOrderItem(orderId);
                    odao.deleteOrder(orderId); //dao.deleteOrder(orderId);
                }
            } else {
                String orderId = request.getParameter("id");
                odao.deleteOrderItem(orderId); //dao.deleteOrderItem(orderId);
                odao.deleteOrder(orderId); //dao.deleteOrder(orderId);
            }
            Base.Util.Utilities.noti(request, true, "Delete success");
            response.sendRedirect("OrderManagement");
        } catch (Exception e) {
            System.out.println("DeleteOrderDoPost: " + e.getMessage());
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
