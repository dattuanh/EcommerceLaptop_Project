/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Util;

import Base.Dal.DaoAccount;
import Base.Dal.DaoCustomer;
import Base.Model.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Giang Minh
 */
public class Validation {

    public static final String ACCOUNT_ROLE = "0";
    public static final String PRODUCTSERI_ROLE = "1";
    public static final String CATEGORY_ROLE = "2";
    public static final String CUSTOMER_ROLE = "3";
    public static final String NEWSGROUP_ROLE = "4";
    public static final String NEWS_ROLE = "5";
    public static final String ORDER_ROLE = "6";
    public static final String ERROR_ROLE = "7";
    public static final String WARRANTY_ROLE = "8";

    public static boolean roleCheck(HttpServletRequest req, HttpServletResponse res, String roleId) {
        HttpSession session = req.getSession(false);
        Account account = null;
        try {
            account = (Account) session.getAttribute("account");
        } catch (Exception e) {
            System.out.println("roleCheck: " + e.getMessage());
        }
        DaoAccount dao = new DaoAccount();
        List<String> accountRoles = dao.getAccountRoles(account);
        return accountRoles.contains(roleId);
    }

    public static boolean phoneNumberValidation(String str) {
        //(0/91): number starts with (0/91)  
        //[7-9]: starting of the number may contain a digit between 0 to 9  
        //[0-9]: then contains digits 0 to 9  
        Pattern ptrn = Pattern.compile("0[1-9][0-9]{8}");
        //the matcher() method creates a matcher that will match the given input against this pattern  
        Matcher match = ptrn.matcher(str);
        //returns a boolean value  
        return (match.find() && match.group().equals(str));
    }

    /**
     *
     * @param pass
     * @return Must have at least one numeric character Must have at least one
     * lowercase character Must have at least one uppercase character Must have
     * at least one special symbol among @#$% Password length should be between
     * 8 and 20 link: https://java2blog.com/validate-password-java/
     */
    public static boolean passwordValidation(String pass) throws Exception {
//        Pattern ptrn = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%])[a-zA-Z\\d@#$%]{8,20}$");
//        Matcher match = ptrn.matcher(pass);
//        //returns a boolean value  
//        return match.find();
        if (pass.length() < 8 || pass.length() > 20) {
            throw new Exception("Password length should be between 8 and 20");
        }

        if (!hasAtLeastOneNumericCharacter(pass)) {
            throw new Exception("Must have at least one numeric character");
        }

        if (!hasAtLeastOneLowercaseCharacter(pass)) {
            throw new Exception("Must have at least one lowercase character");
        }

        if (!hasAtLeastOneUppercaseCharacter(pass)) {
            throw new Exception("Must have at least one uppercase character");
        }

        if (!hasAtLeastOneSpecialSymbol(pass)) {
            throw new Exception("Must have at least one special symbol among @#$%");
        }
        return true;
    }

    public static boolean hasAtLeastOneNumericCharacter(String str) {
        // Loop through each character in the string
        for (char c : str.toCharArray()) {
            // Check if the character is a digit
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasAtLeastOneLowercaseCharacter(String str) {
        // Loop through each character in the string
        for (char c : str.toCharArray()) {
            // Check if the character is a digit
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasAtLeastOneUppercaseCharacter(String str) {
        // Loop through each character in the string
        for (char c : str.toCharArray()) {
            // Check if the character is a digit
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasAtLeastOneSpecialSymbol(String str) {
        // Define the set of special symbols to check against
        String specialSymbols = "@#$%";

        // Loop through each character in the string
        for (char c : str.toCharArray()) {
            // Check if the character is a special symbol
            if (specialSymbols.contains(Character.toString(c))) {
                return true;
            }
        }
        return false;
    }

//    public static String usernameGeneration(String firstName, String lastName) {
//        String username = lastName.substring(0, 1).toUpperCase() + lastName.substring(1)
//                + firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
//        List<String> allUsernames = new DaoCustomer().getAllCustomerUserName();
//        while (true) {
//            if (allUsernames.contains(username)) {
//                
//            }
//        }
//    }
}
