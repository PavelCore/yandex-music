package com.kondr.pavel.yandexmusicapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.kondr.pavel.yandexmusicapp.singerlist.Singer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Pavel on 24.04.2016.
 */

// better to use here smth like cursor, but in this app i have only type of
// requests, so ArrayList<Singer> goes as well
public abstract class BaseLoader extends AsyncTaskLoader<ArrayList<Singer>> {

    public BaseLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<Singer> loadInBackground() {
        try {
            // it will return ArrayList<Singer> if everything goes correct
            return apiCall();
        } catch (IOException e) {
            // else (for example if there is not internet connection) null will be
            // returned and singer list won't be updated
            return null;
        }
    }

    protected abstract ArrayList<Singer> apiCall() throws IOException;
}
