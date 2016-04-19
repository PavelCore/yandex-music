package com.kondr.pavel.yandexmusicapp.singerlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Joiner;
import com.kondr.pavel.yandexmusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Pavel on 04.04.2016.
 */
public class SingerListAdapter extends RecyclerView.Adapter<SingerListAdapter.ViewHolder> {

    private List<Singer> singers;
    private Context context;

    public SingerListAdapter(Context context, List<Singer> singers) {
        this.context = context;
        this.singers = singers;
    }

    private String getStuffText(int albums, int tracks) {
        String albumsStr, tracksStr;
        switch (albums % 10) {
            case 1:
                albumsStr = "альбом";
                break;
            case 2:
            case 3:
            case 4:
                albumsStr = "альбома";
                break;
            default:
                albumsStr = "альбомов";
        }

        switch (tracks % 10) {
            case 1:
                tracksStr = "песня";
                break;
            case 2:
            case 3:
            case 4:
                tracksStr = "песни";
                break;
            default:
                tracksStr = "песен";
        }

        return String.format("%d %s\n%d %s", albums, albumsStr, tracks, tracksStr);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_singer_list,
                viewGroup, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Singer singer = singers.get(i);
        Picasso.with(context).load(singer.getCover().getSmall())
                .resize(120, 120).into(viewHolder.cover);
        viewHolder.name.setText(singer.getName());
        viewHolder.stuffCount.setText(getStuffText(singer.getAlbums(), singer.getTracks()));
        viewHolder.genres.setText(Joiner.on(", ").join(singer.getGenres()));
    }

    @Override
    public int getItemCount() {
        return singers.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cover;
        private TextView name;
        private TextView genres;
        private TextView stuffCount;

        public ViewHolder(View itemView) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.cover_small);
            name = (TextView) itemView.findViewById(R.id.singer);
            genres = (TextView) itemView.findViewById(R.id.genres);
            stuffCount = (TextView) itemView.findViewById(R.id.stuff_count);
        }
    }
}