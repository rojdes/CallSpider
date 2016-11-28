package org.d_m_n.callspider.callspider.app;

import android.app.Application;
import android.content.Context;

import org.d_m_n.callspider.callspider.managers.PreferencesManager;

import butterknife.ButterKnife;

/**
 * Created by d1m11n on 11/11/16.
 */

public class MainApp extends Application {

    private static Context sContext;


    public static Context getAppContext(){
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ButterKnife.setDebug(true);
        PreferencesManager.resetContactsCopied(this);
        sContext = this;
    }


}
