package com.ekoapp.simplechat;

import android.support.multidex.MultiDexApplication;

import com.ekoapp.ekosdk.EkoClient;

public class SimpleChatApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        EkoClient.setup("b3bee95c3ed2f3674e35894a57091688d05fdee2b8336b2b");
    }
}
