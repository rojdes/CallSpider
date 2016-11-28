package org.d_m_n.callspider.callspider.managers;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import org.d_m_n.callspider.callspider.ui.MainActivity;

/**
 * Created by d1m11n on 11/27/16.
 */
public class UserNotifyManager {


    public static void showToast(Activity activity, int stringId, boolean isLong) {
        Toast toast = Toast.makeText(activity, stringId, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showSnackbar(View parent, int messageStringId, int actionStringId, boolean isLong, View.OnClickListener listener){
        Snackbar.make(parent, messageStringId, isLong ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT)
                .setAction(actionStringId, listener)
                .setActionTextColor(Color.RED)
                .show();
    }

    public static void showSnackbar(Activity activity, int messageStringId, int actionStringId, boolean isLongTime, View.OnClickListener listener){
        showSnackbar(activity.findViewById(android.R.id.content), messageStringId, actionStringId, isLongTime,listener);

    }
}
