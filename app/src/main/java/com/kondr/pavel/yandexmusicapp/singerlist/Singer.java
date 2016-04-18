package com.kondr.pavel.yandexmusicapp.singerlist;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pavel on 31.03.2016.
 */
public class Singer implements Parcelable{

    private int id;
    private String name;
    private String[] genres;
    private int tracks;
    private int albums;
    private String link;
    private String description;
    private Cover cover;

    public Singer(Singer other) {
        this.id = other.getId();
        this.name = other.getName();
        this.genres = other.getGenres();
        this.tracks = other.getTracks();
        this.albums = other.getAlbums();
        this.link = other.getLink();
        this.description = other.getDescription();
        this.cover = other.getCover();
    }

    //getters
    public Cover getCover() {
        return cover;
    }

    public int getAlbums() {
        return albums;
    }

    public String getDescription() {
        return description;
    }

    public String[] getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public int getTracks() {
        return tracks;
    }


    // Parceling part
    public Singer(Parcel in) {
        id = in.readInt();
        name = in.readString();
        tracks = in.readInt();
        albums = in.readInt();
        link = in.readString();
        description = in.readString();
        cover = new Cover(in.readString(), in.readString());

        String[] data = new String[in.readInt()];
        in.readStringArray(data);
        genres = data;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(name);
        out.writeInt(tracks);
        out.writeInt(albums);
        out.writeString(link);
        out.writeString(description);
        out.writeString(cover.getSmall());
        out.writeString(cover.getBig());

        out.writeInt(genres.length);
        out.writeStringArray(genres);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Singer> CREATOR
            = new Parcelable.Creator<Singer>() {

        @Override
        public Singer createFromParcel(Parcel in) {
            return new Singer(in);
        }

        @Override
        public Singer[] newArray(int size) {
            return new Singer[size];
        }
    };
}
