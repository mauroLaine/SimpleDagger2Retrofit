package com.example.mauro.daggerplus.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mauro.daggerplus.R;
import com.example.mauro.daggerplus.data.entities.Result;
import com.example.mauro.daggerplus.data.entities.ResultApi;
import com.example.mauro.daggerplus.data.remote.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void doMagic(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MovieService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
        movieService.getMovies(MovieService.API_KEY).enqueue(new Callback<ResultApi>() {
            @Override
            public void onResponse(Call<ResultApi> call, Response<ResultApi> response) {
                ResultApi resultApi = response.body();
                for (Result result: resultApi.getResults()){
                    Log.d(TAG, "onResponse: " +  result.getTitle());
                }

            }

            @Override
            public void onFailure(Call<ResultApi> call, Throwable t) {

            }
        });
    }
}
