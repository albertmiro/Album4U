package com.albertmiro.albums4u.ui.albums.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.albertmiro.albums4u.R;
import com.albertmiro.albums4u.viewmodel.data.Album;
import com.squareup.picasso.Picasso;

public class AlbumsItemView extends RelativeLayout {

    public AlbumsItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_album, this, true);
    }

    void bind(Album album) {

        TextView albumTitle = findViewById(R.id.albumTitle);
        TextView albumArtist = findViewById(R.id.albumArtist);
        TextView albumTracks = findViewById(R.id.albumTracks);
        ImageView albumImage = findViewById(R.id.albumImage);

        albumTitle.setText(album.getName());
        albumArtist.setText(album.getArtistName());
        albumTracks.setText(String.format(getContext().getString(R.string.total_songs), album.getTrackCount()));

        Picasso.get()
                .load(album.getThumbnailUrl())
                .placeholder(R.drawable.placeholder_album)
                .error(R.drawable.placeholder_album)
                .into(albumImage);
    }
}
