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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giang Minh
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)

@WebServlet(name = "CreateCustomer", urlPatterns = {"/CreateCustomer"})
public class CreateCustomer extends HttpServlet {

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
        request.getRequestDispatcher("admin/customer/create.jsp").forward(request, response);
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
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        boolean gender = request.getParameter("gender").equals("1");
        String email = request.getParameter("email");
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        Date createdDate = new Date();
        DaoCustomer daoCustomer = new DaoCustomer();

        try {
            // check existed email, phone, username
            List<Customer> allCustomers = daoCustomer.getAllCustomers();
            for (Customer c : allCustomers) {

                if (c.getEmail().equals(email)) {
                    if (c.getPassword() != null) {
                        throw new Exception("Địa chỉ email đã tồn tại");
                    }
                }

                if (c.getPhone().equals(phone)) {
                    throw new Exception("Số điện thoại đã tồn tại");
                }

                if (c.getUserName().equals(username)) {
                    throw new Exception("username đã tồn tại");
                }
            }

            if (!password.equals(rePassword)) {
                throw new Exception("Mật khẩu xác nhận phải trùng với mật khẩu");
            } else if (!Base.Util.Validation.passwordValidation(password)) {
                throw new Exception();
            }

            // store image to admin
            String image = Base.Util.Utilities.storeImage(request, "image", Base.Util.Utilities.ADMIN_CUSTOMER_IMAGE_PATH);
            String modifiedHistory = Base.Util.Utilities.getLoggingInAccount(request, response).getAccountId() + ";"
                    + createdDate.toString() + ";"
                    + "Create New Customer|";

            Customer c = new Customer(Base.Util.Utilities.INT_DEFAUT, firstName, lastName, gender, email, username, password,
                    address, phone, createdDate, image, modifiedHistory, false);
            daoCustomer.createCustomer(c);
            Base.Util.Utilities.noti(request, true, "Create Customer Success");
            response.sendRedirect("CustomerManagement");
            
        } catch (Exception e) {
            System.out.println("CreateCustomer - " + e.getMessage());
            Base.Util.Utilities.noti(request, false, e.getMessage());
            keepData(request, firstName, lastName, phone, email, password, rePassword, username, address);
            request.getRequestDispatcher("admin/customer/create.jsp").forward(request, response);
        }

    }

    private void keepData(HttpServletRequest request, String firstName, String lastName, String phoneNumber, String email,
            String pass, String repass, String username, String address) {
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("email", email);
        request.setAttribute("pass", pass);
        request.setAttribute("repass", repass);
        request.setAttribute("username", username);
        request.setAttribute("address", address);
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
