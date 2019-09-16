package com.example.fragmentapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.fragmentapp.R;
import com.example.fragmentapp.adapter.SongAdapter;
import com.example.fragmentapp.model.Song;
import com.example.fragmentapp.util.SongUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.song_list)
    RecyclerView recyclerView;

    SongAdapter songAdapter;

    List<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setToolBar();
        defineRecyclerView();

    }


    private void setToolBar() {
        // Set the toolbar as the app bar.
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
    }

    private void defineRecyclerView() {

        songList = new ArrayList<>();
        SongUtil songUtil = new SongUtil();
        songUtil.fillSong();
        songList = songUtil.getSongList();
        Log.i(TAG, "songList size : " + songList.size() );
        // Is the container layout available? If so, set mTwoPane to true.
        if (findViewById(R.id.song_detail_container) != null) {
            songAdapter = new SongAdapter(getApplicationContext(),songList, true);
            recyclerView.setAdapter(songAdapter);
        }else{
            songAdapter = new SongAdapter(getApplicationContext(),songList, false);
            recyclerView.setAdapter(songAdapter);
        }
    }
}
