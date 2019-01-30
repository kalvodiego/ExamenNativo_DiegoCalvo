package com.example.ml_examen.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ProductList {

    @SerializedName("results")
    private ArrayList<Product> products;

    public ArrayList<Product> getProductsArrayList(){
        return products;
    }

    public void setProductsArrayList(ArrayList<Product> productsArrayList){
        this.products = productsArrayList;
    }
}
