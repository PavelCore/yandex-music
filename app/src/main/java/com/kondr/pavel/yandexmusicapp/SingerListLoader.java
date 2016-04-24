package com.kondr.pavel.yandexmusicapp;

import android.content.Context;


import com.kondr.pavel.yandexmusicapp.singerlist.Singer;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Pavel on 24.04.2016.
 */
public class SingerListLoader extends BaseLoader {

    public SingerListLoader(Context context) {
        super(context);
    }

    public interface YandexService {
        @GET("/download.cdn.yandex.net/mobilization-2016/artists.json")
        Call<ArrayList<Singer>> listSingers();
    }

    @Override
    protected ArrayList<Singer> apiCall() throws IOException {
        //just typical work with retrofit2 and gson
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cache-default04d.cdn.yandex.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        YandexService service = retrofit.create(YandexService.class);
        Call<ArrayList<Singer>> call = service.listSingers();

        return call.execute().body();
    }
}
