package org.d_m_n.callspider.callspider.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.d_m_n.callspider.callspider.app.MainApp;
import org.d_m_n.callspider.callspider.app.Constants;
import org.d_m_n.callspider.callspider.app.Logger;
import org.d_m_n.callspider.callspider.services.CallHandlerService;

/**
 * Created by d1m11n on 11/11/16.
 */

public class CallReceiver extends BroadcastReceiver {

    private static final String TAG = CallReceiver.class.getSimpleName();


    @Override
    public void onReceive(Context context, Intent intent) {
        CallBroadcastHandler.logCall(intent);
        if (CallBroadcastHandler.isIdle(intent)){
            return;
        }

        //Try to start stopping call repeatedly in service until receiving IDLE with the same number.Then send this event to stop loop

        String action = null;

        if (CallBroadcastHandler.isRinging(intent)) {
            action = Constants.Actions.CALL_RINGING;
            // Ringing state
            // This code will execute when the phone has an incoming call
            Logger.e(TAG, "number = " + CallBroadcastHandler.getIncomingNumber(intent));
        }

        if (CallBroadcastHandler.isOffhook(intent)) {
            // This code will execute when the call is answered or disconnected
            action = Constants.Actions.CALL_OFFHOOK;
        }
        String phoneNumber = getResultData();
        if (phoneNumber == null) {
            // No reformatted number, use the original
            phoneNumber = CallBroadcastHandler.getIncomingNumber(intent);
        }
        setResultData(null);
        notifyServiceAboutCall(action,phoneNumber);
    }

    private void notifyServiceAboutCall(String action, String number){
        if (number.equals(CallBroadcastHandler.UNKNOWN_NUMBER)){
            return;
        }
        Intent intent = new Intent(MainApp.getAppContext(), CallHandlerService.class);
        intent.setAction(action);
        intent.putExtra(Constants.Extras.NUMBER, number);
        MainApp.getAppContext().startService(intent);
    }
}
