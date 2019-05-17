package com.example.gdsconsulta.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Empresa {
    @SerializedName("codCartao")
    @Expose
    private String codCartao;
    @SerializedName("nomeEmpresa")
    @Expose
    private String nomeEmpresa;
    @SerializedName("saldo")
    @Expose
    private String saldo;
    @SerializedName("dtUltimoUpdate")
    @Expose
    private String dtUltimoUpdate;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("CodEmpresa")
    @Expose
    private String codEmpresa;
    @SerializedName("movimentos")
    @Expose

    private List<Movimento> movimentos = null;

    public String getCodCartao() {
        return codCartao;
    }

    public void setCodCartao(String codCartao) {
        this.codCartao = codCartao;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getDtUltimoUpdate() {
        return dtUltimoUpdate;
    }

    public void setDtUltimoUpdate(String dtUltimoUpdate) {
        this.dtUltimoUpdate = dtUltimoUpdate;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public List<Movimento> getMovimentos() {
        return movimentos;
    }
    public void setMovimentos(List<Movimento> movimentos) {
        this.movimentos = movimentos;
    }


}

