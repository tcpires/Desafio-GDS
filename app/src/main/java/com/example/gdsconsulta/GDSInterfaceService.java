package com.example.gdsconsulta;

import com.example.gdsconsulta.models.Empresa;
import com.example.gdsconsulta.models.Movimento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GDSInterfaceService {

    @GET("/nicolau-kerpen")
    Call<Empresa> getEmpresa(@Query("querycard") long q, @Query("pin") String pin , @Query("qcapi") String qcapi);


}
