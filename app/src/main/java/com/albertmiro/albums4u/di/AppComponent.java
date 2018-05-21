package com.albertmiro.albums4u.di;

import com.albertmiro.albums4u.AlbumsApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        MainActivityModule.class
})
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(AlbumsApp app);

        AppComponent build();
    }

    void inject(AlbumsApp app);
}