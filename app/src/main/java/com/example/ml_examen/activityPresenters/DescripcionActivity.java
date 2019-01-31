package com.example.ml_examen.activityPresenters;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ml_examen.R;
import com.example.ml_examen.model.ProductDetail;

public class DescripcionActivity extends AppCompatActivity implements DescripcionContract.DetalleView {

    private static final String TAG = "DetailsActivity";
    private ProgressBar progressBar;
    private ViewGroup viewGroup;
    private DescripcionContract.presenter detallePresenter;
    TextView titulo, descripcion, descripciontitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);

        detallePresenter = new DescripcionPresenterImplementation(this, new GetProductoInteractorImplementation());
        initProgressBar();
        initElements();
        Log.d(TAG, "onCreate: Iniciado");
    }

    private void initElements() {
        //Método para inicializar los elementos visuales de la vista,
        //se llama al presenter para obtener los datos del detalle del producto
        titulo = findViewById(R.id.titulo_descp);
        descripcion = findViewById(R.id.text_descp);
        descripciontitle = findViewById(R.id.descp_title);
        if (getIntent().hasExtra("id")){
           detallePresenter.requestDetailsFromApi(getIntent().getStringExtra("id"));
        }
        //Se inicializa el groupView
        viewGroup = (ViewGroup) ((ViewGroup) (findViewById(android.R.id.content))).getChildAt(0);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    private void initProgressBar() {
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

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToView(ProductDetail detalle) {
        //Callback llamada cuando se retornan los datos de la API
        Log.d(TAG, "Response OK, setting data to view ");
        hideProgress();
        descripciontitle.setText(getResources().getString(R.string.descripci_n_del_producto));
        descripcion.setText(detalle.getDetalle());
        if (getIntent().hasExtra("title")){
            titulo.setText(getIntent().getStringExtra("title"));
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.d(TAG, "onResponseFailure: " + throwable.getMessage());
        //Obtener la vista actual del activity
        showSnackBar("Ha ocurrido un problema, verifique su conexión");
    }

    @Override
    public void onResponseFailure(String error) {
        Log.d(TAG, "onResponseFailure Server Error: " + error);
        showSnackBar("Algo no salió bien con nuestro servidor");
    }

    @Override
    public void showSnackBar(String s) {
        Log.d(TAG, "showSnackVar: " + s);
        Snackbar.make(viewGroup,s,Snackbar.LENGTH_SHORT)
                .show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        //implementacion de botón hacia atrás de toolbar
        Log.d(TAG, "Go bak to MainActivity");
        onBackPressed();
        return true;
    }
}
