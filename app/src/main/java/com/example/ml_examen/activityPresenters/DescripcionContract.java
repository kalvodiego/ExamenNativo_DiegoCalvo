package com.example.ml_examen.activityPresenters;

import com.example.ml_examen.model.ProductDetail;

public interface DescripcionContract {

    interface presenter {
        //Presenter para obtener datos del detalle del producto
        void requestDetailsFromApi(String productId);
    }

    interface DetalleView{

        void showProgress();

        void hideProgress();

        void setDataToView(ProductDetail detalle);

        void onResponseFailure(Throwable throwable);

        void onResponseFailure(String error);

        void showSnackBar(String s);

    }

    interface GetProductInteractor {

        interface  OnFinishedListener {
            void onFinished(ProductDetail detail);
            void  onFailure(Throwable throwable);
        }

        void getProductDetails(OnFinishedListener onFinishdListener, String query);
    }

}
