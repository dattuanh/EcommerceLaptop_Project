/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.news;

import Base.Dal.DAONews;
import Base.Dal.DAONewsGroup;
import Base.Model.Account;
import Base.Model.News;
import Base.Model.NewsGroup;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Giang Minh
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet(name = "CreateNews", urlPatterns = {"/CreateNews"})
public class CreateNews extends HttpServlet {

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
            out.println("<title>Servlet Create</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Create at " + request.getContextPath() + "</h1>");
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
        DAONewsGroup dao = new DAONewsGroup();
        List<NewsGroup> newsGroupList = dao.getAllNewsGroup();
        request.setAttribute("newsGroupList", newsGroupList);
        request.getRequestDispatcher("admin/news/create.jsp").forward(request, response);
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
        String newsGroupId = request.getParameter("newsGroupId");
        String newsTitle = request.getParameter("title");
        String newsHeading = request.getParameter("heading");
        //String newsImage = request.getParameter("image");
        String newsImage = Base.Util.Utilities.storeImage(request, "image", Base.Util.Utilities.NEWS_IMAGE_PATH);
        String newsContent = request.getParameter("content");
        boolean isPresent = request.getParameter("isPresent").equals("1");
        boolean isSlider = request.getParameter("isSlider").equals("1");

        Date createdDate = Base.Util.Formatter.getCurrentDate();
        Date modifiedDate = createdDate;
        String modifiedHistory = Base.Util.Utilities.getLoggingInAccount(request, response).getAccountId() + ";"
                + modifiedDate.toString() + ";"
                + "Create new News|";

        DAONewsGroup dao = new DAONewsGroup();
        NewsGroup ng = dao.getNewsGroup(Integer.parseInt(newsGroupId));
        Account createdBy = Base.Util.Utilities.getLoggingInAccount(request, response);
        Account modifiedBy = createdBy;
        News c = new News(ng, newsTitle, newsHeading, newsImage, newsContent, createdDate, modifiedDate,
                createdBy, modifiedBy, modifiedHistory, false, isPresent,isSlider);
        DAONews daoNews = new DAONews();
        daoNews.createNews(c);

        response.sendRedirect("NewsManagement");
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
