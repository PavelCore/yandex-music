package com.kondr.pavel.yandexmusicapp.singerlist;

import java.util.List;

/**
 * Created by Pavel on 04.04.2016.
 */
public interface SingerListView {

    void showRefresh();

    void hideRefresh();

    void setContent(List<Singer> singers);
}
