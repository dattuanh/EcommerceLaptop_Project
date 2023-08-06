/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Giang Minh
 */
public class Formatter {

    // convert from string to java.UTIL.date
    public static Date formatDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            return formatter.parse(date);
        } catch (ParseException ex) {
            System.out.println("formatDate: " + ex.getMessage());
        }
        return null;
    }

    // convert from string to java.SQL.date
    public static java.sql.Date stringToSqlDate(String date) {
        return formatDate(formatDate(date));
    }

    // convert from "yyyy-MM-dd" to "dd-MM-yyyy"
    public static Date formatDateToDate(Date date) {
        String formatter1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date);
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String formatedDates[] = formatter1.split("-");
        String ret = "";
        for (int i = formatedDates.length - 1; i >= 0; i--) {
            ret += formatedDates[i] + "-";
        }
        try {
            return formatter2.parse(ret);
        } catch (ParseException ex) {
            System.out.println("formatDateToDate: " + ex.getMessage());
        }
        return null;
    }

    // convert from java.UTIL.date to java.SQL.date
    public static java.sql.Date formatDate(Date date) {
        if (date != null) {
            return new java.sql.Date(date.getTime());
        }
        return null;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        if (date != null) {
            return formatter.format(date);
        }
        return "";
    }

    public static String moneyFormat(double amount) {
        return String.format("%,.0f", amount);
    }

    public static double stringToDouble(String ret) {
        try {
            return Double.parseDouble(ret);
        } catch (Exception e) {
            System.out.println("stringToDouble: " + e.getMessage());
        }
        return -1;
    }

    public static Date getCurrentDate() {
        return new java.util.Date();
    }

    public static String createUserName(String firstName, String lastName) {
        return lastName.substring(0, 1).toUpperCase() + lastName.substring(1)
                + firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
    }
}
