package com.tijani.waterrefineryshirzadproject.models;

public class AverageResultProperties {
    private String id;
    private String date;

    public AverageResultProperties(String id, String date) {
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
