/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package client.controller.accessment;

import Base.Dal.DaoCustomer;
import Base.Model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Giang Minh
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

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
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("client/user/user-access/signUp.jsp").forward(request, response);
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
        String phone = request.getParameter("phone").trim();
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String rePass = request.getParameter("rePass");
        String username = request.getParameter("username");

        try {

            Date createdDate = new Date();
            String modifiedHistory = "Tạo vào ngày " + Base.Util.Formatter.dateToString(createdDate);

            DaoCustomer dao = new DaoCustomer();

            // check existed email
            List<Customer> allCustomers = dao.getAllCustomers();
            for (Customer c : allCustomers) {

                if (c.getEmail().equals(email)) {
                    if (c.getPassword() != null) {
                        throw new Exception("Email existed");
                    }
                }

                if (c.getPhone().equals(phone)) {
                    throw new Exception("Phone number existed");
                }

                if (c.getUserName().equals(username)) {
                    throw new Exception("Username must be unique");
                }
            }

            if (!pass.equals(rePass)) {
                throw new Exception("Confirm password must be same as Password");
            } else if (!Base.Util.Validation.passwordValidation(pass)) {
                throw new Exception("Must have at least one numeric character\n"
                        + "Must have at least one lowercase character\n"
                        + "Must have at least one uppercase character\n"
                        + "Must have at least one special symbol among @#$%\n"
                        + "Password length should be between 8 and 20");
            }

            String sql;
            Customer newCustomer;
            if (phone.isEmpty()) {

                newCustomer = new Customer(Base.Util.Utilities.INT_DEFAUT, firstName, lastName, Base.Util.Utilities.BOOLEAN_DEFAUT, email, username, pass,
                        Base.Util.Utilities.STRING_DEFAUT, Base.Util.Utilities.STRING_DEFAUT, createdDate, Base.Util.Utilities.STRING_DEFAUT, modifiedHistory, false);
                sql = "INSERT INTO Customers (FirstName, LastName, Email, UserName, [Password], CreatedDate, ModifiedHistory, IsDelete)\n"
                        + "VALUES \n"
                        + "(?,?,?,?,?,?,?,?)";
            } else {
                newCustomer = new Customer(Base.Util.Utilities.INT_DEFAUT, firstName, lastName, Base.Util.Utilities.BOOLEAN_DEFAUT, email, username, pass,
                        Base.Util.Utilities.STRING_DEFAUT, phone, createdDate, Base.Util.Utilities.STRING_DEFAUT, modifiedHistory, false);
                sql = "INSERT INTO Customers (FirstName, LastName, Email, UserName, [Password], Phone, CreatedDate, ModifiedHistory, IsDelete)\n"
                        + "VALUES \n"
                        + "(?,?,?,?,?,?,?,?,?)";
            }

            dao.createClientCustomer(newCustomer, sql);
        } catch (Exception e) {
            System.out.println("RegisterController: " + e.getMessage());
            Base.Util.Utilities.noti(request, false, e.getMessage());
            keepData(request, firstName, lastName, phone, email, pass, rePass);
            request.getRequestDispatcher("client/user/user-access/signUp.jsp").forward(request, response);
            return;
        }

        // send mail to welcome new user
//        SendMail sendMail = new SendMail();
//        Util.SendMail.sendMail(email, sendMail.MAIL_WELCOME_SUBJECT, sendMail.getMailWelComeBody(lastName + " " + firstName));
        response.sendRedirect("Login");
    }

    public static void responseError(HttpServletRequest request, HttpServletResponse response, String error) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("error", error);
        response.sendRedirect("Register");
    }

    private void keepData(HttpServletRequest request, String firstName, String lastName, String phoneNumber, String email,
            String pass, String repass) {
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("email", email);
        request.setAttribute("pass", pass);
        request.setAttribute("repass", repass);
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
