/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Model;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Product {
    private int productId;
    private ProductSeri productSerieId;
    private boolean status;
    private String batchSerial;
    private Date createdDate;
    private Date modifiedDate;
    private Account createdBy;
    private Account modifiedBy;
    private String modifiedHistory;
    private Warranty warranty;
    private boolean isDelete;

    public Product() {
    }

    public Product(ProductSeri productSerieId) {
        this.productSerieId = productSerieId;
    }

    public Product(ProductSeri productSerieId, String batchSerial, Date createdDate, Account createdBy) {
        this.productSerieId = productSerieId;
        this.batchSerial = batchSerial;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Product(int productId, ProductSeri productSerieId, boolean status, String batchSerial, Date modifiedDate, Account modifiedBy) {
        this.productId = productId;
        this.productSerieId = productSerieId;
        this.status = status;
        this.batchSerial = batchSerial;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }

    public Product(int productId, ProductSeri productSerieId, Date modifiedDate, Account modifiedBy) {
        this.productId = productId;
        this.productSerieId = productSerieId;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }
    
    
    public Product(int productId, ProductSeri productSerieId, boolean status) {
        this.productId = productId;
        this.productSerieId = productSerieId;
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public ProductSeri getProductSerieId() {
        return productSerieId;
    }

    public void setProductSerieId(ProductSeri productSerieId) {
        this.productSerieId = productSerieId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBatchSerial() {
        return batchSerial;
    }

    public void setBatchSerial(String batchSerial) {
        this.batchSerial = batchSerial;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
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

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Warranty getWarranty() {
        return warranty;
    }

    public void setWarranty(Warranty warranty) {
        this.warranty = warranty;
    }   

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{");
        sb.append("productId=").append(productId);
        sb.append(", productSerieId=").append(productSerieId);
        sb.append(", status=").append(status);
        sb.append(", batchSerial=").append(batchSerial);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", modifiedDate=").append(modifiedDate);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", modifiedBy=").append(modifiedBy);
        sb.append(", modifiedHistory=").append(modifiedHistory);
        sb.append(", warranty=").append(warranty);
        sb.append(", isDelete=").append(isDelete);
        sb.append('}');
        return sb.toString();
    }
    
    
}
