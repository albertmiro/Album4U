package com.albertmiro.albums4u.ui.albums.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.albertmiro.albums4u.R;
import com.albertmiro.albums4u.ui.albums.detail.adapter.SongsAdapter;
import com.albertmiro.albums4u.ui.common.BaseFragment;
import com.albertmiro.albums4u.viewmodel.AppViewModelFactory;
import com.albertmiro.albums4u.viewmodel.LookupViewModel;
import com.albertmiro.albums4u.viewmodel.data.Album;
import com.albertmiro.albums4u.viewmodel.data.Track;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class AlbumDetailFragment extends BaseFragment {

    private LookupViewModel albumsViewModel;

    @Inject
    AppViewModelFactory viewModelFactory;

    private SongsAdapter songsAdapter;

    private int albumId;

    private RecyclerView songsList;
    private RelativeLayout progressBarContainer;
    private ImageView albumImage;
    private TextView albumArtist;
    private TextView noSongs;
    private TextView albumTitle;

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        albumsViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LookupViewModel.class);

        initViews();

        initSongsList();

        initObservers();

        albumsViewModel.loadAlbumSongs(albumId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_detail, container, false);
    }

    private void initViews() {
        songsList = getActivity().findViewById(R.id.songsList);
        progressBarContainer = getActivity().findViewById(R.id.progressBar);
        albumImage = getActivity().findViewById(R.id.albumImage);
        albumTitle = getActivity().findViewById(R.id.albumTitle);
        albumArtist = getActivity().findViewById(R.id.albumArtist);
        noSongs = getActivity().findViewById(R.id.noSongs);
    }

    private void initSongsList() {
        songsAdapter = new SongsAdapter();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        songsList.setAdapter(songsAdapter);
        songsList.setHasFixedSize(true);
        songsList.setLayoutManager(mLayoutManager);
    }

    private void initObservers() {
        albumsViewModel.isDataLoading()
                .observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean hasChanged) {
                        if (hasChanged != null) {
                            if (hasChanged) {
                                progressBarContainer.setVisibility(View.VISIBLE);
                            } else {
                                progressBarContainer.setVisibility(View.GONE);
                            }
                        }
                    }
                });

        albumsViewModel.getAlbum().observe(this, new Observer<Album>() {
            @Override
            public void onChanged(@Nullable Album album) {
                fillAlbumInfo(album);
                showAlbumTracks(album != null ? album.getTracks() : Collections.<Track>emptyList());
            }
        });

        albumsViewModel.isNetworkError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean hasChanged) {
                if (hasChanged != null && hasChanged) {
                    mainActivity.showMessage(getString(R.string.lost_connection));
                }
            }
        });

        albumsViewModel.isUnknownError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean hasChanged) {
                if (hasChanged != null && hasChanged) {
                    mainActivity.showMessage(getString(R.string.unexpected_error));
                }
            }
        });
    }

    private void fillAlbumInfo(Album album) {

        if (album != null) {
            albumTitle.setText(album.getName());
            albumArtist.setText(album.getArtistName());

            Picasso.get()
                    .load(album.getThumbnailUrl())
                    .placeholder(R.drawable.placeholder_album)
                    .error(R.drawable.placeholder_album)
                    .into(albumImage);

            songsAdapter.clearItems();
        } else {
            albumTitle.setText(R.string.no_data);
            albumArtist.setText(R.string.no_data);
            albumImage.setImageResource(R.drawable.placeholder_album);
        }
    }

    private void showAlbumTracks(List<Track> tracks) {
        songsAdapter.setItems(tracks);
        if (tracks.isEmpty()) {
            noSongs.setVisibility(View.VISIBLE);
        } else {
            noSongs.setVisibility(View.GONE);
        }
    }

}
