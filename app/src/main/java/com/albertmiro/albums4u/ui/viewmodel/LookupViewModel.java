package com.albertmiro.albums4u.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.albertmiro.albums4u.domain.Album;
import com.albertmiro.albums4u.domain.ArtistAndAlbums;
import com.albertmiro.albums4u.repository.LookupRepositoryImpl;

import java.net.UnknownHostException;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LookupViewModel extends ViewModel {

    private LookupRepositoryImpl lookupRepository;

    private MutableLiveData<Boolean> isDataLoading;
    private MutableLiveData<ArtistAndAlbums> artistAndAlbums;
    private MutableLiveData<Album> currentAlbum;
    private MutableLiveData<Boolean> isNetworkError;
    private MutableLiveData<Boolean> isUnknownError;

    @Inject
    LookupViewModel(@NonNull LookupRepositoryImpl lookupRepository) {
        this.lookupRepository = lookupRepository;

        this.isDataLoading = new MutableLiveData<>();
        this.artistAndAlbums = new MutableLiveData<>();
        this.currentAlbum = new MutableLiveData<>();
        this.isNetworkError = new MutableLiveData<>();
        this.isUnknownError = new MutableLiveData<>();
    }

    public LiveData<Boolean> isDataLoading() {
        return isDataLoading;
    }

    public LiveData<ArtistAndAlbums> getArtistAndAlbums() {
        return artistAndAlbums;
    }

    public LiveData<Album> getAlbum() {
        return currentAlbum;
    }

    public LiveData<Boolean> isNetworkError() {
        return isNetworkError;
    }

    public LiveData<Boolean> isUnknownError() {
        return isUnknownError;
    }

    public void loadAlbumsForArtist() {
        lookupRepository.getJackJohnsonAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> isDataLoading.setValue(true))
                .subscribe(new SingleObserver<ArtistAndAlbums>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(ArtistAndAlbums albumsAndArtists) {
                        artistAndAlbums.postValue(albumsAndArtists);
                        isDataLoading.postValue(false);
                        isNetworkError.postValue(false);
                        isUnknownError.postValue(false);
                    }

                    @Override
                    public void onError(Throwable t) {
                        isDataLoading.postValue(false);
                        if (t instanceof UnknownHostException) {
                            isNetworkError.postValue(true);
                        } else {
                            isUnknownError.postValue(true);
                        }
                    }
                });
    }

    public void loadAlbumSongs(final int albumId) {
        currentAlbum.postValue(null);

        lookupRepository.getAlbumSongs(albumId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> isDataLoading.setValue(true))
                .subscribe(new SingleObserver<ArtistAndAlbums>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(ArtistAndAlbums artistAndAlbums) {
                        Album album = artistAndAlbums.getAlbum(albumId);
                        isDataLoading.postValue(false);
                        currentAlbum.postValue(album);
                        isNetworkError.postValue(false);
                        isUnknownError.postValue(false);
                    }

                    @Override
                    public void onError(Throwable t) {
                        isDataLoading.postValue(false);
                        if (t instanceof UnknownHostException) {
                            isNetworkError.postValue(true);
                        } else {
                            isUnknownError.postValue(true);
                        }
                    }
                });
    }

}
