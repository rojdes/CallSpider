package org.d_m_n.callspider.callspider.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;

import org.d_m_n.callspider.callspider.app.Constants;
import org.d_m_n.callspider.callspider.app.Logger;
import org.d_m_n.callspider.callspider.controllers.CallController;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by d1m11n on 11/11/16.
 */

public class CallHandlerService extends IntentService {


    private static final String TAG = CallHandlerService.class.getSimpleName();

    public CallHandlerService(String name) {
        super(name);
    }

    public CallHandlerService(){
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null){
            return;
        }
        if (intent.getAction().equals(Constants.Actions.CALL_OFFHOOK)){
            handleOffhook2(intent);
//            handleOffhook(intent);
            return;
        }
        if (intent.getAction().equals(Constants.Actions.CALL_RINGING)){
            handleOffhook2(intent);
            return;
        }
    }

//    private void handleOffhook(@NonNull Intent intent) {
//        Logger.e(TAG,"handle offhook");
//        try {
//            CallController.endCall(this);
//        } catch (ClassNotFoundException e) {
//            Logger.e(TAG, "classnotfound " + e.toString());
//        } catch (NoSuchMethodException e) {
//            Logger.e(TAG, "nosuchmethod " + e.toString());
//        } catch (InvocationTargetException e) {
//            Logger.e(TAG, "invocationtarget " + e.toString());
//        } catch (IllegalAccessException e) {
//            Logger.e(TAG, "illegalaccess " + e.toString());
//        }
//
//    }

    private void handleOffhook2(@NonNull Intent intent) {
        Logger.e(TAG,"handle offhook2");
        try {
            CallController.endCall(this, 3000L);
        } catch (Exception e) {
            Logger.e(TAG, "handle offhook 2 error = " + e.toString());
        }


    }

//    private void handleRinging(@NonNull Intent intent) {
//        Logger.e(TAG,"handle ringing");
//        try {
//            CallController.endCall(this, 2000L);
//        } catch (ClassNotFoundException e) {
//            Logger.e(TAG, "classnotfound " + e.toString());
//        } catch (NoSuchMethodException e) {
//            Logger.e(TAG, "nosuchmethod " + e.toString());
//        } catch (InvocationTargetException e) {
//            Logger.e(TAG, "invocationtarget " + e.toString());
//        } catch (IllegalAccessException e) {
//            Logger.e(TAG, "illegalaccess " + e.toString());
//        }
//    }
}
