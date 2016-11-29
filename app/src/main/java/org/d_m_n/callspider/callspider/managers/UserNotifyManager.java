package org.d_m_n.callspider.callspider.managers;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import org.d_m_n.callspider.callspider.R;

/**
 * Created by d1m11n on 11/27/16.
 */
public class UserNotifyManager {


    private static int notificationId =10500;

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

    public static void showNotification(Context context, String title, String content, Class<? extends Activity> activityClass){
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(content);
        mBuilder.setAutoCancel(true);
        if (activityClass != null) {
            mBuilder.setContentIntent(getPendingIntentForNotification(context, activityClass));
        }
        mNotificationManager.notify(notificationId, mBuilder.build());
    }

    private static PendingIntent getPendingIntentForNotification(Context context, Class<? extends Activity> activityClass) {
        Intent notifyIntent = new Intent(context, activityClass);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(
                context,
                0,
                notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }
}
