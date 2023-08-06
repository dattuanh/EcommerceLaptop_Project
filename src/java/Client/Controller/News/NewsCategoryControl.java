/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package client.controller.News;

import Base.Dal.DAO;
import Base.Dal.DAOClient;
import Base.Dal.DAONews;
import Base.Dal.DAONewsGroup;
import Base.Dal.DaoAccount;
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
@WebServlet(name = "TechCategoryControl", urlPatterns = {"/TechCategoryControl/*"})
public class NewsCategoryControl extends HttpServlet {

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
            out.println("<title>Servlet TechCategoryControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TechCategoryControl at " + request.getContextPath() + "</h1>");
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
        DAONews daoN = new DAONews();
        DAONewsGroup daoNg = new DAONewsGroup();
        String[] parts = request.getPathInfo().split("-");
        String part1 = parts[parts.length - 1].substring(0, parts[parts.length - 1].indexOf("."));

        String newsGroupID = part1;
        NewsGroup newsGroup = daoNg.getNewsGroup(Integer.parseInt(newsGroupID));
        List<News> recentNews = daoN.getRecentNews();
        List<NewsGroup> newsGr = daoNg.getAllNewsGroup();

        List<News> newsByGroup = daoN.getNewsByGroup(Integer.parseInt(newsGroupID));
        List<NewsGroup> group = daoNg.getAllNewsGroup();
        request.setAttribute("group", group);
        request.setAttribute("newsGroup", newsGr);

        request.setAttribute("recentNews", recentNews);
        request.setAttribute("newsBg", newsByGroup);
        request.setAttribute("ng", newsGroup);
        //try ( PrintWriter out = response.getWriter()) {out.print(newsBg);}
        request.getRequestDispatcher("../client/news/tech-category-01.jsp").forward(request, response);

        System.out.println("url: " + request.getPathInfo());
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
