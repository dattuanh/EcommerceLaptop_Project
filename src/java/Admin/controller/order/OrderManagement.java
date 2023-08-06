/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.order;

import Base.Dal.DAOOrder;
import Base.Model.Order;
import Base.Model.OrderStatus;
import java.util.List;
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
@WebServlet(name = "OrderManagement", urlPatterns = {"/OrderManagement"})
public class OrderManagement extends HttpServlet {

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
            out.println("<title>Servlet OrderManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderManagement at " + request.getContextPath() + "</h1>");
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
            //Paging pg = new Paging();
            int count = odao.countTableItems();
            int entries = request.getParameter("entries") == null ? 5 : Integer.parseInt(request.getParameter("entries"));
            int endPage = (count % entries == 0) ? (count / entries) : (count / entries + 1);
            int indexPage = request.getParameter("index") != null ? Integer.parseInt(request.getParameter("index")) : 1;
            List<Order> pagingOrder = odao.pagingOrder(indexPage, entries, "where isDelete = 0");
            List<OrderStatus> slist = odao.getAllOrderStatus();

            request.setAttribute("pagingOrder", pagingOrder);
            request.setAttribute("currentPage", indexPage);
            request.setAttribute("endPage", endPage);
            request.setAttribute("listSize", count);
            request.setAttribute("entries", entries);
            request.setAttribute("slist", slist);
            request.getRequestDispatcher("admin/order/index.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("OrderManagementDoGet: " + e.getMessage());
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
            List<OrderStatus> slist = odao.getAllOrderStatus();
            String filter = request.getParameter("filter");
            String search = request.getParameter("search");
            int entry = Integer.parseInt(request.getParameter("entry"));
            System.out.println(filter);
            System.out.println(search);
            String searchQuery = odao.getAllOrderByFilter(filter, search);

            // total elements searched
            int count = odao.countSearch(searchQuery);
            int endPage = (count % entry == 0) ? (count / entry) : (count / entry + 1);
            int index = (request.getParameter("index") == null || Integer.parseInt(request.getParameter("index")) < 0)
                    ? 1 : Integer.parseInt(request.getParameter("index"));

            // showed list
            List<Order> olist = odao.searchOrderBy(searchQuery, index, entry, "");

            if (olist != null) {
                PrintWriter out = response.getWriter();
                for (Order o : olist) {
                    String status = "";
                    String select = "";
                    if (o.getStatus().getOrderStatusName().equals("Approved")) {
                        status = "btn-outline-success";
                    } else if (o.getStatus().getOrderStatusName().equals("Pending")) {
                        status = "btn-outline-warning";
                    } else if (o.getStatus().getOrderStatusName().equals("Rejected")) {
                        status = "btn-outline-danger";
                    } else {
                        status = "btn-outline-info";
                    }
                    out.print("<tr>\n"
                            + "                                                                <td>\n"
                            + "                                                                    <div class=\"form-check form-check-muted m-0\">\n"
                            + "                                                                        <label class=\"form-check-label\">\n"
                            + "                                                                            <input type='checkbox' class=\"form-check-input\" name=\"Ids[]\" value=\"" + o.getOrderId() + "\">\n"
                            + "<i class=\"input-helper\"></i>"
                            + "                                                                        </label>\n"
                            + "                                                                    </div>\n"
                            + "                                                                </td>\n"
                            + "\n"
                            + "                                                                <td> " + o.getCustomer().getUserName() + " </td>\n"
                            + "                                                                <td>  " + Base.Util.Formatter.dateToString(o.getOrderDate()) + " </td>\n"
                            + "                                                                <td>  " + Base.Util.Formatter.moneyFormat(o.getTotalPrice()) + "</td>\n"
                            + "                                                                <td>\n"
                            + "                                                                     <div class=\"dropdown\">"
                            + "                                                                         <select class=\"btn dropdown-toggle " + status + "\" "
                            + "                                                                                 id=\"Status\""
                            + "                                                                                 onchange=\"location.href = 'EditOrder?statusId=' + this.value + '&orderId=" + o.getOrderId() + "'\""
                            + "                                                                                 name=\"status\""
                            + "                                                                                 data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">");
                    for (OrderStatus st : slist) {
                        if (o.getStatus().getOrderStatusId() == st.getOrderStatusId()) {
                            select = "selected";
                        }
                        out.print("<option class=\"dropdown-item\" value=\"" + st.getOrderStatusId() + "\"" + select + ">" + st.getOrderStatusName() + "</option>");
                        select = "";
                    }
                    out.print("                                                                         </select>"
                            + "                                                                     </div>"
                            + "                                                                </td>\n"
                            + "                                                                <td>" + Base.Util.Formatter.dateToString(o.getPaymentDate()) + "</td>\n"
                            + "                                                                <td>" + o.getPaymentMethod() + "</td>"
                            + "                                                                <td>\n"
                            + "                                                                    <!--<div class=\"badge badge-outline-success\">Approved</div>-->\n"
                            + "                                                                    <a href=\"DetailOrder?orderId=" + o.getOrderId() + "&customerId=" + o.getCustomer().getCustomerId() + "\">\n"
                            + "                                                                        <i class=\"mdi mdi-eye\" style=\"font-size: 22px\"></i>\n"
                            + "                                                                    </a>\n"
                            + "                                                                    &nbsp;\n"
                            + "                                                                    <!--<a href=\"UpdateCustomer?id=1\">-->\n"
                            + "                                                                        <!--<i class=\"mdi mdi-lead-pencil\" style=\"font-size: 22px\"></i>-->\n"
                            + "                                                                    <!--</a>-->\n"
                            + "                                                                    &nbsp;\n"
                            + "                                                                    <i class=\"mdi mdi-delete\" style=\"font-size: 22px; color: #1079eb\"\n"
                            + "                                                                        onclick=\"confirmDelete('DeleteOrder', " + o.getOrderId() + ")\">\n"
                            + "                                                                    </i>\n"
                            + "                                                                </td>\n"
                            + "                                                            </tr>");
                }

                out.print("<td colspan=\"10\">\n"
                        + "                                                        <nav aria-label=\"Page navigation\">\n"
                        + "                                                            <p> There are " + olist.size() + " / " + count + " Orders</p>\n"
                        + "                                                            <ul class=\"pagination justify-content-end\">\n"
                        + "                                                                <li class=\"page-item\">\n"
                        + "                                                                    <button type=\"button\" class=\"page-link\"  " + (index > 1 ? "" : "disabled") + "  onclick=\"search(" + (index - 1) + ")\" aria-label=\"Previous\">\n"
                        + "                                                                        <span aria-hidden=\"true\">&laquo;</span>\n"
                        + "                                                                        <span class=\"sr-only\">Previous</span>\n"
                        + "                                                                    </button>\n"
                        + "                                                                </li>\n");

                for (int i = 1; i <= endPage; i++) {
                    out.print("<li class=\"page-item " + (index == i ? "active" : "") + " \">\n"
                            + "                                                                        <button class=\"page-link\" type=\"button\" name=\"pageLink\" id=\"pageLink\" onclick=\"search(" + i + ")\">" + i + "</button>\n"
                            + "                                                                    </li>");
                }

                out.print("                                                                <li class=\"page-item\">\n"
                        + "                                                                    <button type=\"button\" class=\"page-link\" " + (index < endPage ? "" : "disabled") + " onclick=\"search(" + (index + 1) + ")\" aria-label=\"Next\">\n"
                        + "                                                                        <span aria-hidden=\"true\">&raquo;</span>\n"
                        + "                                                                        <span class=\"sr-only\">Next</span>\n"
                        + "                                                                    </button>\n"
                        + "                                                                </li>\n"
                        + "                                                            </ul>\n"
                        + "                                                        </nav>  \n"
                        + "                                                    </td>");
            }
        } catch (Exception e) {
            System.out.println("OrderManagementDoPost: " + e.getMessage());
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
