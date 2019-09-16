package com.example.fragmentapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentapp.R;
import com.example.fragmentapp.activity.SongDetailActivity;
import com.example.fragmentapp.fragment.SongDetailFragment;
import com.example.fragmentapp.model.Song;
import com.example.fragmentapp.util.SongUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongAdapterViewHolder> {

    private static final String LOG = SongAdapter.class.getName();

    List<Song> songList;
    Context context;

    // Default layout is one pane, not two.
    boolean mTwoPane;

    public SongAdapter(Context context,List<Song> songList,boolean mTwoPaneBoolean) {
        this.songList = songList;
        this.context = context;
        this.mTwoPane = mTwoPaneBoolean;
    }


    @NonNull
    @Override
    public SongAdapter.SongAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.song_list_content;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new SongAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.SongAdapterViewHolder holder, int position) {

        Song song = songList.get(position);
        holder.mIdView.setText(String.valueOf(position + 1));
        holder.mContentView.setText(song.getSongTitle());

    }

    @Override
    public int getItemCount() {
        if(songList == null){
            return 0;
        }
        return songList.size();
    }


    public class SongAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.id)
        TextView mIdView;

        @BindView(R.id.content)
        TextView mContentView;


        public SongAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            // Get selected song position in song list.
            int selectedSong = getAdapterPosition();

            if (mTwoPane) {

                // Create new instance of fragment and add it to
                // the activity using a fragment transaction.
                SongDetailFragment fragment =
                        SongDetailFragment.newInstance(selectedSong);
                ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.song_detail_container, fragment)
                        .addToBackStack(null)
                        .commit();
            } else {
                // Send an intent to the SongDetailActivity
                // with intent extra of the selected song position.
                Context context = view.getContext();
                Intent intent = new Intent(context,
                        SongDetailActivity.class);
                Log.i(LOG,"selectedSong : " + selectedSong);
                intent.putExtra(SongUtil.SONG_ID_KEY,
                        getAdapterPosition());
                context.startActivity(intent);
            }
        }
    }
}
