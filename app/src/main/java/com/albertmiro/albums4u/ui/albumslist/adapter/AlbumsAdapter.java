package com.albertmiro.albums4u.ui.albumslist.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.albertmiro.albums4u.ui.albumslist.AlbumsListFragment;
import com.albertmiro.albums4u.ui.common.adapter.RecyclerViewAdapterBase;
import com.albertmiro.albums4u.ui.common.adapter.ViewWrapper;
import com.albertmiro.albums4u.domain.Album;

public class AlbumsAdapter extends RecyclerViewAdapterBase<Album, AlbumsItemView> {

    private AlbumsListFragment listener;

    @Override
    protected AlbumsItemView onCreateItemView(ViewGroup parent, int viewType) {
        return new AlbumsItemView(parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewWrapper<AlbumsItemView> holder, int position) {
        final Album album = items.get(position);

        if (holder.getView() != null) {
            holder.getView().bind(album);
            holder.getView().setOnClickListener(v -> {
                if (listener != null) {
                    listener.onAlbumClicked(album.getId());
                }
            });
        }
    }

    public void setListener(AlbumsListFragment listener) {
        this.listener = listener;
    }
}
