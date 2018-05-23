package com.albertmiro.albums4u.ui.albumdetail.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.albertmiro.albums4u.ui.common.adapter.RecyclerViewAdapterBase;
import com.albertmiro.albums4u.ui.common.adapter.ViewWrapper;
import com.albertmiro.albums4u.domain.Track;

public class SongsAdapter extends RecyclerViewAdapterBase<Track, SongsItemView> {

    @Override
    protected SongsItemView onCreateItemView(ViewGroup parent, int viewType) {
        return new SongsItemView(parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewWrapper<SongsItemView> holder, int position) {
        final Track track = items.get(position);

        if (holder.getView() != null) {
            holder.getView().bind(track);
        }
    }
}
