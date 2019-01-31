package com.example.ml_examen.activityPresenters;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.ml_examen.R;
import com.example.ml_examen.adapter.ProductsAdapter;
import com.example.ml_examen.model.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private RecyclerView.LayoutManager layoutManager;
    private ViewGroup viewGroup;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Context mContext;

    private MainContract.presenter presenter;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Iniciado");
        mContext = getApplicationContext();
        layoutManager = new LinearLayoutManager( MainActivity.this);
        //Instancia del presenter Implementado
        presenter = new MainPresenterImplementation(this, new GetProductoInteractorImplementation());
        setContentView(R.layout.activity_main);
        initializeToolbarAndRecyclerView();
        initProgressBar();
        initSearchBar();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<Product> productArrayList) {
        //Callback que es llamada cuando se retorna una lista de Productos.
        //Luego se setea al Adapter los datos a ser ser vinculados(Bind) en el recyclerView
        Log.d(TAG, "dataSetToRecyclerView: cantidad" + productArrayList.size());
        ProductsAdapter adapter = new ProductsAdapter(mContext,productArrayList, recyclerItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        //Se implementa este callback que es llamado en caso de que la llamada haya sido erronea.
        //Se le informa al usuario en forma de snackBar que algo no salió bien, y se guarda en log
        //el mensaje de la execpcion retornada.
        Log.d(TAG, "onResponseFailure: " + throwable.getMessage());
        showSnackBar("Ha ocurrido un problema, verifique su conexión");
    }

    @Override
    public void onResponseFailure(String error) {
        showSnackBar("Algo no salió bien con nuestro servidor");
    }

    @Override
    public void showSnackBar(String info) {
        Snackbar.make(viewGroup,info,Snackbar.LENGTH_SHORT)
                .show();
    }


    private void initializeToolbarAndRecyclerView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_view_product_list);
        recyclerView.setLayoutManager(layoutManager);

        //Se inicializa el group view para obtener la vista del la actual Activity
         viewGroup = (ViewGroup) ((ViewGroup) (findViewById(android.R.id.content)))
                                                        .getChildAt(0);
    }

    private void initSearchBar() {
        //Se inicia el Widget de busqueda que implement dos callback.
        SearchView  searchView = findViewById(R.id.searchView);
        searchView.setQueryHint(getString(R.string.query_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Cuando el usuario presiona buscar, se realiza el pedido de los datos al presenter
                Log.d(TAG, "onQuerySubmit -> se busca: " + query);
                presenter.requestDataFromApi(query);
                showProgress();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Cuando el texto cambia, o es eliminado, se limpia la lista.
                Log.d(TAG, "onQueryTextChange -> se reemplaza el texto por: " + newText + "largo: " + (!newText.isEmpty() ? newText.length() : ""));

                presenter.clearData();
                return false;
            }
        });
    }

    private void initProgressBar() {
        //Se inicializa el ProgressBar para ser mostrado en el centro de la pantalla, hasta que se obtengan los datos
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }

    private  RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(Product product) {
            //Click listener que abrirá una nueva actvity, con los detalles del producto seleccionado
            presenter.HandleclickOnProduct(product, mContext);
        }
    };

}
