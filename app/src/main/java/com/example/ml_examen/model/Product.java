package com.example.ml_examen.model;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    private  String id;
    @SerializedName("site_id")
    private  String site_id;
    @SerializedName("title")
    private  String title;
    @SerializedName("price")
    private  String price;
    @SerializedName("thumbnail")
    private  String thumbnail;
    @SerializedName("condition")
    private String condicion;


    public Product(String id, String site_id, String title, String price, String thumbnail, String condicion) {
        this.id = id;
        this.site_id = site_id;
        this.title = title;
        this.price = price;
        this.thumbnail = thumbnail;
        this.condicion = condicion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
}
