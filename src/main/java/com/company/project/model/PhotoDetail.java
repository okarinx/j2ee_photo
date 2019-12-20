package com.company.project.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class PhotoDetail {
    private Integer id;

    private String displayName;

    private String fileUrl;

    public PhotoDetail(Photo photo) {
        this.id = photo.getId();
        this.displayName = photo.getDisplayName();
        this.fileUrl = photo.getFileUrl();
        this.country = photo.getCountry();
        this.position = photo.getPosition();
        this.uploaderName = photo.getUploaderName();
        this.bjsTakePhoto = photo.getBjsTakePhoto();
        this.coordinates = photo.getCoordinates();
        this.scale = photo.getScale();
    }

    private String country;

    private String position;

    private String uploaderName;

    private Date bjsTakePhoto;

    private String coordinates;

    private String scale;

    private boolean favorite;

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return display_name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return file_url
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * @param fileUrl
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return uploader_name
     */
    public String getUploaderName() {
        return uploaderName;
    }

    /**
     * @param uploaderName
     */
    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }

    /**
     * @return bjs_take_photo
     */
    public Date getBjsTakePhoto() {
        return bjsTakePhoto;
    }

    /**
     * @param bjsTakePhoto
     */
    public void setBjsTakePhoto(Date bjsTakePhoto) {
        this.bjsTakePhoto = bjsTakePhoto;
    }

    /**
     * @return coordinates
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return scale
     */
    public String getScale() {
        return scale;
    }

    /**
     * @param scale
     */
    public void setScale(String scale) {
        this.scale = scale;
    }
}
