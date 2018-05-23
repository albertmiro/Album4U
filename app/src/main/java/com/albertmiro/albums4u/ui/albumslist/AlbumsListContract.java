package com.albertmiro.albums4u.ui.albumslist;

import com.albertmiro.albums4u.domain.Album;

import java.util.List;

public interface AlbumsListContract {

    void showAlbums(List<Album> albums);
    void showOrHideProgressBar(boolean show);
    void showMessage(String message);
    void hideRefreshingIcon();
    void showUnknownError();
    void showNetworkError();

}
