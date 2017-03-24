package org.d_m_n.callspider.callspider.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.app.Constants;
import org.d_m_n.callspider.callspider.app.Logger;
import org.d_m_n.callspider.callspider.managers.PermissionManager;
import org.d_m_n.callspider.callspider.tools.FragmentTools;
import org.d_m_n.callspider.callspider.ui.adapters.MainViewPagerAdapter;
import org.d_m_n.callspider.callspider.ui.fragments.BaseFragment;
import org.d_m_n.callspider.callspider.ui.fragments.ContactListFragment;
import org.d_m_n.callspider.callspider.ui.views.ImplToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnPageChange;

import static butterknife.OnPageChange.Callback.PAGE_SCROLLED;
import static butterknife.OnPageChange.Callback.PAGE_SCROLL_STATE_CHANGED;
import static butterknife.OnPageChange.Callback.PAGE_SELECTED;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int ACTIVITY_PERMISSION_REQUEST = 1001;


    MainViewPagerAdapter mPagerAdapter;

    @BindView(R.id.toolbar)
    protected ImplToolbar mToolbar;

    @BindView(R.id.tabLayout)
    protected TabLayout mTabLayout;

    @BindView(R.id.pager)
    protected ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setActionBar(mToolbar);
        if (!PermissionManager.isPhonePermissionGranted(this)){
            PermissionManager.askPhoneStatePermission(this, ACTIVITY_PERMISSION_REQUEST);
        }
        if (!PermissionManager.isContactsPermissionsGranted(this)){
            PermissionManager.askContactsPermissionGranted(this, ACTIVITY_PERMISSION_REQUEST);
        }
        initViews();
    }

    private void initViews(){
        setupViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
        setupTabLayout();
    }

    private void setupViewPager() {
        mPagerAdapter = new MainViewPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
    }

    @OnPageChange(value = R.id.pager, callback = PAGE_SCROLL_STATE_CHANGED)
    protected void onPageScrollStateChanged(int state){
        Logger.e(TAG, "onPageScrollStateChanged = " + state);
    }

    @OnPageChange(value = R.id.pager, callback = PAGE_SELECTED)
    protected void onPageSelected(int position){
        Logger.e(TAG, "onPageSelected = " + position);
    }

    @OnPageChange(value = R.id.pager, callback = PAGE_SCROLLED)
    protected void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
        Logger.e(TAG, "onPageScrolled = " + position);
    }

    private void setupTabLayout(){
        for(int i = 0; i < mPagerAdapter.getCount(); i++) {
            mTabLayout.getTabAt(i).setText(MainViewPagerAdapter.getTabTitleResIdFor(i));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == ACTIVITY_PERMISSION_REQUEST && PermissionManager.handlePermissionRequest(MainActivity.this, permissions, grantResults)){

            //LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(new Intent(Constants.Actions.UPDATE_CONTACTS_LIST));
            initViews();
            return;
        }
    }
}
