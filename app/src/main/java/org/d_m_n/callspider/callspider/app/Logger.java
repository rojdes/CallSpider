package org.d_m_n.callspider.callspider.app;

import android.util.Log;

import com.google.common.base.Strings;

import org.d_m_n.callspider.callspider.BuildConfig;

/**
 * Created by d1m11n on 11/12/16.
 */

public final class Logger {

    public static final void e(String TAG, String comment){
        if(BuildConfig.DEBUG){
            Log.e(TAG, Strings.isNullOrEmpty(comment) ? "null" : comment);
        }
    }

    public static final void e(String TAG, String comment, Exception e){
        if(BuildConfig.DEBUG){
            Log.e(TAG, (Strings.isNullOrEmpty(comment) ? "null" : comment)  + " e = "  + e.toString());
        }
    }

    public static final void w(String TAG, String comment){
        if(BuildConfig.DEBUG){
            Log.w(TAG, Strings.isNullOrEmpty(comment) ? "null" : comment);
        }
    }

    public static final void d(String TAG, String comment){
        if(BuildConfig.DEBUG){
            Log.d(TAG, Strings.isNullOrEmpty(comment) ? "null" : comment);
        }
    }

}
