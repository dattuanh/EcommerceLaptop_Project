/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.category;

import Base.Dal.DaoCategory;
import Base.Dal.DaoProduct;
import Base.Model.Category;
import Base.Model.Product;
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
 * @author Đặng Quốc Việt
 */
@WebServlet(name = "CategoryManagement", urlPatterns = {"/CategoryManagement"})
public class CategoryManagement extends HttpServlet {

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
            out.println("<title>Servlet CategoryManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryManagement at " + request.getContextPath() + "</h1>");
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
        DaoCategory dao = new DaoCategory();
        String filter = "All";
        String search = "";
        int entry = 5;
        int count = dao.countSearch(dao.getAllCategoryByFilter(filter, search));
        int endPage = (count % entry == 0) ? (count / entry) : (count / entry + 1);
        int index = request.getParameter("index") != null ? Integer.parseInt(request.getParameter("index")) : 1;
//        List<ProductSeri> serieList = dao.getAllProductSerieByFilter2(filter, search, index);
        List<Category> categoryList = dao.searchCategoryBy(dao.getAllCategoryByFilter(filter, search), index, "", entry);
        request.setAttribute("currentPage", index);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listSize", count);
        request.setAttribute("categoryList", categoryList);

        request.getRequestDispatcher("admin/category/index.jsp").forward(request, response);
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
        DaoCategory dao = new DaoCategory();
        String filter = request.getParameter("filter");
        String search = request.getParameter("search");
        int entry = Integer.parseInt(request.getParameter("entry"));
        int count = dao.countSearch(dao.getAllCategoryByFilter(filter, search));
        int endPage = (count % entry == 0) ? (count / entry) : (count / entry + 1);
        int index = request.getParameter("index") != null ? Integer.parseInt(request.getParameter("index")) : 1;
//        List<ProductSeri> serieList = dao.getAllProductSerieByFilter2(filter, search, index);
        List<Category> categoryList = dao.searchCategoryBy(dao.getAllCategoryByFilter(filter, search), index, "", entry);
        PrintWriter out = response.getWriter();
        if (categoryList != null) {
            out.print("<thead>\n"
                    + "                                                <tr>\n"
                    + "                                                    <th>\n"
                    + "                                                        <div class=\"form-check form-check-muted m-0\">\n"
                    + "                                                            <label class=\"form-check-label\">\n"
                    + "                                                                <input type=\"checkbox\" class=\"form-check-input\">\n"
                    + "                    <i class=\"input-helper\"></i>"
                    + "                                                            </label>\n"
                    + "                                                        </div>\n"
                    + "                                                             <th> Category Name </th>\n"
                    + "                                                            <th> Numbers of Products </th>\n"
                    + "                                                            <th> Actions </th>"
                    + "                                                </tr>\n"
                    + "                                            </thead>");
            for (Category c : categoryList) {
                out.print("<tr>\n"
                        + "                                                                <td>\n"
                        + "                                                                    <div class=\"form-check form-check-muted m-0\">\n"
                        + "                                                                        <label class=\"form-check-label\">\n"
                        + "                                                                            <input type=\"checkbox\" class=\"form-check-input\" name=\"Ids[]\" value=\"${c.getCategoryID()}\">\n"
                        + "                    <i class=\"input-helper\"></i>"
                        + "                                                                        </label>\n"
                        + "                                                                    </div>\n"
                        + "                                                                </td>\n"
                        + "                                                                <td> " + c.getCategoryName() + " </td>\n"
                        + "                                                                <td> " + c.getProductQuantity() + " </td>\n"
                        + "                                                                <td>\n"
                        + "                                                                    <!--<div class=\"badge badge-outline-success\">Approved</div>-->\n"
                        + "                                                                    <a href=\"ViewCategory?CategoryID=" + c.getCategoryID() + "\">\n"
                        + "                                                                        <i class=\"mdi mdi-eye\" style=\"font-size: 22px\"></i> &nbsp;\n"
                        + "                                                                    </a>\n"
                        + "                                                                    <a href=\"UpdateCategory?CategoryID=" + c.getCategoryID() + "\">\n"
                        + "                                                                        <i class=\"mdi mdi-lead-pencil\" style=\"font-size: 22px\"></i> &nbsp;\n"
                        + "                                                                    </a>\n"
                        + "                                                                     <i class=\"mdi mdi-delete\" style=\"font-size: 22px; color: #1079eb\" \n"
                        + "                                                                       onclick=\"confirmDelete('RemoveCategory', '"+c.getCategoryID()+"')\">\n"
                        + "                                                                    </i>&nbsp;"
                        + "                                                                </td>\n"
                        + "                                                            </tr>");
            }
            out.print("                                         <td colspan=\"10\">\n"
                    + "                                                        <nav aria-label=\"Page navigation\">\n"
                    + "                                                            <p> There are " + categoryList.size() + " / " + count + " Categories</p>\n"
                    + "                                                            <ul class=\"pagination justify-content-end\">\n"
                    + "                                        <li class=\"page-item " + (index > 1 ? "active" : "disabled") + " \">\n"
                    + "                                            <button type=\"button\" class=\"page-link\" name=\"pageLink\" id=\"pageLink\" value=\"" + (index - 1) + "\" aria-label=\"Previous\" onclick=\"searchPage(" + (index - 1) + ")\">\n"
                    + "                                                <span aria-hidden=\"true\">&laquo;</span>\n"
                    + "                                                <span class=\"sr-only\">Previous</span>\n"
                    + "                                            </button>"
                    + "                                        </li>\n");
            for (int i = 1; i <= endPage; i++) {
                out.print(
                        "                                            <li class=\"page-item " + (index == i ? "active disabled" : "") + " \">\n"
                        + "                                                <button type=\"button\" class=\"page-link \" name=\"pageLink\" id=\"pageLink\" value=\"" + i + "\" onclick=\"searchPage(" + i + ")\">" + i + "</button>\n"
                        + "                                            </li>\n");
            }
            out.print("                      <li class=\"page-item " + (index < endPage ? "active" : "disabled") + " \">\n"
                    + "                                            <button type=\"button\" class=\"page-link\" name=\"pageLink\" id=\"pageLink\" value=\"" + (index + 1) + "\" aria-label=\"Next\" onclick=\"searchPage(" + (index + 1) + ")\">\n"
                    + "                                                <span aria-hidden=\"true\">&raquo;</span>\n"
                    + "                                                <span class=\"sr-only\">Next</span>\n"
                    + "                                            </button>"
                    + "                                        </li>\n"
                    + "                                                            </ul>\n"
                    + "                                                        </nav> \n"
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
