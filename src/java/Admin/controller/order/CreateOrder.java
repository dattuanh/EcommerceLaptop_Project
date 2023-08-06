/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.order;

import Base.Dal.DAOOrder;
import Base.Dal.DaoCustomer;
import Base.Model.Account;
import Base.Model.Customer;
import Base.Model.Order;
import Base.Model.OrderStatus;
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
 * @author ADMIN
 */
@WebServlet(name = "CreateOrder", urlPatterns = {"/AddOrder"})
public class CreateOrder extends HttpServlet {

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
            out.println("<title>Servlet Add</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Add at " + request.getContextPath() + "</h1>");
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
            DaoCustomer dao = new DaoCustomer();
            DAOOrder odao = new DAOOrder();
            List<Customer> allCustomers = dao.getAllCustomers();
            List<OrderStatus> allOrderStatus = odao.getAllOrderStatus(); //dao.getAllOrderStatus();

            request.setAttribute("allCustomers", allCustomers);
            request.setAttribute("allOrderStatus", allOrderStatus);
            request.getRequestDispatcher("admin/order/add.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("AddOrderDoGet: " + e.getMessage());
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
            String customerId = request.getParameter("customerId");
            Date orderDate = Base.Util.Formatter.getCurrentDate();
            double totalPrice = Base.Util.Utilities.formatNumber(request.getParameter("totalPrice"));
            String status = request.getParameter("status");
            Date paymentDate = Base.Util.Formatter.getCurrentDate();
            String paymentMethod = request.getParameter("paymentMethod");
            double cashReceive = Base.Util.Utilities.formatNumber(request.getParameter("cashReceive"));
            double cashBack = Base.Util.Utilities.formatNumber(request.getParameter("cashBack"));
            String address = request.getParameter("address");
            Date modifiedDate = Base.Util.Formatter.getCurrentDate();
            Account modifiedAccount = Base.Util.Utilities.getLoggingInAccount(request, response);
            String modifiedHistory = modifiedAccount.getAccountId() + ";" + orderDate.toString() + ";" + "Create New Order";

            DaoCustomer dao = new DaoCustomer();
            DAOOrder odao = new DAOOrder();
            Order o = new Order(dao.getCustomer(Integer.parseInt(customerId)), orderDate, totalPrice, odao.getOrderStatus(status), paymentDate, paymentMethod, cashReceive, cashBack, address, modifiedDate, modifiedAccount, modifiedHistory);
            //dao.createOrder(o);
            odao.createOrder(o);
            response.sendRedirect("OrderManagement");
        } catch (Exception e) {
            System.out.println("AddOrderDoPost: " + e.getMessage());
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
