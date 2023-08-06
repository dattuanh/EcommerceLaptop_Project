/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.order;

import Base.Dal.DAOOrder;
import Base.Model.Account;
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

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Edit", urlPatterns = {"/EditOrder"})
public class Edit extends HttpServlet {

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
            out.println("<title>Servlet Edit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Edit at " + request.getContextPath() + "</h1>");
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
            DAOOrder odao = new DAOOrder();
            String statusId = request.getParameter("statusId");   // update status
            OrderStatus ostatus = odao.getOrderStatus(statusId);

            String orderId = request.getParameter("orderId");  // orderId
            Order o = odao.getOrder(orderId);
            Account modifiedAccount = Base.Util.Utilities.getLoggingInAccount(request, response);
            int acc = modifiedAccount.getAccountId();

            Date modifiedDate = Base.Util.Formatter.getCurrentDate();

            String modifiedHistory = o.getModifiedHistory();
            modifiedHistory += "AccountId: " + acc + ";"
                    + "Date: " + Base.Util.Formatter.getCurrentDate() + ";"
                    + "Action: Update Order|";

            Order updateOrder = new Order(Integer.parseInt(orderId), o.getCustomer(), o.getOrderDate(), o.getTotalPrice(), ostatus, o.getPaymentDate(), o.getPaymentMethod(), o.getCashReceive(), o.getCashBack(), o.getAddress(), modifiedDate, modifiedAccount, modifiedHistory);

            odao.editOrder(updateOrder);
            Base.Util.Utilities.noti(request, true, "Update success");
            response.sendRedirect("OrderManagement");
            
        } catch (Exception e) {
            System.out.println("EditOrderDoGet: " + e.getMessage());
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
        //String orderDate = request.getParameter("orderDate");
        //Util.Formatter.formatDate(orderDate);
        //String statusId = request.getParameter("statusId:");
        //String paymentMethod = request.getParameter("paymentMethod");
        //String cashReceive = request.getParameter("cashReceive");
        //String cashBack = request.getParameter("cashBack");
        //String address = request.getParameter("address");
        //Account modifiedAccount = Util.Utilities.getLoggingInAccount(request, response);
        //int acc = modifiedAccount.getAccountId();
        //Date modifiedDate = Util.Formatter.getCurrentDate();
        //String orderId = request.getParameter("orderId");

        //DAO dao = new DAO();
        //DAOOrder odao = new DAOOrder();
        //Order o = new Order(orderDate, status, paymentMethod, cashReceive, cashBack, address, modifiedDate, modifiedAccount, Util.Utilities.STRING_DEFAUT);
        //odao.editOrder(statusId ,orderId);
        //request.getRequestDispatcher("OrderManagement").forward(request, response);
        //response.sendRedirect("OrderManagement");
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
