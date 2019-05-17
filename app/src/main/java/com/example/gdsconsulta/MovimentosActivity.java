package com.example.gdsconsulta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gdsconsulta.itemDecoration.SimpleDividerItemDecoration;
import com.example.gdsconsulta.models.Empresa;
import com.example.gdsconsulta.models.Movimento;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovimentosActivity extends AppCompatActivity {

    final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
    final OkHttpClient.Builder okHttpClientBuilder = new  OkHttpClient.Builder();
    final Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(Constante.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson));
    final Retrofit retrofit = retrofitBuilder.client(okHttpClientBuilder.build()).build();
    final GDSInterfaceService gdsInterfaceService = retrofit.create(GDSInterfaceService.class);

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adaptador adapter;

    private EndlessRecyclerViewScrollListener scrollListener;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        layoutManager = new LinearLayoutManager(MovimentosActivity.this);
        recyclerView = findViewById(R.id.movs_recicle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        verListaMovimento();

    }
    private void verListaMovimento(){
        final Call<Empresa> chamadaEmpresa = gdsInterfaceService.getEmpresa(15420003466L,"YMON5BHOQM", "");
        chamadaEmpresa.enqueue(new Callback<Empresa>() {
            @Override
            public void onResponse(Call<Empresa> call, final Response<Empresa> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        List<Movimento> movimentos = response.body().getMovimentos();
                        adapter = new Adaptador(movimentos);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);

                    }
                });
            }
            @Override
            public void onFailure(Call<Empresa> call, Throwable t){
                Log.i("Erro Geral", "Erro: " + t.getMessage());
                Toast.makeText(getApplicationContext(), "Falha na Chamada dos repositórios: "
                                + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}