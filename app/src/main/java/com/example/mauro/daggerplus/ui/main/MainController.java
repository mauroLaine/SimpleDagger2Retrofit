package com.example.mauro.daggerplus.ui.main;

import com.example.mauro.daggerplus.data.MoviesRepository;
import com.example.mauro.daggerplus.data.remote.MovieService;

import javax.inject.Inject;

/**
 * Created by mauro on 8/20/17.
 */

public class MainController {

    @Inject
    MovieService movieService;

    private MoviesRepository moviesRepository;
    private MainActivity mainActivity;

    public MainController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public void addActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void removeActivity(){
        this.mainActivity = null;
    }

    public void loadData(){
        moviesRepository.getAllMovies(mainActivity);
    }
}
