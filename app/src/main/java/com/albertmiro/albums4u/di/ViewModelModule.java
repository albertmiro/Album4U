
package com.albertmiro.albums4u.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.albertmiro.albums4u.ui.viewmodel.AppViewModelFactory;
import com.albertmiro.albums4u.ui.viewmodel.LookupViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LookupViewModel.class)
    ViewModel bindLookupViewModel(LookupViewModel lookupViewModel);

    @Binds
    ViewModelProvider.Factory bindViewModelFactory(AppViewModelFactory factory);
}