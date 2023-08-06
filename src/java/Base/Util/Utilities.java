/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Util;

import Base.Model.Account;
import Base.Dal.DAOClient;
import Base.Dal.DaoCustomer;
import Base.Model.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import javax.xml.bind.DatatypeConverter;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

/**
 *
 * @author Giang Minh
 */
public class Utilities {

    public static final String IS_DELETE_FALSE_WITH_WHERE = " where IsDelete = 0";
    public static final String IS_DELETE_FALSE_WITHOUT_WHERE = " and IsDelete = 0";
    public static final String IS_DELETE_TRUE_WITH_WHERE = " where IsDelete = 1";
    public static final String STATUS_TRUE_WITHOUT_WHERE = " and Status = 1";
    public static final String IS_PRESENT_TRUE_WITH_WHERE = " and IsPresent = 1";

    public static final int INT_DEFAUT = 0;
    public static final String STRING_DEFAUT = "";
    public static final boolean BOOLEAN_DEFAUT = false;
    public static final Date DATE_DEFAUT = null;
    public static final Account ACCOUNT_DEFAUT = null;

//    private static final String PROJECT_PATH = new PathFilter().projectPath + "\\ECommerce_Admin\\web\\assets\\shared\\images\\";
//
//    public static final String CUSTOMER_IMAGE_PATH = PROJECT_PATH + "Customer\\";
//    public static final String SERI_IMAGE_PATH = PROJECT_PATH + "Seri\\";
//    
//    public String getProjectPath() {
//        return PROJECT_PATH;
//    }
    public static final String ADMIN_CUSTOMER_IMAGE_PATH = PathFilter.projectPath + "web\\images\\Customer\\";
    public static final String CLIENT_CUSTOMER_IMAGE_PATH = PathFilter.projectPath + "web\\images\\Customer\\";
    public static final String ACCOUNT_IMAGE_PATH = PathFilter.projectPath + "web\\images\\Account\\";
    public static final String SERI_IMAGE_PATH = PathFilter.projectPath + "web\\images\\ProductSeri\\";
    public static final String NEWS_IMAGE_PATH = PathFilter.projectPath + "web\\images\\News\\";

    public static Account getLoggingInAccount(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        return (Account) session.getAttribute("account");
    }

    public static Customer getLoggingInCustomer(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        return (Customer) session.getAttribute("customer");
    }

    public static void setLoggingInCustomer(HttpServletRequest request, HttpServletResponse response, Customer c) {
        HttpSession session = request.getSession();
        session.setAttribute("customer", c);
    }

    public static Customer setLoggingInCustomer(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("customer") != null) {
            Customer c = new DaoCustomer().getCustomer(((Customer) session.getAttribute("customer")).getCustomerId());
            session.removeAttribute("customer");
            session.setAttribute("customer", c);
            return c;
        }

