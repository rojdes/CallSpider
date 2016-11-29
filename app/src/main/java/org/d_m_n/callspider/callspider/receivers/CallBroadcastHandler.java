package org.d_m_n.callspider.callspider.receivers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;

import org.d_m_n.callspider.callspider.app.Logger;

/**
 * Created by d1m11n on 11/11/16.
 */

class CallBroadcastHandler {

    private static final String TAG = CallBroadcastHandler.class.getSimpleName();

    private static final String STATE_RINGING = TelephonyManager.EXTRA_STATE_RINGING;

    private static final String STATE_IDLE = TelephonyManager.EXTRA_STATE_IDLE;

    private static final String STATE_OFFHOOK = TelephonyManager.EXTRA_STATE_OFFHOOK;

    private static final String WHAT_CALL_STATE = TelephonyManager.EXTRA_STATE;

    private static final String WHAT_INCOMING_NUMBER = TelephonyManager.EXTRA_INCOMING_NUMBER;


    static final String UNKNOWN_NUMBER = "-1";


    static boolean isRinging(@NonNull Intent intent) {
        if (intent.hasExtra(WHAT_CALL_STATE)) {
            return intent.getStringExtra(WHAT_CALL_STATE).equals(
                    STATE_RINGING);
        }
        return  false;
    }

    static boolean isOffhook(@NonNull Intent intent) {
        if (intent.hasExtra(WHAT_CALL_STATE)) {
            return intent.getStringExtra(WHAT_CALL_STATE).equals(
                    STATE_OFFHOOK);
        }
        return false;
    }

    static boolean isIdle(@NonNull Intent intent) {
        if (intent.hasExtra(WHAT_CALL_STATE)) {
        return intent.getStringExtra(WHAT_CALL_STATE).equals(
                STATE_IDLE);
        }
        return false;
    }

    static String getCallNumber(@NonNull Intent intent){
        if( !intent.hasExtra(WHAT_INCOMING_NUMBER)) {
            return UNKNOWN_NUMBER;
        }
        String number = intent.getStringExtra(WHAT_INCOMING_NUMBER);
        if ( number.equals("-1")){
            return UNKNOWN_NUMBER;
        }
        return number;
    }

    static void logCall(@NonNull Intent intent){
        Logger.e(TAG, "action is : " + intent.getAction());
        StringBuilder b= new StringBuilder();
        if (isOffhook(intent)){
            b.append("offhook ");
        }
        if(isRinging(intent)){
            b.append("ringing ");
        }
        if(isIdle(intent)){
            b.append("idle ");
        }

        b.append(getCallNumber(intent));
        Logger.w(TAG,"CALL : "+ b.toString());
    }
}
