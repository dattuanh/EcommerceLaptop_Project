/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package client.controller.News;

import Base.Dal.DAONews;
import Base.Util.SlugConverter;
import Base.Dal.DAONewsGroup;
import Base.Model.News;
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
 * @author MSI
 */
@WebServlet(name = "NewsHomeControl", urlPatterns = {"/indexnews"})
public class NewsHomeControl extends HttpServlet {

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
            out.println("<title>Servlet NewsHomeControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewsHomeControl at " + request.getContextPath() + "</h1>");
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
        DAONews dao = new DAONews();
        DAONewsGroup daoNg = new DAONewsGroup();

        List<News> recentNews = dao.getRecentNews();
        List<NewsGroup> newsGroup = daoNg.getAllNewsGroup();
        int count = dao.countTableItems();
        int entries = request.getParameter("entries") == null ? 5 : Integer.parseInt(request.getParameter("entries"));
        int endPage = (count % entries == 0) ? (count / entries) : (count / entries + 1);
        int indexPage = request.getParameter("index") != null ? Integer.parseInt(request.getParameter("index")) : 1;
        List<News> listNews = dao.pagingNews(indexPage);
        List<News> onTopNews = dao.getOnTopNews();
        request.setAttribute("onTopNews", onTopNews);
        request.setAttribute("currentPage", indexPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listSize", count);
        request.setAttribute("entries", entries);
        request.setAttribute("newsGroup", newsGroup);
        request.setAttribute("recentNews", recentNews);
        request.setAttribute("listNews", listNews);

        request.getRequestDispatcher("client/news/indexnews.jsp").forward(request, response);
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
        DAONews daoN = new DAONews();
        String filter = request.getParameter("filter");
        String search = request.getParameter("search");
        String searchQuery = daoN.getAllNewsByFilter(filter, search);
        int entry = Integer.parseInt(request.getParameter("entry"));
        int count = daoN.countSearch(searchQuery);
        int endPage = (count % entry == 0) ? (count / entry) : (count / entry + 1);
        int indexPage = (request.getParameter("index") == null || Integer.parseInt(request.getParameter("index")) < 0)
                ? 1 : Integer.parseInt(request.getParameter("index"));
        System.out.println(indexPage);
        List<News> newsList = daoN.searchNewsByC(searchQuery, indexPage, entry, "");
        if (newsList != null && !newsList.isEmpty()) {
            PrintWriter out = response.getWriter();
            for (News o : newsList) {
                out.println("<div class=\"blog-box row\">");
                out.println("  <div class=\"col-md-4\">");
                out.println("    <div class=\"post-media\">");
                out.println("      <a href=\"TechSingleControl/" + SlugConverter.convertToSlug(o.getNewTitle().replaceAll(" ", "-")) + "-" + o.getNewId() + "-" + o.getNewsGroupID().getNewsGroupID() + "-" + o.getCreateBy().getAccountId() + ".html\">");
                out.println("        <img src=\"images/News/" + o.getNewsImage() + "\" alt=\"\" class=\"img-fluid\">");
                out.println("        <div class=\"hovereffect\"></div>");
                out.println("      </a>");
                out.println("    </div><!-- end media -->");
                out.println("  </div><!-- end col -->");
                out.println("  <div class=\"blog-meta big-meta col-md-8\">");
                out.println("     <h4><a href=\"TechSingleControl/" + SlugConverter.convertToSlug(o.getNewTitle().replaceAll(" ", "-")) + "-" + o.getNewId() + "-" + o.getNewsGroupID().getNewsGroupID() + "-" + o.getCreateBy().getAccountId() + ".html\">" + o.getNewTitle() + "</a></h4>");
                out.println("    <p>" + o.getNewsHeading() + "...</p>");
                out.println("    <small class=\"firstsmall\"><a class=\"bg-orange\" href=\"TechCategoryControl/" + SlugConverter.convertToSlug(o.getNewsGroupID().getNewsGroupName().replaceAll(" ", "-")) + "-" + o.getNewsGroupID().getNewsGroupID() + ".html\" title=\"\">" + o.getNewsGroupID().getNewsGroupName() + "</a></small>");
                out.println("    <small><a href=\"\" title=\"\">" + o.getCreatedDate() + "</a></small>");
                out.println("    <small><a href=\"NewsAuthorControl?id=" + o.getCreateBy().getAccountId() + "\" title=\"\">By " + o.getCreateBy().getFirstName() + " " + o.getCreateBy().getLastName() + "</a></small>");
                out.println("  </div><!-- end meta -->");
                out.println("</div><!-- end blog-box -->");
                out.println(" <hr class=\"invis\">");
            }
            out.println("<nav aria-label=\"Page navigation\">\n"
                    + "                                                            <ul class=\"pagination justify-content-end\">\n"
                    + "                                                                <li class=\"page-item\">\n"
                    + "                                                                    <button type=\"button\" class=\"page-link\"  " + (indexPage > 1 ? "" : "hidden") + "  onclick=\"search(" + (indexPage - 1) + ")\" aria-label=\"Previous\">\n"
                    + "                                                                        <span aria-hidden=\"true\">&laquo;</span>\n"
                    + "                                                                        <span class=\"sr-only\">Previous</span>\n"
                    + "                                                                    </button>\n"
                    + "                                                                </li>\n");
            for (int i = 1; i <= endPage; i++) {
                out.print("<li class=\"page-item " + (indexPage == i ? "active" : "") + " \">\n"
                        + "                                                                        <button class=\"page-link\" type=\"button\" name=\"pageLink\" id=\"pageLink\" onclick=\"search(" + i + ")\">" + i + "</button>\n"
                        + "                                                                    </li>");
            }
            out.print("                                                                <li class=\"page-item\">\n"
                    + "                                                                    <button type=\"button\" class=\"page-link\" " + (indexPage < endPage ? "" : "hidden") + " onclick=\"search(" + (indexPage + 1) + ")\" aria-label=\"Next\">\n"
                    + "                                                                        <span aria-hidden=\"true\">&raquo;</span>\n"
                    + "                                                                        <span class=\"sr-only\">Next</span>\n"
                    + "                                                                    </button>\n"
                    + "                                                                </li>\n"
                    + "                                                            </ul>\n"
                    + "                                                        </nav>  \n");
        } else {
            // Khi kết quả tìm kiếm là rỗng, in thông báo không tìm thấy bài viết
            PrintWriter out = response.getWriter();
            out.println("<div class=\"container\">");
            out.println("  <p style=\"font-size: 20px; font-weight: bold; color: black;\">Không tìm thấy bài viết bạn cần.Vui lòng nhập lại.</p>");
            out.println("</div>");
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
