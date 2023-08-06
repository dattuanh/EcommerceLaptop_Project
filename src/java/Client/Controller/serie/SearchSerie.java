/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package client.controller.serie;

import Base.Dal.DAOClient;
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
@WebServlet(name = "SearchShop", urlPatterns = {"/SearchShop"})
public class SearchSerie extends HttpServlet {

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
            out.println("<title>Servlet SearchSerie</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchSerie at " + request.getContextPath() + "</h1>");
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
//        System.out.println("777");
//        String price[] = request.getParameter("priceFilter");
//        String[] category = request.getParameterValues("category[]");
//        System.out.println(category[1]);
//        String color = request.getParameter("color");
//        String size = request.getParameter("size");
        String[] ct = request.getParameterValues("category[]");
        String category = "";
        for (int i = 0; i < ct.length; i++) {
            category += " CategoryID like '%" + ct[i] + "%' ";
            if (i != ct.length - 1) {
                category += " OR ";
            }
        }
//        System.out.println(category);
//        System.out.println(category.length);
        String[] cl = request.getParameterValues("color[]");
        String color = "";
        for (int i = 0; i < cl.length; i++) {
            color += " Color like '%" + cl[i] + "%' ";
            if (i != cl.length - 1) {
                color += " OR ";
            }
        }
//        System.out.println(color);
//        System.out.println(color.length);
        String[] sz = request.getParameterValues("size[]");
        String size = "";
        for (int i = 0; i < sz.length; i++) {
            size += " Size like '%" + sz[i] + "%' ";
            if (i != sz.length - 1) {
                size += " OR ";
            }
        }

        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String price = "Price >= " + start + " AND Price <= " + end + " ";
        String order = request.getParameter("orderBy") != null ? request.getParameter("orderBy") : " ProductSeriID asc ";
//        System.i.println(size.length);
        String search = request.getParameter("search");
        DAOClient dao = new DAOClient();
        int count = dao.countSearch(category, price, color, size, search);
//        System.out.println(count);
        int endPage = (count % 6 == 0) ? (count / 6) : (count / 6 + 1);
        int index = request.getParameter("index") != null ? Integer.parseInt(request.getParameter("index")) : 1;
        List<ProductSeri> serieList = dao.getAllProductSerieByFilter2(category, price, color, size, search, order, index);
        PrintWriter out = response.getWriter();
        out.print("<div class=\"col-12 pb-1\">"
                + "<p> Found " + count + " sản phẩm</p>"
                + "</div>");
//        System.out.println("888");
        if (serieList != null) {
            for (ProductSeri s : serieList) {
//                System.out.println("999");
                out.println("<div class=\"col-lg-2 col-md-6 col-sm-12 pb-1\">\n"
                        + "                                <div class=\"card product-item border-0 mb-4\">\n"
                        + "                                    <div class=\"card-header product-img position-relative overflow-hidden bg-transparent border p-0\">\n"
                        + "<a href=\"SerieDetail?serieId=" + s.getProductSeriId() + "\">"
                        + "                                        <img class=\"img-fluid w-100\" src=\"images/ProductSeri/" + s.getImagePreview() + "\" alt=\"\">\n"
                        + "</a>"
                        + "                                    </div>\n"
                        + "                                    <div class=\"card-body border-left border-right text-center p-0 pt-4 pb-3\">\n"
                        + "                                        <h6 class=\"text-truncate mb-3\">" + s.getProductSeriName() + "</h6>\n"
                        + "                                        <div class=\"d-flex justify-content-center\">\n"
                        + "                                            <h6>" + Base.Util.Formatter.moneyFormat(s.getPrice()) + "</h6>\n"
                        + "                                            <!--<h6 class=\"text-muted ml-2\"><del>$123.00</del></h6>-->\n"
                        + "                                        </div>\n"
                        + "                                    </div>\n"
                        + "                                            <div class=\"card-footer d-flex justify-content-between bg-light border\">\n"
                        + "                                                <!--<a href=\"SerieDetail?serieId=${serie.productSeriId}\" class=\"btn btn-sm text-dark p-0\"><i class=\"fas fa-eye text-primary mr-1\"></i>View Detail</a>-->\n"
                        + "                                                <a href=\"addToCart?pSeriId=" + s.getProductSeriId() + "\" class=\"btn btn-sm text-dark p-0\"><i class=\"fas fa-shopping-cart text-primary mr-1\"></i>Add To Cart</a>\n"
                        + "                                            </div>"
                        + "                                </div>\n"
                        + "                            </div>");
            }
        }
        out.print("<div class=\"col-12 pb-1\">\n"
                + "                                <p> Hiện có  " + serieList.size() + " trên " + count + " sản phẩm</p>\n"
                + "                                <nav aria-label=\"Page navigation\">\n"
                + "                                    <ul class=\"pagination justify-content-center mb-3\">\n"
                + "                                        <li class=\"page-item\">\n"
                + "                                            <button class=\"page-link\" name=\"pageLink\" " + (index > 1 ? "" : "disabled") + " id=\"pageLink\" value=\"" + (index - 1) + "\" aria-label=\"Previous\" onclick=\"searchPage(" + (index - 1) + ")\">\n"
                + "                                                <span aria-hidden=\"true\">&laquo;</span>\n"
                + "                                                <span class=\"sr-only\">Previous</span>\n"
                + "                                            </button>"
                + "                                        </li>\n");
        for (int i = 1; i <= endPage; i++) {
            out.print(
                    "                                            <li class=\"page-item\">\n"
                    + "                                                <button type=\"button\" class=\"page-link \" " + (index == i ? "disabled" : "") + " name=\"pageLink\" id=\"pageLink\" value=\"" + i + "\" onclick=\"searchPage(" + i + ")\">" + i + "</button>\n"
                    + "                                            </li>\n");
        }
        out.print("                      <li class=\"page-item\">\n"
                + "                                            <button class=\"page-link\" name=\"pageLink\" " + (index < endPage ? "" : "disabled") + " id=\"pageLink\" value=\"" + (index + 1) + "\" aria-label=\"Next\" onclick=\"searchPage(" + (index + 1) + ")\">\n"
                + "                                                <span aria-hidden=\"true\">&raquo;</span>\n"
                + "                                                <span class=\"sr-only\">Next</span>\n"
                + "                                            </button>"
                + "                                        </li>\n"
                + "                                    </ul>\n"
                + "                                </nav>\n"
                + "                            </div>");

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
