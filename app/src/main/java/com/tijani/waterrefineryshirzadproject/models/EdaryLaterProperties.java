package com.tijani.waterrefineryshirzadproject.models;

public class EdaryLaterProperties {
    private String id;
    private String shomarehName;
    private String date;
    private String registrant;
    private String subject;

    public EdaryLaterProperties(String id, String shomarehName, String date, String registrant, String subject) {
        this.id = id;
        this.shomarehName = shomarehName;
        this.date = date;
        this.registrant = registrant;
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShomarehName() {
        return shomarehName;
    }

    public void setShomarehName(String shomarehName) {
        this.shomarehName = shomarehName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