        return null;
    }

    public static void removeLoggingInAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") != null) {
            session.removeAttribute("account");
        }
    }

    public static double formatNumber(String value) {
        if (value == null || value.isEmpty()) {
            return -1;
        }

        if (value.contains(".")) {
            value = value.substring(0, value.indexOf("."));
        }

        String ret = "";

        if (value.contains(",")) {
            for (String s : value.split(",")) {
                ret += s;
            }
        } else {
            ret = value;
        }

        try {
            return Double.parseDouble(ret);
        } catch (Exception e) {
            System.out.println("formatNumber " + e.getMessage());
        }
        return -1;
    }

    public static String storeImage(HttpServletRequest request, String part, String pathTo) throws IOException, ServletException {
        Part filePart = request.getPart(part);
        // Get the filename from the Part header
//        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String fileName = UUID.randomUUID().toString() + "." + getFileExtension(filePart);

        if (fileName.isEmpty()) {
            return "";
        }
// Define the folder where you want to store the uploaded file
//D:/2023_SPRING/01_PRJ301/EOS_Admin/web/shared/images/CauHoi
        String uploadFolder = (pathTo);
//        System.out.println(getServletContext().getRealPath("/uploads"));
//        System.out.println(uploadFolder);
// Create a File object for the upload folder and the uploaded file
        File uploadDir = new File(uploadFolder);
//        File uploadFile = new File(uploadFolder + fileName);
// Check if the upload folder exists, if not create it
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

// Save the file to the uploads directory
        String filePath = uploadDir + File.separator + fileName;
//        System.out.println(filePath);
        filePart.write(filePath);
        return fileName;
    }

    public static String getFileExtension(Part part) {
        String fileName = part.getSubmittedFileName();
        if (fileName != null && !fileName.isEmpty()) {
            int lastDotIndex = fileName.lastIndexOf('.');
            if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
                return fileName.substring(lastDotIndex + 1);
            }
        }
        return "";
    }

    public static String getFileName(Part part) {
        String submittedFileName = part.getSubmittedFileName();
        if (submittedFileName != null) {
            return submittedFileName;
        }

        // Extract the filename from the content-disposition header as a fallback
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }

    public static String generateRandomString(String existingString, int length) {
        // Convert existing string to char array
        char[] chars = existingString.toCharArray();
        int existingStringLength = chars.length;

        // Random number generator
        Random random = new Random();

        // Generate random indices and retrieve characters
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(existingStringLength);
            char randomChar = chars[randomIndex];
            sb.append(randomChar);
        }

        // Convert StringBuilder to String
        return sb.toString();
    }

    public static boolean validatePhoneNumber(String input) {
        // Regex pattern for string validation
        String regex = "^0\\d{9}$";

        // Validate string using regex
        return input.matches(regex);
    }

    public static String md5Hash(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(value.getBytes());
            byte[] digest = md.digest();
            value = DatatypeConverter.printHexBinary(digest).toUpperCase();
            return value;
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("md5Hash " + ex.getMessage());
        }
        return "";
    }

    public static Date plusMonth(java.util.Date d, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static void noti(HttpServletRequest request, boolean flag, String mess) {
        HttpSession session = request.getSession();

        if (flag) {
            session.setAttribute("noti", mess);
            session.setAttribute("status", "1");
        } else {
            session.setAttribute("noti", mess);
            session.setAttribute("status", "-1");
        }
    }

    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    // create generateSecurePassword() method that find the secure password and returns it to the main() method  
    public static String generateSecurePassword() {  
          
        // create character rule for lower case  
        CharacterRule LCR = new CharacterRule(EnglishCharacterData.LowerCase);  
        // set number of lower case characters  
        LCR.setNumberOfCharacters(2);  
  
        // create character rule for upper case  
        CharacterRule UCR = new CharacterRule(EnglishCharacterData.UpperCase);  
        // set number of upper case characters  
        UCR.setNumberOfCharacters(2);  
  
        // create character rule for digit  
        CharacterRule DR = new CharacterRule(EnglishCharacterData.Digit);  
        // set number of digits  
        DR.setNumberOfCharacters(2);  
  
        // create character rule for lower case  
        CharacterData customSpecialCharacters = new CharacterData() {
            @Override
            public String getErrorCode() {
                return "CUSTOM_SPECIAL";
            }

            @Override
            public String getCharacters() {
                // Define your custom set of special characters here
                return "@#$%";
            }
        };
        
        CharacterRule SR = new CharacterRule(customSpecialCharacters);  
        // set number of special characters  
        SR.setNumberOfCharacters(2);  
          
        // create instance of the PasswordGenerator class   
        PasswordGenerator passGen = new PasswordGenerator();  
          
        // call generatePassword() method of PasswordGenerator class to get Passay generated password  
        String password = passGen.generatePassword(8, SR, LCR, UCR, DR);  
          
        // return Passay generated password to the main() method   
        return password;  
    }  
}
