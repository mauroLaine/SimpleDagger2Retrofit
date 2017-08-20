package com.example.mauro.daggerplus.data.remote;

import com.example.mauro.daggerplus.data.entities.ResultApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mauro on 8/20/17.
 */

public interface MovieService {

    String BASE_URL = "https://api.themoviedb.org/";
    String API_KEY = "fe1a661d36400e9d5663a70bc161c4bc";

    @GET("/3/movie/popular")
    Call<ResultApi> getMovies(@Query("api_key") String api);


}
