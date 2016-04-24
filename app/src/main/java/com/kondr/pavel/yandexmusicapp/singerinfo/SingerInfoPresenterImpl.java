package com.kondr.pavel.yandexmusicapp.singerinfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by Pavel on 18.04.2016.
 */
public class SingerInfoPresenterImpl implements SingerInfoPresenter {

    private Context context;

    public SingerInfoPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(String imageUrl, ImageView cover) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

        // this transformation resizes picture to
        // (min(screen width, screen height), min(screen width, screen height))
        // so i can "put image in toolbar" in portrait orientation
        // but in land if i zoom image to full width, it will look terrible
        Transformation transformation = new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);

                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "full screen width";
            }
        };

        Picasso.with(context)
                .load(imageUrl)
                .error(android.R.drawable.stat_notify_error)
                .transform(transformation)
                .into(cover);
    }


    // needed for correct display of the text
    @Override
    public void setStuffText(TextView stuff, int albums, int tracks) {
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

        stuff.setText(String.format("%d %s    %d %s", albums, albumsStr, tracks, tracksStr));
    }
}
