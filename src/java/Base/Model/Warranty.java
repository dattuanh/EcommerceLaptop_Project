/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Model;

import java.util.Date;

/**
 *
 * @author MSI
 */
public class Warranty {

    private int warrantyId;
    private Product productId;
    private Error errorId;
    private Date warrantyDate;
    private boolean status;
    private Date createdDate;
    private Date modifiedDate;
    private Account createdBy;
    private Account modifiedBy;
    private String modifiedHistory;
    private boolean isDelete;
    private String content;
    private int warrantyTime;
    private double warrantyPrice;

    public Warranty() {
    }

//    public Warranty(Product productId, Error errorId, Date warrantyDate, boolean status, Date createdDate, Date modifiedDate, Account createdBy, Account modifiedBy, String modifiedHistory, boolean isDelete, String content) {
//        this.productId = productId;
//        this.errorId = errorId;
//        this.warrantyDate = warrantyDate;
//        this.status = status;
//        this.createdDate = createdDate;
//        this.modifiedDate = modifiedDate;
//        this.createdBy = createdBy;
//        this.modifiedBy = modifiedBy;
//        this.modifiedHistory = modifiedHistory;
//        this.isDelete = isDelete;
//        this.content = content;
//    }
//
//    public Warranty(int warrantyId, Product productId, Error errorId, Date warrantyDate, boolean status, Date modifiedDate, Account modifiedBy, String modifiedHistory, String content) {
//        this.warrantyId = warrantyId;
//        this.productId = productId;
//        this.errorId = errorId;
//        this.warrantyDate = warrantyDate;
//        this.status = status;
//        this.modifiedDate = modifiedDate;
//        this.modifiedBy = modifiedBy;
//        this.modifiedHistory = modifiedHistory;
//        this.content = content;
//    }

//    public Warranty(int warrantyId, Product productId, Error errorId, Date warrantyDate, boolean status, Date createdDate, Date modifiedDate, Account createdBy, Account modifiedBy, String modifiedHistory, boolean isDelete, String content) {
//        this.warrantyId = warrantyId;
//        this.productId = productId;
//        this.errorId = errorId;
//        this.warrantyDate = warrantyDate;
//        this.status = status;
//        this.createdDate = createdDate;
//        this.modifiedDate = modifiedDate;
//        this.createdBy = createdBy;
//        this.modifiedBy = modifiedBy;
//        this.modifiedHistory = modifiedHistory;
//        this.isDelete = isDelete;
//        this.content = content;
//    }

    public Warranty(int warrantyId, Product productId, Error errorId, Date warrantyDate, boolean status, Date createdDate, Date modifiedDate, Account createdBy, Account modifiedBy, String modifiedHistory, boolean isDelete, String content, int warrantyTime, double warrantyPrice) {
        this.warrantyId = warrantyId;
        this.productId = productId;
        this.errorId = errorId;
        this.warrantyDate = warrantyDate;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.modifiedHistory = modifiedHistory;
        this.isDelete = isDelete;
        this.content = content;
        this.warrantyTime = warrantyTime;
        this.warrantyPrice = warrantyPrice;
    }

    public int getWarrantyTime() {
        return warrantyTime;
    }

    public void setWarrantyTime(int warrantyTime) {
        this.warrantyTime = warrantyTime;
    }

    public double getWarrantyPrice() {
        return warrantyPrice;
    }

    public void setWarrantyPrice(double warrantyPrice) {
        this.warrantyPrice = warrantyPrice;
    }
    
    
    
    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public int getWarrantyId() {
        return warrantyId;
    }

    public void setWarrantyId(int warrantyId) {
        this.warrantyId = warrantyId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Error getErrorId() {
        return errorId;
    }

    public void setErrorId(Error errorId) {
        this.errorId = errorId;
    }

    public Date getWarrantyDate() {
        return warrantyDate;
    }

    public void setWarrantyDate(Date warrantyDate) {
        this.warrantyDate = warrantyDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Account getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Account modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedHistory() {
        return modifiedHistory;
    }

    public void setModifiedHistory(String modifiedHistory) {
        this.modifiedHistory = modifiedHistory;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
