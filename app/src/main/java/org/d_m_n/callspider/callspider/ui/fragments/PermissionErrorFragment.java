package org.d_m_n.callspider.callspider.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d_m_n.callspider.callspider.R;

import butterknife.ButterKnife;

/**
 * Created by d1m11n on 11/30/16.
 */
public class PermissionErrorFragment extends BaseFragment{


    public static PermissionErrorFragment newInstance() {
        PermissionErrorFragment f = new PermissionErrorFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_permission_error, container, false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void updateData() {
        super.updateData();
        //TODO update
    }
}
