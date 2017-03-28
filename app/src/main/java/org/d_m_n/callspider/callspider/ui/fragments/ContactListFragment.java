package org.d_m_n.callspider.callspider.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.app.Constants;
import org.d_m_n.callspider.callspider.managers.ContactsManager;
import org.d_m_n.callspider.callspider.managers.PermissionManager;
import org.d_m_n.callspider.callspider.ui.adapters.ContactListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

/**
 * Created by d1m11n on 11/11/16.
 */

public class ContactListFragment extends BaseFragment{


    @BindView(R.id.rv_contact_list)
    protected RecyclerView mrvContactList;

    @BindView(R.id.pb_contact_list)
    protected ProgressBar mpbProgress;

    @BindView(R.id.et_contact_list)
    protected EditText metSearch;


    @BindView(R.id.fab)
    protected FloatingActionButton mFab;


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
        setContactList();
    }

    private void setContactList() {
        mrvContactList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mrvContactList.setLayoutManager(mLayoutManager);
        if (Constants.DebugMode.FAKE_CONTACTS){
            mContactListAdapter = new ContactListAdapter(ContactsManager.with(getActivity()).getFakeContacts());
            mrvContactList.setAdapter(mContactListAdapter);
        } else if (PermissionManager.isContactsPermissionsGranted(getActivity())) {
            mContactListAdapter = new ContactListAdapter(ContactsManager.with(getActivity()).getContacts());
            mrvContactList.setAdapter(mContactListAdapter);
        }
    }

    @OnTextChanged(R.id.et_contact_list)
    public void onSearch(CharSequence text){
        mContactListAdapter.filterWith(text);
    }

    @Override
    public void resetData() {
        super.resetData();
        ContactsManager.with(getActivity()).updateContacts();
        setContactList();
    }
}
