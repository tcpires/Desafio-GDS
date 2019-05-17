package com.example.gdsconsulta;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gdsconsulta.models.Empresa;
import com.example.gdsconsulta.models.Movimento;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private List<Movimento> movimentos;
    DecimalFormat df_valor = new DecimalFormat("#,###.00");
    DecimalFormat df= new DecimalFormat("#,###");

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView operacao_id;
        public TextView operacao_data;
        public TextView codigo_produto;
        public TextView descricao_produto;
        public TextView quantidade_venda;
        public TextView valor_total;
        public TextView cancelado;


        public ViewHolder(View view) {
            super(view);
            this.operacao_id = (TextView) view.findViewById(R.id.operacao_id);
            this.operacao_data = (TextView) view.findViewById(R.id.operacao_data);
            this.codigo_produto = (TextView) view.findViewById(R.id.codigo_produto);
            this.descricao_produto = (TextView) view.findViewById(R.id.descricao_produto);
            this.quantidade_venda = (TextView) view.findViewById(R.id.quantidade_venda);
            this.valor_total = (TextView) view.findViewById(R.id.valor_total);
            this.cancelado = (TextView) view.findViewById(R.id.cancelado);
        }
    }

    public Adaptador(List<Movimento> movimentos) {
        this.movimentos = movimentos;
    }

    @Override
    public Adaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movimentacao_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.operacao_id = (TextView) view.findViewById(R.id.operacao_id);
        viewHolder.operacao_data = (TextView) view.findViewById(R.id.operacao_data);
        viewHolder.codigo_produto = (TextView) view.findViewById(R.id.codigo_produto);
        viewHolder.descricao_produto = (TextView) view.findViewById(R.id.descricao_produto);
        viewHolder.quantidade_venda = (TextView) view.findViewById(R.id.quantidade_venda);
        viewHolder.valor_total = (TextView) view.findViewById(R.id.valor_total);
        viewHolder.cancelado = (TextView) view.findViewById(R.id.cancelado);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        double quantidade = Double.parseDouble(this.movimentos.get(position).getQuantidade());
        double vlTotal = Double.parseDouble(this.movimentos.get(position).getVlTotal());

        viewHolder.operacao_id.setText("ID Operação: " + this.movimentos.get(position).getNumId());
        viewHolder.operacao_data.setText( "Horário: "+ this.movimentos.get(position).getDtOcorrencia());
        viewHolder.codigo_produto.setText("ID Produto: " + this.movimentos.get(position).getCodProduto());
        viewHolder.descricao_produto.setText(this.movimentos.get(position).getDescricao());
        viewHolder.valor_total.setText("R$ "+ df_valor.format(vlTotal));
        viewHolder.quantidade_venda.setText("Quantidade: " + df.format(quantidade) + " unidades");
        viewHolder.cancelado.setText("Cancelado (s/n): " + this.movimentos.get(position).getCancelado());
        String operacaoDC = this.movimentos.get(position).getOperacaoDC();

    }
    private final int limite = 15;
    @Override
    public int getItemCount() {
        if(movimentos.size() > limite) {
            return limite;
        }
        else{
            return movimentos.size();
        }
    }
}
