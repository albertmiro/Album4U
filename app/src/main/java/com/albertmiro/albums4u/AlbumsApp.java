package com.albertmiro.albums4u;

import android.app.Application;

import com.albertmiro.albums4u.repository.LookupRepository;
import com.albertmiro.albums4u.webservice.RestAPI;

public class AlbumsApp extends Application {

    private static LookupRepository lookupRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        RestAPI api = new RestAPI();
        api.init();
        lookupRepository = new LookupRepository(api);
    }

    public static LookupRepository getLookupRepository() {
        return lookupRepository;
    }

}
