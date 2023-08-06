/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package client.controller.accessment;

import Base.Dal.DAOOrder;
import Base.Dal.DaoCustomer;
import Base.Model.Customer;
import Base.Model.Order;
import Base.Model.OrderItem;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Giang Minh
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet(name = "Info", urlPatterns = {"/Info"})
public class Info extends HttpServlet {

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
            out.println("<title>Servlet Info</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Info at " + request.getContextPath() + "</h1>");
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
        Customer c = Base.Util.Utilities.getLoggingInCustomer(request, response);
        if (c == null) {
            response.sendRedirect("Login");
            return;
        }

        String OrderDetailId = request.getParameter("OrderDetailId");
        DAOOrder odao = new DAOOrder();
        if (OrderDetailId != null) {
            List<OrderItem> itemOrderList = odao.getOrderItemByOrderId(OrderDetailId);
            Random random = new Random();
            int randomNum = 0;
            Date ShipDate = new Date();
            Order o = odao.getOrder(OrderDetailId);

            boolean checkCookie = false;
            if (o.getStatus().getOrderStatusName().equalsIgnoreCase("approved")) {
                Cookie[] arr = request.getCookies();
                if (arr != null) {
                    for (Cookie ck : arr) {
                        if (ck.getName().equals("shippingDate")) {
                            checkCookie = true;
                            randomNum = Integer.parseInt(ck.getValue());
                            ck.setMaxAge(0);
                            response.addCookie(ck);
                        }
                    }
                }

                if (checkCookie == false) {
                    randomNum = random.nextInt(3) + 3;
                    Cookie cookie = new Cookie("shippingDate", "" + randomNum + "");
                    cookie.setMaxAge(-1);
                    response.addCookie(cookie);
                }

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(o.getOrderDate());
                // cộng thêm ngày
                calendar.add(Calendar.DAY_OF_MONTH, randomNum);
                ShipDate = calendar.getTime();
            }

            request.setAttribute("c", c);
            request.setAttribute("o", o);
            request.setAttribute("randomNum", randomNum);
            request.setAttribute("randomShippingDate", ShipDate);
            request.setAttribute("itemOrderList", itemOrderList);
            request.getRequestDispatcher("client/user/user-info/orderDetail.jsp").forward(request, response);
        } else {
            List<Order> olist = odao.getOrderByCustomer(c.getCustomerId());
            request.setAttribute("c", c);
            System.out.println("999");
            request.setAttribute("olist", olist);
            request.getRequestDispatcher("client/user/user-info/in4.jsp").forward(request, response);
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
        DaoCustomer dao = new DaoCustomer();
        Customer oldCustomer = Base.Util.Utilities.getLoggingInCustomer(request, response);
        try {
            if ("1".equals(request.getParameter("id"))) {

                // get curent logging in customer
                String fileName = Base.Util.Utilities.getFileName(request.getPart("image"));
                String newImage = !fileName.isEmpty()
                        && (oldCustomer.getImage() == null || !oldCustomer.getImage().equals(fileName))
                        ? Base.Util.Utilities.storeImage(request, "image", Base.Util.Utilities.ADMIN_CUSTOMER_IMAGE_PATH)
                        : oldCustomer.getImage();
                String userName = request.getParameter("userName");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String address = request.getParameter("address");
                boolean sex = request.getParameter("sex").equals("1");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                
                // check existed email
                List<Customer> allCustomers = dao.getAllCustomers();
                for (Customer c : allCustomers) {
                    if (c.getCustomerId() == oldCustomer.getCustomerId()) {
                        continue;
                    }

                    if (c.getEmail().equals(email)) {
                        throw new Exception("Email đã được đăng ký");
                    }
                    if (!"".equals(phone) && c.getPhone().equals(phone)) {
                        throw new Exception("Số điện thoại đã được đăng ký");
                    }
                    if (c.getUserName().equals(userName)) {
                        throw new Exception("Tên đăng nhập đã được sử dụng");
                    }
                }
                
                String modifiedHistory = request.getParameter("modifiedHistory");
                Date createdDate = new Date();
                Customer c = new Customer(oldCustomer.getCustomerId(), firstName, lastName, sex, email, userName,
                        "", address, phone, createdDate, newImage, modifiedHistory, false);
                dao.updateCustomer(c);
                Base.Util.Utilities.setLoggingInCustomer(request, response, c);

            } else if ("2".equals(request.getParameter("id"))) {
                String oldPass = request.getParameter("oldPass");
                String newPass = request.getParameter("newPass");

                if (!Base.Util.Validation.passwordValidation(newPass)) {
                    throw new Exception();
                }

                if (!oldPass.equals(oldCustomer.getPassword())) {
                    throw new Exception("Wrong password!");
                }

                oldCustomer.setPassword(newPass);
                dao.updateCustomerPassword(oldCustomer);
            }

            Base.Util.Utilities.noti(request, true, "Cập nhật thành công");
        } catch (Exception e) {
            System.out.println("InfoController: " + e.getMessage());
            Base.Util.Utilities.noti(request, false, e.getMessage());
        } finally {
            response.sendRedirect("Info");
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
