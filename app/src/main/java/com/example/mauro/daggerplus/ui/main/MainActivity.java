package com.example.mauro.daggerplus.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
    public static final String MOVIE_TITLE = "MOVIE_TITLE";
    public static final String MOVIE_URL = "IMAGE_MOVIE";

    private ListView listView;
    private ArrayAdapter<Result> arrayAdapter;
    private List<Result> results;

    @Inject
    MovieService movieService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpListView();
        injectDependencies();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, results.get(i).getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra(MOVIE_TITLE, results.get(i).getTitle());
                intent.putExtra(MOVIE_URL, results.get(i).getPosterPath());
                startActivity(intent);
            }
        });

    }

    private void setUpListView() {
        results = new ArrayList<>();
        listView = (ListView) findViewById(R.id.a_main_listview);
        arrayAdapter = new ArrayAdapter<Result>(this, android.R.layout.simple_list_item_1, results);

        listView.setAdapter(arrayAdapter);
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
