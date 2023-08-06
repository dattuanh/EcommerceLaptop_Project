/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.account;

import Base.Dal.DaoAccount;
import Base.Model.Account;
import Base.Model.Role;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
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

@WebServlet(name = "UpdateAccount", urlPatterns = {"/UpdateAccount"})
public class UpdateAccount extends HttpServlet {

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
            out.println("<title>Servlet UpdateAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAccount at " + request.getContextPath() + "</h1>");
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
        String accountId = request.getParameter("id");
        DaoAccount daoAccount = new DaoAccount();
        Account a = daoAccount.getAccount(Integer.parseInt(accountId));
        List<Role> roleList = daoAccount.getAllRoles();
        List<String> rolesOfAccount = daoAccount.getAccountRoles(a);

        request.setAttribute("a", a);
        request.setAttribute("roleList", roleList);
        request.setAttribute("roles", rolesOfAccount);
        request.getRequestDispatcher("admin/account/update.jsp").forward(request, response);
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
            int accountId = Integer.parseInt(request.getParameter("id"));
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            boolean gender = request.getParameter("gender").equals("1");
            String dateOfBirth = request.getParameter("dateOfBirth");
            String phoneNumber = request.getParameter("phoneNumber");

            DaoAccount daoAccount = new DaoAccount();

            Account oldAccount = daoAccount.getAccount(accountId);
            String image = oldAccount.getImage();
            if (request.getPart("image").getSize() != 0) {
                image = Base.Util.Utilities.storeImage(request, "image", Base.Util.Utilities.ACCOUNT_IMAGE_PATH);
                String imagePath = Base.Util.Utilities.ACCOUNT_IMAGE_PATH + oldAccount.getImage();
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    imageFile.delete();
                }
            }

            String[] roleIds = request.getParameterValues("roles");
            List<String> newRoleIds = new ArrayList<>(Arrays.asList(roleIds));
            List<Role> allRoles = daoAccount.getAllRoles();

            // if current account deletes its current account role
            if (Base.Util.Utilities.getLoggingInAccount(request, response).getAccountId() == accountId && !newRoleIds.contains("0")) {
                throw new Exception("Removing account role not allowed");
            }

            for (String newR : newRoleIds) {
                daoAccount.updateAdminRole(accountId, newR, true);
            }

            for (Role r : allRoles) {
                if (!newRoleIds.contains(Integer.toString(r.getRoleId()))) {
                    daoAccount.updateAdminRole(accountId, Integer.toString(r.getRoleId()), false);
                }
            }
            System.out.println("oldAccount.getSalt(): " + oldAccount.getSalt());
            Account a = new Account(accountId, userName, password, firstName, lastName, gender,
                    Base.Util.Formatter.formatDate(dateOfBirth), phoneNumber, image, Base.Util.Utilities.BOOLEAN_DEFAUT, oldAccount.getSalt());
            daoAccount.updateAccount(a);
            
            Base.Util.Utilities.noti(request, true, "Cập nhật tài khoản admin thành công");
        } catch (Exception e) {
            System.out.println("Update Account: " + e.getMessage());
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
