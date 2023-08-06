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
public class Account {

    private int accountId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private boolean gender;
    private Date dateOfBirth;
    private String phoneNumber;
    private String image;
    private boolean isDelete;
    private boolean accountType;
    private String salt;

    public Account() {
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

//    public Account(int accountId, String userName, String password) {
//        this.accountId = accountId;
//        this.userName = userName;
//        this.password = password;
//    }
//    public Account(String userName, String password, String firstName, String lastName, Date dateOfBirth, String phoneNumber, String image) {
//        this.userName = userName;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dateOfBirth = dateOfBirth;
//        this.phoneNumber = phoneNumber;
//        this.image = image;
//    }
//    public Account(int accountId, String userName, String password, String firstName, String lastName, Date dateOfBirth, String phoneNumber, String image) {
//        this.accountId = accountId;
//        this.userName = userName;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dateOfBirth = dateOfBirth;
//        this.phoneNumber = phoneNumber;
//        this.image = image;
//    }
    public Account(int accountId, String userName, String password, String firstName, String lastName, boolean gender, Date dateOfBirth, 
            String phoneNumber, String image, boolean isDelete, String salt) {
        this.accountId = accountId;
        this.userName = userName;
        setPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.isDelete = isDelete;
        this.salt = salt;
    }

    public boolean isAccountType() {
        return accountType;
    }

    public void setAccountType(boolean accountType) {
        this.accountType = accountType;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountID) {
        this.accountId = accountID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return getLastName() + " " + getFirstName();
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

}
