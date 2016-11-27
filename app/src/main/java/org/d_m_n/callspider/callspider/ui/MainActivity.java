package org.d_m_n.callspider.callspider.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.ui.fragments.BaseFragment;
import org.d_m_n.callspider.callspider.ui.fragments.ContactListFragment;

public class MainActivity extends AppCompatActivity {

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
}
