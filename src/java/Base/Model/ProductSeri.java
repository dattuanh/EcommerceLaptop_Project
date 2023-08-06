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
public class ProductSeri {

    private int productSeriId;
    private String productSeriName;
    private double price;
    private String description;
    private String imagePreview;
    private Category categoryId;
    private String manufacturer;
    private int warrantyTime;
    private String size;
    private Color color;
    private Date createdDate;
    private Date modifiedDate;
    private Account createdBy;
    private Account modifiedBy;
    private String modifiedHistory;
    private boolean isDelete;
    private int stock;

    public ProductSeri() {
    }

    public ProductSeri(int productSeriId, String productSeriName, double price, String description, String imagePreview, Category categoryId, String manufacturer, int warrantyTime, String size, Color color, Date createdDate, Date modifiedDate, Account createdBy, Account modifiedBy, String modifiedHistory, boolean isDelete) {
        this.productSeriId = productSeriId;
        this.productSeriName = productSeriName;
        this.price = price;
        this.description = description;
        this.imagePreview = imagePreview;
        this.categoryId = categoryId;
        this.manufacturer = manufacturer;
        this.warrantyTime = warrantyTime;
        this.size = size;
        this.color = color;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.modifiedHistory = modifiedHistory;
        this.isDelete = isDelete;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public int getProductSeriId() {
        return productSeriId;
    }

    public void setProductSeriId(int productSeriId) {
        this.productSeriId = productSeriId;
    }

    public String getProductSeriName() {
        return productSeriName;
    }

    public void setProductSeriName(String productSeriName) {
        this.productSeriName = productSeriName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePreview() {
        return imagePreview;
    }

    public void setImagePreview(String imagePreview) {
        this.imagePreview = imagePreview;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getWarrantyTime() {
        return warrantyTime;
    }

    public void setWarrantyTime(int warrantyTime) {
        this.warrantyTime = warrantyTime;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    @Override
    public String toString() {
        return "ProductSeri{" + "productSeriId=" + productSeriId + ", productSeriName=" + productSeriName + ", price=" + price + ", description=" + description + ", " + ", categoryId=" + categoryId + ", manufacturer=" + manufacturer + ", " + ", warrantyTime=" + warrantyTime + ", size=" + size + ", color=" + color + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", modifiedHistory=" + modifiedHistory + ", isDelete=" + isDelete + '}';
    }
}
