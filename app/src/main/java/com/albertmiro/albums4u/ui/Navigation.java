package com.albertmiro.albums4u.ui;

import android.support.v4.app.FragmentActivity;

import com.albertmiro.albums4u.R;
import com.albertmiro.albums4u.ui.albumdetail.AlbumDetailFragment;
import com.albertmiro.albums4u.ui.albumslist.AlbumsListFragment;

class Navigation {

    public static void loadAlbumsListFragment(FragmentActivity activity) {
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new AlbumsListFragment())
                .commit();
    }

    public static void loadAlbumDetail(FragmentActivity activity, int albumId) {
        AlbumDetailFragment albumDetail = new AlbumDetailFragment();
        albumDetail.setAlbumId(albumId);

        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, albumDetail)
                .addToBackStack(null)
                .commit();
    }
}
