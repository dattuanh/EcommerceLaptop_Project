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
import java.io.File;
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
@WebServlet(name = "UpdateNews", urlPatterns = {"/UpdateNews"})
public class UpdateNews extends HttpServlet {

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
            out.println("<title>Servlet UpdateNews</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateNews at " + request.getContextPath() + "</h1>");
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
        String newsid = request.getParameter("id");
        if (newsid == null) {
            response.sendRedirect("NewsManagement");
            return;
        }
        DAONews daoNews = new DAONews();
        DAONewsGroup dao = new DAONewsGroup();
        List<NewsGroup> newsGroupList = dao.getAllNewsGroup();
        News c = daoNews.getNews(Integer.parseInt(newsid));
        request.setAttribute("c", c);
        request.setAttribute("newsGroupList", newsGroupList);
        request.getRequestDispatcher("admin/news/update.jsp").forward(request, response);

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
        DAONews daoNews = new DAONews();

        String newsId = request.getParameter("id");
        News oldNews = daoNews.getNews(Integer.parseInt(newsId));

        String newsGroupId = request.getParameter("newsGroupId");
        String newsTitle = request.getParameter("title");
        String newsHeading = request.getParameter("heading");
        String newsContent = request.getParameter("content");
        boolean isPresent = request.getParameter("isPresent").equals("1");
        boolean isSlider = request.getParameter("isSlider").equals("1");

        Date modifiedDate = Base.Util.Formatter.getCurrentDate();
        Account modifiedBy = Base.Util.Utilities.getLoggingInAccount(request, response);
        String modifiedHistory = modifiedBy.getAccountId() + ";" + modifiedDate.toString() + ";" + "Update News|";

        String image = oldNews.getNewsImage();
        if (request.getPart("image").getSize() != 0) {
            image = Base.Util.Utilities.storeImage(request, "image", Base.Util.Utilities.NEWS_IMAGE_PATH);
            String imagePath = Base.Util.Utilities.NEWS_IMAGE_PATH + oldNews.getNewsImage();
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                imageFile.delete();
            }
        }

        NewsGroup ng = new DAONewsGroup().getNewsGroup(Integer.parseInt(newsGroupId));
        News c = new News(Integer.parseInt(newsId), ng, newsTitle, newsHeading, image, newsContent, 
                modifiedDate, modifiedBy, modifiedHistory, oldNews.isIsDelete(), isPresent,isSlider);
        
        daoNews.updateNews(c);

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
