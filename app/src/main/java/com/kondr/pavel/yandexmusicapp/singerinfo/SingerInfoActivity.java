package com.kondr.pavel.yandexmusicapp.singerinfo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Joiner;
import com.kondr.pavel.yandexmusicapp.R;
import com.kondr.pavel.yandexmusicapp.singerlist.Singer;
import com.kondr.pavel.yandexmusicapp.singerlist.SingerListActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SingerInfoActivity extends AppCompatActivity implements SingerInfoView {

    private Singer singer;
    private SingerInfoPresenter presenter;
    @Bind(R.id.cover_big) ImageView cover;
    @Bind(R.id.genres) TextView genres;
    @Bind(R.id.stuff_count) TextView stuff;
    @Bind(R.id.biography) TextView biography;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        singer = intent.getParcelableExtra(SingerListActivity.SINGER_INFO);

        presenter = new SingerInfoPresenterImpl(this);

        setTitle(singer.getName());
        setContent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }


    @Override
    public void setContent() {
        // in loadImage will be used some transformations, so i decided to let presenter
        // work with this routine
        presenter.loadImage(singer.getCover().getBig(), cover);
        genres.setText(Joiner.on(", ").join(singer.getGenres()));
        presenter.setStuffText(stuff, singer.getAlbums(), singer.getTracks());
        biography.setText(singer.getDescription());
    }
}
