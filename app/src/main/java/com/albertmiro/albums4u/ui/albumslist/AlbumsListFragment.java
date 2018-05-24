package com.albertmiro.albums4u.ui.albumslist;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.albertmiro.albums4u.R;
import com.albertmiro.albums4u.domain.Album;
import com.albertmiro.albums4u.ui.albumslist.adapter.AlbumsAdapter;
import com.albertmiro.albums4u.ui.common.BaseFragment;
import com.albertmiro.albums4u.ui.viewmodel.AppViewModelFactory;
import com.albertmiro.albums4u.ui.viewmodel.LookupViewModel;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class AlbumsListFragment extends BaseFragment implements AlbumListListener {

    private static final int GRID_SPAN_COUNT = 2;

    @Inject
    AppViewModelFactory viewModelFactory;

    private LookupViewModel albumsViewModel;

    private AlbumsAdapter albumsAdapter;
    private RecyclerView albumsList;
    private SwipeRefreshLayout swipeToRefresh;
    private RelativeLayout progressBarContainer;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        albumsViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LookupViewModel.class);

        initViews();

        initSwipeRefresh();

        initAlbumsList();

        initObservers();

        albumsViewModel.loadAlbumsForArtist();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_albums_list, container, false);
    }

    private void initViews() {
        albumsList = getActivity().findViewById(R.id.albumsList);
        swipeToRefresh = getActivity().findViewById(R.id.swipeRefresh);
        progressBarContainer = getActivity().findViewById(R.id.progressBar);
    }

    private void initAlbumsList() {
        albumsAdapter = new AlbumsAdapter();
        albumsAdapter.setListener(this);

        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), GRID_SPAN_COUNT);

        albumsList.setAdapter(albumsAdapter);
        albumsList.setHasFixedSize(true);
        albumsList.setLayoutManager(mLayoutManager);

    }

    private void initSwipeRefresh() {
        swipeToRefresh.setOnRefreshListener(() -> albumsViewModel.loadAlbumsForArtist());
    }

    private void initObservers() {
        albumsViewModel.isDataLoading()
                .observe(this, hasChanged -> {
                    if (hasChanged != null) {
                        if (!swipeToRefresh.isRefreshing()) {
                            showOrHideProgressBar(hasChanged);
                        }
                    }
                });

        albumsViewModel.getArtistAndAlbums().observe(this, artistAndAlbums ->
                showAlbums(artistAndAlbums != null ? artistAndAlbums.getAlbumList() : Collections.<Album>emptyList()));

        albumsViewModel.isNetworkError().observe(this, hasChanged -> {
            if (hasChanged != null && hasChanged) {
                showNetworkError();
            }
        });

        albumsViewModel.isUnknownError().observe(this, hasChanged -> {
            if (hasChanged != null && hasChanged) {
                showUnknownError();
            }
        });
    }

    public void showOrHideProgressBar(boolean showProgress) {
        if (showProgress) {
            progressBarContainer.setVisibility(View.VISIBLE);
        } else {
            progressBarContainer.setVisibility(View.GONE);
        }
    }

    public void showUnknownError() {
        showMessage(getString(R.string.unexpected_error));
        hideRefreshingIcon();
    }

    public void showNetworkError() {
        showMessage(getString(R.string.lost_connection));
        hideRefreshingIcon();
    }

    public void showAlbums(List<Album> albums) {
        if (swipeToRefresh.isRefreshing()) {
            albumsAdapter.clearItems();
            swipeToRefresh.setRefreshing(false);
        }

        if (albums.isEmpty()) {
            showMessage(getString(R.string.no_albums));
        } else {
            albumsAdapter.setItems(albums);
        }
    }

    public void hideRefreshingIcon() {
        if (swipeToRefresh.isRefreshing()) {
            swipeToRefresh.setRefreshing(false);
        }
    }

    public void showMessage(String message) {
        mainActivity.showMessage(message);
    }

    @Override
    public void onAlbumClicked(int albumId) {
        mainActivity.onAlbumClicked(albumId);
    }
}
