package com.albertmiro.albums4u.ui.albums;

import com.albertmiro.albums4u.viewmodel.data.Album;

import java.util.List;

public interface AlbumsListContract {

    void showAlbums(List<Album> albums);
    void showOrHideProgressBar(boolean show);
    void showMessage(String message);
    void hideRefreshingIcon();
    void showUnknownError();
    void showNetworkError();

}
