package com.example.ml_examen.model;

import com.google.gson.annotations.SerializedName;

public class ProductDetail {
    @SerializedName("plain_text")
    private String detalle;

    public ProductDetail(String detalle) {
        this.detalle = detalle;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
