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
@WebServlet(name = "ChangePassword", urlPatterns = {"/ChangePassword"})
public class ChangePassword extends HttpServlet {

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
            out.println("<title>Servlet ChangePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath() + "</h1>");
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
        HttpSession ss = request.getSession();
        if (request.getParameter("id").equals("0")) {
            SendMail mail = new SendMail();
            Customer c = Base.Util.Utilities.getLoggingInCustomer(request, response);
            String token = RandomString.make(30);
            String resetPasswordLink = Base.Util.Utilities.getSiteURL(request) + "/ChangePassword?token=" + token + "&id=1&email=" + c.getEmail();

            ss.setAttribute("token", token);
            System.out.println("reset link: " + resetPasswordLink);
            SendMail.sendMail(c.getEmail(), "CHANGE PASSWORD", mail.getChangePassMail(resetPasswordLink));
            Base.Util.Utilities.noti(request, true, "Link thay đổi mật khẩu\n vừa được gửi tới mail của bạn");
            response.sendRedirect("Home");
        } else if (request.getParameter("id").equals("1")) {
            String token = request.getParameter("token");
            System.out.println("retrive token: " + token);
            if (token.equals(ss.getAttribute("token"))) {
                request.getRequestDispatcher("client/user/user-access/passRecovery.jsp").forward(request, response);
                Base.Util.Utilities.noti(request, true, "Nhap mat khau moi");
            } else {
                response.sendRedirect("Home");
                Base.Util.Utilities.noti(request, false, "Duong dan khong hop le\nDoi mat khau that bai!");
            }
        }

//        Customer c = Base.Util.Utilities.getLoggingInCustomer(request, response);
//
//        if (c == null) {
//            response.sendRedirect("Register");
//            return;
//        }
//
//        request.getRequestDispatcher("client/user/user-access/passRecovery.jsp").forward(request, response);
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

        String token = request.getParameter("token");
        if(!request.getSession().getAttribute("token").equals(token)) {
            Base.Util.Utilities.noti(request, false, "Invalid");
            response.sendRedirect("Home");
            return;
        }
        
        String email = request.getParameter("email");
        Customer c = new DaoCustomer().getCustomerByEmail(email);

        String pass = request.getParameter("pass");
        String rePass = request.getParameter("rePass");

        if (!pass.equals(rePass)) {
            System.out.println("different password");
            Base.Util.Utilities.noti(request, false, "");
            response.sendRedirect("Home");
            return;
        } else {
            String encryptPass = Base.Util.EncryptionDecryptionAES.enDecrypt(pass, c.getEmail(), true);
            new DaoCustomer().updateCustomerPassword(encryptPass, c.getCustomerId());
        }

        Customer currenCustomer = Base.Util.Utilities.getLoggingInCustomer(request, response);
        if (email.equals(currenCustomer.getEmail())) {
            c.setPassword(pass);
            Base.Util.Utilities.setLoggingInCustomer(request, response, c);
        }

        response.sendRedirect("Home");
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
