package org.d_m_n.callspider.callspider.tools;

import android.app.Activity;
import android.app.Fragment;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by d1m11n on 12/1/16.
 */

public class FragmentTools {

    public static Fragment getFragment(Activity activity, ViewPager pager, FragmentPagerAdapter adapter, int position) {
        return activity.getFragmentManager().findFragmentByTag("android:switcher:" + pager.getId() + ":" + adapter.getItemId(position));
    }
}
