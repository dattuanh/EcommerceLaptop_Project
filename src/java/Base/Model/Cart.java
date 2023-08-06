/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Cart {
    private double totalPrice;
    private List<CartItem> listItem;

    public Cart() {
    }

    public Cart(float totalPrice, List<CartItem> listItem) {
        this.totalPrice = totalPrice;
        this.listItem = listItem;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getListItem() {
        return listItem;
    }

    public void setListItem(List<CartItem> listItem) {
        this.listItem = listItem;
    }
    
    public Cart(List<CartItem> listItem) {
        this.listItem = listItem;
    }

    public double getTotalMoney() {
        double t = 0;
        for (CartItem item : listItem) {
            t += (item.getQuantity() * item.getProductSeri().getPrice());
        }
        return t;
    }

    public CartItem getItemById(int id) {
        for (CartItem item : listItem) {
            if (item.getProductSeri().getProductSeriId() == id) {
                return item;
            }
        }
        return null;
    }

    public boolean checkExit(int id) {
        for (CartItem item : listItem) {
            if (item.getProductSeri().getProductSeriId() == id) {
                return true;
            }
        }
        return false;
    }

    public void addItem(CartItem cartItem) {
        if (checkExit(cartItem.getProductSeri().getProductSeriId())) {
            CartItem oldItem = getItemById(cartItem.getProductSeri().getProductSeriId());
            oldItem.setQuantity(oldItem.getQuantity() + cartItem.getQuantity());
        } else {
            listItem.add(cartItem);
        }
    }
    
    private ProductSeri getProductSeriById(int id, List<ProductSeri> list){
        for (ProductSeri productSeri : list) {
            if(productSeri.getProductSeriId() == id){
                return productSeri;
            }
        }
        return null;
    }
    
    public Cart (String txt, List<ProductSeri> list){
        listItem = new ArrayList<>();
        try {
            if(txt != null && txt.length() != 0){
                String[] s = txt.split("-");
                for (String i : s) {
                    String[] n = i.split(":");
                    int id = Integer.parseInt(n[0]);
                    int quantity = Integer.parseInt(n[1]);
                    ProductSeri p = getProductSeriById(id, list);
                    CartItem item = new CartItem(p, quantity);
                    addItem(item);
                }
            }
        } catch (Exception e) {
            System.out.println("Can't Add to cart: " + e.getMessage());
        }
    }
    
    public int getQuantityByProductSeriId(int id){
        return getItemById(id).getQuantity();
    }
    
}
