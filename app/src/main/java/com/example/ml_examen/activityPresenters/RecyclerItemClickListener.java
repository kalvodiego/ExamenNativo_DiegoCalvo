package com.example.ml_examen.activityPresenters;

import com.example.ml_examen.model.Product;

public interface RecyclerItemClickListener {
    //Interface para capturar el click en un item del Recycler View
    void onItemClick(Product product);

}
