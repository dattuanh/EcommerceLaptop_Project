/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Model;

/**
 *
 * @author Giang Minh
 */
public class Color {
    private int colorId;
    private String colorName;
    private int quantity;

    public Color() {
    }

    public Color(int colorId, String colorName) {
        this.colorId = colorId;
        this.colorName = colorName;
    }
    
    public Color(int colorId, String colorName, int quantity) {
        this.colorId = colorId;
        this.colorName = colorName;
        this.quantity = quantity;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
