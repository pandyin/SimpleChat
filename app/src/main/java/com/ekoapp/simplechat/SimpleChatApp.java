package com.ekoapp.simplechat;

import android.support.multidex.MultiDexApplication;

import com.ekoapp.ekosdk.EkoClient;

public class SimpleChatApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        EkoClient.setup(ApiKeySharedPref.get());
    }
}
