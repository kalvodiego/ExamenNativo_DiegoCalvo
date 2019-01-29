package com.example.ml_examen.interface_directory;

import com.example.ml_examen.model.ProductDetail;
import com.example.ml_examen.model.ProductList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataService {

    //En esta clase describiremos los Endpoints de la API.
    //Obtener lista de productos buscados.
    @GET("sites/MLA/search")
    Call<ProductList> getProductsData(@Query("q") String query);

    //Obtener detalle del producto
    @GET("items/{id}/description")
    Call<ProductDetail> getProductDetail(@Path("id") String id);

}
