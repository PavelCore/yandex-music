package com.kondr.pavel.yandexmusicapp.singerlist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.kondr.pavel.yandexmusicapp.R;
import com.kondr.pavel.yandexmusicapp.RecyclerItemClickListener;
import com.kondr.pavel.yandexmusicapp.singerinfo.SingerInfoActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SingerListActivity extends AppCompatActivity implements SingerListView,
        SwipeRefreshLayout.OnRefreshListener {

    public final static String SINGER_INFO = "singer info";
    public final static String STATE_SINGER_LIST = "singer list";

    private SingerListPresenter presenter;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Singer> singerList;

    @Bind(R.id.refresh_singer_list) SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.singer_list) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        presenter = new SingerListPresenterImpl(this);

        if (savedInstanceState == null) {
            singerList = new ArrayList<>();
            presenter.refresh();
        } else {
            singerList = savedInstanceState.getParcelableArrayList(STATE_SINGER_LIST);
        }

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SingerListAdapter(getApplicationContext(), singerList);
        mRecyclerView.setAdapter(mAdapter);


        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), SingerInfoActivity.class);
                        intent.putExtra(SINGER_INFO, singerList.get(position));
                        startActivity(intent);
                    }
                })
        );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_singer_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(STATE_SINGER_LIST, singerList);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRefresh() {
        showRefresh();
        presenter.refresh();
    }

    @Override
    public void showRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setContent(ArrayList<Singer> content) {
        singerList.clear();
        for (Singer singer : content) {
            singerList.add(new Singer(singer));
        }
    }
}
