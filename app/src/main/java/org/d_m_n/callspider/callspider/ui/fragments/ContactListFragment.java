package org.d_m_n.callspider.callspider.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.managers.ContactsManager;
import org.d_m_n.callspider.callspider.managers.PreferencesManager;
import org.d_m_n.callspider.callspider.tools.ContactTools;
import org.d_m_n.callspider.callspider.ui.adapters.ContactListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by d1m11n on 11/11/16.
 */

public class ContactListFragment extends BaseFragment{


    @BindView(R.id.rv_contact_list)
    protected RecyclerView mrvContactList;


    private LinearLayoutManager mLayoutManager;
    private ContactListAdapter mContactListAdapter;


    public static ContactListFragment newInstance(){
        ContactListFragment f= new ContactListFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.frg_contact_list, container,false);

        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boolean isFirstStart = true;
        setContactList(PreferencesManager.isFirstLaunched(getActivity()));
    }

    private void setContactList(boolean isFirstStart) {

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mrvContactList.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(getActivity());
        mrvContactList.setLayoutManager(mLayoutManager);
        mContactListAdapter = new ContactListAdapter(ContactsManager.with(getActivity()).getContacts());
        mrvContactList.setAdapter(mContactListAdapter);


    }

    @Override
    public void updateData() {
        super.updateData();
        ContactsManager.with(getActivity()).updateContacts();
    }
}
