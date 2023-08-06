/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Model;

/**
 *
 * @author ADMIN
 */
public class CartItem {
    private ProductSeri productSeri;
    private int quantity;

    public CartItem() {
    }

    public CartItem(ProductSeri productSeri, int quantity) {
        this.productSeri = productSeri;
        this.quantity = quantity;
    }

    public ProductSeri getProductSeri() {
        return productSeri;
    }

    public void setProductSeri(ProductSeri productSeri) {
        this.productSeri = productSeri;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
