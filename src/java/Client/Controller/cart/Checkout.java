/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package client.controller.cart;

//import Base.Dal.DAOClient;
import Base.Dal.DAO;
import Base.Dal.DAOOrder;
import Base.Dal.DaoCustomer;
import Base.Model.Cart;
import Base.Model.Customer;
import Base.Model.ProductSeri;
import Base.Util.SendMail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Checkout", urlPatterns = {"/Checkout"})
public class Checkout extends HttpServlet {

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
            out.println("<title>Servlet Checkout</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Checkout at " + request.getContextPath() + "</h1>");
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
        String page = "";
        try {
            System.out.println("*******");
            String check = request.getParameter("vnp_ResponseCode");
            if (Integer.parseInt(check) == 24) {  //check huy don hang
                Base.Util.Utilities.noti(request, true, "Cancel successfully");
                page = "Home";
            } else {
                DAO dao = new DAO();
                List<ProductSeri> list = dao.getAllProductSerie();

                Cookie[] arr = request.getCookies();
                String txt = "";
                if (arr != null) {
                    for (Cookie c : arr) {
                        if (c.getName().equals("cart")) {
                            txt += c.getValue();
                            c.setMaxAge(0);
                            response.addCookie(c);
                        }
                    }
                }
                Cart cart = new Cart(txt, list);

                DAOOrder cdao = new DAOOrder();
                String SplitText = request.getParameter("vnp_OrderInfo");
                String CusID = SplitText.substring(0, SplitText.indexOf("."));
                String address = SplitText.substring(SplitText.indexOf(".") + 1).replace(".", " ");

                String paymentMethod = request.getParameter("vnp_BankCode");

                Customer customer = dao.getCustomer(Integer.parseInt(CusID));
                cdao.checkoutOrder(customer, cart, paymentMethod, address);
                SendMail mail = new SendMail();
                    SendMail.sendMail(customer.getEmail(), "PAYMENT SUCCESS", mail.getMailConfirmPayment(customer.getUserName(), "Đơn hàng đang được giao", getDateNow()));
                Cookie myCookie = new Cookie("cart", "");
                myCookie.setMaxAge(2 * 24 * 60 * 60);
                response.addCookie(myCookie);   
                
                Base.Util.Utilities.noti(request, true, "Payment success");
                page = "Home";
            }
        } catch (Exception e) {
            System.out.println("CheckoutDoGet: " + e.getMessage());
        }
        response.sendRedirect(page);
    }
    
    private Date getDateNow() {
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        // Lấy ngày, tháng, năm từ đối tượng Calendar
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Tạo đối tượng Date từ ngày, tháng, năm
        Date date = new Date(year - 1900, month, day);
        return date;
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
        System.out.println("99999");
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
