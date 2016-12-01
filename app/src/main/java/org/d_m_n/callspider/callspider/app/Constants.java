package org.d_m_n.callspider.callspider.app;

import android.telephony.TelephonyManager;

import org.d_m_n.callspider.callspider.BuildConfig;

/**
 * Created by d1m11n on 11/11/16.
 */

public final class Constants {

    public static final String APP_ID = BuildConfig.APPLICATION_ID;

    public interface Actions {

        String CALL_OFFHOOK = APP_ID + ".call_offhook";

        String CALL_IDLE = APP_ID + ".call_idle";

        String CALL_RINGING = APP_ID + ".call_ringing";

        String UPDATE_CONTACTS_LIST = APP_ID + ".update_contacts";
    }

    public interface Extras {

        String NUMBER = APP_ID + ".call_number";

    }
}
