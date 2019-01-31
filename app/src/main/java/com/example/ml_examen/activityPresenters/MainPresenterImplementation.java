package com.example.ml_examen.activityPresenters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.ml_examen.model.Product;

import java.util.ArrayList;

//Clase con  la implementacion de los métodos de las interfaces implementadas,
public class MainPresenterImplementation implements MainContract.presenter, MainContract.GetProductInteractor.OnFinishdListener, RecyclerItemClickListener {

    private static final String TAG = "Main_Presenter";
    private MainContract.MainView mainView;
    private MainContract.GetProductInteractor getProductInteractor;


    MainPresenterImplementation(MainContract.MainView mainView, MainContract.GetProductInteractor getProductInteractor) {
        this.mainView = mainView;
        this.getProductInteractor = getProductInteractor;
    }

    @Override
    public void clearData() {
        if (mainView != null){
            Log.d(TAG, "-> Clear Data");
            ArrayList<Product> emptyArray = new ArrayList<>();
            mainView.setDataToRecyclerView(emptyArray);
        }
    }


    @Override
    public void requestDataFromApi(String query) {
        if (mainView != null){
            mainView.showProgress();
        }
        Log.d(TAG, "-> Get Data from API: " + query);
        //Se llama al interactor, encargado de obtener los datos de la API
        getProductInteractor.getProductArrayList(this, query);
    }

    @Override
    public void HandleclickOnProduct(Product product, Context context) {
        Log.d(TAG, "-> onClick: click en: " + product.getId());
        navigateToDetailsActivity(product, context);
    }

    private void navigateToDetailsActivity(Product product, Context context) {
        Log.d(TAG, "-> Navigate to: Detalle Activity");
        Intent intent = new Intent(context, DescripcionActivity.class);
        //Se agregan datos del producto seleccionado que se envian a la nueva Activity
        intent.putExtra("id", product.getId());
        intent.putExtra("title", product.getTitle());
        context.startActivity(intent);
    }

    @Override
    public void onFinished(ArrayList<Product> productArrayList) {
        if (mainView != null){
            if(productArrayList.size() == 0){
                //Si la lista está vacia, mostrar mensaje de que no se encontrarn productos
                //con esa descripción
                mainView.showSnackBar("No se encontraron productos con los datos ingresados");
            }
            mainView.setDataToRecyclerView(productArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        if (mainView != null){
            mainView.onResponseFailure(throwable);
            mainView.hideProgress();
        }
    }

    @Override
    public void onItemClick(Product product) {
    }
}
