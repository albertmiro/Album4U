package com.albertmiro.albums4u.ui.albumdetail.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.albertmiro.albums4u.R;
import com.albertmiro.albums4u.utils.AppUtils;
import com.albertmiro.albums4u.domain.Track;

public class SongsItemView extends RelativeLayout {

    public SongsItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_track, this, true);
    }

    void bind(final Track track) {

        TextView trackName = findViewById(R.id.trackName);
        TextView trackTime = findViewById(R.id.trackTime);

        trackName.setText(track.getTrackName());
        trackTime.setText(AppUtils.convertMillisToString(track.getTrackTimeMillis()));

        setOnClickListener(v -> {
            try {
                Uri myUri = Uri.parse(track.getPreviewUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(myUri, "audio/m4a");
                getContext().startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Log.d("Songs", "No activity found to play music");
            }
        });

    }
}
