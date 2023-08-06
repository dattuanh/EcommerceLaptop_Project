/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Giang Minh
 */
@WebServlet(name = "SendMail", urlPatterns = {"/SendMail"})
public class SendMail extends HttpServlet {

    public static void sendMail(String toEmail, String subject, String body) {
        final String fromEmail = "ComputerStoreTesting@gmail.com"; //requires valid gmail id
        final String password = "ltzhffpozmfwyaka"; // correct password for gmail id

        System.out.println("TLSEmail Start");
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Create a new message
            Message message = new MimeMessage(session);

            // Set the sender, recipient, and subject
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

//            // Set the email content
//            message.setText(WELCOME_MAIL);
            // Set the HTML content of the message
            message.setContent(body, "text/html; charset=utf-8");
            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }
    }

    public String getMailWelComeBody(String receiverName, String password) {
        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "  <style>\n"
                + "    body {\n"
                + "      font-family: Arial, sans-serif;\n"
                + "    }\n"
                + "\n"
                + "    h1 {\n"
                + "      color: #333333;\n"
                + "      font-size: 24px;\n"
                + "      margin-bottom: 20px;\n"
                + "    }\n"
                + "\n"
                + "    p {\n"
                + "      color: #666666;\n"
                + "      font-size: 16px;\n"
                + "      line-height: 1.5;\n"
                + "      margin-bottom: 10px;\n"
                + "    }\n"
                + "\n"
                + "    a {\n"
                + "      color: #007bff;\n"
                + "      text-decoration: none;\n"
                + "    }\n"
                + "  </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "  <h1>Welcome to ComputerStore!</h1>\n"
                + "  <p>Dear " + receiverName + ",</p>\n"
                + "  <p>Thank you for joining ComputerStore. We are excited to have you on board.</p>\n"
                + "  <p>Please go to our website to have the best laptops:</p>\n"
                + "  <p><a href=\"http://localhost:9999/ECommerce_Client/\">ComputerStore</a></p>\n"
                + "<p>You can also sign in to our website using this mail and this password: " + password + " </p>\n"
                + "  <p>If you have any questions, feel free to <a href=\"mailto:ComputerStoreTesting@gmail.com\">contact our support team</a>.</p>\n"
                + "  <p>Best regards,</p>\n"
                + "  <p>The ComputerStore Team</p>\n"
                + "</body>\n"
                + "</html>";
    }

    public String getMailNewsContent(String receiverName) {
        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "    <title>Exciting News about Our Products!</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "        }\n"
                + "        \n"
                + "        .container {\n"
                + "            max-width: 600px;\n"
                + "            margin: 0 auto;\n"
                + "            padding: 20px;\n"
                + "            background-color: #f7f7f7;\n"
                + "        }\n"
                + "        \n"
                + "        h1 {\n"
                + "            color: #333333;\n"
                + "        }\n"
                + "        \n"
                + "        p {\n"
                + "            color: #555555;\n"
                + "        }\n"
                + "        \n"
                + "        .button {\n"
                + "            display: inline-block;\n"
                + "            background-color: #007bff;\n"
                + "            color: #ffffff;\n"
                + "            padding: 10px 20px;\n"
                + "            text-decoration: none;\n"
                + "            border-radius: 4px;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container\">\n"
                + "        <h1>Exciting News about Our Products!</h1>\n"
                + "        <p>Dear " + receiverName + ",</p>\n"
                + "        \n"
                + "        <p>We are thrilled to share some exciting news about our latest products! Our team has been working hard to bring you innovative solutions that will enhance your experience and meet your needs.</p>\n"
                + "        \n"
                + "        <h2>New Product 1: XYZ Widget</h2>\n"
                + "        <p>Introducing our brand new XYZ Widget, designed to simplify your daily tasks and improve productivity. With its advanced features and user-friendly interface, the XYZ Widget will revolutionize the way you work.</p>\n"
                + "        \n"
                + "        <h2>New Product 2: ABC Gadget</h2>\n"
                + "        <p>We are proud to present the ABC Gadget, a cutting-edge device that combines functionality and style. Experience the future of technology with the ABC Gadget, packed with powerful capabilities and sleek design.</p>\n"
                + "        \n"
                + "        <p>To learn more about our new products and how they can benefit you, visit our website or contact our dedicated customer support team.</p>\n"
                + "        \n"
                + "        <p>Thank you for being a part of our journey. We value your continued support and look forward to serving you with our latest innovations.</p>\n"
                + "        \n"
                + "        <p>Best regards,</p>\n"
                + "        <p>Your Company Name</p>\n"
                + "        \n"
                + "        <p style=\"text-align: center;\">\n"
                + "            <a class=\"button\" href=\"http://localhost:9999/ECommerce_Client/\">Visit Website</a>\n"
                + "            <br>\n"
                + "            <small>If you no longer wish to receive these emails, <a href=\"mailto:unsubscribe@yourcompany.com\">unsubscribe</a>.</small>\n"
                + "        </p>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
    }

    public String getChangePassMail(String passChangeLink) {
        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Change Password</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div style=\"max-width: 600px; margin: 0 auto;\">\n"
                + "        <h2>Notification of Password Change</h2>\n"
                + "        <p>Dear User,</p>\n"
                + "        <p>\n"
                + "            This is to inform you that your password has been changed successfully.\n"
                + "            If you did not initiate this change, please contact our support team immediately.\n"
                + "        </p>\n"
                + "        <p>\n"
                + "            If you have made the password change request, you can now log in using your new password.\n"
                + "        </p>\n"
                + "        <p>\n"
                + "            If you would like to change your password again, please click on the link below:\n"
                + "        </p>\n"
                + "        <p>\n"
                + "            <a href=\"" + passChangeLink + "\">Reset Password</a>\n"
                + "        </p>\n"
                + "        <p>\n"
                + "            Thank you for using our service!\n"
                + "        </p>\n"
                + "        <p>Best regards,</p>\n"
                + "        <p>Your Company</p>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
    }

    public String MAIL_WELCOME_SUBJECT = "Welcome to ComputerStore";
    public String MAIL_SEND_NEWS_SUBJECT = "Hello from ComputerStore";
    public String MAIL_THANKYOU_SUBJECT = "Thank You for buying our ComputerStore's Products";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        SendMail.sendMail(email, MAIL_SEND_NEWS_SUBJECT, getMailNewsContent(name));

        resp.sendRedirect("Home");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public String getMailConfirmPayment(String receiverName, String status, int OrderId, Date orderDate) {
        // after 15 days order will be shipped
        String estimatedDeliveryDate = addDaysToDate(orderDate, 15, "dd/MM/yyyy");
        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "  <style>\n"
                + "    body {\n"
                + "      font-family: Arial, sans-serif;\n"
                + "    }\n"
                + "\n"
                + "    h1 {\n"
                + "      color: #333333;\n"
                + "      font-size: 24px;\n"
                + "      margin-bottom: 20px;\n"
                + "    }\n"
                + "\n"
                + "    p {\n"
                + "      color: #666666;\n"
                + "      font-size: 16px;\n"
                + "      line-height: 1.5;\n"
                + "      margin-bottom: 10px;\n"
                + "    }\n"
                + "\n"
                + "    a {\n"
                + "      color: #007bff;\n"
                + "      text-decoration: none;\n"
                + "    }\n"
                + "  </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "  <h1>You have successfully made the payment!</h1>\n"
                + "  <h3>Dear " + receiverName + ",</h3>\n"
                + "  <p>ComputerStore would like to inform you about the status of your order as follows:</p>\n"
                + "  <ul>\n"
                + "      <li>Order Status: " + status + "</li>\n"
                + "      <li>Order ID: " + OrderId + "</li>\n"
                + "      <li>Shipping carrier: " + "GRAB" + "</li>\n"
                + "      <li>Estimated delivery time: " + estimatedDeliveryDate + "</li>\n"
                + "  </ul>"
                + "  <p>Thank you for buying our Products. We are excited to have you on board.</p>\n"
                + "  <p>If you have any questions, feel free to <a href=\"mailto:ComputerStoreTesting@gmail.com\">contact our support team</a>.</p>\n"
                + "  <p>Best regards,</p>\n"
                + "  <p>The ComputerStore Team</p>\n"
                + "</body>\n"
                + "</html>";
    }

    public String addDaysToDate(Date date, int days, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        Date newDate = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(newDate);
    }

    public String MAIL_WELCOME_BODY
            = "<!DOCTYPE html>\n"
            + "<html>\n"
            + "<head>\n"
            + "  <style>\n"
            + "    body {\n"
            + "      font-family: Arial, sans-serif;\n"
            + "    }\n"
            + "\n"
            + "    h1 {\n"
            + "      color: #333333;\n"
            + "      font-size: 24px;\n"
            + "      margin-bottom: 20px;\n"
            + "    }\n"
            + "\n"
            + "    p {\n"
            + "      color: #666666;\n"
            + "      font-size: 16px;\n"
            + "      line-height: 1.5;\n"
            + "      margin-bottom: 10px;\n"
            + "    }\n"
            + "\n"
            + "    a {\n"
            + "      color: #007bff;\n"
            + "      text-decoration: none;\n"
            + "    }\n"
            + "  </style>\n"
            + "</head>\n"
            + "<body>\n"
            + "  <h1>Welcome to ComputerStore!</h1>\n"
            + "  <p>Dear Mr,</p>\n"
            + "  <p>Thank you for joining ComputerStore. We are excited to have you on board.</p>\n"
            + "  <p>Please go to our website to have the best laptops:</p>\n"
            + "  <p><a href=\"http://localhost:9999/ECommerce_Client/\">ComputerStore</a></p>\n"
            + "  <p>If you have any questions, feel free to <a href=\"mailto:ComputerStoreTesting@gmail.com\">contact our support team</a>.</p>\n"
            + "  <p>Best regards,</p>\n"
            + "  <p>The ComputerStore Team</p>\n"
            + "</body>\n"
            + "</html>";

    public String getNewPassMail(String forgotPass) {
        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Change Password</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div style=\"max-width: 600px; margin: 0 auto;\">\n"
                + "        <h2>Notification of Password Change</h2>\n"
                + "        <p>Dear User,</p>\n"
                + "        <p>\n"
                + "            Click this link if you are the one who really change your account's password: \n"
                + forgotPass
                + "        <p>\n"
                + "            Thank you for using our service!\n"
                + "        </p>\n"
                + "        <p>Best regards,</p>\n"
                + "        <p>Your Company</p>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
    }

     public String getMailConfirmPayment(String receiverName, String status, Date orderDate) {
        // after 15 days order will be shipped
        String estimatedDeliveryDate = addDaysToDate(orderDate, 15, "dd/MM/yyyy");
        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "  <style>\n"
                + "    body {\n"
                + "      font-family: Arial, sans-serif;\n"
                + "    }\n"
                + "\n"
                + "    h1 {\n"
                + "      color: #333333;\n"
                + "      font-size: 24px;\n"
                + "      margin-bottom: 20px;\n"
                + "    }\n"
                + "\n"
                + "    p {\n"
                + "      color: #666666;\n"
                + "      font-size: 16px;\n"
                + "      line-height: 1.5;\n"
                + "      margin-bottom: 10px;\n"
                + "    }\n"
                + "\n"
                + "    a {\n"
                + "      color: #007bff;\n"
                + "      text-decoration: none;\n"
                + "    }\n"
                + "  </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "  <h1>You have successfully made the payment!</h1>\n"
                + "  <h3>Dear " + receiverName + ",</h3>\n"
                + "  <p>ComputerStore would like to inform you about the status of your order as follows:</p>\n"
                + "  <ul>\n"
                + "      <li>Order Status: " + status + "</li>\n"
                + "      <li>Shipping carrier: " + "GRAB" + "</li>\n"
                + "      <li>Estimated delivery time: " + estimatedDeliveryDate + "</li>\n"
                + "  </ul>"
                + "  <p>You can login with this email to keep up to date about your Order in our website </p>\n"
                + "  <ul>\n"
                + "      <li>Here is your password: @123</li>\n"
                + "  </ul>"
                + "  <p>Thank you for buying our Products. We are excited to have you on board.</p>\n"
                + "  <p>If you have any questions, feel free to <a href=\"mailto:ComputerStoreTesting@gmail.com\">contact our support team</a>.</p>\n"
                + "  <p>Best regards,</p>\n"
                + "  <p>The ComputerStore Team</p>\n"
                + "</body>\n"
                + "</html>";
    }
     
}
