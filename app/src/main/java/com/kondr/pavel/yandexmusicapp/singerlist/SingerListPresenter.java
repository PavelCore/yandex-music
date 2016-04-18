package com.kondr.pavel.yandexmusicapp.singerlist;

import java.util.List;

/**
 * Created by Pavel on 31.03.2016.
 */
public interface SingerListPresenter {

    void refresh();

    void presentSingerList(List<Singer> singers);
}
