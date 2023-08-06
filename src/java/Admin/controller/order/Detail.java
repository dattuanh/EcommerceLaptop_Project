/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.order;

import Base.Dal.DAOOrder;
import Base.Dal.DaoCustomer;
import Base.Model.Customer;
import Base.Model.Order;
import Base.Model.OrderItem;
import Base.Model.OrderStatus;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Detail", urlPatterns = {"/DetailOrder"})
public class Detail extends HttpServlet {

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
            out.println("<title>Servlet Detail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Detail at " + request.getContextPath() + "</h1>");
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
            String orderId = request.getParameter("orderId");
            String customerId = request.getParameter("customerId");
            DaoCustomer dao = new DaoCustomer();
            DAOOrder odao = new DAOOrder();
            List<OrderItem> orderItemList = odao.getOrderItemByOrderId(orderId);
            Order order = odao.getOrder(orderId);
            Customer cus = dao.getCustomer(Integer.parseInt(customerId));
            request.setAttribute("cus", cus);
            request.setAttribute("order", order);
            request.setAttribute("orderItemList", orderItemList);
            request.getRequestDispatcher("admin/order/detail.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("DetailOrderDoGet:" + e.getMessage());
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
            String orderId = request.getParameter("orderId");
            //DAO dao = new DAO();
            DAOOrder odao = new DAOOrder();
            Order order = odao.getOrder(orderId); //dao.getOrder(orderId);
            List<OrderStatus> slist = odao.getAllOrderStatus(); //dao.getAllOrderStatus();
            request.setAttribute("slist", slist);
            request.setAttribute("order", order);
            request.getRequestDispatcher("admin/order/edit.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("DetailOrderDoPost: " + e.getMessage());
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
