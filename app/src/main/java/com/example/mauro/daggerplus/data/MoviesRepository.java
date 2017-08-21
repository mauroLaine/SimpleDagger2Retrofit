package com.example.mauro.daggerplus.data;

import android.util.Log;

import com.example.mauro.daggerplus.data.entities.Result;
import com.example.mauro.daggerplus.data.entities.ResultApi;
import com.example.mauro.daggerplus.data.remote.MovieService;
import com.example.mauro.daggerplus.ui.main.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mauro on 8/20/17.
 */


public class MoviesRepository {
    private static final String TAG = "MoviesRepositoryTAG_";
    private MovieService movieService;

    public MoviesRepository(MovieService movieService) {
        this.movieService = movieService;
    }

    public void getAllMovies(final MainActivity mainActivity){

        movieService.getMovies(MovieService.API_KEY).enqueue(new Callback<ResultApi>() {
            @Override
            public void onResponse(Call<ResultApi> call, Response<ResultApi> response) {

//                results.clear();
//                results.addAll(response.body().getResults());
//                arrayAdapter.notifyDataSetChanged();

                mainActivity.updateResults(response.body().getResults());
                ResultApi resultApi = response.body();
                for (Result result : resultApi.getResults()) {
                    Log.d(TAG, "onResponse: " + result.getTitle());
                }
            }

            @Override
            public void onFailure(Call<ResultApi> call, Throwable t) {

            }
        });
    }
}
