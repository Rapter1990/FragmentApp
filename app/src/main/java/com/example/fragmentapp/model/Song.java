package com.example.fragmentapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {

    private String songTitle;
    private String details;

    public Song() {
    }

    public Song(String songTitle, String details) {
        this.songTitle = songTitle;
        this.details = details;
    }

    protected Song(Parcel in) {
        songTitle = in.readString();
        details = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songTitle='" + songTitle + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(songTitle);
        parcel.writeString(details);
    }
}
