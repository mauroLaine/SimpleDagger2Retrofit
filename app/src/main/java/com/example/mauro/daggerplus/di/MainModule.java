package com.example.mauro.daggerplus.di;

import com.example.mauro.daggerplus.data.MoviesRepository;
import com.example.mauro.daggerplus.data.remote.MovieService;
import com.example.mauro.daggerplus.ui.main.MainActivity;
import com.example.mauro.daggerplus.ui.main.MainController;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mauro on 8/20/17.
 */

@Module
public class MainModule {

    @Provides
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(MovieService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public MovieService provideMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    @Provides
    public MainController provideMainController(MoviesRepository moviesRepository) {
        return new MainController(moviesRepository);
    }
}
