package com.example.ml_examen.activityPresenters;

import com.example.ml_examen.model.Product;

import java.util.ArrayList;

public interface MainContract {
    
    //Se llama cuando el usuario interactua con la vista
    interface presenter{

        void clearData();

        void requestDataFromApi(String query);
        
    }
    
    //Interfaz para comunicar la vista Main con metodos para mostrar/ocultar progressSpinner y para 
    //setear Data y mostrar fallos en la response.
    
    interface MainView {

        void showProgress();
        
        void hideProgress();
        
        void setDataToRecyclerView(ArrayList<Product> productArrayList);
        
        void onResponseFailure(Throwable throwable);

        void onResponseFailure(String error);

        void showSnackBar(String s);
    }
    
    //Get interactor, clase para oobtener data de la api
    
    interface GetProductInteractor {
        
        interface OnFinishdListener {
            void onFinished(ArrayList<Product> productArrayList);
            void onFailure(Throwable throwable);
        }
        
        void getProductArrayList(OnFinishdListener onFinishdListener, String query);
        
    }
    

}
