package com.tijani.waterrefineryshirzadproject.models;

public class ShekayatProperties {
    private String id;
    private String officeName;
    private String date;
    private String subject;
    private String img;

    public ShekayatProperties(String id, String officeName, String date, String subject,String img) {
        this.id = id;
        this.officeName = officeName;
        this.date = date;
        this.subject = subject;
        this.img=img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
