/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Model;

import java.util.Date;

/**
 *
 * @author Giang Minh
 */
public class Customer {

    private int customerId;
    private String firstName;
    private String lastName;
    private boolean gender;
    private String email;
    private String userName;
    private String password;
    private String address;
    private String phone;
    private Date createdDate;
    private String image;
    private String modifiedHistory;
    private boolean isDelete;

    public Customer() {
    }

    public Customer(int customerId, String firstName, String lastName, boolean gender, String email, String userName, String password,
            String address, String phone, Date createdDate, String image, String modifiedHistory, boolean isDelete) throws Exception {
        this.customerId = customerId;
        setFirstName(firstName);
        setLastName(lastName);
        this.gender = gender;
        this.email = email;
        setUserName(userName);
        this.password = password;
        this.address = address;
        setPhone(phone);
        this.createdDate = createdDate;
        this.image = image;
        this.modifiedHistory = modifiedHistory;
        this.isDelete = isDelete;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int CustomerID) {
        this.customerId = CustomerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {
        if (firstName != null && !firstName.isEmpty()) {
            this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase().trim();
        } else {
            throw new Exception("Firstname must be within 1-24 chars");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception {
        if (lastName != null && !lastName.isEmpty()) {
            this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase().trim();
        } else {
            throw new Exception("Lastname must be within 1-24 chars");
        }
    }

    public String getFullName() {
        return getLastName() + " " + getFirstName();
    }

    public String getEmail() {
        if (email == null) {
            return "";
        }
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        if (userName == null) {
            return "";
        }
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        if (password == null) {
            return "";
        }
        return password;
    }

    public void setPassword(String password) {
//        if (!Base.Util.Validation.passwordValidation(password)) {
//            throw new Exception("Must have at least one numeric character\n"
//                    + "Must have at least one lowercase character\n"
//                    + "Must have at least one uppercase character\n"
//                    + "Must have at least one special symbol among @#$%\n"
//                    + "Password length should be between 8 and 20");
//        }
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        if (phone == null) {
            return "";
        }
        return phone;
    }

    // phone number is allowed to be empty
    // => phone is not empty => Validation
    public void setPhone(String phone) throws Exception {
        if (phone != null) {
            if (!"".equals(phone) && !Base.Util.Validation.phoneNumberValidation(phone)) {
                throw new Exception("Invalid phone number");
            }
        }
        this.phone = phone;
    }

}
