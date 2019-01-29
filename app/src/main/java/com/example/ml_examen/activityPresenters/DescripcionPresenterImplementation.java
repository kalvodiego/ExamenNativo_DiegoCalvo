package com.example.ml_examen.activityPresenters;

import com.example.ml_examen.model.ProductDetail;

public class DescripcionPresenterImplementation implements DescripcionContract.presenter, DescripcionContract.GetProductInteractor.OnFinishedListener {

    private DescripcionContract.DetalleView detalleView;
    private DescripcionContract.GetProductInteractor getDetailInteractor;

    public DescripcionPresenterImplementation(DescripcionContract.DetalleView detalleView, DescripcionContract.GetProductInteractor getDetailInteractor) {
        this.detalleView = detalleView;
        this.getDetailInteractor = getDetailInteractor;
    }

    @Override
    public void requestDetailsFromApi(String productId) {
        if (detalleView != null){
            detalleView.showProgress();
        }
        getDetailInteractor.getProductDetails(this, productId);
    }

    @Override
    public void onFinished(ProductDetail detail) {
        if (detalleView != null){
            detalleView.setDataToView(detail);
            detalleView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        if (detalleView != null){
            detalleView.onResponseFailure(throwable);
            detalleView.hideProgress();
        }
    }
}
