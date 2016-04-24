package com.kondr.pavel.yandexmusicapp.singerlist;

/**
 * Created by Pavel on 16.04.2016.
 */
public class Cover {
    private String small;
    private String big;

    public Cover(String small, String big) {
        this.small = small;
        this.big = big;
    }

    public String getBig() {
        return big;
    }

    public String getSmall() {
        return small;
    }
}
