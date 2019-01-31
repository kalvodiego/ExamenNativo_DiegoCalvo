package com.example.ml_examen.activityPresenters;

import android.content.pm.ActivityInfo;

import com.example.ml_examen.model.Product;
import com.example.ml_examen.model.ProductDetail;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class GetProductoInteractorImplementationTest {

    GetProductoInteractorImplementation  interactor;
    MainActivity mainActivity;
    DescripcionActivity descripcionActivity;

    @Before
    public void setUp() throws Exception {
        interactor = new GetProductoInteractorImplementation();
    }

    @Test
    public void CheckStateAfterScreenRotation() {
        MainContract.GetProductInteractor.OnFinishdListener listener;
        listener = new MainContract.GetProductInteractor.OnFinishdListener() {
            @Override
            public void onFinished(ArrayList<Product> productArrayList) {
                mainActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                verify(mainActivity).setDataToRecyclerView(productArrayList);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        };
        interactor.getProductArrayList( listener , "Perro");
    }

    @Test
    public void VerifyshowDialogOnNotFoundProduct() {
        MainContract.GetProductInteractor.OnFinishdListener listener;
        listener = new MainContract.GetProductInteractor.OnFinishdListener() {
            @Override
            public void onFinished(ArrayList<Product> productArrayList) {
                verify(mainActivity).showSnackBar("Producto no encontrado");
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        };
        interactor.getProductArrayList( listener , "sajdkajsdhkjash");
    }

    @Test
    public void VerifySetDataToRecyclerView() {
        MainContract.GetProductInteractor.OnFinishdListener listener;
        listener = new MainContract.GetProductInteractor.OnFinishdListener() {
            @Override
            public void onFinished(ArrayList<Product> productArrayList) {
                verify(mainActivity).setDataToRecyclerView(productArrayList);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        };
        interactor.getProductArrayList( listener , "Perro");
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

    @Test
    public void detailsDataSetted() {
        DescripcionContract.GetProductInteractor.OnFinishedListener listener;
        listener = new DescripcionContract.GetProductInteractor.OnFinishedListener() {
            @Override
            public void onFinished(ProductDetail detail) {
               verify(descripcionActivity).setDataToView(detail);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        };
        interactor.getProductDetails(listener, "MLA692704565");
    }
}