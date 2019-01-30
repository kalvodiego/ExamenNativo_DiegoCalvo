package com.example.ml_examen.activityPresenters;

import com.example.ml_examen.model.Product;

import java.util.ArrayList;

//Clase con  la implementacion de los métodos de las interfaces implementadas,
public class MainPresenterImplementation implements MainContract.presenter, MainContract.GetProductInteractor.OnFinishdListener, RecyclerItemClickListener {

    private MainContract.MainView mainView;
    private MainContract.GetProductInteractor getProductInteractor;

    MainPresenterImplementation(MainContract.MainView mainView, MainContract.GetProductInteractor getProductInteractor) {
        this.mainView = mainView;
        this.getProductInteractor = getProductInteractor;
    }

    @Override
    public void clearData() {
        if (mainView != null){
            ArrayList<Product> emptyArray = new ArrayList<>();
            mainView.setDataToRecyclerView(emptyArray);
        }
    }


    @Override
    public void requestDataFromApi(String query) {
        if (mainView != null){
            mainView.showProgress();
        }
        //Se llama al interactor, encargado de obtener los datos de la API
        getProductInteractor.getProductArrayList(this, query);
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
