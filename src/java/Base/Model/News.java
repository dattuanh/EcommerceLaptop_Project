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
public class News {

    private int newId;
    private NewsGroup newsGroupID;
    private String newTitle;
    private String newsHeading;
    private String newsImage;
    private String newsContent;
    private Date createdDate;
    private Date modifiedDate;
    private Account createBy;
    private Account modifiedBy;
    private String modifiedHistory;
    private boolean isDelete;
    private boolean isPresent;
    private boolean isSlider;

    public News() {
    }

    public News(NewsGroup newsGroupID, String newTitle, String newsHeading, String newsImage, String newsContent, Date createdDate,
            Date modifiedDate, Account createBy, Account modifiedBy, String modifiedHistory, boolean isDelete, boolean isPresent, boolean isSlider) {
        this.newsGroupID = newsGroupID;
        this.newTitle = newTitle;
        this.newsHeading = newsHeading;
        this.newsImage = newsImage;
        this.newsContent = newsContent;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createBy = createBy;
        this.modifiedBy = modifiedBy;
        this.modifiedHistory = modifiedHistory;
        this.isDelete = isDelete;
        this.isPresent = isPresent;
        this.isSlider = isSlider;
    }

    public News(int newId, NewsGroup newsGroupID, String newTitle, String newsHeading, String newsImage, String newsContent,
            Date modifiedDate, Account modifiedBy, String modifiedHistory, boolean isDelete, boolean isPresent, boolean isSlider) {
        this.newId = newId;
        this.newsGroupID = newsGroupID;
        this.newTitle = newTitle;
        this.newsHeading = newsHeading;
        this.newsImage = newsImage;
        this.newsContent = newsContent;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
        this.modifiedHistory = modifiedHistory;
        this.isDelete = isDelete;
        this.isPresent = isPresent;
        this.isSlider = isSlider;
    }

    public News(int newId, NewsGroup newsGroupID, String newTitle, String newsHeading,
            String newsImage, String newsContent, Date createdDate, Date modifiedDate, Account createBy,
            Account modifiedBy, String modifiedHistory, boolean isDelete, boolean isPresent, boolean isSlider) {
        this.newId = newId;
        this.newsGroupID = newsGroupID;
        this.newTitle = newTitle;
        this.newsHeading = newsHeading;
        this.newsImage = newsImage;
        this.newsContent = newsContent;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createBy = createBy;
        this.modifiedBy = modifiedBy;
        this.modifiedHistory = modifiedHistory;
        this.isDelete = isDelete;
        this.isPresent = isPresent;
        this.isSlider = isSlider;

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

    public NewsGroup getNewsGroupID() {
        return newsGroupID;
    }

    public void setNewsGroupID(NewsGroup newsGroupID) {
        this.newsGroupID = newsGroupID;
    }

    public int getNewId() {
        return newId;
    }

    public void setNewId(int newId) {
        this.newId = newId;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public String getNewsHeading() {
        return newsHeading;
    }

    public void setNewsHeading(String newsHeading) {
        this.newsHeading = newsHeading;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
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

    public Account getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Account createBy) {
        this.createBy = createBy;
    }

    public boolean isIsSlider() {
        return isSlider;
    }

    public void setIsSlider(boolean isSlider) {
        this.isSlider = isSlider;
    }

    public Account getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Account modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public boolean isIsPresent() {
        return isPresent;
    }

    public void setIsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

}
