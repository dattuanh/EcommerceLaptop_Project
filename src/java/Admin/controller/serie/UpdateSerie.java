/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.serie;

import Base.Dal.DaoCategory;
import Base.Dal.DaoColor;
import Base.Dal.DaoSerie;
import Base.Model.Account;
import Base.Model.Category;
import Base.Model.Color;
import Base.Model.Image;
import Base.Model.ProductSeri;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "UpdateSerie", urlPatterns = {"/UpdateSerie"})
public class UpdateSerie extends HttpServlet {

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
            out.println("<title>Servlet UpdateSerie</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateSerie at " + request.getContextPath() + "</h1>");
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
        String serieID = request.getParameter("serie");
        DaoSerie daoSerie = new DaoSerie();
        DaoColor daoColor = new DaoColor();
        DaoCategory daoCategory = new DaoCategory();
        ProductSeri s = daoSerie.getProductSeri(serieID);
        List<Category> categoryList = daoCategory.getAllCategories();
        List<Color> allColors = daoColor.getAllColors();
        List<Image> serieImages = daoSerie.getProductSerieImages(serieID);
        // temporarily add preview image to image list
        Image i = new Image();
        i.setImageName(s.getImagePreview());
        serieImages.add(i);
        
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("selectedSerie", s);
        request.setAttribute("allColors", allColors);
        request.setAttribute("serieImages", serieImages);
        request.getRequestDispatcher("admin/product/updateSerie.jsp").forward(request, response);
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
        int serieID = Integer.parseInt(request.getParameter("SerieID"));
        String serieName = request.getParameter("SerieName");
        double price = Double.parseDouble(request.getParameter("Price"));
        String description = request.getParameter("Description");
        String image = request.getParameter("Image");
        String categoryID = request.getParameter("CategoryID");
        String manufacturer = request.getParameter("Manufacturer");
        int warrantyTime = Integer.parseInt(request.getParameter("WarrantyTime"));
        String size = request.getParameter("Size");
        String color = request.getParameter("Color");
        Account modifiedBy = Base.Util.Utilities.getLoggingInAccount(request, response);
        Date date = Base.Util.Formatter.getCurrentDate();
        String modifiedHistory = request.getParameter("modifiedHistory") == null ? "" : request.getParameter("modifiedHistory");
        modifiedHistory += date.toString() + " - " + modifiedBy.getUserName() + ": Update Serie <br>";
        
        DaoSerie daoSerie = new DaoSerie();
        DaoColor daoColor = new DaoColor();
        DaoCategory daoCategory = new DaoCategory();
        ProductSeri s = new ProductSeri(serieID, serieName, price, description, image, daoCategory.getCategory(categoryID), manufacturer, 
                warrantyTime, size, daoColor.getColor(color), Base.Util.Utilities.DATE_DEFAUT, date, Base.Util.Utilities.ACCOUNT_DEFAUT, modifiedBy, modifiedHistory, Base.Util.Utilities.BOOLEAN_DEFAUT);
        daoSerie.updateSerie(s);
        
        response.sendRedirect("SeriesManagement");
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
