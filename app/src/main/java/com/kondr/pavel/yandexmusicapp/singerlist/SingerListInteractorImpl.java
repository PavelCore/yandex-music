package com.kondr.pavel.yandexmusicapp.singerlist;

import android.widget.TextView;

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
public class SingerListInteractorImpl implements SingerListInteractor {

    final private SingerListPresenterImpl presenter;

    public SingerListInteractorImpl(SingerListPresenterImpl presenter) {
        this.presenter = presenter;
    }

    public interface YandexService {
        @GET("/download.cdn.yandex.net/mobilization-2016/artists.json")
        Call<List<Singer>> listSingers();
    }

    @Override
    public void getSingerList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cache-default04d.cdn.yandex.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        YandexService service = retrofit.create(YandexService.class);
        Call<List<Singer>> call = service.listSingers();

        call.enqueue(new Callback<List<Singer>>() {
            @Override
            public void onResponse(Call<List<Singer>> call, Response<List<Singer>> response) {
                presenter.presentSingerList(response.body());
            }

            @Override
            public void onFailure(Call<List<Singer>> call, Throwable t) {
                presenter.presentSingerList(null);
            }

        });
    }
}
