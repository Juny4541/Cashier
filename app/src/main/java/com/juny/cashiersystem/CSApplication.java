package com.juny.cashiersystem;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.juny.cashiersystem.util.Env;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * application
 * Created by Juny on 2018/4/3.
 */

public class CSApplication extends Application {
    private static RealmConfiguration config;
    private static String key = "chenrunfang321";

    @Override
    public void onCreate() {
        super.onCreate();
        final Context appContext = getApplicationContext();
        Env.setContext(appContext);
        initStetho();
        initRealm();
    }

    public static RealmConfiguration getRealmConfiguration() {
        return config;
    }

    /**
     * <br> Description: Stetho配置
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/11 11:07
     */
    private void initStetho() {
        //Stetho
        Stetho.initializeWithDefaults(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }

    /**
     * <br> Description: Realm 配置
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/11 11:07
     */
    private void initRealm() {
        // Realm
        Realm.init(this);
        config = new RealmConfiguration.Builder()
                .name("juny.realm")//指定数据库的名称。如不指定默认名为default。
                .schemaVersion(0) // 版本号
                .deleteRealmIfMigrationNeeded()//声明版本冲突时自动删除原数据库，开发时候打开
//                .inMemory()// 声明数据库只在内存中持久化
//                .encryptionKey(key.getBytes()) // 密钥
                .build();
    }
}
