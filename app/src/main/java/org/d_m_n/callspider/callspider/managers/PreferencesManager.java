package org.d_m_n.callspider.callspider.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.securepreferences.SecurePreferences;


/**
 * Created by d1m11n on 11/15/16.
 */

public final class PreferencesManager {

    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;

    private static final String FIRST_LAUNCHED = "already_launched";
    private static final String CONTACT_COPIED = "contacts_copied";

    private static final String CONTAINS_SELECTED_CONTACTS = "was_selected";


    private static void initPrefInstanceIfNeeded(Context context){
        if(prefs == null){
            prefs = new SecurePreferences(context);
            editor = prefs.edit();
        }
    }


    public static void setFirstLaunched(@NonNull Context context){
        initPrefInstanceIfNeeded(context);
        editor.putBoolean(FIRST_LAUNCHED, true).apply();
    }

    public static boolean isFirstLaunched(@NonNull Context context){
       initPrefInstanceIfNeeded(context);
        return prefs.getBoolean(FIRST_LAUNCHED, false);
    }

    public static void setContactsCopied(@NonNull Context context){
        initPrefInstanceIfNeeded(context);
        editor.putBoolean(CONTACT_COPIED, true).apply();
    }

    public static void resetContactsCopied(@NonNull Context context){
        initPrefInstanceIfNeeded(context);
        editor.putBoolean(CONTACT_COPIED, false).apply();
    }

    public static boolean isContactsCopied(@NonNull Context context){
        initPrefInstanceIfNeeded(context);
        return prefs.getBoolean(CONTACT_COPIED, false);
    }

    public static void setContactsWereSelected(@NonNull Context context){
        initPrefInstanceIfNeeded(context);
        editor.putBoolean(CONTAINS_SELECTED_CONTACTS, true).apply();
    }

    public static boolean isContactsWereSelected(@NonNull Context context){
        initPrefInstanceIfNeeded(context);
        return prefs.getBoolean(CONTAINS_SELECTED_CONTACTS,false);
    }



}
