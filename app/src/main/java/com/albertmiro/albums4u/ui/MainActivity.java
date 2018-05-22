package com.albertmiro.albums4u.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.albertmiro.albums4u.R;
import com.albertmiro.albums4u.ui.common.BaseActivity;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity implements HasSupportFragmentInjector {

    private Toolbar toolbar;
    private ConstraintLayout mainRootView;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        setUpToolbar();

        Navigation.loadAlbumsListFragment(this);
    }

    private void initViews() {
        mainRootView = findViewById(R.id.mainRootView);
        toolbar = findViewById(R.id.toolbar);
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
    }

    public void showMessage(String message) {
        Snackbar.make(mainRootView,message,Snackbar.LENGTH_LONG).show();
    }

    public void onAlbumClicked(int albumId) {
        Navigation.loadAlbumDetail(this, albumId);
    }


    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

}
