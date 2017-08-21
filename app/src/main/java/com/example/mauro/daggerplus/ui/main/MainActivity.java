package com.example.mauro.daggerplus.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mauro.daggerplus.R;
import com.example.mauro.daggerplus.data.entities.Result;
import com.example.mauro.daggerplus.data.entities.ResultApi;
import com.example.mauro.daggerplus.data.remote.MovieService;
import com.example.mauro.daggerplus.di.DaggerMainComponent;
import com.example.mauro.daggerplus.di.MainModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";

    private ListView listView;
    private ArrayAdapter<Result> arrayAdapter;
    private List<Result> results;

    @Inject
    MovieService movieService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        results = new ArrayList<>();
        listView = (ListView) findViewById(R.id.a_main_listview);
        arrayAdapter = new ArrayAdapter<Result>(this, android.R.layout.simple_list_item_1, results);

        listView.setAdapter(arrayAdapter);

        injectDependencies();

    }

    private void injectDependencies() {
        DaggerMainComponent.builder()
                .mainModule(new MainModule())
                .build().inject(this);
    }

    public void doMagic(View view) {

        movieService.getMovies(MovieService.API_KEY).enqueue(new Callback<ResultApi>() {
            @Override
            public void onResponse(Call<ResultApi> call, Response<ResultApi> response) {

                results.clear();
                results.addAll(response.body().getResults());
                arrayAdapter.notifyDataSetChanged();

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
