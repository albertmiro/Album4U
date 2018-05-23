package com.albertmiro.albums4u.di;

import com.albertmiro.albums4u.ui.albumslist.AlbumsListFragment;
import com.albertmiro.albums4u.ui.albumdetail.AlbumDetailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract AlbumsListFragment contributeAlbumListFragment();

    @ContributesAndroidInjector
    abstract AlbumDetailFragment contributeAlbumDetailFragment();
}