/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.news;

import Base.Dal.DAONews;
import Base.Model.News;
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
@WebServlet(name = "NewsManagement", urlPatterns = {"/NewsManagement"})
public class NewsManagement extends HttpServlet {

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
            out.println("<title>Servlet NewsManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewsManagement at " + request.getContextPath() + "</h1>");
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
        DAONews daoNews = new DAONews();
        int count = daoNews.countTableItemsA();
        int entry = request.getParameter("entries") == null ? 5 : Integer.parseInt(request.getParameter("entries"));
        int endPage = (count % entry == 0) ? (count / entry) : (count / entry + 1);
        int indexPage = request.getParameter("index") != null ? Integer.parseInt(request.getParameter("index")) : 1;
        List<News> newsList = daoNews.searchNewsByA("", indexPage, entry, "");

        request.setAttribute("newsList", newsList);
        request.setAttribute("currentPage", indexPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("entries", entry);
        request.setAttribute("listSize", count);
        request.getRequestDispatcher("admin/news/index.jsp").forward(request, response);
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
        DAONews daoNews = new DAONews();

        if (request.getParameter("newsId") != null) {
            int newsId = Integer.parseInt(request.getParameter("newsId"));
            String action = request.getParameter("action");

            News news = daoNews.getNews(newsId);

            if ("client".equals(action)) {
                // Đảo ngược giá trị thuộc tính isPresent
                news.setIsPresent(!news.isIsPresent());
            } else if ("slider".equals(action)) {
                // Đảo ngược giá trị thuộc tính isSlider
                news.setIsSlider(!news.isIsSlider());
            }

            daoNews.updateNewsStatus(news);
            return;
        }

        String filter = request.getParameter("filter");
        String search = request.getParameter("search");

        String searchQuery = daoNews.getAllNewsByFilter(filter, search);
        int entry = Integer.parseInt(request.getParameter("entry"));
//        System.out.println("searchquery: " + searchQuery);
        // total elements searched
        int count = daoNews.countSearchA(searchQuery);
        int endPage = (count % entry == 0) ? (count / entry) : (count / entry + 1);
        int index = (request.getParameter("index") == null || Integer.parseInt(request.getParameter("index")) < 0)
                ? 1 : Integer.parseInt(request.getParameter("index"));

        // showed list
        List<News> newsList = daoNews.searchNewsByA(searchQuery, index, entry, "");

        if (newsList != null) {
            PrintWriter out = response.getWriter();
            for (News c : newsList) {
                 out.println("<tr>");
                out.println("    <td>");
                out.println("        <div class=\"form-check form-check-muted m-0\">");
                out.println("            <label class=\"form-check-label\">");
                out.println("                <input type='checkbox' class=\"form-check-input\" name=\"Ids[]\" value=\"" + c.getNewId() + "\">");
                out.println("                <i class=\"input-helper\"></i>");
                out.println("            </label>");
                out.println("        </div>");
                out.println("    </td>");
                out.println("    <td class=\"py-1\">");
                out.println("        <img id=\"output\" src=\"images/News/" + c.getNewsImage() + "\"");
                out.println("             onerror=\"this.onerror=null;this.src='images/others/img-14.jpg';\"");
                out.println("             />");
                out.println("    </td>");
                out.println("    <td>");
                out.println("        " + (c.getNewTitle().length() >= 20 ? c.getNewTitle().substring(0, 20) : c.getNewTitle()));
                out.println("    </td>");
//                out.println("    <td>");
//                out.println("        " + (c.getNewsHeading().length() >= 20 ? c.getNewsHeading().substring(0, 20) : c.getNewsHeading()));
//                out.println("    </td>");
                out.println("    <td>");
                out.println("        " + (c.getCreateBy() != null ? c.getCreateBy().getUserName() : ""));
                out.println("    </td>");
                out.println("    <td>");
                out.println("        " + (c.getModifiedBy() != null ? c.getModifiedBy().getUserName() : ""));
                out.println("    </td>");
                out.println("    <td>");
                out.println("        <span class=\"badge " + (c.isIsPresent() ? "badge-success" : "badge-warning") + "\"");
                out.println("              onclick=\"showToClient(this, " + c.getNewId() + ")\">");
                out.println("            " + (c.isIsPresent() ? "Show" : "Not Show"));
                out.println("        </span>");
                out.println("    </td>");
                out.println("    <td>");
                out.println("        <span class=\"badge " + (c.isIsSlider() ? "badge-success" : "badge-warning") + "\"");
                out.println("              onclick=\"showToSlider(this, " + c.getNewId() + ")\">");
                out.println("            " + (c.isIsSlider() ? "Show" : "Not Show"));
                out.println("        </span>");
                out.println("    </td>");
                out.println("    <td>");
                out.println("        &nbsp;");
                out.println("        <a href=\"UpdateNews?id=" + c.getNewId() + "\">");
                out.println("            <i class=\"mdi mdi-lead-pencil\" style=\"font-size: 22px\"></i>");
                out.println("        </a>");
                out.println("        &nbsp;");
                out.println("        <i class=\"mdi mdi-delete\" style=\"font-size: 22px; color: #1079eb\"");
                out.println("           onclick=\"confirmDelete('DeleteNews', " + c.getNewId() + ")\">");
                out.println("        </i>");
                out.println("    </td>");
                out.println("</tr>");

            }

            out.print("<td colspan=\"10\">\n"
                    + "                                                        <nav aria-label=\"Page navigation\">\n"
                    + "                                                            <p> There are " + newsList.size() + " / " + count + " news</p>\n"
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
