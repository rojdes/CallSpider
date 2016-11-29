package org.d_m_n.callspider.callspider.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.app.Constants;
import org.d_m_n.callspider.callspider.app.Logger;
import org.d_m_n.callspider.callspider.app.MainApp;
import org.d_m_n.callspider.callspider.controllers.CallController;
import org.d_m_n.callspider.callspider.managers.UserNotifyManager;
import org.d_m_n.callspider.callspider.ui.MainActivity;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

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
        if (intent.getAction().equals(Constants.Actions.CALL_OFFHOOK)){ //New OUTGOING CALL
            handleOffhook2(intent);
            return;
        }
        if (intent.getAction().equals(Constants.Actions.CALL_RINGING)){
            handleOffhook2(intent);
            return;
        }
    }

    private void handleOffhook2(@NonNull final Intent intent) {
        Logger.e(TAG,"handle offhook2");
        try {
            CallController.endCall(3000L, new CallController.CallEndCallback() {
                @Override
                public void onEndCall(boolean isSuccessfull) {
                    Resources res = MainApp.getAppContext().getResources();
                    String number = intent.getStringExtra(Constants.Extras.NUMBER);
                    UserNotifyManager.showNotification(CallHandlerService.this,
                            res.getString(R.string.app_name),
                            String.format(Locale.ENGLISH, res.getString(R.string.call_was_blocked), number),
                            MainActivity.class);
                }
            });

        } catch (Exception e) {
            Logger.e(TAG, "handle offhook 2 error = " + e.toString());
        }


    }
}
