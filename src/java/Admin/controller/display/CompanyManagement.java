/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.display;

import Base.Dal.DaoDisplay;
import Base.Model.News;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)

/**
 *
 * @author admin
 */
@WebServlet(name = "CompanyManagement", urlPatterns = {"/CompanyManagement"})
public class CompanyManagement extends HttpServlet {

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
            out.println("<title>Servlet CompanyManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CompanyManagement at " + request.getContextPath() + "</h1>");
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
        DaoDisplay dao = new DaoDisplay();
        News company = dao.getCompanyInformation();
        request.setAttribute("company", company);
        request.getRequestDispatcher("admin/DisplayManagement/index.jsp").forward(request, response);
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
        DaoDisplay dao = new DaoDisplay();
        System.out.println(request.getParameter("heading"));
        News n = dao.getCompanyInformation();
        String newsHeading = request.getParameter("heading");
        String newsContent = request.getParameter("content");
        String image = n.getNewsImage();
        if (request.getPart("image").getSize() != 0) {
            image = Base.Util.Utilities.storeImage(request, "image", Base.Util.Utilities.NEWS_IMAGE_PATH);
            String imagePath = Base.Util.Utilities.NEWS_IMAGE_PATH + n.getNewsImage();
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                imageFile.delete();
            }
        }

        n.setNewsHeading(newsHeading);
        n.setNewsContent(newsContent);
        n.setNewsImage(image);

        dao.updateCompanyInfor(n);

        response.sendRedirect("CompanyManagement");
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
