package org.d_m_n.callspider.callspider.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.managers.PermissionManager;
import org.d_m_n.callspider.callspider.managers.UserNotifyManager;
import org.d_m_n.callspider.callspider.ui.fragments.BaseFragment;
import org.d_m_n.callspider.callspider.ui.fragments.ContactListFragment;

public class MainActivity extends AppCompatActivity {

    private static final int ACTIVITY_PERMISSION_REQUEST = 1001;
    private BaseFragment mActualFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNewFragment(ContactListFragment.newInstance());
    }

    private void setNewFragment(BaseFragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.content, fragment).commitAllowingStateLoss();
        mActualFragment = fragment;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!PermissionManager.isPhonePermissionGranted(this)){
            PermissionManager.askPhoneStatePermission(this, ACTIVITY_PERMISSION_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == ACTIVITY_PERMISSION_REQUEST &&
          PermissionManager.isPhonePermissionsRequestGranted(grantResults)){
            UserNotifyManager.showToast(this, R.string.verify_permissions_error,true);
            return;
        }

    }
}
