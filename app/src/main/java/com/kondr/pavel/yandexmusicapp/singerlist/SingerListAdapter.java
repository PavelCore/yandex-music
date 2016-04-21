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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 04.04.2016.
 */
public class SingerListAdapter extends RecyclerView.Adapter<SingerListAdapter.ViewHolder> {

    private List<Singer> singers;
    private Context context;

    public SingerListAdapter(Context context, List<Singer> singers) {
        this.context = context;
        this.singers = new ArrayList<>();
        for (Singer singer : singers) {
            this.singers.add(singer);
        }
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

    // needed for correct display of the text
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

    public Singer getItem(int pos) {
        return singers.get(pos);
    }

    // some methods for adding, moving and removing with notification
    public Singer removeItem(int pos) {
        Singer singer = singers.remove(pos);
        notifyItemRemoved(pos);
        return singer;
    }

    public void addItem(int pos, Singer singer) {
        singers.add(pos, singer);
        notifyItemInserted(pos);
    }

    public void moveItem(int from, int to) {
        Singer singer = singers.remove(from);
        singers.add(to, singer);
        notifyItemMoved(from, to);
    }

    // we need keep an eye on positions when we change list, so the following functions
    // do this routine and animate changes
    public void changeList(List<Singer> newSingers) {
        applyRemovals(newSingers);
        applyAdditions(newSingers);
        applyMovedItems(newSingers);
    }

    private void applyRemovals(List<Singer> newSingers) {
        for (int pos = singers.size() - 1; pos >= 0; --pos) {
            if (!newSingers.contains(singers.get(pos))) {
                removeItem(pos);
            }
        }
    }

    private void applyAdditions(List<Singer> newModels) {
        for (int pos = 0, count = newModels.size(); pos < count; pos++) {
            final Singer singer = newModels.get(pos);
            if (!singers.contains(singer)) {
                addItem(pos, singer);
            }
        }
    }

    private void applyMovedItems(List<Singer> newSingers) {
        for (int to = newSingers.size() - 1; to >= 0; --to) {
            int from = singers.indexOf(newSingers.get(to));
            if (from >= 0 && from != to) {
                moveItem(from, to);
            }
        }
    }

}