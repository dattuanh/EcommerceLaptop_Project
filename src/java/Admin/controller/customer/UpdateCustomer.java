/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.customer;

import Base.Dal.DaoCustomer;
import Base.Model.Customer;
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

/**
 *
 * @author Giang Minh
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)

@WebServlet(name = "UpdateCustomer", urlPatterns = {"/UpdateCustomer"})
public class UpdateCustomer extends HttpServlet {

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
            out.println("<title>Servlet UpdateCustomer</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCustomer at " + request.getContextPath() + "</h1>");
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
        String customerId = request.getParameter("id");
        if (customerId == null) {
            response.sendRedirect("CustomerManagement");
            return;
        }
        DaoCustomer daoCustomer = new DaoCustomer();
        Customer c = daoCustomer.getCustomer(Integer.parseInt(customerId));
        request.setAttribute("c", c);
        request.getRequestDispatcher("admin/customer/update.jsp").forward(request, response);
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
        try {
            DaoCustomer daoCustomer = new DaoCustomer();

            String customerId = request.getParameter("id");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            boolean gender = request.getParameter("gender").equals("1"); // true ~ male
            String email = request.getParameter("email");
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            Date createdDate = Base.Util.Formatter.formatDate(request.getParameter("createdDate")); // current date

            Customer oldCustomer = daoCustomer.getCustomer(Integer.parseInt(customerId));
            String image = oldCustomer.getImage();
            if (request.getPart("image").getSize() != 0) {
                image = Base.Util.Utilities.storeImage(request, "image", Base.Util.Utilities.ADMIN_CUSTOMER_IMAGE_PATH);
                String imagePath = Base.Util.Utilities.ADMIN_CUSTOMER_IMAGE_PATH + oldCustomer.getImage();
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    imageFile.delete();
                }
            }

            String modifiedHistory = oldCustomer.getModifiedHistory();
            modifiedHistory += "AccountId: " + Base.Util.Utilities.getLoggingInAccount(request, response).getAccountId() + ";"
                    + "Date: " + Base.Util.Formatter.getCurrentDate() + ";"
                    + "Action: Update Customer|";

            Customer c = new Customer(Integer.parseInt(customerId), firstName, lastName, gender, email, userName, password, address, phone, createdDate, image, modifiedHistory, false);
            daoCustomer.updateCustomer(c);
            Base.Util.Utilities.noti(request, true, "Update Customer Success");
            response.sendRedirect("CustomerManagement");
        } catch (Exception e) {
            System.out.println("Update Customer: " + e.getMessage());
            Base.Util.Utilities.noti(request, false, e.getMessage());
            response.sendRedirect("UpdateCustomer");
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
