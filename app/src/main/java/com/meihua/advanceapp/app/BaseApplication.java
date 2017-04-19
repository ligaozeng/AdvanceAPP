package com.meihua.advanceapp.app;

import android.app.Application;

import com.meihua.advanceapp.ui.BaseActivity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by : lgz
 * Time : 2017/4/18.
 * Desc :
 *
 */

public class BaseApplication extends Application {

    public static BaseApplication mContext;
    private Set<BaseActivity> allActivities;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;

    }

    public static BaseApplication getInstance(){
        return mContext;
    }

    //注册activity
    public void registerActivity(BaseActivity act) {
        if (allActivities == null) {
            allActivities = new HashSet<BaseActivity>();
        }
        allActivities.add(act);
    }

    //注销activity
    public void unregisterActivity(BaseActivity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    //退出应用程序
    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (BaseActivity act : allActivities) {
                    if (act != null && !act.isFinishing())
                        act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    // realm 相关注册
//    private void initRealm() {
//        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
//                .name(RealmHelper.DB_NAME)
//                .schemaVersion(1)
//                .rxFactory(new RealmObservableFactory())
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        Realm.setDefaultConfiguration(realmConfiguration);
//    }

}
