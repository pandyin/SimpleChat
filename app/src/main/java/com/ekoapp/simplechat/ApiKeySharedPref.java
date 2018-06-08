package com.ekoapp.simplechat;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ekoapp.ekosdk.internal.util.AppContext;
import com.f2prateek.rx.preferences2.RxSharedPreferences;


public class ApiKeySharedPref {

    private static final String API_KEY = "API_KEY";

    private static RxSharedPreferences rxSharedPref = RxSharedPreferences
            .create(AppContext.get().getSharedPreferences(SimpleChatApp.class.getName(), Context.MODE_PRIVATE));

    public static void set(@NonNull String apiKey) {
        rxSharedPref.getString(API_KEY).set(apiKey);
    }

    @NonNull
    public static String get() {
        return rxSharedPref.getString(API_KEY, "b3bee95c3ed2f3674e35894a57091688d05fdee2b8336b2b").get();
    }
}
