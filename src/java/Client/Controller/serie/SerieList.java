/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package client.controller.serie;

import Base.Dal.DAOClient;
import Base.Dal.DaoCategory;
import Base.Dal.Paging;
import Base.Model.Category;
import Base.Model.Color;
import Base.Model.Filter;
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
@WebServlet(name = "SerieList", urlPatterns = {"/SerieList"})
public class SerieList extends HttpServlet {

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
            out.println("<title>Servlet SerieList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SerieList at " + request.getContextPath() + "</h1>");
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
        Paging pg = new Paging();
        String search = request.getParameter("search") != null ? request.getParameter("search") : "";
        String category = request.getParameter("category") != null ? request.getParameter("category") : "";
        int count = dao.countSearch(" CategoryID like ('%"+category+"%') ", " Price like ('%%') ", " Color like ('%%') ", " Size like ('%%') ", search);
        int endPage = (count % 6 == 0) ? (count / 6) : (count / 6 + 1);
        int indexPage = request.getParameter("index") != null ? Integer.parseInt(request.getParameter("index")) : 1;
        List<ProductSeri> serieList = dao.getAllProductSerieByFilter(category, search, indexPage);
        
        request.setAttribute("productSerie", serieList);
        request.setAttribute("currentPage", indexPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listSize", count);

        String start = dao.getStartPrice();
        String end = dao.getEndPrice();
        request.setAttribute("start", start);
        request.setAttribute("end", end);

        List<Category> categoryList = new DaoCategory().getAllCategories();
        request.setAttribute("categoryList", categoryList);
        List<Color> colorList = dao.getAllColor();
        request.setAttribute("colorList", colorList);
        List<Filter> sizeList = dao.getAllSize();
        request.setAttribute("sizeList", sizeList);
        
        request.setAttribute("search", search);
        request.setAttribute("category", category);
        
        request.getRequestDispatcher("client/shop.jsp").forward(request, response);
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
        processRequest(request, response);
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
