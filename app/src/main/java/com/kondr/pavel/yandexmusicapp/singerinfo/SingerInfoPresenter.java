package com.kondr.pavel.yandexmusicapp.singerinfo;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Pavel on 18.04.2016.
 */
public interface SingerInfoPresenter {

    void loadImage(String imageUrl, ImageView cover);

    void setStuffText(TextView stuff, int albums, int tracks);

}
