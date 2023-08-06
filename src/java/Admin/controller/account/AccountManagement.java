/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.account;

import Base.Dal.DaoAccount;
import Base.Model.Account;
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
 * @author Giang Minh
 */
@WebServlet(name = "AccountManagement", urlPatterns = {"/AccountManagement"})
public class AccountManagement extends HttpServlet {

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
            out.println("<title>Servlet AccountManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountManagement at " + request.getContextPath() + "</h1>");
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
        DaoAccount daoAccount = new DaoAccount();
        int count = daoAccount.countSearch("");
        int entries = request.getParameter("entries") == null ? 5 : Integer.parseInt(request.getParameter("entries"));
        int endPage = (count % entries == 0) ? (count / entries) : (count / entries + 1);
        int indexPage = request.getParameter("index") != null ? Integer.parseInt(request.getParameter("index")) : 1;
        List<Account> accountList = daoAccount.searchAccountBy("", indexPage, entries, "");

        request.setAttribute("accountList", accountList);
        request.setAttribute("currentPage", indexPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listSize", count);
        request.setAttribute("entries", entries);
        request.getRequestDispatcher("admin/account/index.jsp").forward(request, response);
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
        DaoAccount daoAccount = new DaoAccount();
        String filter = request.getParameter("filter");
        String search = request.getParameter("search");
        int entry = Integer.parseInt(request.getParameter("entry"));

        String searchQuery = daoAccount.getAllAccountByFilter(filter, search);
//        System.out.println(searchQuery);
        // total elements searched
        int count = daoAccount.countSearch(searchQuery);
        int endPage = (count % entry == 0) ? (count / entry) : (count / entry + 1);
        int index = (request.getParameter("index") == null || Integer.parseInt(request.getParameter("index")) < 0)
                ? 1 : Integer.parseInt(request.getParameter("index"));

        // showed list
        List<Account> accountList = daoAccount.searchAccountBy(searchQuery, index, entry, "");

        PrintWriter out = response.getWriter();
        int j = 0;
        for (Account a : accountList) {
            out.print("<tr>\n"
                    + "                                                                <td>\n"
                    + "                                                                    <div class=\"form-check form-check-muted m-0\">\n"
                    + "                                                                        <label class=\"form-check-label\">\n"
                    + "                                                                            <input type=\"checkbox\" class=\"form-check-input\" name=\"Ids[]\" value=\"" + a.getAccountId() + "\">\n"
                    + "<i class=\"input-helper\"></i>"
                    + "                                                                        </label>\n"
                    + "                                                                    </div>\n"
                    + "                                                                </td>\n"
                    + "                                                                <td> \n"
//                    + "                                                                    <a href=\"AccountRolesManagement?id=" + a.getAccountId() + "\"> " + a.getUserName() + " </a>\n"
                    + a.getUserName()
                    + "                                                                </td>\n"
                    + "                                                                <td id=\"HidePass\" onclick=\"ShowHidePass(" + j + ")\"> \n"
                    + "                                                                    <i id=\"i" + j + "\" class=\"mdi mdi-barcode\" style=\"font-size: 22px\"></i>\n"
                    + "                                                                    <p id=\"p" + (j++) + "\" style=\"display: none\">\n"
                    + "                                                                        " + a.getPassword() + "\n"
                    + "                                                                    </p>\n"
                    + "                                                                </td>\n"
                    + "                                                                <td>\n"
                    + "                                                                    " + a.getLastName() + " " + a.getFirstName() + "\n"
                    + "                                                                </td>\n"
                    + "                                                                <td>\n"
                    + "                                                                    " + a.getPhoneNumber() + "\n"
                    + "                                                                </td>\n"
                    + "                                                                <td>\n"
                    + "                                                                    <a href=\"UpdateAccount?id=" + a.getAccountId() + "\">\n"
                    + "                                                                        <i class=\"mdi mdi-lead-pencil\" style=\"font-size: 22px\"></i>\n"
                    + "                                                                    </a>\n"
                    + "                                                                    &nbsp;\n"
                    + "                                                                    <i class=\"mdi mdi-delete\" style=\"font-size: 22px; color: #1079eb\" \n"
                    + "                                                                       onclick=\"confirmDelete('DeleteCustomer', " + a.getAccountId() + ")\">\n"
                    + "                                                                    </i>"
                    + "                                                                </td>\n"
                    + "                                                            </tr>");
        }

        out.print("<td colspan=\"10\">\n"
                + "                                                        <nav aria-label=\"Page navigation\">\n"
                + "                                                            <p> There are " + accountList.size() + " / " + count + " Accounts</p>\n"
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
