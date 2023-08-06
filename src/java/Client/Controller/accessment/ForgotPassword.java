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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.bytebuddy.utility.RandomString;

/**
 *
 * @author Giang Minh
 */
@WebServlet(name = "ForgotPassword", urlPatterns = {"/ForgotPassword"})
public class ForgotPassword extends HttpServlet {

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
            out.println("<title>Servlet ForgotPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPassword at " + request.getContextPath() + "</h1>");
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

        if ("1".equals(request.getParameter("id"))) {
            HttpSession ss = request.getSession();
            SendMail mail = new SendMail();
            String email = request.getParameter("email");
            String token = RandomString.make(30);
            String resetPasswordLink = Base.Util.Utilities.getSiteURL(request) + "/ForgotPassword?token=" + token + "&id=2&email=" + email;

            ss.setAttribute("token", token);
            ss.setMaxInactiveInterval(60 * 5);
            System.out.println("reset link: " + resetPasswordLink);
            SendMail.sendMail(email, "CHANGE PASSWORD", mail.getNewPassMail(resetPasswordLink));
            Base.Util.Utilities.noti(request, true, "Mật khẩu mới vừa được gửi tới địa chỉ email của bạn");
            response.sendRedirect("Home");
        } else if ("2".equals(request.getParameter("id"))) {
            request.getRequestDispatcher("client/user/user-access/passRecovery.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("client/user/user-access/forgotPassword.jsp").forward(request, response);
        }

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
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String token = request.getParameter("token");

        try {

            Customer c = daoCustomer.checkExistCustomer(email);

            if (c == null) {
                throw new Exception("Email không tồn tại");
            }

            if (!request.getSession().getAttribute("token").equals(token)) {
                throw new Exception("Token hết hạn");
            }

            String pass = request.getParameter("pass");
            String rePass = request.getParameter("rePass");

            if (!Base.Util.Validation.passwordValidation(pass)) {
                throw new Exception();
            } else if (!pass.equals(rePass)) {
                throw new Exception("Mật khẩu xác nhận phải trùng với mật khẩu");
            }

            c.setPassword(pass);
            daoCustomer.updateCustomerPassword(c);
            Base.Util.Utilities.noti(request, true, "Tạo mật khẩu mới thành công");
            response.sendRedirect("Login");

        } catch (Exception e) {
            System.out.println("ForgotPasswordController: " + e.getMessage());
            Base.Util.Utilities.noti(request, false, e.getMessage());
            response.sendRedirect("ForgotPassword?id=" + id + "&email=" + email + "&token=" + token);
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
