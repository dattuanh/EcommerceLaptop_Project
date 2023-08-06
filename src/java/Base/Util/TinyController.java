/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Base.Util;

import Base.Dal.DaoImage;
import Base.Dal.DaoProduct;
import Base.Dal.DaoSerie;
import Base.Model.Image;
import Base.Model.ProductSeri;
import static Base.Util.Utilities.*;
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
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author MSI
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
@WebServlet(name = "TinyController", urlPatterns = {"/TinyController"})
public class TinyController extends HttpServlet {

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
            out.println("<title>Servlet TinyController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TinyController at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String contentType = request.getContentType();

        if (contentType != null && contentType.contains("multipart/form-data")) {
            try {
                Collection<Part> parts = request.getParts();
//                System.out.println("directory: " + request.getParameter("directory"));
                System.out.println("tiny1");
                if (request.getParameter("directory").contains("ProductSeri") && request.getParameter("id") != null) {
                    String productSeriId = request.getParameter("id");
                    addSeriImages(parts, productSeriId);
                }
                System.out.println("tiny2");

                for (Part part : parts) {
                    String fileName = getFileName(part);
                    if (fileName != null && !fileName.isEmpty()) {
                        String uploadDirectory = getServletContext().getRealPath("") + "News";
                        String uploadWeb = getRelativePath(request.getParameter("directory"));
                        File uploadDir = new File(uploadDirectory);
                        uploadDir.mkdirs();
                        File uploadDir1 = new File(uploadWeb);
                        uploadDir1.mkdirs();
//                        String filePath = uploadDirectory + File.separator + fileName;
                        String filePath = uploadDirectory + File.separator + fileName;

                        part.write(filePath);
                        System.out.println("filePath: " + filePath);
                        System.out.println("uploadWeb: " + uploadWeb + fileName);
//                        part.write(uploadWeb + File.separator + fileName);
                        part.write(uploadWeb + fileName);
//duong

                        // Trả về URL của ảnh đã tải lên
                        String imageUrl = request.getContextPath() + "/News/" + fileName;
                        response.setContentType("application/json");
                        response.getWriter().write("{\"location\": \"" + imageUrl + "\"}");
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
            }
        }

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("{\"error\": \"No file data found\"}");
    }

    public String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] contentDispositionParts = contentDisposition.split(";");
        for (String partHeader : contentDispositionParts) {
            if (partHeader.trim().startsWith("filename")) {
                return partHeader.substring(partHeader.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;

    }

    private void addSeriImages(Collection<Part> parts, String productSeriId) {
        try {
            DaoImage daoImage = new DaoImage();
            ProductSeri ps = new DaoSerie().getProductSeri(productSeriId);
            List<Integer> imageIds = new ArrayList<>();
            for (Part part : parts) {

                // get file extension, like png, jpg, webg, ...
//                String fileExtension = Base.Util.Utilities.getFileExtension(part);
//                 Generate a unique filename for each image
//                String uniqueFileName = Base.Util.Utilities.getFileName(part) + "." + fileExtension;
                String uniqueFileName = Base.Util.Utilities.getFileName(part);
                System.out.println("uniqueFileName: " + uniqueFileName);
                // add image name to db
                Image i = new Image();
                i.setImageName(uniqueFileName);
                int imageId = daoImage.createImage(i);
                imageIds.add(imageId);
                // Save the file to the upload directory with the unique filename
//                part.write(Base.Util.Utilities.SERI_IMAGE_PATH + File.separator + uniqueFileName);
            }

            for (int img : imageIds) {
                // add seriid and imgId to SeriImage table
                daoImage.createSeriImage(ps.getProductSeriId(), img);
            }

            System.out.println("day la addSeriImages in tinycontroller");

            List<Integer> images = daoImage.getImageIdsByProductSeri(productSeriId);
            for (int imageId : imageIds) {
                if (!images.contains(imageId)) {
                    daoImage.removeImageProductSeri(imageId, productSeriId);
                }
            }
        } catch (Exception e) {
            System.out.println("addSeriImagesAtTinyController: " + e.getMessage());
        }
    }

    private String getRelativePath(String directory) {
        String condition = "";
        if (directory.toLowerCase().contains("productseri")) {
            condition = "ProductSeri";
        } else if (directory.toLowerCase().contains("news")) {
            condition = "News";
        }
        switch (condition) {
            case "ProductSeri":
                return SERI_IMAGE_PATH;
            case "News":
                return NEWS_IMAGE_PATH;
            default:
                throw new AssertionError();
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
