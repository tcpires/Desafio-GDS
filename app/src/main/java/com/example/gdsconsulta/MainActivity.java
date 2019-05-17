package com.example.gdsconsulta;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gdsconsulta.models.Empresa;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
    final OkHttpClient.Builder okHttpClientBuilder = new  OkHttpClient.Builder();
    final Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(Constante.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson));
    final Retrofit retrofit = retrofitBuilder.client(okHttpClientBuilder.build()).build();
    final GDSInterfaceService gdsInterfaceService = retrofit.create(GDSInterfaceService.class);

    private Empresa empresa = new Empresa();
    TextView nomeEmpresaTextView = null;
    TextView nomeClienteTextView = null;
    TextView saldoCartãoTextView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomeEmpresaTextView = findViewById(R.id.nome_empresa);
        nomeClienteTextView = findViewById(R.id.nome_cliente);
        saldoCartãoTextView = findViewById(R.id.cifrao);

        verListaEmpresaECliente();

        Button btnBuscar = findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MovimentosActivity.class);
                startActivity(intent);
            //
            }
        });
    }
    private void verListaEmpresaECliente(){
        final Call<Empresa> chamadaEmpresa = gdsInterfaceService.getEmpresa(15420003466L,"YMON5BHOQM", "");
        chamadaEmpresa.enqueue(new Callback<Empresa>() {
            @Override
            public void onResponse(Call<Empresa> call, final Response<Empresa> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Empresa empresa = response.body();
                        nomeClienteTextView.setText(empresa.getNome());
                        nomeEmpresaTextView.setText(empresa.getNomeEmpresa());
                        saldoCartãoTextView.setText("R$" + empresa.getSaldo());
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