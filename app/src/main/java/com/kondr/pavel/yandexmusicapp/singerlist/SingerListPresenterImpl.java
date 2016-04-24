package com.kondr.pavel.yandexmusicapp.singerlist;

import android.app.LoaderManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 31.03.2016.
 */
public class SingerListPresenterImpl implements SingerListPresenter {

    private SingerListInteractor interactor;
    private SingerListView view;

    public SingerListPresenterImpl(SingerListView view, LoaderManager manager) {
        this.view = view;
        this.interactor = new SingerListInteractorImpl(this, (Context) view, manager);
    }

    @Override
    public void refresh() {
        view.showRefresh();

        // used for better ui perfomance
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // i don't want to know here how exactly i retrieve singer list, so i
                // pass this routine to model
                interactor.getSingerList();
            }
        }, 1000);
    }

    @Override
    public void presentSingerList(ArrayList<Singer> singers) {
        view.hideRefresh();
        if (singers != null) {
            // null returned if there was an error in retrieving singer lost
            view.setContent(singers);
        }
    }

    /* here you can set up your own searching filter, now it finds singers which
    * have searchQuery in their name*/
    @Override
    public ArrayList<Singer> filter(ArrayList<Singer> singers, String searchQuery) {
        searchQuery = searchQuery.toLowerCase();
        ArrayList<Singer> filteredList = new ArrayList<>();
        for (Singer singer : singers) {
            if (singer.getName().toLowerCase().contains(searchQuery)) {
                filteredList.add(singer);
            }
        }
        return filteredList;
    }
}
