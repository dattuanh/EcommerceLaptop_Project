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
public class Image {
    private int imageId;
    private String imageName;
    private int fileSize;
    private String fileFormat;
    private String description;
    private String keywords;
    private String url;
    private Date createdDate;
    private int creator;

    public Image() {
    }

    public Image(int imageId, String imageName, int fileSize, String fileFormat, String description, String keywords, String url, Date createdDate, int creator) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.fileSize = fileSize;
        this.fileFormat = fileFormat;
        this.description = description;
        this.keywords = keywords;
        this.url = url;
        this.createdDate = createdDate;
        this.creator = creator;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }
    
}
