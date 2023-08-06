/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.webaccess;

import Base.Dal.DAO;
import Base.Model.AdminRole;
import Base.Model.Role;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Giang Minh
 */
@WebServlet(name = "AccountRolesManagement", urlPatterns = {"/AccountRolesManagement"})
public class AccountRolesManagement extends HttpServlet {

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
            out.println("<title>Servlet AccountRolesManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountRolesManagement at " + request.getContextPath() + "</h1>");
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
        DAO dao = new DAO();
        String accountId = request.getParameter("id");
        List<AdminRole> adminRoles = dao.getAdminRole(accountId);
//        List<Role> roles = dao.getAllRoles();
//        System.out.println(adminRoles.size());
        request.setAttribute("adminRoles", adminRoles);
//        request.setAttribute("roles", roles);
        request.getRequestDispatcher("admin/AccessManagement/accountRole.jsp").forward(request, response);
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
        DAO dao = new DAO();
        dao.getAllRoles();
        String[] roleIds = request.getParameterValues("roleIds[]");
        List<String> newRoleIds = new ArrayList<>(Arrays.asList(roleIds));
        int accountId = Integer.parseInt(request.getParameter("id"));
        List<Role> allRoles = dao.getAllRoles();

        for (String newR : newRoleIds) {
            dao.updateAdminRole(accountId, newR, true);
        }
        
        for (Role r : allRoles) {
            if(!newRoleIds.contains(Integer.toString(r.getRoleId()))) {
                dao.updateAdminRole(accountId, Integer.toString(r.getRoleId()), false);
            }
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
