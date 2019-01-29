package com.example.ml_examen.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.mercadolibre.com";

    /*Se crea una instancia del objeto Retrofit
      Inicialización perezosa */
    public static Retrofit getRetrofitInstance(){

        if (retrofit == null){
          retrofit = new retrofit2.Retrofit.Builder()
                            .baseUrl(BASE_URL)

                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        }
        return retrofit;

    }

}
