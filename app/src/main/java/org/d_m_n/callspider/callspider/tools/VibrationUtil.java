package org.d_m_n.callspider.callspider.tools;

import android.content.Context;
import android.os.Vibrator;

import org.d_m_n.callspider.callspider.app.MainApp;

/**
 * Created by d1m11n on 11/11/16.
 */

public final class VibrationUtil {

    public static void vibrateFor(long timeMs){
        Vibrator v = (Vibrator) MainApp.getAppContext().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(timeMs);
    }
}
