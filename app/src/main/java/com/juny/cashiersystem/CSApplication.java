package com.juny.cashiersystem;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.juny.cashiersystem.util.Env;

import io.realm.Realm;

//import com.facebook.stetho.Stetho;

/**
 * application
 * Created by Juny on 2018/4/3.
 */

public class CSApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 设置程序环境（必须先设置）
        final Context appContext = getApplicationContext();
        Env.setContext(appContext);
        Realm.init(this);
        Stetho.initializeWithDefaults(this);  //Stetho
    }
}
