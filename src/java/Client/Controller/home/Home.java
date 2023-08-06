/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package client.controller.home;

import Base.Dal.DAOClient;
import Base.Dal.DaoCategory;
import Base.Model.Category;
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
@WebServlet(name = "Home", urlPatterns = {"/Home"})
public class Home extends HttpServlet {

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
            out.println("<title>Servlet home</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet home at " + request.getContextPath() + "</h1>");
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
        DAOClient dao = new DAOClient();
        List<Category> categoryList = new DaoCategory().getAllCategories();
        List<ProductSeri> trendySerie = dao.getTrendySerie(6);
        List<ProductSeri> newSerie = dao.getArrivedProduct(6);
        
        request.setAttribute("newSerie", newSerie);
        request.setAttribute("category", categoryList);
        request.setAttribute("trendySerie", trendySerie);
        request.getRequestDispatcher("client/index.jsp").forward(request, response);
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
        String search = request.getParameter("search");
        DAOClient dao = new DAOClient();
        List<ProductSeri> serieList = dao.topBarSearch(search);
        PrintWriter out = response.getWriter();
        if (serieList != null && !serieList.isEmpty()) {
            for (ProductSeri s : serieList) {
                out.println("<li class=\"list-items pl-3 col-md-6\">\n"
                        + "                        <div class=\"row product-item mb-4\">\n"
                        + "                            <div class=\" product-img overflow-hidden col col-md-6\">\n"
                        + "                                <a href=\"SerieDetail?serieId=" + s.getProductSeriId() + "\">\n"
                        + "                                    <img class=\"img-fluid w-60\" src=\"images/ProductSeri/" + s.getImagePreview() + "\" alt=\"\">\n"
                        + "                                </a>\n"
                        + "                            </div>\n"
                        + "                            <div class=\"align-items-center text-left col col-md-6\">\n"
                        + "                                <h6 class=\"text-truncate mb-3\">" + s.getProductSeriName() + "</h6>\n"
                        + "                                <div class=\"d-flex\">\n"
                        + "                                    <h6>" + Base.Util.Formatter.moneyFormat(s.getPrice()) + "</h6>\n"
                        + "                                </div>\n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        + "                    </li>");
            }
            out.print("                                        <li class=\"list-items border-top col-md-12\">\n"
                    + "                                            <a href=\"SerieList?search=" + search + "\">\n"
                    + "                                            <div class=\"align-items-center text-center\">\n"
                    + "                                                    <h6 class=\"text-truncate\">See more >>></h6>\n"
                    + "                                            </div>\n"
                    + "                                            </a>\n"
                    + "                                        </li>");
        } else {
            out.print("                                        <li class=\"list-items border-top col-md-12\">\n"
                    + "                                            <div class=\"align-items-center text-center\">\n"
                    + "                                                    <h6 class=\"text-truncate\">Not Found</h6>\n"
                    + "                                            </div>\n"
                    + "                                        </li>");
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
