package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "file_url")
    private String fileUrl;

    private String country;

    private String position;

    @Column(name = "uploader_name")
    private String uploaderName;

    @Column(name = "bjs_take_photo")
    private Date bjsTakePhoto;

    private String coordinates;

    private String scale;

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