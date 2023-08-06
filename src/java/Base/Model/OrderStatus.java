/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Model;

/**
 *
 * @author Giang Minh
 */
public class OrderStatus {

    private int orderStatusId;
    private String orderStatusName;

    public OrderStatus() {
    }

    public OrderStatus(int orderStatusId, String orderStatusName) {
        this.orderStatusId = orderStatusId;
        this.orderStatusName = orderStatusName;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

}
