package com.example.mauro.daggerplus.di;

import com.example.mauro.daggerplus.data.MoviesRepository;
import com.example.mauro.daggerplus.data.remote.MovieService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mauro on 8/20/17.
 */

@Module
public class RepositoryModul {

    @Provides
    public MoviesRepository provideMovieRepository(MovieService movieService){
        return new MoviesRepository(movieService);
    }
}
