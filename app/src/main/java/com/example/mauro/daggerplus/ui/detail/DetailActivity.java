package com.example.mauro.daggerplus.ui.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.mauro.daggerplus.R;
import com.example.mauro.daggerplus.ui.main.MainActivity;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivityTAG_";
    private String title;
    private String path;


    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        title = getIntent().getStringExtra(MainActivity.MOVIE_TITLE);
        path = getIntent().getStringExtra(MainActivity.MOVIE_URL);

        imageView = (ImageView) findViewById(R.id.a_detail_image);

        setTitle(title);

        String ruta = "http://image.tmdb.org/t/p/w500/" + path;
        Picasso.with(this).load(ruta).into(imageView);

        Log.d(TAG, "onCreate: " + title + ", " + path);

    }
}
