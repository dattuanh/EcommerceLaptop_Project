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
import Base.Util.Utilities;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;

/**
 *
 * @author Giang Minh
 */
@WebServlet(name = "PaymentController", urlPatterns = {"/PaymentController"})
public class PaymentController extends HttpServlet {

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
            out.println("<title>Servlet PaymentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentController at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private int getCustomerIDByEmail(String email, String firstName, String lastName, String userName, String address, String phone) {
        DaoCustomer dao = new DaoCustomer();
        int numCus = 0;
        boolean checkNewCus = true;
        for (Customer c1 : dao.getAllCustomers()) {
            if (c1.getEmail().equals(email)) {
                numCus = c1.getCustomerId();
                checkNewCus = false;
            }
        }

        //SendMail mail = new SendMail();
        //SendMail.sendMail(c.getEmail(), "CHANGE PASSWORD", mail.getChangePassMail(resetPasswordLink));
        if (checkNewCus) {
            try {
                String autoPassWord = Base.Util.Utilities.generateSecurePassword();
                Customer c = new Customer(Utilities.INT_DEFAUT, firstName, lastName, Utilities.BOOLEAN_DEFAUT, email, userName, autoPassWord,
                        address, phone, new Date(), Utilities.STRING_DEFAUT, Utilities.STRING_DEFAUT, Utilities.BOOLEAN_DEFAUT);
                dao.createCustomer(c);

                for (Customer c1 : dao.getAllCustomers()) {
                    if (c1.getEmail().equals(email)) {
                        numCus = c1.getCustomerId();
                        //checkNewCus = false;
                    }
                }
            } catch (Exception e) {
                System.out.println("PaymentController: " + e.getMessage());
            }
        }
        return numCus;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAO dao = new DAO();
        List<ProductSeri> list = dao.getAllProductSerie();

        Cookie[] arr = req.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie c : arr) {
                if (c.getName().equals("cart")) {
                    txt += c.getValue();
                }
            }
        }
        Cart cart = new Cart(txt, list);
        String page = "";

        try {
            if (cart == null) {
                page = "Home";
                //resp.sendRedirect("Home");
            } else {
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                String userName = firstName + lastName;
                String email = req.getParameter("email");
                String phone = req.getParameter("phone");
                String address = req.getParameter("address");
                String paymentMethod = req.getParameter("paymentMethod");

                if (paymentMethod.equals("Direct")) {
                    int numCus = getCustomerIDByEmail(email, firstName, lastName, userName, address, phone);
                    System.out.println("Direct");

                    DaoCustomer cusdao = new DaoCustomer();
                    DAOOrder cdao = new DAOOrder();
                    Customer customer = cusdao.getCustomer(numCus);
                    cdao.checkoutOrder(customer, cart, paymentMethod, address);
                    SendMail mail = new SendMail();
                    SendMail.sendMail(email, "PAYMENT SUCCESS", mail.getMailConfirmPayment(customer.getUserName(), "Đơn hàng đang được giao", getDateNow()));

                    Cookie myCookie = new Cookie("cart", "");
                    myCookie.setMaxAge(2 * 24 * 60 * 60);
                    resp.addCookie(myCookie);

                    System.out.println("Direct 2");
                    Base.Util.Utilities.noti(req, true, "Payment success");
                    page = "Home";
                } else {
                    int numCus = getCustomerIDByEmail(email, firstName, lastName, userName, address, phone);
                    System.out.println("cus VNB");
                    String total = req.getParameter("total");
                    total = total.replaceAll("[^0-9]", "");

                    String vnp_Version = "2.1.0";
                    String vnp_Command = "pay";
                    String vnp_OrderInfo = Integer.toString(numCus) + "." + address.trim().replace(" ", ".");
                    String orderType = "billpayment"; //req.getParameter("ordertype");
                    String vnp_TxnRef = Base.Util.Config.getRandomNumber(8);
                    String vnp_IpAddr = Base.Util.Config.getIpAddress(req);
                    String vnp_TmnCode = Base.Util.Config.vnp_TmnCode;

                    long amount = Long.parseLong(total) * 100;

                    Map vnp_Params = new HashMap<>();
                    vnp_Params.put("vnp_Version", vnp_Version);
                    vnp_Params.put("vnp_Command", vnp_Command);
                    vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
                    vnp_Params.put("vnp_Amount", String.valueOf(amount));
                    vnp_Params.put("vnp_CurrCode", "VND");
                    String bank_code = "NCB";  // paymentMethod   
                    if (bank_code != null && !bank_code.isEmpty()) {
                        vnp_Params.put("vnp_BankCode", bank_code);
                    }
                    vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
                    vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
                    vnp_Params.put("vnp_OrderType", orderType);

                    String locate = req.getParameter("language");
                    if (locate != null && !locate.isEmpty()) {
                        vnp_Params.put("vnp_Locale", locate);
                    } else {
                        vnp_Params.put("vnp_Locale", "vn");
                    }
                    vnp_Params.put("vnp_ReturnUrl", Base.Util.Config.vnp_Returnurl);
                    vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
                    Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                    String vnp_CreateDate = formatter.format(cld.getTime());

                    vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
                    cld.add(Calendar.MINUTE, 15);
                    String vnp_ExpireDate = formatter.format(cld.getTime());
                    //Add Params of 2.1.0 Version
                    vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
                    //Build data to hash and querystring
                    List fieldNames = new ArrayList(vnp_Params.keySet());
                    Collections.sort(fieldNames);
                    StringBuilder hashData = new StringBuilder();
                    StringBuilder query = new StringBuilder();
                    Iterator itr = fieldNames.iterator();
                    while (itr.hasNext()) {
                        String fieldName = (String) itr.next();
                        String fieldValue = (String) vnp_Params.get(fieldName);
                        //System.out.println(vnp_Params.get(fieldName));
                        if ((fieldValue != null) && (fieldValue.length() > 0)) {
                            //Build hash data
                            hashData.append(fieldName);
                            hashData.append('=');
                            hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                            //Build query
                            query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                            query.append('=');
                            query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                            if (itr.hasNext()) {
                                query.append('&');
                                hashData.append('&');
                            }
                        }
                    }
                    String queryUrl = query.toString();
                    String vnp_SecureHash = Base.Util.Config.hmacSHA512(Base.Util.Config.vnp_HashSecret, hashData.toString());
                    queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
                    String paymentUrl = Base.Util.Config.vnp_PayUrl + "?" + queryUrl;
                    com.google.gson.JsonObject job = new JsonObject();
                    job.addProperty("code", "00");
                    job.addProperty("message", "success");
                    job.addProperty("data", paymentUrl);
                    Gson gson = new Gson();
                    resp.getWriter().write(gson.toJson(job));
                }
            }

        } catch (Exception e) {
            System.out.println("PaymentControllerDoPost: " + e.getMessage());
        }
        if (page.equalsIgnoreCase("home")) {
            System.out.println("check Home");
            resp.sendRedirect(page);
        }
    }
    //vui lòng tham khảo thêm tại code demo

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
