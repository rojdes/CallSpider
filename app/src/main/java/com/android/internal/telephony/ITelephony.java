package com.android.internal.telephony;

/**
 * Created by d1m11n on 11/12/16.
 */

public interface ITelephony {

    boolean endCall();

    void answerRingingCall();

    void silenceRinger();

}