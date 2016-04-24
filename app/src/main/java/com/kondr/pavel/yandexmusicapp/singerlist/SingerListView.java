package com.kondr.pavel.yandexmusicapp.singerlist;

import android.app.LoaderManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 04.04.2016.
 */
public interface SingerListView {

    void showRefresh();

    void hideRefresh();

    void setContent(ArrayList<Singer> singers);

}
