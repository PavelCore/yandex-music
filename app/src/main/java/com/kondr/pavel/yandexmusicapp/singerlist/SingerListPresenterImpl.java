package com.kondr.pavel.yandexmusicapp.singerlist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pavel on 31.03.2016.
 */
public class SingerListPresenterImpl implements SingerListPresenter {

    private SingerListInteractor interactor;
    private SingerListView view;

    public SingerListPresenterImpl(SingerListView view) {
        this.view = view;
        this.interactor = new SingerListInteractorImpl(this);
    }

    @Override
    public void refresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                interactor.getSingerList();
            }
        }, 1000);
    }

    @Override
    public void presentSingerList(List<Singer> singers) {
        view.hideRefresh();
        if (singers != null) {
            view.setContent(singers);
        }
    }
}
