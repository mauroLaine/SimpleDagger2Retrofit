package com.example.mauro.daggerplus.di;

import com.example.mauro.daggerplus.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by mauro on 8/20/17.
 */

@Component(modules = MainModule.class)
public interface MainComponent {
    //    component per view
    void inject(MainActivity mainActivity);
}
