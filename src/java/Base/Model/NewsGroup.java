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
public class NewsGroup {

    private int newsGroupID;
    private String newsGroupName;
    private Date createdDate;
    private Date modifiedDate;
    private Account createdBy;
    private Account modifiedBy;
    private String modifiedHistory;
    private boolean isDelete;

    public NewsGroup() {
    }
    
    public NewsGroup(int newsGroupID, String newsGroupName, Date modifiedDate, Account modifiedBy, String modifiedHistory, boolean isDelete) {
         this.newsGroupID = newsGroupID;
        this.newsGroupName = newsGroupName;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
        this.modifiedHistory = modifiedHistory;
        this.isDelete = isDelete;
    }

    public NewsGroup(String newsGroupName, Date createdDate, Date modifiedDate, Account createdBy, Account modifiedBy, String modifiedHistory, boolean isDelete) {
        this.newsGroupName = newsGroupName;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.modifiedHistory = modifiedHistory;
        this.isDelete = isDelete;
    }

    public NewsGroup(int newsGroupID, String newsGroupName, Date createdDate, Date modifiedDate, Account createdBy, Account modifiedBy, String modifiedHistory, boolean isDelete) {
        this.newsGroupID = newsGroupID;
        this.newsGroupName = newsGroupName;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.modifiedHistory = modifiedHistory;
        this.isDelete = isDelete;
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

    public int getNewsGroupID() {
        return newsGroupID;
    }

    public void setNewsGroupID(int newsGroupID) {
        this.newsGroupID = newsGroupID;
    }

    public String getNewsGroupName() {
        return newsGroupName;
    }

    public void setNewsGroupName(String newsGroupName) {
        this.newsGroupName = newsGroupName;
    }

}
