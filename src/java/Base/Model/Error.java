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
public class Error {

    private int errorId;
    private String errorMessage;
    private Date createdDate;
    private Date modifiedDate;
    private Account createdBy;
    private Account modifiedBy;
    private String modifiedHistory;
    private String content;
    private boolean isDelete;
    private Date repairDate;
    private double money;
    private int repairStatus;
    private Product repairProduct;

    public Error() {
    }

//    // for updating error
    public Error(int errorId, String errorMessage, Date modifiedDate, Account modifiedBy, String modifiedHistory, String content,
            Date repairDate, double money, int repairStatus, Product repairProduct) {
        this.errorId = errorId;
        this.errorMessage = errorMessage;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
        this.modifiedHistory = modifiedHistory;
        this.content = content;
        this.repairDate = repairDate;
        this.money = money;
        this.repairStatus = repairStatus;
        this.repairProduct = repairProduct;
    }

    public Error(int errorId, String errorMessage, Date createdDate, Date modifiedDate, Account createdBy, Account modifiedBy,
            String modifiedHistory, String content, boolean isDelete, Date repairDate, double money, int repairStatus, Product repairProduct) {
        this.errorId = errorId;
        this.errorMessage = errorMessage;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.modifiedHistory = modifiedHistory;
        this.content = content;
        this.isDelete = isDelete;
        this.repairDate = repairDate;
        this.money = money;
        this.repairStatus = repairStatus;
        this.repairProduct = repairProduct;
    }

    public Product getRepairProduct() {
        return repairProduct;
    }

    public void setRepairProduct(Product repairProduct) {
        this.repairProduct = repairProduct;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(int repairStatus) {
        this.repairStatus = repairStatus;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public int getErrorId() {
        return errorId;
    }

    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
