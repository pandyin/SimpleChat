package com.ekoapp.simplechat;

import android.support.multidex.MultiDexApplication;

import com.ekoapp.ekosdk.EkoClient;

public class SimpleChatApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        EkoClient.setup("b3bde15c3989f86045658e4a530a1688d1088be0be3d6f25");
    }
}
