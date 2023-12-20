package com.example.projecto2matesandroid;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.POST;

public interface ApiServer {

    @GET("/getLogin")
    Call<Usuari> getLogin();

    @POST("/loginprofesor")
    Call<Usuari> login(@Body Usuari usuari);

    @GET("/logout")
    Call<Void> logout();

    @GET("/getAulas")
    Call<List<ItemHome>> getAulas();

    @GET("/consultarUsuaris")
    Call<List<ItemAlumno>> getAlumnos(@Query("id")String aulaID);

    @GET("/consultarUsuariPerId/{id}")
    Call<ItemAlumno> getAlumne(@Path("id") String id);

    @GET("/quitarAlumnoAula/{id}")
    Call<ItemAlumno> removeAlumne(@Path("id") String id);

    @POST("/crearAula")
    Call<Aula> crearAula(@Body Aula aula);

    @POST("/restablecerConstrasenya")
    Call<actualizarConstrasenyaModel> restablecerConstrasenya(@Body actualizarConstrasenyaModel actualizarConstrasenyaModel);


}
