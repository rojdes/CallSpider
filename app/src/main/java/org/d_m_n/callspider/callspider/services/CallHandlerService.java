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
import org.d_m_n.callspider.callspider.managers.ContactsManager;
import org.d_m_n.callspider.callspider.managers.UserNotifyManager;
import org.d_m_n.callspider.callspider.model.CommonContact;
import org.d_m_n.callspider.callspider.ui.MainActivity;

import java.util.Locale;

/**
 * Created by d1m11n on 11/11/16.
 */

public class CallHandlerService extends IntentService {


    private static final String TAG = CallHandlerService.class.getSimpleName();

    private LastCallHolder mCallHolder;

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
        String number = intent.getStringExtra(Constants.Extras.NUMBER);
        if (isNeedEndCall(intent, number)) {
            endCall(number);
        }
    }

    private boolean isNeedEndCall(Intent intent, String number){
        CommonContact cc= ContactsManager.with(this).getContactBy(number);
        Logger.e(TAG, "isNeedEndCall = " + String.valueOf(cc) + " " + " number = " +number);
        if(cc == null){
            return false;
        }
        switch (cc.direction){
            case FULL:
                return true;
            case INCOMING:
                mCallHolder = new LastCallHolder(true, number);
                return intent.getAction().equals(Constants.Actions.CALL_RINGING);
            case OUTGOING: //TODO CHECK with incoming call and then answering
                if (mCallHolder != null && mCallHolder.startWithRinging && mCallHolder.number.equalsIgnoreCase(number)){
                    mCallHolder = null;
                    return  false;
                }
                mCallHolder = null;
                return intent.getAction().equals(Constants.Actions.CALL_OFFHOOK);
            case NOT_SET:
            default:
                return false;
        }
    }

    private void endCall(@NonNull final String number) {
        Logger.e(TAG,"handle endCall " + number);
        try {
            CallController.endCall(new CallController.CallEndCallback() {
                @Override
                public void onEndCall(boolean isSuccessfull, Exception e) {
                    if (isSuccessfull){
                        notifyEndCall(number);
                    } else {
                        notifyErrorEndCall(number, e.toString());
                    }

                }
            });

        } catch (Exception e) {
            Logger.e(TAG, "handle endCall error = " + e.toString());
        }


    }

    private void notifyErrorEndCall(String number, String error) {
        UserNotifyManager.showToast(CallHandlerService.this, String.format(Locale.ENGLISH, MainApp.getAppContext().getResources().getString(R.string.blocking_call_error), number, error), true);
    }

    private void notifyEndCall(String number) {
        Resources res = MainApp.getAppContext().getResources();
        UserNotifyManager.showNotification(CallHandlerService.this,
                res.getString(R.string.app_name),
                String.format(Locale.ENGLISH, res.getString(R.string.call_was_blocked), number),
                MainActivity.class);
    }

    private class LastCallHolder{

        private boolean startWithRinging;

        private String number;

        private LastCallHolder(boolean startWithRinging, String number){
            this.startWithRinging = startWithRinging;
            this.number = number;
        }


    }
}
