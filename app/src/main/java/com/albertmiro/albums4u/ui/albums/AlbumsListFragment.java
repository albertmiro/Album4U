package com.albertmiro.albums4u.ui.albums;

import android.arch.lifecycle.Observer;
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
import com.albertmiro.albums4u.ui.albums.adapter.AlbumsAdapter;
import com.albertmiro.albums4u.ui.common.BaseFragment;
import com.albertmiro.albums4u.viewmodel.LookupViewModel;
import com.albertmiro.albums4u.viewmodel.data.Album;
import com.albertmiro.albums4u.viewmodel.data.ArtistAndAlbums;

import java.util.Collections;
import java.util.List;

public class AlbumsListFragment extends BaseFragment implements AlbumListListener {

    private static final int GRID_SPAN_COUNT = 2;

    private LookupViewModel albumsViewModel;

    private AlbumsAdapter albumsAdapter;
    private RecyclerView albumsList;
    private SwipeRefreshLayout swipeToRefresh;
    private RelativeLayout progressBarContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_albums_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        albumsViewModel = LookupViewModel.getInstance(getActivity());

        initViews();

        initSwipeRefresh();

        initAlbumsList();

        initObservers();

        albumsViewModel.loadAlbumsForArtist();
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
        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                albumsViewModel.loadAlbumsForArtist();
            }
        });
    }

    private void initObservers() {
        albumsViewModel.isDataLoading()
                .observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean hasChanged) {
                        if (hasChanged != null) {
                            if (!swipeToRefresh.isRefreshing()) {
                                if (hasChanged) {
                                    progressBarContainer.setVisibility(View.VISIBLE);
                                } else {
                                    progressBarContainer.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                });

        albumsViewModel.getArtistAndAlbums().observe(this, new Observer<ArtistAndAlbums>() {
            @Override
            public void onChanged(@Nullable ArtistAndAlbums artistAndAlbums) {
                showAlbums(artistAndAlbums != null ? artistAndAlbums.getAlbumList() : Collections.<Album>emptyList());
            }
        });

        albumsViewModel.isNetworkError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean hasChanged) {
                if (hasChanged != null && hasChanged) {
                    mainActivity.showMessage(getString(R.string.lost_connection));
                    hideRefreshingIcon();
                }
            }
        });

        albumsViewModel.isUnknownError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean hasChanged) {
                if (hasChanged != null && hasChanged) {
                    mainActivity.showMessage(getString(R.string.unexpected_error));
                    hideRefreshingIcon();
                }
            }
        });
    }

    private void showAlbums(List<Album> albums) {
        if (swipeToRefresh.isRefreshing()) {
            albumsAdapter.clearItems();
            swipeToRefresh.setRefreshing(false);
        }

        if (albums.isEmpty()) {
            mainActivity.showMessage(getString(R.string.no_albums));
        } else {
            albumsAdapter.setItems(albums);
        }
    }

    private void hideRefreshingIcon() {
        if (swipeToRefresh.isRefreshing()) {
            swipeToRefresh.setRefreshing(false);
        }
    }

    @Override
    public void onAlbumClicked(int albumId) {
        mainActivity.onAlbumClicked(albumId);
    }
}
