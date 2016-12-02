package org.d_m_n.callspider.callspider.tools;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import org.d_m_n.callspider.callspider.app.MainApp;

/**
 * Created by d1m11n on 12/2/16.
 */

public final class UiSizesTools {

    private static int densityDpi = DisplayMetrics.DENSITY_MEDIUM;
    private static float density = 1;

    private static final int ICON_TOOLBAR_SIZEdp =24;

    private static final int ICON_CLICK_SPACE_TOOLBAR_SIZEdp = 48;

    static {

        Resources resources = getContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();

        densityDpi = displayMetrics.densityDpi;
        density = displayMetrics.density;
    }

    private static Context getContext(){
        return MainApp.getAppContext();
    }

    public static int getIconToolbarSizePx(){
        return px(ICON_TOOLBAR_SIZEdp);
    }

    public static int getIconToolbarClickSpaceSize(){
        return px(ICON_CLICK_SPACE_TOOLBAR_SIZEdp);
    }

    /**
     * dp to px
     */
    public static int px(float dp) {
        return (int) Math.ceil(dp * density);
    }

    /**
     * px to dp
     */
    public static float dp(float px) {
        return (int) Math.ceil(px / density);
    }



}
