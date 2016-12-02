package org.d_m_n.callspider.callspider.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.app.Constants;
import org.d_m_n.callspider.callspider.app.Logger;
import org.d_m_n.callspider.callspider.managers.PermissionManager;
import org.d_m_n.callspider.callspider.tools.FragmentTools;
import org.d_m_n.callspider.callspider.ui.adapters.MainViewPagerAdapter;
import org.d_m_n.callspider.callspider.ui.fragments.BaseFragment;
import org.d_m_n.callspider.callspider.ui.fragments.ContactListFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int ACTIVITY_PERMISSION_REQUEST = 1001;
    private ViewPager mViewPager;
    MainViewPagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager =(ViewPager)findViewById(R.id.pager);
        if (!PermissionManager.isPhonePermissionGranted(this) || !PermissionManager.isContactsPermissionsGranted(this)){
            PermissionManager.askPhoneStatePermission(this, ACTIVITY_PERMISSION_REQUEST);
        }
        setViewPager();
    }

    private void setViewPager() {
        mPagerAdapter = new MainViewPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Logger.e(TAG, "onPageScrolled = " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Logger.e(TAG, "onPageSelected = " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Logger.e(TAG, "onPageScrollStateChanged = " + state);

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == ACTIVITY_PERMISSION_REQUEST && PermissionManager.handlePermissionRequest(MainActivity.this, permissions, grantResults)){

            //LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(new Intent(Constants.Actions.UPDATE_CONTACTS_LIST));
            BaseFragment fr = (ContactListFragment) FragmentTools.getFragment(MainActivity.this, mViewPager,mPagerAdapter,0);
            if(fr != null){
                fr.updateData();
            }
            return;
        }



    }
}
