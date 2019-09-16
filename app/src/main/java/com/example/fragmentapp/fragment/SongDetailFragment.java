package com.example.fragmentapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fragmentapp.R;
import com.example.fragmentapp.model.Song;
import com.example.fragmentapp.util.SongUtil;

public class SongDetailFragment extends Fragment {

    // Song includes the song title and detail.
    public Song mSong;

    public SongDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (getArguments().containsKey(SongUtil.SONG_ID_KEY)) {
            // Load the content specified by the fragment arguments.
            mSong = new SongUtil().getSongList().get(getArguments()
                    .getInt(SongUtil.SONG_ID_KEY));
        }
    }

    /**
     * This method inflates the fragment's view and shows the song
     * detail information.
     *
     * @param inflater LayoutInflater object to inflate views
     * @param container ViewGroup that the fragment's UI should be attached to
     * @param savedInstanceState Bundle containing previous state
     * @return Fragment view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.song_detail,
                container, false);

        // Show the detail information in a TextView.
        if (mSong != null) {
            ((TextView) rootView.findViewById(R.id.song_detail))
                    .setText(mSong.getDetails());
        }

        return rootView;
    }

    /**
     * This method sets up a bundle for the arguments to pass
     * to a new instance of this fragment.
     *
     * @param selectedSong Integer position of selected song in song list
     * @return fragment
     */
    public static SongDetailFragment newInstance (int selectedSong) {
        SongDetailFragment fragment = new SongDetailFragment();
        // Set the bundle arguments for the fragment.
        Bundle arguments = new Bundle();
        arguments.putInt(SongUtil.SONG_ID_KEY, selectedSong);
        fragment.setArguments(arguments);
        return fragment;
    }
}
