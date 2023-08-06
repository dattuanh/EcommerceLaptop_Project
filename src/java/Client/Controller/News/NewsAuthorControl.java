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
@WebServlet(name = "NewsAuthorControl", urlPatterns = {"/NewsAuthorControl/*"})
public class NewsAuthorControl extends HttpServlet {

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
            out.println("<title>Servlet NewsAuthorControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewsAuthorControl at " + request.getContextPath() + "</h1>");
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
        String[] parts = request.getPathInfo().split("&");

        String part1 = parts[0].substring(1); // Bỏ đi dấu '/'
        String authorId = part1;
        //List<News>  newsBa = dao.getNewsByAccount(Integer.parseInt(authorId));
        List<NewsGroup> newsGroup = daoNg.getAllNewsGroup();
        Account acc = daoA.getAccount(Integer.parseInt(authorId));
        List<News> recentNews = daoN.getRecentNews();
        
        List<News> listNews = daoN.getNewsByAccount(Integer.parseInt(authorId));
        request.setAttribute("listNews", listNews);
       
       

        request.setAttribute("recentNews", recentNews);
        request.setAttribute("acc", acc);
        //request.setAttribute("newsBa", newsBa);
        request.setAttribute("newsGroup", newsGroup);
        //try ( PrintWriter out = response.getWriter()) {out.print(newsGroup);}
        request.getRequestDispatcher("../client/news/tech-author.jsp").forward(request, response);

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
