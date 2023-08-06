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
import Base.Model.Account;
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
@WebServlet(name = "TechSingleControl", urlPatterns = {"/TechSingleControl/*"})
public class NewsSingleControl extends HttpServlet {

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
            out.println("<title>Servlet TechSingleControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TechSingleControl at " + request.getContextPath() + "</h1>");
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
        DaoAccount daoA = new DaoAccount();
        String[] parts = request.getPathInfo().split("-");

        String part1 = parts[parts.length - 3];
        String part2 = parts[parts.length - 2];
        String part3 = parts[parts.length - 1].substring(0, parts[parts.length - 1].indexOf("."));
        String newsId = part1;
        String groupId = part2;
        String authorId = part3;
        List<News> newsbg = daoN.getNewsByGroup(Integer.parseInt(groupId));
        List<News> recentNews = daoN.getRecentNews();
        List<NewsGroup> newsGroup = daoNg.getAllNewsGroup();
//        List<News> news = daoN.getAllNews();

        News nextNews = daoN.getNextNews(Integer.parseInt(newsId));
        News prevNews = daoN.getPrevNews(Integer.parseInt(newsId));
        Account acc = daoA.getAccount(Integer.parseInt(authorId));
        request.setAttribute("prevNews", prevNews);
        request.setAttribute("nextNews", nextNews);

        News c = daoN.getNews(Integer.parseInt(newsId));
        request.setAttribute("newsGroup", newsGroup);
        request.setAttribute("newsbg", newsbg);
        request.setAttribute("recentNews", recentNews);
//        request.setAttribute("news", news);
        request.setAttribute("c", c);
        request.setAttribute("acc", acc);
        request.getRequestDispatcher("../client/news/tech-single.jsp").forward(request, response);

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
