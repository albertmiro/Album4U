package com.albertmiro.albums4u.di;

import com.albertmiro.albums4u.ui.albums.AlbumsListFragment;
import com.albertmiro.albums4u.ui.albums.detail.AlbumDetailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract AlbumsListFragment contributeAlbumListFragment();

    @ContributesAndroidInjector
    abstract AlbumDetailFragment contributeAlbumDetailFragment();
}