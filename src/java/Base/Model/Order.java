/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Order {

    private int orderId;
    private Customer customer;
    private Date orderDate;
    private double totalPrice;
    private OrderStatus status;
    private Date paymentDate;
    private String paymentMethod;
    private double cashReceive;
    private double cashBack;
    private String address;
    private Date modifiedDate;
    private Account modifiedAccount;
    private String modifiedHistory;

    public Order() {
    }
    
    public Order(Customer customer, Date orderDate, double totalPrice, OrderStatus status, Date paymentDate, String paymentMethod, double cashReceive, double cashBack, String address, Date modifiedDate, Account modifiedAccount, String modifiedHistory) {
        this.customer = customer;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.cashReceive = cashReceive;
        this.cashBack = cashBack;
        this.address = address;
        this.modifiedDate = modifiedDate;
        this.modifiedAccount = modifiedAccount;
        this.modifiedHistory = modifiedHistory;
    }

    public Order(int orderId, Customer customer, Date orderDate, double totalPrice, OrderStatus status, Date paymentDate, String paymentMethod, double cashReceive, double cashBack, String address, Date modifiedDate, Account modifiedAccount, String modifiedHistory) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.cashReceive = cashReceive;
        this.cashBack = cashBack;
        this.address = address;
        this.modifiedDate = modifiedDate;
        this.modifiedAccount = modifiedAccount;
        this.modifiedHistory = modifiedHistory;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Account getModifiedAccount() {
        return modifiedAccount;
    }

    public void setModifiedAccount(Account modifiedAccount) {
        this.modifiedAccount = modifiedAccount;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Account modifiedAccount() {
        return modifiedAccount;
    }

    public void modifiedAccount(Account modifiedAccount) {
        this.modifiedAccount = modifiedAccount;
    }

    public String getModifiedHistory() {
        return modifiedHistory;
    }

    public void setModifiedHistory(String modifiedHistory) {
        this.modifiedHistory = modifiedHistory;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
//        return Util.Formatter.dateToString(orderDate);
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }



    public Date getPaymentDate() {
//        return Util.Formatter.dateToString(paymentDate);
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getCashReceive() {
        return cashReceive;
    }

    public void setCashReceive(double cashReceive) {
        this.cashReceive = cashReceive;
    }

    public double getCashBack() {
        return cashBack;
    }

    public void setCashBack(double cashBack) {
        this.cashBack = cashBack;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
