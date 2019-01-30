package com.example.ml_examen.activityPresenters;

import com.example.ml_examen.model.Product;
import com.example.ml_examen.model.ProductDetail;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GetProductoInteractorImplementationTest {

    GetProductoInteractorImplementation  interactor;

    @Before
    public void setUp() throws Exception {
        interactor = new GetProductoInteractorImplementation();
    }

    @Test
    public void getProductArrayListNotEmpty() {
        MainContract.GetProductInteractor.OnFinishdListener listener;
        listener = new MainContract.GetProductInteractor.OnFinishdListener() {
            @Override
            public void onFinished(ArrayList<Product> productArrayList) {
                assertFalse(productArrayList.isEmpty());
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        };
        interactor.getProductArrayList( listener , "Perro");
    }

    @Test
    public void ElementNotFoundIsEmpty(){
        MainContract.GetProductInteractor.OnFinishdListener listener;
        listener = new MainContract.GetProductInteractor.OnFinishdListener() {
            @Override
            public void onFinished(ArrayList<Product> productArrayList) {
                assertTrue(productArrayList.isEmpty());
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        };
        //Buscamos un nombre de producto que no existe
        interactor.getProductArrayList( listener , "sajdkajsdhkjash");
    }

    @Test
    public void getProductDetailsNotEmpty() {
        DescripcionContract.GetProductInteractor.OnFinishedListener listener;
        listener = new DescripcionContract.GetProductInteractor.OnFinishedListener() {
            @Override
            public void onFinished(ProductDetail detail) {
                assertNotNull(detail.getDetalle());
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        };
        interactor.getProductDetails(listener, "MLA692704565");
    }
}