package com.example.ml_examen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ml_examen.R;
import com.example.ml_examen.activityPresenters.RecyclerItemClickListener;
import com.example.ml_examen.model.Product;

import java.util.ArrayList;
import java.util.List;

//Adapter para hacer bind de los datos con el Recycler View

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private Context mContext;
    private ArrayList<Product> dataList;
    private RecyclerItemClickListener recyclerItemClickListener;

    public ProductsAdapter(Context mContext, ArrayList<Product> dataList, RecyclerItemClickListener recyclerItemClickListener) {
        this.mContext = mContext;
        this.dataList = dataList;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @Override
    public ProductViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position, @NonNull List<Object> payloads) {
        holder.nombre.setText(dataList.get(position).getTitle());
        holder.condicion.setText(dataList.get(position).getCondicion().contentEquals("used")? "Usado" : "Nuevo");
        String price = "$"+dataList.get(position).getPrice();
        holder.precio.setText(price);
        ImageView imageView = holder.thumbnail;
        Glide.with(mContext).load((dataList.get(position).getThumbnail())).into(imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(dataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView nombre, precio, condicion;

        ProductViewHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.productThumbnail);
            nombre =  itemView.findViewById(R.id.text_nombre);
            precio =  itemView.findViewById(R.id.text_precio);
            condicion =  itemView.findViewById(R.id.text_condicion);
        }
    }

}
