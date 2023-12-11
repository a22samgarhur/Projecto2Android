package com.example.projecto2matesandroid;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.POST;

public interface ApiServer {

    @GET("/getLogin")
    Call<Usuari> getLogin();

    @POST("/loginprofesor")
    Call<Usuari> login(@Body Usuari usuari);

    @GET("/getAulas")
    Call<List<ItemHome>> getAulas();
}
