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
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CloseOrder", urlPatterns = {"/CloseOrder"})
public class CloseOrder extends HttpServlet {

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
            out.println("<title>Servlet CloseOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CloseOrder at " + request.getContextPath() + "</h1>");
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
            String restore = request.getParameter("restore");

            if (restore != null) {
                String orderId = request.getParameter("orderId");
                odao.restoreOrder(orderId);
                Base.Util.Utilities.noti(request, true, "Restore success");
                response.sendRedirect("OrderManagement");
            } else {
                List<Order> olist = odao.getAllDeletedOrders("where isDelete = 1");
                request.setAttribute("olist", olist);
                request.getRequestDispatcher("admin/order/closeOrder.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("CloseOrderDoGet:" + e.getMessage());
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

            String[] orderIds = request.getParameterValues("orderIds[]");
            for (String orderId : orderIds) {
                odao.restoreOrder(orderId);
            }
            response.sendRedirect("OrderManagement");
        } catch (Exception e) {
            System.out.println("CloseOrderDoPost:" + e.getMessage());
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
