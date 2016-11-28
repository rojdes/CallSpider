package org.d_m_n.callspider.callspider.tools;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by d1m11n on 11/27/16.
 */

public class AndroidTools {

    public static void openApplicationSettings(Activity activity, int requestCode) {
        Intent appSettingsIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(appSettingsIntent, requestCode);
    }


}
