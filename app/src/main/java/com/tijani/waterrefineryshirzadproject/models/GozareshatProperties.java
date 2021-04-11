package com.tijani.waterrefineryshirzadproject.models;

public class GozareshatProperties {
    private String id;
    private String date;
    private String nemonehLocation;
    private String parametrType;
    private String nemonehNumber;

    public GozareshatProperties(String id, String date, String nemonehLocation, String parametrType, String nemonehNumber) {
        this.id = id;
        this.date = date;
        this.nemonehLocation = nemonehLocation;
        this.parametrType = parametrType;
        this.nemonehNumber = nemonehNumber;
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

    public String getNemonehLocation() {
        return nemonehLocation;
    }

    public void setNemonehLocation(String nemonehLocation) {
        this.nemonehLocation = nemonehLocation;
    }

    public String getParametrType() {
        return parametrType;
    }

    public void setParametrType(String parametrType) {
        this.parametrType = parametrType;
    }

    public String getNemonehNumber() {
        return nemonehNumber;
    }

    public void setNemonehNumber(String nemonehNumber) {
        this.nemonehNumber = nemonehNumber;
    }
}
