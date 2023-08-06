/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.account;

import Base.Dal.DaoAccount;
import Base.Model.Account;
import Base.Model.Role;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Giang Minh
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)

@WebServlet(name = "CreateAccount", urlPatterns = {"/CreateAccount"})
public class CreateAccount extends HttpServlet {

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
            out.println("<title>Servlet CreateAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateAccount at " + request.getContextPath() + "</h1>");
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
        DaoAccount daoAccount = new DaoAccount();
        List<Role> roleList = daoAccount.getAllRoles();

        request.setAttribute("roleList", roleList);
        request.getRequestDispatcher("admin/account/create.jsp").forward(request, response);
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
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            boolean gender = request.getParameter("gender").equals("1");
            String dateOfBirth = request.getParameter("dateOfBirth");
            String phoneNumber = request.getParameter("phoneNumber");
            String image = Base.Util.Utilities.storeImage(request, "image", Base.Util.Utilities.ACCOUNT_IMAGE_PATH);
            String[] roleIds = request.getParameterValues("roleIds");
            List<String> roleIdList = new ArrayList<>();
            if (roleIds != null) {
                roleIdList = Arrays.asList(roleIds);
            }

            if (!password.equals(confirmPassword)) {
                throw new Exception("Invalid confirm password");
            }

            DaoAccount daoAccount = new DaoAccount();
            String salt = Base.Util.EncryptionDecryptionAES.getNextSalt();

            Account a = new Account(Base.Util.Utilities.INT_DEFAUT, userName, password, firstName, lastName, gender,
                    Base.Util.Formatter.formatDate(dateOfBirth), phoneNumber, image, Base.Util.Utilities.BOOLEAN_DEFAUT, salt);

            int newAccountId = daoAccount.createAccount(a);
            List<Role> allRoles = daoAccount.getAllRoles();
            if (newAccountId >= 0) {
                for (Role role : allRoles) {
                    boolean status = roleIdList.contains(Integer.toString(role.getRoleId()));
                    daoAccount.createAdminRole(newAccountId, role.getRoleId(), Base.Util.Utilities.getLoggingInAccount(request, response).getAccountId(), status);
                }
            }
            Base.Util.Utilities.noti(request, true, "Tạo tài khoản admin thành công");

        } catch (Exception e) {
            System.out.println("CreateAccount:" + e.getMessage());
            Base.Util.Utilities.noti(request, false, e.getMessage());
        }
        response.sendRedirect("AccountManagement");
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
