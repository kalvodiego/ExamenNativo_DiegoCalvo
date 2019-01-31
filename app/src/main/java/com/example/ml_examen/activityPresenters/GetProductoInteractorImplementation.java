package com.example.ml_examen.activityPresenters;

import android.util.Log;

import com.example.ml_examen.interface_directory.DataService;
import com.example.ml_examen.model.ProductDetail;
import com.example.ml_examen.model.ProductList;
import com.example.ml_examen.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProductoInteractorImplementation implements MainContract.GetProductInteractor, DescripcionContract.GetProductInteractor{

    @Override
    public void getProductArrayList(final OnFinishdListener onFinishdListener, String query) {

        //Handler para la interface de Retrofit
        DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);

        //Metodo para obtener los productos
        Call<ProductList> call = service.getProductsData(query);

        //Log de la URL llamada
        Log.wtf("URL Llamada: ", call.request().url() + "" );

        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                //Log de la Response exitosa
                Log.wtf("Response exitosa: ", response.message() + "" );
                ArrayList<ProductList> list = new ArrayList<>();
                onFinishdListener.onFinished( response.body().getProductsArrayList());
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                //Log de la Response erronea
                Log.wtf("Response erronea: ", t.getMessage());
                onFinishdListener.onFailure(t);
            }
        });

    }

    @Override
    public void getProductDetails(final OnFinishedListener onFinishdListener, String id) {
        //Handler para la interface de Retrofit
        DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);

        //Metodo para obtener los productos
        Call<ProductDetail> call = service.getProductDetail(id);

        //Log de la URL llamada
        Log.wtf("URL Llamada: ", call.request().url() + "" );

        call.enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {
               if (response.isSuccessful()) {
                   //Log de la Response exitosa
                   Log.wtf("Response exitosa: ", response.message() + "");
                   String res = response.body() != null ? response.body().getDetalle(): "";
                   ProductDetail productDetail = new ProductDetail(res);
                   onFinishdListener.onFinished(productDetail);
               }else{
                   //Log de response error
                   Log.wtf("Response erronea: ",  response.errorBody() + "");
               }
            }

            @Override
            public void onFailure(Call<ProductDetail> call, Throwable t) {
                //Log de la Response erronea
                Log.wtf("Response erronea: ", t.getMessage());
                onFinishdListener.onFailure(t);
            }
        });
    }
}
