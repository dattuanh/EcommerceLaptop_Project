/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package client.controller.accessment;

import Base.Dal.DaoCustomer;
import Base.Model.Customer;
import Base.Util.SendMail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 *
 * @author Giang Minh
 */
@WebServlet(name = "ClientLogin", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        Cookie[] cookies = request.getCookies();
        String email = "";
        String pass = "";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("customerEmail")) {
                    email = c.getValue();
                    request.setAttribute("email", email);
                }

                if (c.getName().equals("customerPass")) {
//                    System.out.println("c get value: " + c.getValue());
                    pass = Base.Util.EncryptionDecryptionAES.enDecrypt(c.getValue(), email, false);
                    request.setAttribute("pass", pass);
                }
            }
        }
        request.getRequestDispatcher("client/user/user-access/login.jsp").forward(request, response);
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
        DaoCustomer daoCustomer = new DaoCustomer();
        Customer customer;
        String email = "";
        String pass = "";
        String encyptedPass = "";

        try {
            // using google sign in
            if (request.getParameter("googleIdToken") != null) {
                email = request.getParameter("email");
                customer = daoCustomer.checkExistCustomer(email);
                // no customer exist with the email => create a new one
                if (customer == null) {
                    String exp = request.getParameter("exp");
                    String name = request.getParameter("name");
                    String firstName = name.split(" ")[0];
                    String lastName = "";

                    for (int i = 1; i < name.split(" ").length; i++) {
                        String s = name.split(" ")[i];
                        lastName += s + " ";
                    }

                    String userName = Base.Util.Formatter.createUserName(firstName, lastName) + exp;
                    String password = Base.Util.Utilities.generateSecurePassword();
//                    String encryptedPass = Base.Util.EncryptionDecryptionAES.enDecrypt(password, email, true);
//                    System.out.println("encrypt pass:" + encryptedPass);

                    customer = new Customer();
                    customer.setFirstName(firstName);
                    customer.setLastName(lastName);
                    customer.setEmail(email);
                    customer.setUserName(userName);
                    customer.setPassword(password);
                    customer.setCreatedDate(new Date());
                    
                    daoCustomer.createCustomer(customer);

                    // send mail to welcome new user
                    SendMail sendMail = new SendMail();
                    Base.Util.SendMail.sendMail(email, sendMail.MAIL_WELCOME_SUBJECT, 
                            sendMail.getMailWelComeBody(lastName + " " + firstName, password));
                }
            } else {
                // use normal account
                email = request.getParameter("email");
                pass = request.getParameter("pass");
                encyptedPass = Base.Util.EncryptionDecryptionAES.enDecrypt(pass, email, true);
                customer = daoCustomer.getCustomer(email, encyptedPass, pass);
            }

            if (customer == null) {
                throw new Exception("Email hoặc mật khẩu không tồn tại");
            }
            
            Base.Util.Utilities.setLoggingInCustomer(request, response, customer);
            
            if (request.getParameter("googleIdToken") == null
                    && request.getParameter("remember") != null) {
                // save normal account to cookie
                Cookie userC = new Cookie("customerEmail", email);
                Cookie passC = new Cookie("customerPass", encyptedPass);

                userC.setMaxAge(60 * 60 * 24 * 30);
                passC.setMaxAge(60 * 60 * 24 * 30);

                response.addCookie(userC);
                response.addCookie(passC);
            }
            response.sendRedirect("Home");
            Base.Util.Utilities.noti(request, true, "Xin chào " + customer.getUserName());

        } catch (Exception e) {
            System.out.println("LoginController: " + e.getMessage());
            Base.Util.Utilities.noti(request, false, e.getMessage());
            response.sendRedirect("Login");
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
