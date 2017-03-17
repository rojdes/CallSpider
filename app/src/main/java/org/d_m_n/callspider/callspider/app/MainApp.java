package org.d_m_n.callspider.callspider.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import org.d_m_n.callspider.callspider.BuildConfig;
import org.d_m_n.callspider.callspider.managers.PreferencesManager;
import org.d_m_n.callspider.callspider.services.SystemEventsService;

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
        ButterKnife.setDebug(BuildConfig.DEBUG);
        startService(new Intent(this, SystemEventsService.class));
        sContext = this;
    }


}
