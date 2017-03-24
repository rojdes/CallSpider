package org.d_m_n.callspider.callspider.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.securepreferences.SecurePreferences;

import org.d_m_n.callspider.callspider.app.MainApp;


/**
 * Created by d1m11n on 11/15/16.
 */

public final class PreferencesManager {

    private static final String LAST_INCOMING_NUMBER = "last_incoming_number";
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;

    private static final String FIRST_LAUNCHED = "already_launched";
    private static final String CONTACT_COPIED = "contacts_copied";

    private static final String CONTAINS_SELECTED_CONTACTS = "was_selected";


    private static final String PASSWORD_SET = "ps";


    private static void initPrefInstanceIfNeeded(Context context){
        if(prefs == null){
            prefs = new SecurePreferences(context);
            editor = prefs.edit();
        }
    }

    public static void setFirstLaunched(boolean state){
        initPrefInstanceIfNeeded(MainApp.getAppContext());
        editor.putBoolean(FIRST_LAUNCHED, state).apply();
    }

    public static boolean isFirstLaunched(){
       initPrefInstanceIfNeeded(MainApp.getAppContext());
        return prefs.getBoolean(FIRST_LAUNCHED, false);
    }

    public static void setContactsCopied(boolean state){
        initPrefInstanceIfNeeded(MainApp.getAppContext());
        editor.putBoolean(CONTACT_COPIED, state).apply();
    }

    public static boolean isContactsCopied(){
        initPrefInstanceIfNeeded(MainApp.getAppContext());
        return prefs.getBoolean(CONTACT_COPIED, false);
    }

    public static void setContactsWereSelected(boolean state){
        initPrefInstanceIfNeeded(MainApp.getAppContext());
        editor.putBoolean(CONTAINS_SELECTED_CONTACTS, state).apply();
    }

    public static boolean isContactsWereSelected(){
        initPrefInstanceIfNeeded(MainApp.getAppContext());
        return prefs.getBoolean(CONTAINS_SELECTED_CONTACTS,false);
    }


    public static boolean isPasswordSet() {
        initPrefInstanceIfNeeded(MainApp.getAppContext());
        return prefs.getBoolean(PASSWORD_SET,false);
    }

    public static void setPassword(boolean state) {
        initPrefInstanceIfNeeded(MainApp.getAppContext());
        editor.putBoolean(PASSWORD_SET,state).apply();
    }

    public static void setLastNumber(String number) {
        initPrefInstanceIfNeeded(MainApp.getAppContext());
        editor.putString(LAST_INCOMING_NUMBER ,number).apply();
    }

    public static String getLastNumber() {
        initPrefInstanceIfNeeded(MainApp.getAppContext());
        return prefs.getString(LAST_INCOMING_NUMBER ,"");
    }



}
