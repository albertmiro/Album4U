package com.albertmiro.albums4u.ui.common;

import android.support.v4.app.Fragment;

import com.albertmiro.albums4u.di.Injectable;
import com.albertmiro.albums4u.ui.MainActivity;

public class BaseFragment extends Fragment implements Injectable {

    public MainActivity mainActivity;

    @Override
    public void onStart() {
        super.onStart();
        this.mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.currentFragment = this;
    }

    @Override
    public void onStop() {
        super.onStop();
        mainActivity.currentFragment = null;
    }

}
