package com.albertmiro.albums4u.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.albertmiro.albums4u.repository.LookupRepository;
import com.albertmiro.albums4u.viewmodel.data.Album;
import com.albertmiro.albums4u.viewmodel.data.ArtistAndAlbums;

import java.net.UnknownHostException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LookupViewModel extends ViewModel {

    private LookupRepository lookupRepository;

    private MutableLiveData<Boolean> isDataLoading;
    private MutableLiveData<ArtistAndAlbums> artistAndAlbums;
    private MutableLiveData<Album> currentAlbum;
    private MutableLiveData<Boolean> isNetworkError;
    private MutableLiveData<Boolean> isUnknownError;

    @Inject
    LookupViewModel(@NonNull LookupRepository lookupRepository) {

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
        isDataLoading.postValue(true);

        lookupRepository.getJackJohnsonAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArtistAndAlbums>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ArtistAndAlbums albumsAndArtist) {
                        artistAndAlbums.postValue(albumsAndArtist);
                        isDataLoading.postValue(false);
                        isNetworkError.postValue(false);
                        isUnknownError.postValue(false);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        isDataLoading.postValue(false);
                        if (t instanceof UnknownHostException) {
                            isNetworkError.postValue(true);
                        } else {
                            isUnknownError.postValue(true);
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                });
    }

    public void loadAlbumSongs(final int albumId) {
        isDataLoading.postValue(true);
        currentAlbum.postValue(null);

        lookupRepository.getAlbumSongs(albumId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArtistAndAlbums>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ArtistAndAlbums albumsAndArtist) {
                        Album album = albumsAndArtist.getAlbum(albumId);
                        isDataLoading.postValue(false);
                        currentAlbum.postValue(album);
                        isNetworkError.postValue(false);
                        isUnknownError.postValue(false);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        isDataLoading.postValue(false);
                        if (t instanceof UnknownHostException) {
                            isNetworkError.postValue(true);
                        } else {
                            isUnknownError.postValue(true);
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                });
    }

}
