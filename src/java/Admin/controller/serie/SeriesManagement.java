/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.serie;

import Base.Dal.DaoSerie;
import Base.Dal.Paging;
import Base.Model.ProductSeri;
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
 * @author admin
 */
@WebServlet(name = "SeriesManagement", urlPatterns = {"/SeriesManagement"})
public class SeriesManagement extends HttpServlet {

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
            out.println("<title>Servlet ProductManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductManagement at " + request.getContextPath() + "</h1>");
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
        Paging pg = new Paging();
        DaoSerie daoSerie = new DaoSerie();
        int count = pg.getNumeberOf("ProductSeries");
        int endPage = (count % 9 == 0) ? (count / 9) : (count / 9 + 1);
        int indexPage = request.getParameter("index") != null ? Integer.parseInt(request.getParameter("index")) : 1;
        List<ProductSeri> serieList = daoSerie.pagingSeries(indexPage);

        request.setAttribute("serieList", serieList);
        request.setAttribute("currentPage", indexPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listSize", count);
        request.getRequestDispatcher("admin/product/index.jsp").forward(request, response);
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
        DaoSerie dao = new DaoSerie();
        Paging pg = new Paging();
        String filter = request.getParameter("filter");
        String search = request.getParameter("search");
        int entry = Integer.parseInt(request.getParameter("entry"));
        int count = dao.countSearch(dao.getAllProductSerieByFilter(filter, search));
        int endPage = (count % entry == 0) ? (count / entry) : (count / entry + 1);
        int index = request.getParameter("index") != null ? Integer.parseInt(request.getParameter("index")) : 1;
//        List<ProductSeri> serieList = dao.getAllProductSerieByFilter2(filter, search, index);
        List<ProductSeri> serieList = dao.searchProductSerieBy(dao.getAllProductSerieByFilter(filter, search), index, "",entry);
        PrintWriter out = response.getWriter();
        if (serieList != null) {
            out.print("<thead>\n"
                    + "        <tr>\n"
                    + "            <th>\n"
                    + "                <div class=\"form-check form-check-muted m-0\">\n"
                    + "                    <label class=\"form-check-label\" for=\"checkbox-all\">\n"
                    + "                    <input type=\"checkbox\" class=\"form-check-input\" id=\"checkbox-all\">\n"
                    + "                    <i class=\"input-helper\"></i>"
                    + "                     </label>\n"
                    + "                </div>"
                    + "            </th>\n"
                    + "            <!--<th> Image </th>-->\n"
                    + "            <th> Series Name </th>\n"
                    + "            <th> Price </th>\n"
                    + "            <th> Category </th>\n"
                    + "            <th> Warranty Time </th>\n"
                    + "            <th> Size </th>\n"
                    + "            <th> Color </th>\n"
                    + "            <th> Stock </th>\n"
                    + "            <th> Last Modified </th>\n"
                    + "            <th> Actions </th>\n"
                    + "        </tr>\n"
                    + "    </thead>");
            for (ProductSeri s : serieList) {
                out.println("    <tr>\n"
                        + "        <td>\n"
                        + "            <div class=\"form-check form-check-muted m-0\">\n"
                        + "                <label class=\"form-check-label\">\n"
                        + "                    <input type=\"checkbox\" class=\"form-check-input\" name=\"Ids[]\" value=\"" + s.getProductSeriId() + "\">\n"
                        + "                    <i class=\"input-helper\"></i>"
                        + "                </label>\n"
                        + "            </div>\n"
                        + "        </td>\n"
                        + "        <td> " + s.getProductSeriName().substring(0, 25) + " </td>\n"
                        + "        <td> \n"
                        + "            " + Base.Util.Formatter.moneyFormat(s.getPrice()) + "\n"
                        + "        </td>\n"
                        + "        <td> " + s.getCategoryId().getCategoryName() + " </td>\n"
                        + "        <td> " + s.getWarrantyTime() + " </td>\n"
                        + "        <td> " + s.getSize() + " </td>\n"
                        + "        <td> " + s.getColor().getColorName() + " </td>\n"
                        + "        <td> " + s.getStock() + " </td>\n"
                        + "        <td>\n"
                        + "            " + Base.Util.Formatter.dateToString(s.getModifiedDate()) + "\n"
                        + "        </td>\n"
                        + "          <td>\n"
                        + "                    <a href=\"ViewSerie?serie=" + s.getProductSeriId() + "\">\n"
                        + "                        <i class=\"mdi mdi-eye\" style=\"font-size: 22px\"></i> &nbsp;\n"
                        + "                    </a>\n"
                        + "                        <a href=\"UpdateSerie?serie=" + s.getProductSeriId() + "\">\n"
                        + "                            <i class=\"mdi mdi-lead-pencil\" style=\"font-size: 22px\"></i> &nbsp;\n"
                        + "                        </a>\n"
                        + "                       &nbsp;\n"
                        + "                       <i class=\"mdi mdi-delete\" style=\"font-size: 22px; color: #1079eb\" \n"
                        + "                       onclick=\"confirmDelete('RemoveSerie', "+ s.getProductSeriId() +")\">\n"
                        + "                       </i>"
                        + "                </td>\n"
                        + "            </tr>");
            }
        }
        out.print("                                                    <td colspan=\"10\">\n"
                + "                                                        <nav aria-label=\"Page navigation\">\n"
                + "                                                            <p> There are " + serieList.size() + " / " + count + " Series</p>\n"
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
