/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.newsgroup;

import Base.Dal.DAONewsGroup;
import Base.Model.NewsGroup;
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
@WebServlet(name = "NewsGroupManagement", urlPatterns = {"/NewsGroupManagement"})
public class NewsGroupManagement extends HttpServlet {

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
            out.println("<title>Servlet NewsGroupManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewsGroupManagement at " + request.getContextPath() + "</h1>");
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
        DAONewsGroup dao = new DAONewsGroup();
        int count = dao.countTableItems();
        int entries = request.getParameter("entries") == null ? 5 : Integer.parseInt(request.getParameter("entries"));
        int endPage = (count % entries == 0) ? (count / entries) : (count / entries + 1);
        int indexPage = request.getParameter("index") != null ? Integer.parseInt(request.getParameter("index")) : 1;
        List<NewsGroup> newsGroupList = dao.pagingNewsGroup(indexPage, entries);
        request.setAttribute("currentPage", indexPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listSize", count);
        request.setAttribute("entries", entries);
        request.setAttribute("newsGroupList", newsGroupList);
        request.getRequestDispatcher("admin/newsgroup/index.jsp").forward(request, response);
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
        DAONewsGroup dao = new DAONewsGroup();
        System.out.println("ok");
        String filter = request.getParameter("filter");
        String search = request.getParameter("search");
        int entry = Integer.parseInt(request.getParameter("entry"));
        String searchQuery = dao.getAllNewsGroupByFilter(filter, search, 0);
        // total elements searched
        int count = dao.countSearch(searchQuery);
        int endPage = (count % entry == 0) ? (count / entry) : (count / entry + 1);
        int index = (request.getParameter("index") == null || Integer.parseInt(request.getParameter("index")) < 0)
                ? 1 : Integer.parseInt(request.getParameter("index"));

        // showed list
        List<NewsGroup> newsGroupList = dao.searchNewsBy(searchQuery, index, entry, "");

        if (newsGroupList != null) {
            PrintWriter out = response.getWriter();
            for (NewsGroup c : newsGroupList) {
                out.print("<tr>\n"
                        + "                                                                <td>\n"
                        + "                                                                    <div class=\"form-check form-check-muted m-0\">\n"
                        + "                                                                        <label class=\"form-check-label\">\n"
                        + "                                                                            <input type=\"checkbox\" class=\"form-check-input\" name=\"Ids[]\" value=\"" + c.getNewsGroupID() + "\">\n"
                        + "<i class=\"input-helper\"></i>"
                        + "                                                                        </label>\n"
                        + "                                                                    </div>\n"
                        + "                                                                </td>\n"
                        + "                                                                <td> " + c.getNewsGroupID() + "  </td>\n"
                        + "                                                                <td> " + c.getNewsGroupName() + " </td>\n"
                        + "                                                                <td>\n"
                        + "                                                                    &nbsp;\n"
                        + "                                                                    <a href=\"UpdateNewsGroup?id=" + c.getNewsGroupID() + "\">\n"
                        + "                                                                        <i class=\"mdi mdi-lead-pencil\" style=\"font-size: 22px\"></i>\n"
                        + "                                                                    </a>\n"
                        + "                                                                    &nbsp;\n"
                        + "                                                                    <i class=\"mdi mdi-delete\" style=\"font-size: 22px; color: #1079eb\" \n"
                        + "                                                                       onclick=\"confirmDelete('DeleteNewsGroup', " + c.getNewsGroupID() + "\">\n"
                        + "                                                                    </i>\n"
                        + "                                                                </td>\n"
                        + "                                                            </tr>");
            }

            out.print("<td colspan=\"10\">\n"
                    + "                                                        <nav aria-label=\"Page navigation\">\n"
                    + "                                                            <p> There are " + newsGroupList.size() + " / " + count + " groups</p>\n"
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
