/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.serie;

import Base.Dal.DaoCategory;
import Base.Dal.DaoColor;
import Base.Dal.DaoImage;
import Base.Dal.DaoSerie;
import Base.Model.Account;
import Base.Model.Category;
import Base.Model.Color;
import Base.Model.Image;
import Base.Model.ProductSeri;
import Base.Util.TinyController;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)

@WebServlet(name = "CreateSerie", urlPatterns = {"/CreateSerie"})
public class CreateSerie extends HttpServlet {

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
        DaoCategory daoCategory = new DaoCategory();
        List<Category> categoryList = daoCategory.getAllCategories();
        DaoColor daoColor = new DaoColor();
        List<Color> allColors = daoColor.getAllColors();

        request.setAttribute("allColors", allColors);
        request.setAttribute("categoryList", categoryList);
//        session.setAttribute("account", Base.Util.Utilities.getLoggingInAccount(request, response));
        request.getRequestDispatcher("admin/product/createSerie.jsp").forward(request, response);
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
        String serieName = request.getParameter("SerieName");
        double price = Base.Util.Utilities.formatNumber(request.getParameter("Price"));
//        String description = request.getParameter("Description");
        String image = Base.Util.Utilities.storeImage(request, "image", Base.Util.Utilities.SERI_IMAGE_PATH);
        String categoryID = request.getParameter("CategoryID");
//        int stock = Integer.parseInt(request.getParameter("Stock"));
        String manufacturer = request.getParameter("Manufacturer");
        int warrantyTime = Integer.parseInt(request.getParameter("WarrantyTime"));
        String size = request.getParameter("Size");
        String color = request.getParameter("Color");
        Account createdBy = Base.Util.Utilities.getLoggingInAccount(request, response);
        Date date = Base.Util.Formatter.getCurrentDate();
        String modifiedHistory = serieName + " created by " + createdBy.getUserName() + " at " + Base.Util.Formatter.dateToString(date);
//        String content = request.getParameter("content");
//        System.out.println("content: " + extractImageSrc(content));
//        List<String> images = extractImageSrc(content);

//        
//        
//        for (String imgName : images) {
//            Image i = new Image();
//            i.setImageName(imgName);
//            int imageId = daoImage.createImage(i);
//            imageIds.add(imageId);
//        }
        DaoSerie daoSerie = new DaoSerie();
        DaoColor daoColor = new DaoColor();
        DaoCategory daoCategory = new DaoCategory();
        DaoImage daoImage = new DaoImage();
        List<Integer> imageIds = new ArrayList<>();

        for (Part part : request.getParts()) {
            if (!part.getName().equals("image")) {
                String fileName = new TinyController().getFileName(part);
                System.out.println("filename: " + fileName);
                if (fileName != null && !fileName.isEmpty()) {
                    // get file extension, like png, jpg, webg, ...
                    String fileExtension = Base.Util.Utilities.getFileExtension(part);
//                 Generate a unique filename for each image
                    String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;
                    // add image name to db
                    Image i = new Image();
                    i.setImageName(uniqueFileName);
                    int imageId = daoImage.createImage(i);
                    imageIds.add(imageId);
                    // Save the file to the upload directory with the unique filename
                    part.write(Base.Util.Utilities.SERI_IMAGE_PATH + File.separator + uniqueFileName);
                }
            }
        }
//        String images = Base.Util.Utilities.storeImage(request, "images", Base.Util.Utilities.SERI_IMAGE_PATH);

        ProductSeri s = new ProductSeri(Base.Util.Utilities.INT_DEFAUT, serieName, price, "", image, daoCategory.getCategory(categoryID),
                manufacturer, warrantyTime, size, daoColor.getColor(color), date, date, createdBy, createdBy, modifiedHistory, false);
        // get seriId created from above ProductSeri
        int newSeriId = daoSerie.createSerie(s);
        for (int img : imageIds) {
            // add seriid and imgId to SeriImage table
            daoImage.createSeriImage(newSeriId, img);
        }
        response.sendRedirect("AddProduct?serie=" + newSeriId);
    }

    private static List<String> extractImageSrc(String html) {
        String regex = "src=\"(.*?)\"";
        List<String> imageNames = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            String imageUrl = matcher.group(1);
            String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
            imageNames.add(imageName);
        }

//        for (String imageName : imageNames) {
//            System.out.println(imageName);
//        }
//        
        return imageNames;
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
