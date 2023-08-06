/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package admin.controller.footer;

import Base.Dal.DaoDisplay;
import Base.Model.Account;
import Base.Model.News;
import Base.Model.NewsGroup;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name="UpdateFooterLevel2", urlPatterns={"/UpdateFooterLevel2"})
public class UpdateFooterLevel2 extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateFooterLevel2</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateFooterLevel2 at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DaoDisplay dao = new DaoDisplay();
        List<News> nav = dao.getAllFooters();
        String[] tempList = request.getParameterValues("temp[]") == null ? new String[0] : request.getParameterValues("temp[]");
        for (int i = 0; i < tempList.length; i++) {
            dao.removeNavLevel2(tempList[i]);
        }
        for (News n : nav) {
            String[] Ids = request.getParameterValues(n.getNewId() + "[]");
            if (Ids != null) {
                News t = dao.getNews(n.getNewId());
                NewsGroup temp = dao.getFooterLevel_1(t.getNewTitle());
                News c = new News();
                Account modifiedBy = Base.Util.Utilities.getLoggingInAccount(request, response);
                Date modifiedDate = Base.Util.Formatter.getCurrentDate();
                c.setModifiedDate(modifiedDate);
                c.setModifiedBy(modifiedBy);
                for (int i = 0; i < Ids.length; i++) {
                    if (Ids[i] == null) {

                    } else {
                        c.setNewsGroupID(temp);
                        c.setNewId(Integer.parseInt(Ids[i]));
                        c.setNewsHeading(i + "");
                        c.setModifiedHistory(modifiedDate.toString() + " - ad: Update " + Ids[i] + " Button to " + i + "<br>");
                        dao.updateNavBarLevel2(c);
                    }
                }
            }
        }
        response.sendRedirect("FooterManagement");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
