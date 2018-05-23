package com.albertmiro.albums4u.di;

import com.albertmiro.albums4u.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = ViewModelModule.class)
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}
