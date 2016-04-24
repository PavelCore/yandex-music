package com.kondr.pavel.yandexmusicapp.singerlist;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.widget.TextView;

import com.kondr.pavel.yandexmusicapp.SingerListLoader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Pavel on 31.03.2016.
 */
public class SingerListInteractorImpl implements SingerListInteractor,
        LoaderManager.LoaderCallbacks<ArrayList<Singer>> {

    final private SingerListPresenterImpl presenter;
    final private Context context;
    final private LoaderManager manager;

    public SingerListInteractorImpl(SingerListPresenterImpl presenter,
                                    Context context,
                                    LoaderManager manager) {
        this.presenter = presenter;
        this.context = context;
        this.manager = manager;
    }


    @Override
    public void getSingerList() {
        /* Loaders are used for appropriate behavior in situations when user
        * rotate his screen, but the answer has not yet been retrieved.
        * Loaders correctly work in this situations and if loader with id=0 exists,
        * we will re-connect an existing one */

        manager.initLoader(0, null, this);
    }

    @Override
    public Loader<ArrayList<Singer>> onCreateLoader(int id, Bundle args) {
        return new SingerListLoader(context);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Singer>> loader) {
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Singer>> loader, ArrayList<Singer> data) {
        // show singerList to user
        presenter.presentSingerList(data);
        manager.destroyLoader(loader.getId());
    }
}
