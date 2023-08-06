/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.warranty;

import Base.Dal.DaoWarranties;
import Base.Model.Warranty;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author MSI
 */
@WebServlet(name = "DeleteWarranties", urlPatterns = {"/DeleteWarranties"})
public class DeleteWarranties extends HttpServlet {

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
            out.println("<title>Servlet DeleteWarranties</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteWarranties at " + request.getContextPath() + "</h1>");
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
        String warrantyId = request.getParameter("id");
        if (warrantyId == null || warrantyId.isEmpty()) {
            response.sendRedirect("WarrantiesManagement");
            return;
        }
        DaoWarranties dao = new DaoWarranties();
        Warranty a = dao.getWarranties(Integer.parseInt(warrantyId));
        request.setAttribute("a", a);
        request.getRequestDispatcher("admin/warranty/delete.jsp").forward(request, response);
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
        DaoWarranties dao = new DaoWarranties();

        String[] Ids = request.getParameterValues("Ids[]");
        if (Ids != null) {
            for (String Id : Ids) {
                dao.deleteWarranty(Id);
            }
        } else {
            String warrantyId = request.getParameter("id");
            dao.deleteWarranty(warrantyId);
        }
        response.sendRedirect("WarrantiesManagement");
    
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
