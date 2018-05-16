package com.albertmiro.albums4u.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;

import com.albertmiro.albums4u.R;
import com.albertmiro.albums4u.ui.common.BaseActivity;

public class MainActivity extends BaseActivity {

    private Toolbar toolbar;
    private ConstraintLayout mainRootView;

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
}
