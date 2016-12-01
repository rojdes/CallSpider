package org.d_m_n.callspider.callspider.ui.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import org.d_m_n.callspider.callspider.ui.fragments.BaseFragment;
import org.d_m_n.callspider.callspider.ui.fragments.CategoriesFragment;
import org.d_m_n.callspider.callspider.ui.fragments.ContactListFragment;



/**
 * Created by d1m11n on 12/1/16.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {


    private static final int COUNT = 2;

    //http://stackoverflow.com/questions/9727173/support-fragmentpageradapter-holds-reference-to-old-fragments/9745935#9745935
    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public BaseFragment getItem(int position) {
        switch (position){
            case 0:
                return ContactListFragment.newInstance();
            default:
                return CategoriesFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
